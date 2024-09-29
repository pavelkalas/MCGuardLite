package cz.pavelkalas.structures;

public class Attempt {

	/**
	 * Player instance
	 */
	private String playerName;

	/**
	 * Attempts count
	 */
	private int attempted = 0;
	
	public Attempt(String playerName) {
		this.playerName = playerName;
	}
	
	public Attempt(String playerName, int attempted) {
		this.playerName = playerName;
		this.attempted = attempted;
	}
	
	public String getPlayer() {
		return this.playerName;
	}
	
	public int getAttemptsCount() {
		return this.attempted;
	}
}
