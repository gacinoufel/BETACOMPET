package competitor;

/**
 * Defines someone or a group of people represented by a name, number of points and 
 * the number of match played; these matches are played in a competition
 * @author  GACI Noufel
 * @version 1.0
 */

public class Competitor {

    private String name;
    private int nbPoints;
    private int nbMatchPlayed;

    /**
     * Creates an instance of competitor with given <code>name</code> and initializes
     * the number of points et the numbre of match played at 0
     * @param name name to be given to the competitor
     */
    public Competitor(String name) {
        this.name = name;
        this.nbPoints = 0;
        this.nbMatchPlayed = 0;

    }

    /**
     * Provides this competitor's name
     * @return the name given to this competitor
     */
    public String getName() {
        return this.name;
    }

    /**
     * Provides this competitor's number of points
     * @return this competitor's number of points
     */
    public int getNbPoints() {
        return this.nbPoints;
    }


    /**
     * Increases the number of points of a competitor by n
     * @param n the number of points to be added
     */
    public void addPoints(int n) {
        this.nbPoints+= n;
    }

    /**
	 * Provides the number of matches played by this competitor.
	 * @return this competitor's number of matches played
	 */
	  public int getNbMatchPlayed() {
		  return this.nbMatchPlayed;
	  }

    /**
	 * Increases by one the number of matches played by this competitor.
	 */
	public void addMatchPlayed() {
		this.nbMatchPlayed++;
	}


    /**
     * Indicates if this competitor and <em>o</em> have the same name and
     * number points by returning <code>true</code>, otherwise <code>false</code>
     * @param o object to be compared with
     * @return <code>true</code> if this competitor and the object got the same name and
     * number of points, otherwise <code>false</code>
     */
    public boolean equals(Object o) {
    	if (o instanceof Competitor) {
    		Competitor other = (Competitor) o;
    		return this.name.equals(other.name) && this.nbPoints == other.nbPoints;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * Returns a string that describes this competitor by providing
     * all its informations
     * @return this competitor's informations
     */
    public String toString() {
    	return "Competitor " + this.name + " has " + this.nbPoints + " points.";
    }




}
