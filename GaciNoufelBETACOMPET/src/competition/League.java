package competition;

import java.util.List;
import competitor.Competitor;


/**
 * This class is a competition where matches between competitors are played 
 * home and away - the winner is the one who got the most victories
 * @author  GACI Noufel
 * @version 1.0
 */

public class League extends Competition {
	
	// constructor
	public League(List<Competitor> competitors) {
		super(competitors);
	}
	
	@Override
	public void play(List<Competitor> c) {
		for (Competitor c1 : c) {
			for (Competitor c2 : c) {
				// to ensure that a competitor doesn't compete himself
				if (! c1.equals(c2)) {
					this.playMatch(c1,c2);
				}
			}
		}
	}
	
	
	
}
