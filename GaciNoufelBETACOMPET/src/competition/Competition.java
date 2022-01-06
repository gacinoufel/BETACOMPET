package competition;

import java.util.*;
import java.util.Map.Entry;

import util.MapUtil;
import match.Match;
import match.observer.*;
import competitor.Competitor;


/**
 * A competition is represented by a set of <em>matches</em> where <em>competitors</em>
 * compete against each other.
 * A set of observers can eventually attend every matches of the competition
 * @author  GACI Noufel
 * @version 2.0
 */


public abstract class Competition {

	protected Match match;
	protected final List<Competitor> competitors;
	protected List<MatchListener> matchListeners;

	/**
	 * Constructor that creates a competition with the given list of competitors
	 * @param competitors list of competitors that begin the competition
	 */
	public Competition(List<Competitor> competitors) {
		this.competitors = competitors;
		this.matchListeners = new ArrayList<>();
	}

	/**
	 * Provides the match of this competition
	 * @return the match being played in this competition
	 */
	public Match getMatch() {
		return this.match;
	}

	/**
	 * Enables to change the match to play by the given one
	 * @param newMatch new match to be played
	 */
	public void setMatch(Match newMatch) {
		this.match = newMatch;
	}

	/**
	 * Provides all competitors involved in this competition
	 * @return all competitors of this competition
	 */
	public List<Competitor> allCompetitors() {
		return this.competitors;
	}
	


	/**
	 * Rolls out all the matches of this competition and provides the ranking at the end
	 */
	public void play() {
		this.play(competitors);
		this.displayRanking(this.ranking());

	}

	/**
	 * Enables to all competitors of this competition to play matches
	 * @param competitors all the competitors who have to compete
	 */
	protected abstract void play(List<Competitor> competitors);

	/**
	 * Enables to run a match between two given competitors in this competition and provides
	 * its winner
	 * @param c1 one of the competitor
	 * @param c2 the other competitor
	 * @return the competitor who won the march
	 */
	public Competitor playMatch(Competitor c1, Competitor c2) {
		Competitor winner;
		this.setMatch(new Match(c1, c2));
		this.match.play();
		
		winner = this.match.getWinner();
		this.match.displayWinner(winner);
		// "notify" each observer about the match that has just been played
		for(MatchListener ml : this.matchListeners) {
			ml.matchResult(this.match);
		}

		return winner;
	}

	/**
	 * Creates a map where a <em>Competitor</em> is associated to a integer representing the
	 * number of matches he has won. Those elements are sorted by descending values - from the one who got
	 * the most victories to the one who got the less
	 * @return a collection of competitors sorted by descending number of victories
	 */
	public Map<Competitor, Integer> ranking() {
		// initialization of an empty hash map
		Map<Competitor, Integer> rang = new HashMap<> ();
		// we fill the map with each competitor of this competition
		for (Competitor c : competitors) {
			rang.put(c, c.getNbPoints());
		}
		// we sort those elements in descending order of points
		rang = MapUtil.sortByDescendingValue(rang);

		return rang;
	}

	/**
	 * Displays each element of the given <em>map</em> by showing the key and its value
	 * @param map map of elements to be displayed
	 */
	public void displayRanking(Map <Competitor, Integer> map) {
		Set <Entry<Competitor, Integer>> entries = map.entrySet();
		// initialization of an iterator
		Iterator<Entry<Competitor, Integer>> it_entries = entries.iterator();
		System.out.println("\n*** Ranking ***");
		while (it_entries.hasNext()) { //while there are still elements 
			Entry<Competitor, Integer> elt = it_entries.next(); // take the next element
			System.out.println(elt.getKey().getName()+ " - "+ elt.getValue());
		}
		System.out.println("\n");
	}
	
	/**
	 * Appends the given observer <code>ml</code> (if not already included) to the set of observers 
	 * who attend this competition
	 * @param ml observer to be added
	 */
	public synchronized void addMatchListener(MatchListener ml) {
		if(!this.matchListeners.contains(ml)) {
			this.matchListeners.add(ml);
		}
	}
	
	
	/**
	 * If exists, pulls out the given observer <code>ml</code> from this competition's set of observers
	 * @param ml observer to be removed
	 */
	public synchronized void removeMatchListener(MatchListener ml) {
		this.matchListeners.remove(ml);
	}
	
	
	/**
	 * Changes the actual set with the given one <code>nexML</code>
	 * @param newML list of new observers
	 */
	public void setMatchListener(List<MatchListener> newML) {
		this.matchListeners = newML;
	}



}
