package org.zalupabyneko.zpolice.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zalupabyneko.zpolice.other.gosChatManager;

import static org.bukkit.Bukkit.getServer;

public class goschatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("zpolice.goschat"))
        {
            sender.sendMessage(color("&c&lok and?"));
            return true;
        }
        if (args.length > 0)
        {
            for (Player p : getServer().getOnlinePlayers()) {
                if (p.hasPermission("zpolice.goschat")) {
                    p.sendMessage(color("&3[Гос. Чат] &6"+sender.getName()+" &3➥ &f")+String.join(" ",args));
                }
            }
            return true;

        }
            Player player = (Player) sender;
            if (gosChatManager.isGOSChatEnabled(player)) {
                gosChatManager.disableGOSChat(player);
                player.sendMessage(color("&3Государственный чат выключен"));
            } else {
                gosChatManager.enableGOSChat(player);
                player.sendMessage(color("&3Государственный чат включен"));
            }
           return true;
    }
    private String color (String string){
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
