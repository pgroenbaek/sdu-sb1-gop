/**
 * Represents a pig game player.
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class PigPlayer {

    private int pointsTotal = 0;
    private int pointsInRound = 0;

    public PigPlayer() {

    }

    public int getTotalPoints() {
        return pointsTotal;
    }

    public void addPointsToTotal(int points) {
        this.pointsTotal += points;
    }

    public void resetTotalPoints() {
        this.pointsTotal = 0;
    }

    public int getPointsInRound() {
        return pointsInRound;
    }

    public void addPointsInRound(int points) {
        this.pointsInRound += points;
    }

    public void resetPointsInRound() {
        this.pointsInRound = 0;
    }

}
