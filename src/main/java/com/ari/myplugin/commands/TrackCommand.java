package com.ari.myplugin.commands;

import com.ari.myplugin.managers.TrackManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TrackCommand implements CommandExecutor {

    private final TrackManager trackManager;

    public TrackCommand(TrackManager trackManager) {
        this.trackManager = trackManager;
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
            sender.sendMessage(Component.text("Usage: /track <player>").color(NamedTextColor.RED));
            return true;
        }

        // instance of the target
        Player target = Bukkit.getPlayerExact(args[0]);

        // check if the player is online
        if(target == null || !target.isOnline()) {
            sender.sendMessage(Component.text("Player not found...").color(NamedTextColor.RED));
            return true;
        }

        // player cannot track themselves
        if(target.equals(player)) {
            sender.sendMessage(Component.text("You can't track yourself").color(NamedTextColor.RED));
            return true;
        }

        // give the player a compass
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        player.getInventory().addItem(compass);

        // set the compass to point the target instance
        trackManager.setTracker(player, target);
        player.setCompassTarget(target.getLocation());

        // confirm the targeted player
        player.sendMessage(Component.text("Tracking " + target.getName()).color(NamedTextColor.WHITE));
        return true;
    }
}
