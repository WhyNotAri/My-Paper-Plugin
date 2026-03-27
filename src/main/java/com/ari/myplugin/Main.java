package com.ari.myplugin;

import com.ari.myplugin.commands.HelloCommand;
import com.ari.myplugin.commands.TrackerCommand;
import com.ari.myplugin.listener.JoinListener;
import com.ari.myplugin.listener.TrackerListener;
import com.ari.myplugin.managers.TrackerManager;
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
        if (getCommand("tracker") != null) {
            Objects.requireNonNull(getCommand("tracker")).setExecutor(new TrackerCommand(trackerManager));
        }else {
            getLogger().info("tracker command not found");
        }

        // Register Events
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new TrackerListener(trackerManager), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Ari's Plugin deactivated");
    }
}