package cz.pavelkalas.utils;

import java.util.ArrayList;
import java.util.List;

import cz.pavelkalas.structures.Attempt;

public class AttemptManager {

	private List<Attempt> playerList = new ArrayList<Attempt>();
	
	public AttemptManager() {
	}

	public Attempt findEntry(String player) {
		for (Attempt attempt : playerList) {
			if (attempt.getPlayer().equals(player)) {
				return attempt;
			}
		}
		
		return null;
	}
	
	public void addAttempt(String player) {
		playerList.add(new Attempt(player));
	}
	
	public void resetAttempt(Attempt attempt) {
		playerList.remove(attempt);
	}
	
	public void incrementAttempt(String player) {
		Attempt attempt = findEntry(player);

		playerList.remove(attempt);
		playerList.add(new Attempt(player, attempt.getAttemptsCount() + 1));
	}
	
	public void resetAttempt(String playerName) {
		Attempt attempt = findEntry(playerName);
		
		playerList.remove(attempt);
		playerList.add(new Attempt(playerName, 0));
	}
}
