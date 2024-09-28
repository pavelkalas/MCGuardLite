package cz.pavelkalas.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cz.pavelkalas.utils.ConfigurationManager;

public class ConnectionListener implements Listener {
	
	private ConfigurationManager configManager;
	
	public ConnectionListener(ConfigurationManager configManager) {
		this.configManager = configManager;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String[] joinmsg = configManager.getConfig("joinmsg").split(";");
		
		if (joinmsg != null && joinmsg.length < 10) {
			for (String msg : joinmsg) {
				if (msg.length() < 256) {
					player.sendMessage("[Server] " + msg.trim());
				}
			}
		}
	}
}
