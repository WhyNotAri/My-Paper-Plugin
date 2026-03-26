package com.ari.myplugin;

import com.ari.myplugin.commands.HelloCommand;
import com.ari.myplugin.listener.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("HolaPlugin activado!");

        // Registrar comando
        if (getCommand("hola") != null) {
            getCommand("hola").setExecutor(new HelloCommand());
        }

        // Registrar eventos
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HolaPlugin desactivado!");
    }
}