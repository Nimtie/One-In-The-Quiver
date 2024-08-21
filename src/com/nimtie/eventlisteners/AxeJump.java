package com.nimtie.eventlisteners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public class AxeJump implements Listener {

    private final ArrayList<UUID> airbornePlayers = new ArrayList<>();

    @EventHandler
    public void onAxeJump(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.HAND && Objects.equals(e.getItem(), new ItemStack(Material.IRON_AXE))) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                Player p = e.getPlayer();
                airbornePlayers.add(p.getUniqueId());
                p.setVelocity(new Vector(p.getLocation().getDirection().getX() * 2.7,
                        p.getLocation().getDirection().getY() * 1.3,
                        p.getLocation().getDirection().getZ() * 3.4));
            }
        }
    }

    @EventHandler
    public void cancelFall(EntityDamageEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            Player p = (Player) e.getEntity();
            Iterator<UUID> iter = airbornePlayers.iterator();
            while (iter.hasNext())
                if (iter.next().equals(p.getUniqueId())) {
                    iter.remove();
                    e.setCancelled(true);
                }
            e.setDamage(e.getDamage());
        }
    }
}

