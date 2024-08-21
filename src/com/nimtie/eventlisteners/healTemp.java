package com.nimtie.eventlisteners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class healTemp implements Listener {

    // Will likely be replaced to a command/cut, used for testing atm
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.HAND && Objects.equals(e.getItem(), new ItemStack(Material.ARROW))) {
            Player p = e.getPlayer();
            p.setHealth(20);
            p.setFoodLevel(20);
        }
    }
}


