package org.zalupabyneko.zpolice.commands;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.TextComponent;
import org.zalupabyneko.zpolice.ZPolice;

import java.io.*;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class nnoCommand implements CommandExecutor {
    private HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    private int cooldownTime = 300; // время в секундах
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerName = player.getName();
        Player lastSender = null;
        String lastSenderName = null;
        String pathToSender = "plugins\\ZPolice\\lastSender.zlp";
        File file = new File(pathToSender);
        String username = player.getName();
        int onlinePlayers = 0; // Общий онлайн ментов либо админов

        if (cooldowns.containsKey(playerName)) { // Кд на команду
            long secondsLeft = ((cooldowns.get(playerName) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
            if (secondsLeft > 0 && !player.hasPermission("zpolice.cdbypass")) {
                player.sendMessage(color("&c&lok and? &cТы эту команду юзать не сможешь ещё " + secondsLeft + " секунд. Пиши в лс ментам если игнорят тебя"));
                return true;
            }
        }
        cooldowns.put(playerName, System.currentTimeMillis()); // Добавляем время последнего использования команды

        Location loc = player.getLocation(); // Чекаем, где игрок ебется
        String worldName = getDimensionString(player.getWorld());
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        TextComponent accept = new TextComponent("[ Принять вызов ]"); // Оформление сообщений
        accept.setColor(net.md_5.bungee.api.ChatColor.RED);
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/911 accept"));
        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Нажми чтобы откликнуться на вызов [ /911 accept ]").color(net.md_5.bungee.api.ChatColor.RED).create()));
        String message = "&3[911] &6" + username + " &3Вызывает полицейского на координаты &7" + x + " " + y + " " + z + " &3в " + worldName; // Предварительная паста для сообщения у ментов
//(sender.hasPermission("zpolice.911get") &&
        if ((args.length > 0 && !args[0].equals("accept")) || args.length <= 0){
            try {
                FileWriter fw = new FileWriter(pathToSender);
                fw.write(sender.getName());
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (args.length > 0) {

            // Если игрок ввел дополнительный текст, добавляем его к сообщению
            String additionalText = String.join(" ", args);
            message += "\n&3Комментарий: &f" + additionalText;
        }

        if (args.length > 0 && args[0].equals("accept") && sender.hasPermission("zpolice.911get")) { // Тут логика принятия вызова (/911 accept)
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(pathToSender))) {
                    lastSenderName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                lastSender = Bukkit.getPlayer(lastSenderName);
                lastSender.sendMessage(color("&3[911] &6" + playerName + " &fскоро подлетит к тебе, ожидай"));
                file.delete();
                for (Player p : getServer().getOnlinePlayers()) { // Смотрим каждого мента в онлайне и каждому отправляем сообщение
                    if (p.hasPermission("zpolice.911get")) {
                        p.sendMessage(color("&3[911] &6" + playerName + " &fпринял вызов!"));
                    }
                }
                return true;
            } else {
                sender.sendMessage(color("&4Вызов либо уже принят, либо еще никто никого не вызывал"));
                return true;
            }
        }

        for (Player p : getServer().getOnlinePlayers()) { // Смотрим каждого мента в онлайне и каждому отправляем сообщение
            if (p.hasPermission("zpolice.911get")) { // А так же тут идёт проверка есть-ли менты онлайн на серве
                p.sendMessage(color(message));
                p.spigot().sendMessage(accept);
                onlinePlayers++;
            }
        }
        if (onlinePlayers>0){
            sender.sendMessage(color("&3[911] &fМенты получили твой донос!"));
        }
        else {
            sender.sendMessage(color("&3[911] &fВсе менты или админы оффлайн, разбирайся сам"));
        }
        onlinePlayers = 0;


        return true;
    }
    private String color (String string){
        return ChatColor.translateAlternateColorCodes('&',string);
    }

    private String getDimensionString(World world) { // Так скажем это "переводчик" для измерений, что суются в сообщение ментам
        switch (world.getEnvironment()) {
            case NORMAL:
                return "&6обычном мире";
            case NETHER:
                return "&cнезере";
            case THE_END:
                return "&5энде";
            default:
                return "&4неизвестном мире";
        }
    }
}
