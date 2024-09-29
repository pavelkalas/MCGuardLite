package cz.pavelkalas.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {

	/**
	 * Ban player's nickname and IP too.
	 * 
	 * @param player   Player name
	 * @param message  Player IP address
	 */
	public static void banPlayer(Player player, String message) {
		CommandsSender.sendCommands(new String[] { "ban " + player.getName() + " " + message,
				"ban-ip " + player.getAddress().getAddress().toString().substring(1) + " " + message });
	}
}
