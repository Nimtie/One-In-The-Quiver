package com.nimtie.eventlisteners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ArrowDamage implements Listener {

    private final HashMap<UUID, Integer> streakMap = new HashMap<UUID, Integer>();


    @EventHandler
    public void onArrowHit(ProjectileHitEvent e) {
        Projectile proj = e.getEntity();
        //Checks to see if a player is shooting an arrow at another player
        if (proj.getShooter() instanceof Player p && proj.getType() == (EntityType.ARROW)) {
            if ((Objects.requireNonNull(e.getHitEntity())).getType().equals(EntityType.PLAYER)) {
                proj.remove();
                if (!e.getHitEntity().equals(p)) {
                    //Stuff That happens to the player hit with arrow
                    Player phit = (Player) e.getHitEntity();
                    BlockData blood = Material.REDSTONE_BLOCK.createBlockData();
                    p.spawnParticle(Particle.BLOCK, phit.getLocation(), 200, 0, 1, 0, blood);
                    phit.damage(20);
                    if (streakMap.containsKey(phit.getUniqueId())) {
                        if (streakMap.get(phit.getUniqueId()) >= 3) {
                            Bukkit.broadcastMessage(phit.getName() + " has lost a " + streakMap.get(phit.getUniqueId()) + " kill streak!");
                        }
                    }
                    streakMap.put(phit.getUniqueId(), 0);
                    //Stuff That happens to the player who shot the arrow
                    p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                    if (!streakMap.containsKey(p.getUniqueId())) {
                        streakMap.put(p.getUniqueId(), 1);
                    } else {
                        streakMap.put(p.getUniqueId(), streakMap.get(p.getUniqueId()) + 1);
                    }
                    if (streakMap.get(p.getUniqueId()) == 3) {
                        Bukkit.broadcastMessage(p.getName() + " is on a 3 kill streak!");
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
