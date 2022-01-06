package competition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitor.Competitor;
import util.SizeIsNotPowerOf2Exception;

public class TournamentTest extends CompetitionTest {

	private Tournament tournament;

	@BeforeEach
	public void before() {
		competitors = new ArrayList<>();
		// we add some competitors to this list
		competitors.add(new Competitor("team1"));
		competitors.add(new Competitor("team2"));
		competitors.add(new Competitor("team3"));
		tournament = new Tournament(competitors);
	}

	@Test
	public void testPlayOneRoundWhenSizeIsNotPowerOf2() {
		assertThrows(SizeIsNotPowerOf2Exception.class, () -> {
			tournament.playOneRound(competitors);
		});
	}

	@Test
	public void testPlayOneRoundOk() {
		//we add another competitor to the tournament
		competitors.add(new Competitor("team4"));
		// the size before the first round
		int sizeBefore = competitors.size();
		try {
			competitors = tournament.playOneRound(competitors);
		} catch (SizeIsNotPowerOf2Exception e) {
			fail();
		}
		// to ensure that the size has decreased by half after the round
		assertEquals(sizeBefore/2, competitors.size());
		// to ensure that all the players who are in the same group are played in tournament
		assertEquals(1, competitors.get(0).getNbMatchPlayed());
		assertEquals(1, competitors.get(1).getNbMatchPlayed());

	}

	@Test
	public void testPlay(){
		competitors.add(new Competitor("team4"));
		tournament.play(competitors);
		// to ensure that all the players are played in tournament at least one time
		assertTrue(competitors.get(0).getNbMatchPlayed()>=1);
		assertTrue(competitors.get(1).getNbMatchPlayed()>=1);
		assertTrue(competitors.get(2).getNbMatchPlayed()>=1);
		assertTrue(competitors.get(3).getNbMatchPlayed()>=1);


	}

	@Override
	protected void createCompetition() {
		this.myCompetition = new Tournament(competitors);
	}



}
