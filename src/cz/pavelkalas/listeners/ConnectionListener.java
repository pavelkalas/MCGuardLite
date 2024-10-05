package cz.pavelkalas.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cz.pavelkalas.core.Strings;
import cz.pavelkalas.utils.ConfigurationManager;

public class ConnectionListener implements Listener {
	
	/**
	 * Maximum join message count.
	 */
	
	private final int MAX_MESSAGES_COUNT = 10;
	
	/**
	 * Maximum single message (per line) length.
	 */
	private final int MAX_SINGLE_MESSAGE_LENGTH = 256;
	
	/**
	 * ConfigurationManager instance.
	 */
	private ConfigurationManager configManager;
	
	public ConnectionListener(ConfigurationManager configManager) {
		this.configManager = configManager;
	}
	
	/**
	 * On player connect
	 * 
	 * @param event  Event instance.
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String[] joinmsg = configManager.getConfig("joinmsg").split(";");
		
		player.sendMessage("");
		player.sendMessage(Strings.JOINMSG_INFO);
		player.sendMessage("");
		
		if (joinmsg != null && joinmsg.length <= MAX_MESSAGES_COUNT) {
			for (String msg : joinmsg) {
				if (msg.length() <= MAX_SINGLE_MESSAGE_LENGTH && msg.trim().length() > 0) {
					player.sendMessage("[Server] " + msg.trim());
				}
			}
			
			player.sendMessage("");
		}
	}
}
