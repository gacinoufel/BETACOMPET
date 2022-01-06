package match;

import competitor.Competitor;

/**
 * Represents a game played randomly between two competitors and 
 * designates a winner at the end
 * @author  GACI Noufel
 * @version 2.0
 */

public class Match {
	
	private Competitor competitor1;
	private Competitor competitor2;
	private Competitor winner;
	private Competitor looser;
	
	
	/**
	 * Constructor that creates a match with given competitors <em>c1</em>
	 * and <em>c2</em>
	 * @param c1 first competitor of this match
	 * @param c2 second competitor of this match
	 */
	public Match(Competitor c1, Competitor c2) {
		this.competitor1 = c1;
		this.competitor2 = c2;
	}


	/**
	 * Provides one of the competitor oh this match
	 * @return the first competitor of this match
	 */
	public Competitor getCompetitor1() {
		return this.competitor1;
	}

	/**
	 * Provides the other competitor of this match
	 * @return the second competitor of this match
	 */
	public Competitor getCompetitor2() {
		return this.competitor2;
	}

	
	/**
	 * Displays competitors competing against each other
	 * @return a string that represents the competitors competing
	 */
	public String displayCompetitors() {
		return this.getCompetitor1().getName()+" vs "+ this.getCompetitor2().getName();
	}
	
	/**
	 * Shows the progress of this match by displaying the competitors and the 
	 * one who won
	 * @param winner competitor who won the game
	 */
	public void displayWinner(Competitor winner) {
		System.out.println(this.displayCompetitors() + " --> " + winner.getName() + " wins !");
	}
	
	/**
	 * Enables to run a match between two competitors and designates randomly
	 * the winner knowing that competitors have the same chance to win.
	 *  The winner gain one point
	 */
	public void play() {
		int alea = 0 + (int)(Math.random() * ((1-0) + 1));
		if (alea == 0) {
			this.winner = this.competitor1;
			this.looser = this.competitor2;
			}
		else {
			this.winner = this.competitor2;
			this.looser = this.competitor1;
		}
		
		this.winner.addPoints(1);
		//updates the number of match played
		this.updatePlayedMatches();

	} 
	
	
	/**
	 * Provides the competitor who won this match
	 * @return the winner of this match
	 */
	public Competitor getWinner() {
		return this.winner;
	}
	
	
	
	/**
	 * Provides the competitor who lost this match
	 * @return this match's looser
	 */
	public Competitor getLooser() {
		return this.looser;
		
	}
	
	/**
	 * For each match played by two competitors, it increases the number of match they have played
	 */
	public void updatePlayedMatches() {
		this.getCompetitor1().addMatchPlayed();
		this.getCompetitor2().addMatchPlayed();
	}

	

}
