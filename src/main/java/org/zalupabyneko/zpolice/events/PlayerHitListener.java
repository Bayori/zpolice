package org.zalupabyneko.zpolice.events;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerHitListener implements Listener {

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player hitter = (Player) event.getDamager();
            if (hitter.hasPermission("zpolice.baton"))
            {
                Player hitTo = (Player) event.getEntity();
                ItemStack weapon = hitter.getInventory().getItemInMainHand();
                if (!freezeEvents.isBatonned(hitTo) && weapon.getType() == Material.STICK && weapon.hasItemMeta() && weapon.getItemMeta().hasDisplayName() && (weapon.getItemMeta().getDisplayName().equals("Дубинка") || weapon.getItemMeta().getDisplayName().equals("дубинка"))){
                        freezeEvents.doBaton(hitTo);
                        hitter.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&7Вы повалили &f"+hitTo.getName())));
                }
            }
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Player && player.hasPermission("zpolice.baton")) {
            Player clickedPlayer = (Player) event.getRightClicked();
            if (freezeEvents.isBatonned(clickedPlayer))
            {
                freezeEvents.unBaton(clickedPlayer);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color("&7Вы подняли &f"+clickedPlayer.getName())));
            }
        }
    }
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}