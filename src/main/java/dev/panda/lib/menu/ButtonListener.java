package dev.panda.lib.menu;

import dev.panda.lib.menu.pagination.PaginatedMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class ButtonListener implements Listener {

	private final Plugin plugin;

	public ButtonListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onButtonPress(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		Menu openMenu = Menu.currentlyOpenedMenus.get(player.getName());
		if (openMenu != null) {
			if (event.getSlot() != event.getRawSlot()) {
				if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
					event.setCancelled(false);
				}
				return;
			}

			if (openMenu.getButtons().containsKey(event.getSlot())) {
				Button button = openMenu.getButtons().get(event.getSlot());
				boolean shouldCancel = button.shouldCancel(player, event.getSlot(), event.getClick());
				boolean shouldShift = button.shouldShift(player, event.getSlot(), event.getClick());

				if (shouldCancel && shouldShift) {
					event.setCancelled(true);
				}
				else {
					event.setCancelled(shouldCancel);
				}

				button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());

				if (Menu.currentlyOpenedMenus.containsKey(player.getName())) {
					Menu newMenu = Menu.currentlyOpenedMenus.get(player.getName());
					if (newMenu == openMenu) {
						if (openMenu.isUpdateAfterClick()) {
							openMenu.setClosedByMenu(true);
							newMenu.openMenu(player);
						}
					}
				}
				else if (button.shouldUpdate(player, event.getSlot(), event.getClick())) {
					openMenu.setClosedByMenu(true);
					openMenu.openMenu(player);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		Menu openMenu = Menu.currentlyOpenedMenus.get(player.getName());

		if (openMenu != null) {
			openMenu.onClose(player);

			Menu.currentlyOpenedMenus.remove(player.getName());

			if (openMenu instanceof PaginatedMenu) {
				return;
			}
		}

		player.setMetadata("scanglitch", new FixedMetadataValue(plugin, true));
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if (player.hasMetadata("scanglitch")) {
			player.removeMetadata("scanglitch", plugin);
			for (ItemStack it : player.getInventory().getContents()) {
				if (it != null) {
					ItemMeta meta = it.getItemMeta();
					if (meta != null && meta.hasDisplayName()) {
						if (meta.getDisplayName().contains("§b§c§d§e")) {
							player.getInventory().remove(it);
						}
					}
				}
			}
		}
	}
}