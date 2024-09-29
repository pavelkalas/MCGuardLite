package cz.pavelkalas.utils;

import java.util.ArrayList;
import java.util.List;

import cz.pavelkalas.structures.Attempt;

public class AttemptManager {

	/**
	 * List of players attempts.
	 */
	private List<Attempt> playerList = new ArrayList<Attempt>();
	
	/**
	 * Finds an entry in list.
	 * 
	 * @param player   Player name
	 * @return         Returns Attempt instance
	 */
	public Attempt findEntry(String player) {
		for (Attempt attempt : playerList) {
			if (attempt.getPlayer().equals(player)) {
				return attempt;
			}
		}
		
		return null;
	}
	
	/**
	 * Adds attempt to list.
	 * 
	 * @param player    Player name
	 */
	public void addAttempt(String player) {
		playerList.add(new Attempt(player));
	}
	
	/**
	 * Incements attempts count += 1
	 * 
	 * @param player    Player name
	 */
	public void incrementAttempt(String player) {
		Attempt attempt = findEntry(player);

		playerList.remove(attempt);
		playerList.add(new Attempt(player, attempt.getAttemptsCount() + 1));
	}
	
	/**
	 * Reset attempts to 0
	 * 
	 * @param playerName    Player name
	 */
	public void resetAttempt(String playerName) {
		Attempt attempt = findEntry(playerName);
		
		playerList.remove(attempt);
		playerList.add(new Attempt(playerName, 0));
	}
}
