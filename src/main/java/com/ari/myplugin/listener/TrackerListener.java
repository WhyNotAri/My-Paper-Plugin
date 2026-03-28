package com.ari.myplugin.listener;

import com.ari.myplugin.managers.TrackManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class TrackerListener implements Listener {
    private final TrackManager trackManager;

    public TrackerListener(TrackManager trackManager) {
        this.trackManager = trackManager;
    }

    // alert trackers that the target disconnected
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player disconnected = event.getPlayer();

        for(Player online : disconnected.getServer().getOnlinePlayers()) {
            Player target = trackManager.getTarget(online);

            if(target != null && target.equals(disconnected)) {
                online.sendMessage("Target " + disconnected.getName() + " disconnected");
            }
        }
    }

    // update the location of the target by clicking the compass everytime
    @EventHandler
    public void onCompassClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() != Material.COMPASS) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (trackManager.isTracking(player)) {
                trackManager.updateCompassTarget(player);
                event.setCancelled(true);
            }
        }
    }
}
