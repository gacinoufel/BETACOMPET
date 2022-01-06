package competition;


import java.util.*;

import competitor.Competitor;
//import match.*;
import util.SizeIsNotPowerOf2Exception;

/**
 * It represents competition where matches, still played between competitors, are direct elimination
 * which means that only those who will win their match will be able to play the next round.
 * At the end, there is only <em>1</em> competitor - the winner
 * @author  GACI Noufel
 * @version 1.0
 */

public class Tournament extends Competition {
	
	public Tournament(List<Competitor> competitors) {
		super(competitors);
	}
	
	/**
	 * Lets us know whether or not <em>n</em> is a power of two. It returns <code>true</code> if it 
	 * does, otherwise <code>false</code>
	 * @param n the number to examine
	 * @return <code>true</code> if the given number is a power of two otherwise <code>false</code>
	 */
	protected boolean IsPowerOf2(int n) {
		return (n != 0) && ((n & (n-1)) == 0);
	}
	
	
	/**
	 * Provides a list of competitors who have won a round of this tournament with initially 
	 * a list of competitors if and only if the number of competitors is a power of 2
	 * @param competitors set of competitors competing
	 * @return the list of competitors who have won a round of this tournament
	 * @throws SizeIsNotPowerOf2Exception if and only if the size of the given list is not a power of two
	 */
	public List<Competitor> playOneRound(List<Competitor> competitors) throws SizeIsNotPowerOf2Exception {
		if (! this.IsPowerOf2(competitors.size())) {
			throw new SizeIsNotPowerOf2Exception("The number of competitors of your tournament must be a power of two !");
		}
		// initialization of an iterator to iterate on all elements of the list
		Iterator<Competitor> it = competitors.iterator();
		// creation of an empty list for the winner competitors
		List<Competitor> winners = new ArrayList<>();
		Competitor c1;
		Competitor c2;
		while (it.hasNext()) {
			c1 = it.next(); // to get the next element of the list
			c2 = it.next(); // to get the element after c1
			winners.add(this.playMatch(c1, c2)); // we add the winner of the match to the list of winners
		}
		return winners;
		
	}

	
	@Override
	public void play(List<Competitor> competitors) {
		try {
			List<Competitor> winners = playOneRound(competitors);
			while(winners.size() != 1){
				winners = playOneRound(winners);
			}
			System.out.println("The winner is "+ winners.get(0).getName());
		}
		catch (SizeIsNotPowerOf2Exception e) {
			System.out.println(e.getMessage());
		}
	}
 


}
