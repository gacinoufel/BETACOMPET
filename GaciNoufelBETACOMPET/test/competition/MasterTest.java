package competition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import competition.strategy.*;
import competitor.Competitor;


public class MasterTest extends CompetitionTest {
	
	protected Master master;
	protected final int NB_CPTORS = 16;
	protected SelectionStrategy strategy;

	@Override
	protected void createCompetition() {
		this.myCompetition = new Master(competitors, strategy);
		
	}
	
	@BeforeEach
	public void before() {
		this.strategy = new MockStrategy();
		competitors = new ArrayList<>();
		
		for(int i = 0; i < this.NB_CPTORS; i++) {
			competitors.add(new Competitor("team"+i));
		}
		master = new Master(competitors, strategy);
	}
	
	@Test
	public void testPlayFirstStage() {
		int nbGr = master.getStrategy().nbOfGroups();
		int nbSelect = master.getStrategy().nbToSelect();
		int sizeBefore = competitors.size();
		// play the first stage
		competitors = master.playFirstStage(competitors);
		// check that size has decreased after the first stage
		assertEquals(competitors.size(), sizeBefore - (nbSelect * nbGr));
	}
	
	@Test
	public void testPlayFinalStageWhenHasNotBeenPlayed() {
		// play the first round
		competitors = master.playFirstStage(competitors);
		// we remove one competitor from the selected one
		competitors.remove(competitors.size()-1);
		// number of match played by some competitors before the final stage
		int c1Match = competitors.get(0).getNbMatchPlayed();
		int c2Match = competitors.get(2).getNbMatchPlayed();
		// play the final stage 
		master.playFinalStage(competitors);
		// to make sure that final stage has been played
		assertEquals(c1Match, competitors.get(0).getNbMatchPlayed());
		assertEquals(c2Match, competitors.get(2).getNbMatchPlayed());
			
	}
	
	
	

}
