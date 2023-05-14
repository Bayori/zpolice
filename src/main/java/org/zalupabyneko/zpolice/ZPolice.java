package org.zalupabyneko.zpolice;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.zalupabyneko.zpolice.commands.*;
import org.zalupabyneko.zpolice.events.PlayerHitListener;
import org.zalupabyneko.zpolice.events.freezeEvents;
import org.zalupabyneko.zpolice.other.CommandTabCompleter;
import org.zalupabyneko.zpolice.other.batonBlackListCommands;
import org.zalupabyneko.zpolice.other.gosChatManager;

import java.io.File;
import java.util.Map;

public final class ZPolice extends JavaPlugin implements Listener {
    String directoryPathStr = "plugins\\ZPolice";
    File directoryPath = new File(directoryPathStr);

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new PlayerHitListener(), this);
        getServer().getPluginManager().registerEvents(new freezeEvents(), this);

        filesOperations();
        batonBlackListCommands.batonList();
        getCommand("zpolice").setExecutor(new zpoliceCommand());
        getCommand("911").setExecutor(new nnoCommand());
        getCommand("911").setTabCompleter(new CommandTabCompleter());
        getCommand("jailtime").setExecutor(new jailtimeCommand());
        getCommand("goschat").setExecutor(new goschatCommand());
        getCommand("gct").setExecutor(new goschatCommand());
        getCommand("baton").setExecutor(new baton());
        getCommand("baton").setTabCompleter(new CommandTabCompleter());

        getLogger().info(color("&3  _________  "));
        getLogger().info(color("&3 |__  /  _ \\ "));
        getLogger().info(color("&3   / /| |_) |"));
        getLogger().info(color("&3  / /_|  __/ "));
        getLogger().info(color("&3 /____|_|    &cZalupa Police &4") + this.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) { // Я в курсах что этот ивент можно было в свой класс сунуть
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        if (gosChatManager.isGOSChatEnabled(player)) {
            // отправка сообщения только игрокам, у которых включен госчат
            gosChatManager.redirectMessage(event.getPlayer(), color("&3[Гос. Чат] &6"+playerName+" &3➥ &f")+event.getMessage());
            event.setCancelled(true);
        } else {
            // перенаправление сообщения в обычный чат
            event.getRecipients().clear();
            event.getRecipients().addAll(Bukkit.getOnlinePlayers());
        }
    }
    public void filesOperations() {
        if (!directoryPath.exists()) {
            try {
                directoryPath.mkdirs();
                //saveResource("config.yml", false); // Пока что нет такого контента, который будет засунут в кфг
                saveResource("batonBlackListCommands.yml", false);
                getLogger().info(color("&cКаталог plugins/ZPolice/ успешно создан!"));
            } catch (Exception e) {
                e.printStackTrace();
                getLogger().info(color("&4Ошибка при создании каталога plugins/ZPolice -> Попробуйте создать его вручную"));
            }
        }
    }

    private static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}

