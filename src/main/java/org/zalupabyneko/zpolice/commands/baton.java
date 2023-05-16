package org.zalupabyneko.zpolice.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zalupabyneko.zpolice.events.freezeEvents;
import org.zalupabyneko.zpolice.other.batonnedPlayersList;

public class baton implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("zpolice.baton"))
        {
            sender.sendMessage(color("&c&lok and?"));
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0 && sender.hasPermission("zpolice.forcebaton"))
        {
            freezeEvents.doBaton(player);
        } else if (args.length == 0 && !sender.hasPermission("zpolice.forcebaton")){
            sender.sendMessage(color("&c&lok and?"));
            return true;
        }
        if (args.length > 0 && args[0].equals("list"))
        {
            String list = batonnedPlayersList.getBatonPlayersList(freezeEvents.getBatonPlayers());
            sender.sendMessage(color("&6Поваленные дубинкой игроки: &f")+(list.length() == 0 ? color("&cотсутсвуют") : list));
        }
        else if (args.length > 0 && args[0].equals("unbaton")){
            if (args.length > 1 && args[1] != null)
            {
                Player targetPlayer = sender.getServer().getPlayer(args[1]);
                if (targetPlayer == null) {
                    sender.sendMessage(color("&4Игрок не найден")); return true;
                }
                freezeEvents.unBaton(targetPlayer);
            } else {
                sender.sendMessage(color("&7Укажи цель &c→ &7/baton unbaton {Никнейм}"));
                return true;
            }

        }
        else if (args.length > 0 && sender.hasPermission("zpolice.forcebaton")){
            Player targetPlayer = sender.getServer().getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage(color("&4Игрок не найден")); return true;
            }
            freezeEvents.doBaton(targetPlayer);
        } else if (args.length > 0 && !sender.hasPermission("zpolice.forcebaton")){
            sender.sendMessage(color("&c&lok and?"));
            return true;
        }
        return true;
    }
    private String color(String string) {return ChatColor.translateAlternateColorCodes('&', string);}
}
