package match.observer;

import match.Match;

public class MockListener implements MatchListener {
	
	//to make sure a bookmaker is notified
	public int notification = 0;
	
	public void matchResult(Match event) {
		this.notification++;
	}
	

}
