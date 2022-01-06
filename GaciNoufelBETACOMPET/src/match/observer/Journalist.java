package match.observer;

import match.Match;

/**
 * Journalist attends a competition and broadcasts the result of every matches.
 * @author GACI Noufel 
 * @version 1.0
 */


public class Journalist implements MatchListener {

    /**
	 * Broadcast the result of the given <code>event</code> 
	 * @param event observable match
	 */
	 public void matchResult(Match event) {
        System.out.println(event.getWinner().getName() + " (" + event.getWinner().getNbPoints()+
        		" pts) won against "+ event.getLooser().getName()+
        		" (" + event.getLooser().getNbPoints() + " pts).\n");
    }


    
}
