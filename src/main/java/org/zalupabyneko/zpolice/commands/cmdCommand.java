package org.zalupabyneko.zpolice.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class cmdCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String path = "plugins\\ZPolice\\cmdHelpPlayers.yml";
        String pathpolice = "plugins\\ZPolice\\cmdHelpPolice.yml";
        if (sender.hasPermission("zpolice.cmdplayer")){
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    sender.sendMessage(color(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sender.hasPermission("zpolice.cmdpolice")){
                try (BufferedReader br = new BufferedReader(new FileReader(pathpolice))) {
                    String linePolice = null;
                    while ((linePolice = br.readLine()) != null) {
                        sender.sendMessage(color(linePolice));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return true;
    }
    private static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}