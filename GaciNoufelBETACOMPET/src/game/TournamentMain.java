package game;

import java.util.ArrayList;
import java.util.List;

import competition.Tournament;
import competitor.*;
import match.observer.*;

/**
 * @author  GACI Noufel
 * @version 1.0
 */

public class TournamentMain {

	public static void main(String[] args) {
		List<Competitor> competitors = new ArrayList<>();
		Tournament tournament;
		MatchListener journalist;
		
		if (args.length > 0) {
			System.out.println("You're playing a tournament\n");
			//pour parcourir l'ensemble des arguments passes
			for(int i = 0; i < args.length; i++) {
				competitors.add(new Competitor(args[i]));
			}
			
			journalist = new Journalist();
			tournament = new Tournament(competitors);
			
			tournament.addMatchListener(journalist);
			tournament.play();
		}
		else {
			System.out.println("You must enter at least one name to play...");
		}

	}

}
