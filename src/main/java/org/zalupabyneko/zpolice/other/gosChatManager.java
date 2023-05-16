package org.zalupabyneko.zpolice.other;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class gosChatManager {
    private static Set<Player> goschatPlayers = new HashSet<>();
    public static void enableGOSChat(Player player) {
        goschatPlayers.add(player);
    }

    public static void disableGOSChat(Player player) {
        goschatPlayers.remove(player);
    }

    public static boolean isGOSChatEnabled(Player player) {
        return goschatPlayers.contains(player);
    }

    public static Set<Player> getGOSChatPlayers() {
        return goschatPlayers;
    }
    public static void redirectMessage(Player sender, String message) {
        for (Player p : sender.getServer().getOnlinePlayers()) {
            if (p.hasPermission("zpolice.goschat")) {
                p.sendMessage(message);
            }
        }
    }
}
