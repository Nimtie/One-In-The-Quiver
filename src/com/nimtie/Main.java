package com.nimtie;

import com.nimtie.eventlisteners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin Running");
        getServer().getPluginManager().registerEvents(new Blockbreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new ArrowDamage(), this);
        getServer().getPluginManager().registerEvents(new AxeJump(), this);
        getServer().getPluginManager().registerEvents(new healTemp(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Stopping");
    }
}
