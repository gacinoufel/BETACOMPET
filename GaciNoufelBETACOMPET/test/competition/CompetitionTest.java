package competition;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitor.Competitor;
import match.observer.*;

public abstract class CompetitionTest {
	
	protected Competitor c1;
	protected Competitor c2;
	protected List<Competitor> competitors;
	
	// attributes allowing to make test inheritance 
	protected Competition myCompetition;
	protected abstract void createCompetition();
	
	@BeforeEach
	public void init() {
		competitors = new ArrayList<>();
		c1 = new Competitor("Noufel");
		c2 = new Competitor("GACI");
		competitors.add(c1);
		competitors.add(c2);
		
		this.createCompetition();
	}

	@Test
	public void testPlay() {
		MockCompetition competition = new MockCompetition(competitors);
		// check before calling the method
		assertEquals(0, competition.playWithArgumentsCalled);
		competition.play();
		//now we ensure that the method has been called
		assertEquals(1, competition.playWithArgumentsCalled);
	}
	
	/**
	 * Test used for test inheritance
	 */
	@Test 
	public void testPlayMatch() {
		Competitor winner = this.myCompetition.playMatch(c1, c2);
		
		assertNotNull(winner);
		assertEquals(1, winner.getNbPoints());
	}
	
	@Test
	public void listenersAreNotified() {
		MockListener bm = new MockListener();
		this.myCompetition.addMatchListener(bm);
		//before playing a match
		assertEquals(0, bm.notification);
		//play two matches
		this.myCompetition.playMatch(c1, c2);
		this.myCompetition.playMatch(c1, c2);
		//make sure observer has been notified
		assertEquals(2, bm.notification);
	}

}
