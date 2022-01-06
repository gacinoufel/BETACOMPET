package competition;

import java.util.List;

import competition.strategy.*;
import competitor.Competitor;

/**
 * Competition where competitors compete in two different stages. The first stage is played as a league and the final
 * stage as a tournament where, depending on the chosen <code>strategy</code>, some competitors are selected
 * @author  GACI Noufel
 * @version 1.0
 */

public class Master extends Competition {
	
	protected SelectionStrategy strategy;

	
	/**
	 * Creates an instance of a master with the given list of competitors and strategy 
	 * @param competitors list of competitors that will compete
	 * @param strategy the strategy that will be used
	 */
	public Master(List<Competitor> competitors, SelectionStrategy strategy) {
		super(competitors);
		this.strategy = strategy;
	}

	/**
	 * Provides the strategy used for this competition
	 * @return this competition's strategy
	 */
	public SelectionStrategy getStrategy() {
		return this.strategy;
	}
	

	/**
	 * Changes the strategy used by this competition by the given one 
	 * @param newStrategy new strategy to be used by this competition
	 */
	public void setStrategy(SelectionStrategy newStrategy) {
		this.strategy = newStrategy;
	}
	

	@Override
	protected void play(List<Competitor> competitors) {
		List<Competitor> next = playFirstStage(competitors);
		
		this.playFinalStage(next);

	}
	
	
	/**
	 * Plays this competition's first stage with the given list <code>competitors</code> and returns the list
	 * of those who have been selected for the next stage
	 * @param competitors list of competitors who'll play the first stage
	 * @return the list of selected competitors 
	 */
	public List<Competitor> playFirstStage(List<Competitor> competitors) {
		League league;
		
		//create group stage
		List< List<Competitor> > allGroups = this.strategy.createGroupStage(competitors);
		// make each group play the first stage
		for(List<Competitor> groups : allGroups) {
			league = new League(groups);
			league.setMatchListener(this.matchListeners);
			league.play();
		}
		
		return strategy.bestOfGroupStage(allGroups);
		
	}
	
	
	/**
	 * Plays the final stage of this competition with the given list <code>cptors</code>
	 * @param cptors list of competitors that'll play the final stage
	 */
	public void playFinalStage(List<Competitor> cptors) {
		Tournament tournament = new Tournament(cptors);
		tournament.setMatchListener(matchListeners);
		
		tournament.play();
		
	}

	
	
	
}
