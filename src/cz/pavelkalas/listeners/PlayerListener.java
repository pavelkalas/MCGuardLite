package cz.pavelkalas.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import cz.pavelkalas.core.Strings;
import cz.pavelkalas.utils.AttemptManager;
import cz.pavelkalas.utils.ConfigurationManager;
import cz.pavelkalas.utils.PlayerUtils;

public class PlayerListener implements Listener {

	/**
	 * Maximum walking speed per move.
	 */
	private double MAX_WALKING_SPEED = 3.25;

	/**
	 * Maximum sprinting speed per move.
	 */
	private double MAX_SPRINTING_SPEED = 6.5;

	/**
	 * Maximum flying(+sprinting) speed per move.
	 */
	private double MAX_FLYING_SPEED = 11.0;

	/**
	 * Maximum ignoring attempts before punishment.
	 */
	private int MAX_ATTEMPTS = 1;
	
	/**
	 * AttemptManager instance.
	 */
	private AttemptManager attemptManager;

	/**
	 * Defines if is rule enabled or not.
	 */
	private boolean isEnabled = false;

	public PlayerListener(ConfigurationManager configManager) {
		this.attemptManager = new AttemptManager();

		String[] arguments = configManager.getConfigArguments("noviolatemoving");
		
		if (arguments != null && arguments.length >= 5) {
			isEnabled = arguments[0].equals("1");
			MAX_ATTEMPTS = Integer.parseInt(arguments[1]);
			MAX_WALKING_SPEED = Double.parseDouble(arguments[2]);
			MAX_SPRINTING_SPEED = Double.parseDouble(arguments[3]);
			MAX_WALKING_SPEED = Double.parseDouble(arguments[4]);
		}
	}

	/**
	 * Runs everytime ANY player move.
	 * 
	 * @param event Player movement event
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (isEnabled) {
			Player player = event.getPlayer();
			String playerName = player.getName();

			Location from = event.getFrom();
			Location to = event.getTo();

			to.setY(0);
			from.setY(0);

			double fromXZ = from.distance(to) * 10;
			double limit = 0;

			if (player.isSprinting() && !player.isFlying() && player.isOnGround()) {
				limit = MAX_SPRINTING_SPEED;
			} else if (player.isFlying()) {
				limit = MAX_FLYING_SPEED;
			} else if (player.isOnGround()) {
				limit = MAX_WALKING_SPEED;
			}

			if (fromXZ > limit && !player.isDead() && limit > 0.0) {
				if (attemptManager.findEntry(playerName) == null) {
					attemptManager.addAttempt(playerName);
				} else if (attemptManager.findEntry(playerName).getAttemptsCount() <= MAX_ATTEMPTS) {
					attemptManager.incrementAttempt(playerName);
					player.kickPlayer(Strings.MSG_DONT_USE_CHEATS);
					return;
				} else if (attemptManager.findEntry(playerName).getAttemptsCount() > MAX_ATTEMPTS) {
					attemptManager.resetAttempt(playerName);
					PlayerUtils.banPlayer(player, Strings.MSG_YOURE_BANNED);
					return;
				}
			}
		}
	}
}
