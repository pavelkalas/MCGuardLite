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
	
	/**
	 * Constructor with initial attempts value.
	 * 
	 * @param playerName    Player name
	 * @param attempted     Initial attempts value
	 */
	public Attempt(String playerName, int attempted) {
		this.playerName = playerName;
		this.attempted = attempted;
	}
	
	/**
	 * Returns a player name.
	 * 
	 * @return Player name
	 */
	public String getPlayer() {
		return this.playerName;
	}
	
	/**
	 * Returns a count of attempts.
	 * 
	 * @return Attempts count
	 */
	public int getAttemptsCount() {
		return this.attempted;
	}
}
