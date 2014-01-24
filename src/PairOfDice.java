/**
 * Represents a pair of dice.
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class PairOfDice {

    private Die die1;
    private Die die2;

    public PairOfDice() {
        die1 = new Die();
        die2 = new Die();

        // Make sure the first values of the dice is pseudo-random.
        die1.roll();
        die2.roll();
    }

    public void roll() {
        die1.roll();
        die2.roll();
    }

    public int getSum() {
        return getFirstDieValue() + getSecondDieValue();
    }

    public int getFirstDieValue() {
        return die1.getFaceValue();
    }

    // No reason to check the value since it is already done in the Die class.
    public void setFirstDieValue(int value) {
        die1.setFaceValue(value);
    }

    public int getSecondDieValue() {
        return die2.getFaceValue();
    }

    // No reason to check the value since it is already done in the Die class.
    public void setSecondDieValue(int value) {
        die2.setFaceValue(value);
    }
}
