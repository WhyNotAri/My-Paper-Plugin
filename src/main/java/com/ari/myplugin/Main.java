package com.ari.myplugin;

import com.ari.myplugin.commands.HelloCommand;
import com.ari.myplugin.commands.TrackCommand;
import com.ari.myplugin.listener.JoinListener;
import com.ari.myplugin.listener.LocatorBarListener;
import com.ari.myplugin.listener.TrackerListener;
import com.ari.myplugin.managers.TrackerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRules;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Ari's Plugin activated");

        TrackerManager trackerManager = new TrackerManager(this);

        // Hello command
        if (getCommand("hello") != null) {
            Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand());
        } else {
            getLogger().info("hello command not found");
        }

        // Tracker command
        if (getCommand("track") != null) {
            Objects.requireNonNull(getCommand("track")).setExecutor(new TrackCommand(trackerManager));
        }else {
            getLogger().info("track command not found");
        }

        // deactivate the locator bar
        for(World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRules.LOCATOR_BAR, false);
        }

        // Register Events
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new TrackerListener(trackerManager), this);
        getServer().getPluginManager().registerEvents(new LocatorBarListener(), this);
        getLogger().info("Locator Bar deactivated");
    }

    @Override
    public void onDisable() {
        getLogger().info("Ari's Plugin deactivated");
    }
}