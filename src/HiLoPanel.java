import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class HiLoPanel extends JPanel {

    private int attempts;
    private int numberGuessed;
    private int numberToGuess;

    private Random generator = new Random();

    private JLabel guessesLabel;
    private JLabel currentGuessLabel;
    private JLabel infoLabel;
    private JLabel inputLabel;
    private JLabel higherLowerLabel;

    private JButton guessButton;
    private JButton resetButton;
    private JTextField textField;
    private JSeparator separator;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel centerTextPanel1;
    private JPanel centerTextPanel2;
    private JPanel bottomPanel;

    public HiLoPanel() {

        guessesLabel = new JLabel("Number of attempts: 0  ");
        currentGuessLabel = new JLabel("Current guess: --");
        infoLabel = new JLabel("Guess a number from 1 to 100.");
        inputLabel = new JLabel("Enter a guess:");
        higherLowerLabel = new JLabel();
        higherLowerLabel.setFont(new Font(higherLowerLabel.getFont().getFontName(), higherLowerLabel.getFont().getStyle(), 32));

        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");

        ButtonListener listener = new ButtonListener();
        resetButton.addActionListener(listener);
        guessButton.addActionListener(listener);

        textField = new JTextField(10);
        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBorder(BorderFactory.createEtchedBorder());
        separator.setPreferredSize(new Dimension(separator.getPreferredSize().width, 25));

        centerTextPanel1 = new JPanel();
        centerTextPanel1.add(infoLabel);
        centerTextPanel2 = new JPanel();
        centerTextPanel2.add(higherLowerLabel);

        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.add(guessesLabel);
        topPanel.add(currentGuessLabel);
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(centerTextPanel1);
        centerPanel.add(centerTextPanel2);
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        bottomPanel.add(inputLabel);
        bottomPanel.add(textField);
        bottomPanel.add(separator);
        bottomPanel.add(guessButton);
        bottomPanel.add(resetButton);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        resetGame();
    }

    private void resetGame() {
        attempts = 0;
        numberGuessed = 0;
        guessButton.setEnabled(true);
        numberToGuess = generator.nextInt(100) + 1;
        higherLowerLabel.setText("");
        guessesLabel.setText("Number of attempts: 0  ");
        currentGuessLabel.setText("Current guess: --");
        infoLabel.setText("Guess a number from 1 to 100.");
    }

    private void makeGuess() {
        attempts++;
        guessesLabel.setText("Number of attempts: " + attempts + "  ");
        currentGuessLabel.setText("Current guess: " + numberGuessed);

        if(numberToGuess == numberGuessed) {
            guessButton.setEnabled(false);
            higherLowerLabel.setText("Congratulations, you guessed the number!");
        }
        else if(numberToGuess > numberGuessed) {
            higherLowerLabel.setText("Your guess is too low");
        }
        else if(numberToGuess < numberGuessed) {
            higherLowerLabel.setText("Your guess is too high");
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == guessButton) {
                try {
                    numberGuessed = Integer.parseInt(textField.getText());

                    if(numberGuessed > 0 && numberGuessed < 101) {
                        makeGuess();
                    }
                    else {
                        infoLabel.setText("You have to enter a number between 1 and 100!");
                    }
                }
                catch(NumberFormatException e) {
                    infoLabel.setText("You have to enter a number between 1 and 100!");
                }
            }
            else if(event.getSource() == resetButton) {
                resetGame();
            }
        }
    }
}
