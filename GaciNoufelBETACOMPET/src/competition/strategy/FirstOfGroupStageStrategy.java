package competition.strategy;

import java.util.*;
import competitor.Competitor;

/**
 * Strategy where <code>4</code> groups are created and only <code>the first</code> of each group are selected 
 * @author GACI Noufel
 * @version 1.0
 */

public class FirstOfGroupStageStrategy implements SelectionStrategy {
	

	@Override
	public List<Competitor> bestOfGroupStage(List< List<Competitor> > competitors) {
		List<Competitor> nextStage = new ArrayList<>();
		
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
			}
		}
		
		return nextStage;
	}

	@Override
	public int nbToSelect() {
		return 1;
	}

	@Override
	public int nbOfGroups() {
		return 4;
	}




}
