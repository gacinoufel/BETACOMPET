package match.observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import competitor.*;
import match.*;

/**
 * Bookmakers update competitors' ratings depending on the results of the matches of the competition :
 * decrease if victory and increase if loss. Initially, all competitors ratings are at <code>1</code>
 * @author GACI Noufel 
 * @version 1.0
 */

public class Bookmakers implements MatchListener {

	// collection de couples (competiteur, n) ou n represente la cote actuelle du competiteur
    private Map<Competitor, Integer> cotes_liste; 
    
    /**
     * Instantiates a bookmaker by initializing its list of competitors ratings. 
     * At first, all competitors of the given list <code>lc</code> has a ratings of <code>1</code>
     * which can be updated during a competition
     * @param lc list of competiting competitors
     */
    public Bookmakers(List<Competitor> lc) {
    	this.cotes_liste = new HashMap<>(); 
    	for(Competitor c : lc) {
    		this.cotes_liste.put(c, 1);
    	}
    }

    /**
	 * Broadcasts all the necessary informations of the given <code>event</code> match
	 * @param event match observable
	 */
    public void matchResult (Match event) {
    	//displays current ratings before updating them
    	this.displayOldRatings(event.getWinner(), event.getLooser());
    	// updates ratings
        this.updateRatings(event.getWinner(), event.getLooser());
        //displays new ratings
        this.showRatings(event.getWinner(), event.getLooser());
        
    }

    /**
	 * Updates one competitor's popularity after a match : it increases for the looser and decreases for the winner
	 * @param winner one of the competitor 
     * @param looser the other one 
	 */
    private void updateRatings(Competitor winner, Competitor looser) {
        this.setRatings(looser, 1);  // the rating increases by 1 when he looses
        this.setRatings(winner, -1);  //the rating decreases by 1 when he wins 
    }

    /**
	 * Computes and updates competitor <code>c</code> new rating with given number <code>cote</code> if
	 * and only if its current ratings is more than <code>1</code>, otherwise it won't change
	 * @param c competitor's ratings to change
     * @param cote value to be added 
	 */
    public void setRatings(Competitor c, int cote) {
        if (cotes_liste.containsKey(c)) {
        	int old = this.getRatings(c); 
        	int newOne = old + cote;
            if (newOne < 1) { // on considere qu'une cote ne peut pas etre negative, un competiteur ne peut pas avoir moins de 1 en cote
            	cotes_liste.put(c, old); 
            }
            else {
            	cotes_liste.put(c, newOne);
            }
        }
    }
    

    /**
	 * Provides the ratings for the given competitor <code>c</code>
	 * @param c competitor for whom we need the ratings
     * @return <code>c</code>'s ratings 
	 */
    public int getRatings(Competitor c) {
    	return this.cotes_liste.get(c); 
    }
    

    /**
	 * Gives every registered competitor's ratings 
     * @return a set of competitor/rating
	 */
    public Set<Map.Entry<Competitor, Integer>> getCotesListe() {
    	return this.cotes_liste.entrySet();
    }

    /**
	 * Displays competitors <code>c1</code> and <code>c2</code> ratings after a match
	 * @param c1 one of the competitor
     * @param c2 the second one 
	 */
     public void showRatings(Competitor c1, Competitor c2) {
        System.out.println(c1.getName() + "'s rating is now "+ getRatings(c1)+
        		", and " + c2.getName()+ "'s rating is " + getRatings(c2)+ "\n");
    }
     
     
     /**
      * Displays given competitors <code>c1</code> and <code>c2</code> ratings before 
      * being updated
     * @param c1 one the competitor
     * @param c2 the other one
     */
    public void displayOldRatings(Competitor c1, Competitor c2) {
    	 System.out.println(c1.getName() + "'s victory (cote "+ this.getRatings(c1)+
    			 ") against "+ c2.getName() + " (cote "+ this.getRatings(c2) + ").");
     }

	
    
}
