package com.ari.myplugin.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(
                Component.text("✦ " + event.getPlayer().getName() + " has joined the game.")
                        .color(NamedTextColor.GREEN)
        );
    }
}
