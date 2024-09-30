package cz.pavelkalas.listeners.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import cz.pavelkalas.core.Strings;
import cz.pavelkalas.utils.AttemptManager;
import cz.pavelkalas.utils.ConfigurationManager;
import cz.pavelkalas.utils.PlayerUtils;

public class ClickListener implements Listener {

	/**
	 * Maximum clicks by provided time.
	 */
	private int MAX_CLICKS_PER_TIME = 30;
	
	/**
	 * Maximum ignoring attempts before punishment.
	 */
	private int MAX_ATTEMPTS = 1;

	/**
	 * Maximum time to exceed clicking speed.
	 */
	private int MAX_EXCEED_TIME = 1000;
	
	/**
	 * Player clicks list.
	 */
	private HashMap<String, List<Long>> playerClicks = new HashMap<>();

	/**
	 * AttemptManager instance.
	 */
	private AttemptManager attemptManager;

	/**
	 * Defines if is rule enabled or not.
	 */
	private boolean isEnabled;

	public ClickListener(ConfigurationManager configManager) {
		attemptManager = new AttemptManager();

		String[] arguments = configManager.getConfigArguments("nospeedclick");

		if (arguments != null) {
			isEnabled = arguments[0].equals("1");
			MAX_EXCEED_TIME = Integer.parseInt(arguments[1]);
			MAX_CLICKS_PER_TIME = Integer.parseInt(arguments[2]);
			MAX_ATTEMPTS = Integer.parseInt(arguments[3]);
		}
	}

	/**
	 * On player clicking.
	 * 
	 * @param event   Player interacting event instance
	 */
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if (isEnabled) {
			Player player = event.getPlayer();
			onClick(player);
		}
	}

	/**
	 * Handling player clicking.
	 * 
	 * @param player   Player instance
	 */
	public void onClick(Player player) {
		String playerName = player.getName();
		long currentTime = System.currentTimeMillis();

		if (!playerClicks.containsKey(playerName)) {
			playerClicks.put(playerName, new ArrayList<>());
		}

		List<Long> clicks = playerClicks.get(playerName);

		Iterator<Long> iterator = clicks.iterator();

		while (iterator.hasNext()) {
			long time = iterator.next();
			if (currentTime - time > MAX_EXCEED_TIME) {
				iterator.remove();
			}
		}

		clicks.add(currentTime);

		if (attemptManager.findEntry(player.getName()) == null) {
			attemptManager.addAttempt(player.getName());
		}
		
		if (attemptManager.findEntry(player.getName()).getAttemptsCount() >= MAX_ATTEMPTS) {
			PlayerUtils.banPlayer(player, Strings.MSG_YOURE_BANNED);
			attemptManager.resetAttempt(player.getName());
		} else if (clicks.size() > MAX_CLICKS_PER_TIME) {
			attemptManager.incrementAttempt(player.getName());
			PlayerUtils.kickPlayer(player, Strings.MSG_DONT_USE_CHEATS);
			clicks.clear();
		}

		playerClicks.put(playerName, clicks);
	}
}
