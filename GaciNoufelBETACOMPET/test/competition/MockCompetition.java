package competition;

import java.util.List;
import competitor.Competitor;


/**
 * A mock competition allows to represent a fake competition so that anyone can test some
 * methods that are defined in superclass
 * @author  GACI Noufel
 * @version 1.0
 */

public class MockCompetition extends Competition {

	// to "track" if method play is called
	public int playWithArgumentsCalled = 0;


	public MockCompetition(List<Competitor> competitors) {
		super(competitors);
	}

	/**
	 * Defines the performance adopted when this method is used by increasing
	 * the attribute playWithArgumentsCalled by one
	 */
	public void play(List<Competitor> competitors){
		this.playWithArgumentsCalled++;
	}




}
