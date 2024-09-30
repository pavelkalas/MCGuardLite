package cz.pavelkalas.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import cz.pavelkalas.core.Strings;
import cz.pavelkalas.utils.ConfigurationManager;

public class BlockInteractionListener implements Listener {

	/**
	 * Defines if is rule enabled or not.
	 */
	private boolean isEnabled = false;

	public BlockInteractionListener(ConfigurationManager configManager) {
		isEnabled = configManager.getConfig("nooperatorblockinteraction").contains("1");
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();

		if (isEnabled && !player.isOp()) {
			player.sendMessage(Strings.MSG_YOU_CANNOT_DO_THAT);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();

		if (isEnabled && !player.isOp()) {
			player.sendMessage(Strings.MSG_YOU_CANNOT_DO_THAT);
			event.setCancelled(true);
		}
	}
}
