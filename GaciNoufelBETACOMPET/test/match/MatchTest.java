package match;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitor.Competitor;

public class MatchTest {
	
	private Competitor competitor1;
	private Competitor competitor2;
	private Match match;
	
	@BeforeEach
	public void init() {
		competitor1 = new Competitor("Gaci");
		competitor2 = new Competitor("Noufel");
		match = new Match(competitor1, competitor2);
	}

	@Test
	public void testMatchHasBeenPlayed() {
		//to ensure that they haven't played yet
		assertEquals(0, match.getCompetitor1().getNbMatchPlayed());
		assertEquals(0, match.getCompetitor2().getNbMatchPlayed());
		//play the match
		match.play();
		//to ensure they have played
		assertEquals(1, match.getCompetitor1().getNbMatchPlayed());
		assertEquals(1, match.getCompetitor2().getNbMatchPlayed()); 
		
	}
	
	@Test
	public void testPlayWinnerAndLooserOK() {
		match.play();
		Competitor winner = match.getWinner();
		Competitor looser = match.getLooser();
		//to ensure that after the match, there is actually a winner and a looser
		assertNotNull(winner);
		assertNotNull(looser);
		// to ensure that the same competitor is not winner and looser
		assertFalse(winner.equals(looser));
	}

}
