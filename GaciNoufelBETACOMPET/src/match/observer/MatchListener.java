package match.observer;

import match.*;

/**
 * Interface to enable listeners to be notified any time an event (in this case a match) happens
 * @author GACI Noufel 
 * @version 1.0
 */

public interface MatchListener extends java.util.EventListener {

    /**
     * Notify this listener on the event that has just happened and let him broadcast
     * all the required information 
     * @param event event containing required informations
     */
    public void matchResult(Match event);

}
