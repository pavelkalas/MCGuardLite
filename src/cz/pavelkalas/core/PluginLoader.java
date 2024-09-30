package cz.pavelkalas.core;

import org.bukkit.plugin.java.JavaPlugin;

import cz.pavelkalas.listeners.ConnectionListener;
import cz.pavelkalas.listeners.player.BlockInteractionListener;
import cz.pavelkalas.listeners.player.MovementListener;
import cz.pavelkalas.utils.ConfigurationManager;

public class PluginLoader extends JavaPlugin {

	/**
	 * ConfigurationManager instance.
	 */
	private ConfigurationManager configManager;
	
	/**
	 * On plugin load.
	 */
	@Override
	public void onEnable() {
		configManager = new ConfigurationManager("mcguard.ini");
		configManager.loadConfig();
		
		if (configManager.isLoaded()) {
			getServer().getPluginManager().registerEvents(new ConnectionListener(configManager), this);
			getServer().getPluginManager().registerEvents(new MovementListener(configManager), this);
			getServer().getPluginManager().registerEvents(new BlockInteractionListener(configManager), this);
		}
	}

	/**
	 * On plugin unload (stopping server, reloading, etc..)
	 */
	@Override
	public void onDisable() {
	}
}
