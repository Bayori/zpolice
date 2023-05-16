package org.zalupabyneko.zpolice.other;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.StringJoiner;

public class batonnedPlayersList {
    public static String result = null;
    public static void setPlayerToString(Set<Player> players)
    {
        StringJoiner sj = new StringJoiner(", "); // создаем StringJoiner с разделителем ", "

        for (Player player : players) {
            sj.add(player.getName()); // добавляем имя игрока в StringJoiner
        }

        result = sj.toString(); // получаем результирующую строку
    }

    public static String getBatonPlayersList(Set<Player> players)
    {
        setPlayerToString(players);
        return result;
    }

}
