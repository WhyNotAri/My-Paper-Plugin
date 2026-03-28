package com.ari.myplugin.listener;

import org.bukkit.GameRules;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class LocatorBarListener implements Listener {
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        event.getWorld().setGameRule(GameRules.LOCATOR_BAR, false);
    }
}
