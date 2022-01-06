package competitor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompetitorTest {
	
	private static Competitor team1;
	private static Competitor team2;
	
	@BeforeEach
	public void init() {
		team1 = new Competitor("Groupe1");
		team2 = new Competitor("Groupe2");
	}

	@Test
	public void testAddpoints() {
		int points = team1.getNbPoints();
		// check that competitor's points is 0 at the beginning
        assertEquals(0, points);
        // we add a point to team1
        team1.addPoints(1);
        // check that point has been added
        assertEquals(1, team1.getNbPoints()+points);
    }
	
	@Test
	public void testEquals() {
		Competitor team3 = new Competitor("Groupe1");
		assertTrue(team1.equals(team3));
		assertFalse(team1.equals(team2));
		// we add 2 points to team2
		team1.addPoints(2);
		// we re-apply the test to check that they're not equals anymore
		assertFalse(team1.equals(team3));
	}

}
