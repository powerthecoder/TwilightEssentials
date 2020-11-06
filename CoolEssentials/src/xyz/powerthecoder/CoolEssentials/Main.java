package xyz.powerthecoder.CoolEssentials;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

    public Inventory inv;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        createInv();
    }

    @Override
    public void onDisable() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Commands
        if (label.equalsIgnoreCase("e")) {
            if (!(sender instanceof Player)) {
                // Console
                sender.sendMessage("You need to be in game!");
                return true;
            }
         // Player
            Player player = (Player) sender;
            player.sendMessage("Test");
            player.openInventory(inv);
            return true;
        }
        
        if (label.equalsIgnoreCase("about")) {
        	if (!(sender instanceof Player)) {
        		// Console
        		sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Lead Developer:" + ChatColor.WHITE + " Leo Power");
        		sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Junior Developer:" + ChatColor.WHITE + " Omar Morejon");
        		sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "GitHub:" + ChatColor.WHITE + " https://github.com/powerthecoder/CoolEssentials");
        		sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Version:" + ChatColor.WHITE + " 1.0");
        	}
        	Player player = (Player) sender;
        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Lead Developer:" + ChatColor.WHITE + " Leo Power");
        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Junior Developer:" + ChatColor.WHITE + " Omar Morejon");
        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "GitHub:" + ChatColor.WHITE + " https://github.com/powerthecoder/CoolEssentials");
        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Version:" + ChatColor.WHITE + " 1.0");
        }


        return false;
    }

    @EventHandler()
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(inv)) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        if (event.getSlot() == 0) {
            // Survival Mode
            player.sendMessage(ChatColor.GREEN + "Changing GameMode SURVIVAL!");
            player.setGameMode(GameMode.SURVIVAL);
            player.closeInventory();
        }
        if (event.getSlot() == 1) {
            // Creative Mode
            player.sendMessage(ChatColor.GREEN + "Changing GameMode CREATIVE");
            player.setGameMode(GameMode.CREATIVE);
            player.closeInventory();
        }
        if (event.getSlot() == 2) {
            // Spectator Mode
            player.sendMessage(ChatColor.GREEN + "Changing GameMode SPECATOR");
            player.setGameMode(GameMode.SPECTATOR);
            player.closeInventory();
        }
        if (event.getSlot() == 3) {
            // Kick
            Command.broadcastCommandMessage(player, ChatColor.RED + "Kicked " + player.getName());
            player.kickPlayer("You have kicked yourself!");
        }
        if (event.getSlot() == 4) {
            // Ban
            Command.broadcastCommandMessage(player, ChatColor.RED + "Banned " + player.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + player.getName() + " You have banned yourself!");
        }
        if (event.getSlot() == 5) {
            // Kill
            player.sendMessage(ChatColor.RED + "Killed Yourself!");
            player.setHealth(0);
        }
        if (event.getSlot() == 6) {
            // Speed x2
            player.sendMessage(ChatColor.GREEN + "You are now 2x faster!");
            player.setWalkSpeed(2);
            player.closeInventory();
        }
        if (event.getSlot() == 8) {
            // Exit
            player.closeInventory();
        }
    }

    public void createInv() {
        // GUI
        inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Essentials Mod Menu");

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        // Survival Mode
        meta.setDisplayName("Survival Mode");
        item.setItemMeta(meta);
        inv.setItem(0, item);

        // Creative Mode
        item.setType(Material.GRASS_BLOCK);
        meta.setDisplayName("Creative Mode");
        item.setItemMeta(meta);
        inv.setItem(1, item);

        // Spectator Mode
        item.setType(Material.PLAYER_HEAD);
        meta.setDisplayName("Spectator Mode");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        // Kick
        item.setType(Material.LEATHER_BOOTS);
        meta.setDisplayName("Kick");
        item.setItemMeta(meta);
        inv.setItem(3, item);

        // Ban
        item.setType(Material.DIAMOND_AXE);
        meta.setDisplayName("Ban");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        // Kill
        item.setType(Material.SKELETON_SKULL);
        meta.setDisplayName("Kill");
        item.setItemMeta(meta);
        inv.setItem(5, item);

        // Speed x2
        item.setType(Material.SPLASH_POTION);
        meta.setDisplayName("Speed x2");
        item.setItemMeta(meta);
        inv.setItem(6, item);

        // Exit
        item.setType(Material.BARRIER);
        meta.setDisplayName("Exit Menu");
        item.setItemMeta(meta);
        inv.setItem(8, item);
    }
}
