package cz.pavelkalas.utils;

import org.bukkit.Bukkit;

public class CommandsSender {
	
	/**
	 * Sends single command.
	 * 
	 * @param commandText   Command text.
	 */
	public static void sendCommand(String commandText) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandText);
	}
	
	/**
	 * Sends an array of commands.
	 * 
	 * @param commandsText   Array of commands
	 */
	public static void sendCommands(String[] commandsText) {
		for (String commandText : commandsText) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandText);
		}
	}
}
