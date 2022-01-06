package competition.strategy;


public class TopTwoStrategyTest extends SelectionStrategyTest {


	@Override
	protected SelectionStrategy createStrategy() {
		return new TopTwoStrategy();
		
	}

}
