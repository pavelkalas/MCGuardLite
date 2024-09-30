package cz.pavelkalas.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {

	/**
	 * Ban player's nickname and IP too.
	 * 
	 * @param player     Player instance
	 * @param message    Player IP address
	 */
	public static void banPlayer(Player player, String message) {
		if (message != null && player != null) {
			CommandsSender.sendCommands(new String[] { "ban " + player.getName() + " " + message,
					"ban-ip " + player.getAddress().getAddress().toString().substring(1) + " " + message });
		}
	}

	/**
	 * Kills the player.
	 * 
	 * @param player    Player instance
	 */
	public static void killPlayer(Player player) {
		if (player != null && !player.isDead()) {
			player.damage(Double.MAX_VALUE);
		}
	}
	
	/**
	 * Kicks the player.
	 * 
	 * @param player   Player instance
	 */
	public static void kickPlayer(Player player, String message) {
		if (player != null && !player.isDead() && message != null) {
			CommandsSender.sendCommand("kick " + player.getName() + " " + message);
		}
	}
}
