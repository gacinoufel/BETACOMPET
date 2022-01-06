package competition.strategy;

import java.util.*;

import competitor.Competitor;
import util.MapUtil;

/**
 * Determines a strategy for selecting competitors through stages
 * @author  GACI Noufel
 * @version 1.0
 */

public interface SelectionStrategy {
	
	/**
	 * Returns the number of elements to select
	 * @return the number of elements to select
	 */
	public int nbToSelect();
	
	
	/**
	 * Returns the number of groups to create
	 * @return the number of groups to create
	 */
	public int nbOfGroups();
	
	
	/**
	 * Computes, for the given list <code>c</code>, the length of its different portions that will be
	 * created
	 * @param c the list to cut
	 * @return portion size
	 */
	public default int computeSize(List<Competitor> c) {
		return c.size() / this.nbOfGroups();
	}
	
	/**
	 * Creates, from the given list <code>competitors</code>, groups depending on the number of competitors 
	 * and the number of group to be created
	 * @param competitors the list of competitors to divide into groups
	 * @return list of all stages
	 */
	public default List< List<Competitor> > createGroupStage(List<Competitor> competitors) {
		// compute size for each group
		int stage_size = this.computeSize(competitors); 
		// data structure for group stage
		List< List<Competitor> > groups = new ArrayList<>();
		Iterator<Competitor> it = competitors.iterator();
		
		for (int i = 0; i < this.nbOfGroups(); i++) {
			List<Competitor> list = new ArrayList<>();
			
			while (it.hasNext() && list.size() < stage_size) {
				list.add(it.next());
			}
			groups.add(list);
		}
		return groups;
	}
	
	
	/**
	 * Sorts elements of <code>list</code> by descending values - from the competitor with the most points 
	 * to the one with the least
	 * @param list list of elements to sort
	 * @return a collection of elements sorted by descending value 
	 */
	public static Map<Competitor, Integer> sortedCompetitors(List<Competitor> list) {
		Map<Competitor, Integer> cptors = new HashMap<>();
		//adding competitors to the map
		for(Competitor c : list) {
			cptors.put(c, c.getNbPoints());
		}
		
		return MapUtil.sortByDescendingValue(cptors);
		
	}
	
	/**
	 * Depending on the strategy selects in the list <code>competitors</code>, competitors with the most 
	 * number of victories
	 * @param competitors list of competitors to select
	 * @return list of all the competitors who have been selected
	 */
	public List<Competitor> bestOfGroupStage(List< List<Competitor> > competitors);

}
