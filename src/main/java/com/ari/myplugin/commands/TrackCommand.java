package com.ari.myplugin.commands;

import com.ari.myplugin.managers.TrackerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

public class TrackCommand implements CommandExecutor {

    private final TrackerManager trackerManager;

    public TrackCommand(TrackerManager trackerManager) {
        this.trackerManager = trackerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // command can only be activated by a player in-game
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command");
            return true;
        }

        // alert of correct use of the command
        if(args.length != 1) {
            sender.sendMessage("Usage: /track <tracker>");
            return true;
        }

        // instance of the target
        Player target = Bukkit.getPlayerExact(args[0]);

        // check if the player is online
        if(target == null || !target.isOnline()) {
            sender.sendMessage("Player not found");
            return true;
        }

        // player cannot track themselves
        if(target.equals(player)) {
            sender.sendMessage("You can't tracker yourself");
            return true;
        }

        // give the player a compass
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        player.getInventory().addItem(compass);

        // set the compass to point the target instance
        trackerManager.setTracker(player, target);
        player.setCompassTarget(target.getLocation());

        // confirm the targeted player
        player.sendMessage("Tracker has been set to " + target.getName());
        return true;
    }
}
