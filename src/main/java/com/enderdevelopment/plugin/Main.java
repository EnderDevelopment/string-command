package com.enderdevelopment.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class Main extends JavaPlugin {
    private static final long REPLENISH_INTERVAL_TICKS = 30L * 20L;
    private final Map<UUID, BukkitTask> replenishmentTasks = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("string").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Component.text("Only players can use this command.", NamedTextColor.RED));
                return true;
            }

            UUID playerId = player.getUniqueId();
            if (replenishmentTasks.containsKey(playerId)) {
                player.sendMessage(Component.text("Your string replenishment is already active.", NamedTextColor.YELLOW));
                return true;
            }

            BukkitTask task = Bukkit.getScheduler().runTaskTimer(this, () -> {
                Player onlinePlayer = Bukkit.getPlayer(playerId);
                if (onlinePlayer == null) {
                    stopReplenishment(playerId);
                    return;
                }
                giveString(onlinePlayer);
            }, REPLENISH_INTERVAL_TICKS, REPLENISH_INTERVAL_TICKS);
            replenishmentTasks.put(playerId, task);
            player.sendMessage(Component.text("String replenishment started. You will receive one string every 30 seconds.",
                    NamedTextColor.GREEN));
            return true;
        });
    }

    @Override
    public void onDisable() {
        for (BukkitTask task : replenishmentTasks.values()) {
            task.cancel();
        }
        replenishmentTasks.clear();
    }

    private void giveString(Player player) {
        Map<Integer, ItemStack> leftovers = player.getInventory().addItem(new ItemStack(Material.STRING));
        if (!leftovers.isEmpty()) {
            leftovers.values().forEach(item -> player.getWorld().dropItemNaturally(player.getLocation(), item));
        }
        player.sendMessage(Component.text("You received one string.", NamedTextColor.GREEN));
    }

    private void stopReplenishment(UUID playerId) {
        BukkitTask task = replenishmentTasks.remove(playerId);
        if (task != null) {
            task.cancel();
        }
    }
}
