import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class SnakeEyesPanel extends JPanel {

    private int rolls = 500;
    private int rollsDone;
    private int count = 0;

    private PairOfDice dice = new PairOfDice();

    private Timer timer;

    private ImageIcon[] diceIcons = {
        new ImageIcon("pics/dice/terning1.jpg"),
        new ImageIcon("pics/dice/terning2.jpg"),
        new ImageIcon("pics/dice/terning3.jpg"),
        new ImageIcon("pics/dice/terning4.jpg"),
        new ImageIcon("pics/dice/terning5.jpg"),
        new ImageIcon("pics/dice/terning6.jpg")
    };

    private JLabel die1;
    private JLabel die2;

    private JLabel rollsLabel;
    private JLabel snakeEyesLabel;
    private JLabel ratioLabel;
    private JLabel infoLabel;
    private JLabel inputLabel;

    private JButton rollButton;
    private JButton resetButton;
    private JTextField textField;
    private JSeparator separator;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel centerTextPanel1;
    private JPanel centerTextPanel2;
    private JPanel bottomPanel;

    public SnakeEyesPanel() {

        die1 = new JLabel(diceIcons[0]);
        die1.setBorder(BorderFactory.createEtchedBorder());
        die2 = new JLabel(diceIcons[0]);
        die2.setBorder(BorderFactory.createEtchedBorder());

        rollsLabel = new JLabel("Number of rolls: 0  ");
        snakeEyesLabel = new JLabel("Number of snake eyes: 0  ");
        ratioLabel = new JLabel("Ratio: 0.0%");
        infoLabel = new JLabel("Press 'Roll dice' to count the number of snake eyes you get.");
        inputLabel = new JLabel("Enter the number of rolls: ");

        rollButton = new JButton("Roll dice");
        resetButton = new JButton("Reset");

        ButtonListener listener = new ButtonListener();
        resetButton.addActionListener(listener);
        rollButton.addActionListener(listener);

        timer = new Timer(5, new TimerListener());
        textField = new JTextField(10);
        textField.setText(Integer.toString(rolls));
        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBorder(BorderFactory.createEtchedBorder());
        separator.setPreferredSize(new Dimension(separator.getPreferredSize().width, 25));

        centerTextPanel1 = new JPanel();
        centerTextPanel1.add(infoLabel);
        centerTextPanel2 = new JPanel();
        centerTextPanel2.add(die1);
        centerTextPanel2.add(die2);

        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.add(rollsLabel);
        topPanel.add(snakeEyesLabel);
        topPanel.add(ratioLabel);
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(centerTextPanel1);
        centerPanel.add(centerTextPanel2);
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        bottomPanel.add(inputLabel);
        bottomPanel.add(textField);
        bottomPanel.add(separator);
        bottomPanel.add(rollButton);
        bottomPanel.add(resetButton);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        resetGame();
    }

    private void resetGame() {
        count = 0;
        rolls = 0;
        rollsDone = 0;
        rollButton.setEnabled(true);
        resetButton.setEnabled(false);
        textField.setEnabled(true);
        rollsLabel.setText("Number of rolls: " + rolls + "  ");
        snakeEyesLabel.setText("Number of snake eyes: " + count + "  ");
        ratioLabel.setText("Ratio: 0.0%");
    }

    private void performRolls(int rollAmount) {

       for(int roll = 1; roll <= rollAmount; roll++) {
           dice.roll();
           rollsDone++;

           if(dice.getFirstDieValue() == 1 && dice.getSecondDieValue() == 1) {
               count++;
           }
       }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == rollButton) {
                try {
                    rolls = Integer.parseInt(textField.getText());

                    if(rolls > 0) {
                        textField.setEnabled(false);
                        rollButton.setEnabled(false);
                        resetButton.setEnabled(false);
                        timer.start();
                        infoLabel.setText("Press 'Roll dice' to count the number of snake eyes you get.");
                    }
                    else {
                        infoLabel.setText("You have to enter a positive number!");
                    }
                }
                catch(NumberFormatException e) {
                    infoLabel.setText("You have to enter a number!");
                }
            }
            else if(event.getSource() == resetButton) {
                resetGame();
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if((rollsDone > (rolls - 10))) {
                performRolls(rolls - rollsDone);
            }
            else if(rollsDone <= (rolls - 10)) {
                performRolls(10);
            }

            rollsLabel.setText("Number of rolls: " + rollsDone + "  ");
            snakeEyesLabel.setText("Number of snake eyes: " + count + "  ");
            ratioLabel.setText("Ratio: " + (Math.round(((float)count / rollsDone)*10000.0)/100.0) + "%");

            die1.setIcon(diceIcons[dice.getFirstDieValue() - 1]);
            die2.setIcon(diceIcons[dice.getSecondDieValue() - 1]);

            if(rollsDone == rolls) {
                resetButton.setEnabled(true);
                timer.stop();
            }
        }
    }
}
