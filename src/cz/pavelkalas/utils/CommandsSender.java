package cz.pavelkalas.utils;

import org.bukkit.Bukkit;

public class CommandsSender {
	
	public static void sendCommand(String commandText) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandText);
	}
	
	public static void sendCommands(String[] commandsText) {
		for (String commandText : commandsText) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandText);
		}
	}
}
