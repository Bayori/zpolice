package org.zalupabyneko.zpolice.other;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("911") && args.length == 1 && commandSender.hasPermission("zpolice.911get"))
        {
            return new ArrayList<>(Arrays.asList("accept"));
        }
        if (command.getName().equalsIgnoreCase("baton") && args.length == 1 && commandSender.hasPermission("zpolice.baton"))
        {
            return new ArrayList<>(Arrays.asList("list", "unbaton"));
        }
        return null;
    }
}
