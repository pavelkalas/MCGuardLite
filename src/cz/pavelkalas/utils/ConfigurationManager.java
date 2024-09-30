package cz.pavelkalas.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.pavelkalas.structures.Config;

public class ConfigurationManager {

	/**
	 * Configuration list
	 */
	private List<Config> configList = new ArrayList<Config>();
	
	/**
	 * Configuration loaded state
	 */
	private boolean isLoaded;

	/**
	 * Configuration file instance
	 */
	private final File configFile;

	public ConfigurationManager(String configFileStr) {
		this.configFile = new File(configFileStr);
		isLoaded = false;
	}

	/**
	 * Loads the configuration into memory.
	 */
	public void loadConfig() {
		try {
			if (!configFile.exists()) {
				configFile.createNewFile();
			}
			
			if (configList.size() > 0) {
				configList.clear();
			}

			try (FileReader fileReader = new FileReader(configFile); Scanner scanner = new Scanner(fileReader)) {

				while (scanner.hasNextLine()) {
					String currentLine = scanner.nextLine().trim();

					if (currentLine.isEmpty() || !currentLine.contains("=")) {
						continue;
					}

					String[] parts = currentLine.split("=", 2);

					String name = parts[0].trim();
					String value = parts[1].trim();
					
					if (value.contains("//")) {
						value = value.split("//")[0].trim();
					}

					if (!name.isEmpty()) {
						configList.add(new Config(name, value));
					}
				}
			}

			isLoaded = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks for the configuration load.
	 * 
	 * @return Returns TRUE if is configuration successfully loaded.
	 */
	public boolean isLoaded() {
		return this.isLoaded;
	}

	/**
	 * Returns a value by provided key in config.
	 * 
	 * @param name  Key in configuration.
	 * @return      Returns a value from key in config.
	 */
	public String getConfig(String name) {
		if (isLoaded()) {
			for (Config conf : this.configList) {
				if (conf.name.equals(name)) {
					return conf.value;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Get configuration arguments.
	 * 
	 * @param name  Key in configuration.
	 * @return      Returns an array of arguments.
	 */
	public String[] getConfigArguments(String name) {
		String value = getConfig(name);
		
		if (value.contains(" ")) {
			return value.split(" ");
		}
		
		return null;
	}
	
	/**
	 * Get configuration argument at specified index.
	 * 
	 * @param name           Key in configuration.
	 * @param argumentIndex  Argument index in array.
	 * @return				 Returns a argument from an array of arguments.
	 */
	public String getConfigArgumentAt(String name, int argumentIndex) {
		String value = getConfig(name);
		
		if (value.contains(" ")) {
			String[] args = value.split(" ");
			
			if (args.length > argumentIndex && argumentIndex >= 0) {
				return args[argumentIndex];
			}
		}
		
		return null;
	}
}
