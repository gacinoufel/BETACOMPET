package competition.strategy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import competitor.Competitor;



public abstract class SelectionStrategyTest {
	
	
	protected SelectionStrategy strategy;
	protected abstract SelectionStrategy createStrategy();
	protected List<Competitor> list;
	

	@BeforeEach
	public void init()  {
		list = new ArrayList<>();
		for(int i = 0; i < 12; i++) {
			list.add(new Competitor("Team"+(i+1)));
		}
		strategy = this.createStrategy();
	}

	@Test
	public void testCreateGroupStage() {
		List< List<Competitor>> groups = strategy.createGroupStage(list);
		// to ensure that the expected number of groups has been created
		assertEquals(groups.size(), strategy.nbOfGroups());
		
	}
	
	@Test
	public void testBestOfGroupStage() {
		MockStrategy stgy = new MockStrategy();
		
		List< List<Competitor>> groups = stgy.createGroupStage(list);
		List<Competitor> selected = stgy.bestOfGroupStage(groups);
		
		int nbGroups = stgy.nbOfGroups();
		int nbSelect = stgy.nbToSelect();
		//to ensure that the accurate number has been selected
		assertEquals(selected.size(), nbGroups * nbSelect);
		
	}

}
