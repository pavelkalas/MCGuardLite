package cz.pavelkalas.core;

import org.bukkit.plugin.java.JavaPlugin;

import cz.pavelkalas.listeners.ConnectionListener;
import cz.pavelkalas.utils.ConfigurationManager;

public class PluginLoader extends JavaPlugin {

	private ConfigurationManager configManager;
	
	/**
	 * On plugin load.
	 */
	@Override
	public void onEnable() {
		configManager = new ConfigurationManager("mcguard.ini");
		configManager.loadConfig();
		
		getServer().getPluginManager().registerEvents(new ConnectionListener(configManager), this);
	}

	/**
	 * On plugin unload (stopping server, reloading, etc..)
	 */
	@Override
	public void onDisable() {
	}
}
