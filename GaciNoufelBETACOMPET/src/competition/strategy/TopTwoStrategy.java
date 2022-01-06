package competition.strategy;

import java.util.*;
import java.util.Map.Entry;

import competitor.Competitor;

/**
 * Creates <code>3</code> groups and selects <code>the top 2</code> of each group. In addition, the top two third 
 * are drafted
 * @author GACI Noufel
 * @version 1.0
 */

public class TopTwoStrategy implements SelectionStrategy {

	
	
	/**
	 * Selects in the given list <code>c</code> the two competitors with the most points - in other words the best 
	 * third
	 * @param c list of competitors to draft
	 * @return the list containing the top two third
	 */
	private List<Competitor> twoBestThirds(List<Competitor> c) {
		List<Competitor> next = new ArrayList<>();
		// sort the remaining competitors
		Map<Competitor, Integer> selected = SelectionStrategy.sortedCompetitors(c);
		// initialization of an iterator
		Set<Entry <Competitor, Integer>> set = selected.entrySet();
		Iterator<Entry <Competitor, Integer>> iter = set.iterator();
		
		int i = 0; // a counter
		while(iter.hasNext() && i < 2) {
			next.add(iter.next().getKey());
			i++;
		}
		
		return next;
		
	}
	

	@Override
	public List<Competitor> bestOfGroupStage(List< List<Competitor>> competitors) {
		List<Competitor> nextStage = new ArrayList<>();
		// list of unselected competitors
		List<Competitor> reste = new ArrayList<>();
		
		for(List<Competitor> group : competitors) {
			int i = 0;
			//sort the current group
			Map<Competitor, Integer> sc = SelectionStrategy.sortedCompetitors(group);
			
			for(Map.Entry<Competitor, Integer> entry : sc.entrySet()) {
				// can we add more ?
				if (i < this.nbToSelect()) {
					nextStage.add(entry.getKey());
					i++;
				}
				//otherwise we add the remaining competitors
				else {
					reste.add(entry.getKey());
				}
			}
		}
		// adding the top two third competitors selected
		nextStage.addAll(this.twoBestThirds(reste));
		
		return nextStage;
	}

	@Override
	public int nbToSelect() {
		return 2;
	}

	@Override
	public int nbOfGroups() {
		return 3;
	}

}
