package org.zalupabyneko.zpolice.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class zpoliceCommand implements CommandExecutor {



    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(color("&3[ &lzalupa.police &3]\n&bby Nekorise ☄"));
        return true;
    }
    private String color (String string){
        return ChatColor.translateAlternateColorCodes('&',string);
    }

}
