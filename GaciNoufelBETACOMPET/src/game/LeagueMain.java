package game;

import java.util.ArrayList;
import java.util.List;

import competition.League;
import competitor.*;
import match.observer.*;

/**
 * Main class that enables to run a league 
 * @author  GACI Noufel
 * @version 1.0
 */

public class LeagueMain {

	
	public static void main(String[] args) {
		
		List<Competitor> competitors = new ArrayList<>();
		League league;
		MatchListener ml;
		
		if (args.length > 0) {
			try {
				System.out.println("You're playing a League !\n");
				// pour parcourir l'ensemble des arguments
				for(int i = 0; i < args.length; i++) {
					competitors.add(new Competitor(args[i]));
				}
				ml = new Bookmakers(competitors);
				league = new League(competitors);
				
				league.addMatchListener(ml);
				league.play();
			} catch (IndexOutOfBoundsException e) {
				e.getMessage();
			}
		}
		else {
			System.out.println("You must enter at least one name to play...");
		}

	}

}
