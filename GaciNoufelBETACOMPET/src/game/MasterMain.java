package game;

import competitor.Competitor;
import match.observer.*;
import competition.Master;
import competition.strategy.*;

import java.util.*;

/**
 * Class that enables to run the program with the Master Competition 
 * @author  GACI Noufel
 * @version 1.0
 */


public class MasterMain {

	
	public static void main(String[] args) {
		
		// to get user input
		Scanner keyboard = new Scanner(System.in);
		
		// to store available strategies
		Map<Integer, SelectionStrategy> strategies = new HashMap<>();
		strategies.put(1, new FirstOfGroupStageStrategy());
		strategies.put(2, new TopTwoStrategy());
		
		List<Competitor> competitors = new ArrayList<>();
		Master master;
		
		if (args.length > 0) {
			System.out.println("Welcome to the Master Competition \n");
			// to create competitors from standard input
			for(int i = 0; i < args.length; i++) {
				competitors.add(new Competitor(args[i]));
			}
			
			System.out.println("Here are some strategies below : \n");
			System.out.println("1. Select the first of each group stage\n"
					+ "2. Select the top two of each group + the two best thirds\n");
			System.out.println("Which strategy do you want to use for this competition ? Enter 1 or 2\n ");
			
			int choice = keyboard.nextInt();
			keyboard.close();
			// choose the type of strategy 
			master = new Master(competitors, strategies.get(choice));
			// add the Listeners in my Master 
			master.addMatchListener(new Bookmakers(competitors));
			master.addMatchListener(new Journalist());
			// Play the master 
			master.play();
			
		}
		else {
			System.out.println("Cannot play without any competitors...");
		}

	}

}
