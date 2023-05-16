package org.zalupabyneko.zpolice.commands;

import com.earth2me.essentials.User;
import com.earth2me.essentials.Essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class jailtimeCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Plugin ess = Bukkit.getPluginManager().getPlugin("Essentials"); // Берем эссеншлс
            if (args.length == 0) {
                Player player = (Player) sender;
                User user = new User(player,(Essentials) ess); // Инициализируем юзера
                if (user.isJailed()){
                    sender.sendMessage(color("&6Тебе осталось кайфовать на нарах ещё &a"+String.valueOf(user.getFormattedJailTime()))); // Берем с эсеншелса инфу скок челу еще сидеть
                } else {
                    sender.sendMessage(color("&4Ты не в джейле"));
                }

            }
            else if (sender.hasPermission("zpolice.jailtimeother")){
                Player player = sender.getServer().getPlayer(args[0]); // Тут всё аналогично, только с аргументом
                if (player == null)
                {
                    sender.sendMessage(color("&7/jailtime "+args[0]+" &c→ &7Игрок не найден на сервере в данный момент"));
                    return true;
                }
                User user = new User(player, (Essentials) ess);
                if (user.isJailed()){
                    sender.sendMessage(color("&6Этому дурочку осталось ещё &a"+String.valueOf(user.getFormattedJailTime())+"\n&6Он сидит в &a"+String.valueOf(user.getJail())));
                } else {
                    sender.sendMessage(color("&c"+args[0]+"&4 не в джейле"));
                }
            } else {sender.sendMessage(color("&c&lok and?"));}
        return true;
    }

    private String color (String string){
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
