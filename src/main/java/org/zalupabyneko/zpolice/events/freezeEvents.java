package org.zalupabyneko.zpolice.events;

import dev.geco.gsit.api.GSitAPI;
import dev.geco.gsit.objects.GetUpReason;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.zalupabyneko.zpolice.other.batonBlackListCommands;
import org.zalupabyneko.zpolice.other.gosChatManager;

import java.util.*;

public class freezeEvents implements Listener {
    private static Set<Player> batonPlayers = new HashSet<>();
    public static void doBaton(Player player) {
        batonPlayers.add(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128, false, false)); // Костыль ебучий. Я иначе не знаю уже как запретить челу прыгать, но разрешить падать
        GSitAPI.startCrawl(player);
        GSitAPI.setCanSit(player, false);
    }

    public static void unBaton(Player player) {
        batonPlayers.remove(player);
        player.removePotionEffect(PotionEffectType.JUMP);
        GSitAPI.stopCrawl(player, GetUpReason.ACTION);
        GSitAPI.setCanSit(player, true);
    }

    public static boolean isBatonned(Player player) {
        return batonPlayers.contains(player);
    }
    public static Set<Player> getBatonPlayers() {
        return batonPlayers;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (isBatonned(player) && player.isOnGround()){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0];
        if (isBatonned(player) && batonBlackListCommands.blackCommands.contains(command))
        {
            player.sendMessage(color("&cНельзя использовать эту команду, пока ты повален!"));
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (isBatonned(player)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        if (isBatonned(player))
        {
            unBaton(player);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (isBatonned(player))
        {
            unBaton(player);
        }
        if (gosChatManager.isGOSChatEnabled(player)){
            gosChatManager.disableGOSChat(player);
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (isBatonned(player) && (event.getMaterial() == Material.CHORUS_FRUIT || event.getMaterial() == Material.ENDER_PEARL || event.getMaterial() == Material.MILK_BUCKET)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (isBatonned(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (isBatonned(player)) {
            event.setCancelled(true);
        }
    }
    private String color(String string) {return ChatColor.translateAlternateColorCodes('&', string);}
}
