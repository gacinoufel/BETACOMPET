package competition.strategy;


import java.util.ArrayList;
import java.util.List;

import competitor.Competitor;

public class MockStrategy implements SelectionStrategy {

	
	@Override
	public int nbToSelect() {
		return 1;
		
	}

	
	@Override
	public int nbOfGroups() {
		return 8;
		
	}

	
	@Override
	public List<Competitor> bestOfGroupStage(List< List<Competitor> > competitors) {
		List<Competitor> res = new ArrayList<>();
		for (List<Competitor> gs : competitors) {
			res.add(gs.get(0)); // on récupère le premier de chaque groupe
		}
		return res;
		
	}
	
}
