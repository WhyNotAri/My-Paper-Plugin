package com.ari.myplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TrackerManager {

    private final JavaPlugin plugin;
    private final Map<UUID, UUID> trackers = new HashMap<>();

    public TrackerManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // set the tracker to the specified target and saves it on the HashMap
    public void setTracker(@NonNull Player tracker, @NonNull Player target) {
        trackers.put(tracker.getUniqueId(), target.getUniqueId());
    }

    // get the targeted player
    public Player getTarget(@NonNull Player tracker) {
        UUID targetId = trackers.get(tracker.getUniqueId());
        if (targetId == null) {
            return null;
        }
        return Bukkit.getPlayer(targetId);
    }

    // verify if it is tracking an existent player
    public boolean isTracking(@NonNull Player tracker) {
        return trackers.containsKey(tracker.getUniqueId());
    }

    // delete the configurated tracker
    public void removeTracker(@NonNull Player tracker) {
        trackers.remove(tracker.getUniqueId());
    }

    // update the location of the target
    public void updateCompassTarget(@NonNull Player tracker) {
        Player target = getTarget(tracker);
        if (target != null && target.isOnline()) { // check if it is still an existent and online player
            tracker.setCompassTarget(target.getLocation());
            tracker.sendMessage("Compass updated to " + target.getName() + "'s location");
        } else {
            tracker.sendMessage("Target player is no longer online");
            removeTracker(tracker);
        }
    }
}
