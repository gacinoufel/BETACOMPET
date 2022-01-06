package competition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class LeagueTest extends CompetitionTest {


	@Override
	protected void createCompetition() {
		this.myCompetition = new League(competitors);
	}


	@Test
	public void testPlay(){
		myCompetition.play(competitors);
		// to make sure that all the players have played at least two time
		assertTrue(competitors.get(0).getNbMatchPlayed() >= 2);
		assertTrue(competitors.get(1).getNbMatchPlayed() >= 2);

	}

}
