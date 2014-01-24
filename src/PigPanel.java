import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class PigPanel extends JPanel {


    private PigPlayer computer = new PigPlayer();
    private PigPlayer user = new PigPlayer();
    private boolean isUserPlaying;

    private PairOfDice dice = new PairOfDice();

    private Timer waitTimer;
    private Timer rollTimer;
    private int rollTimerCount;

    private JButton userRollButton;
    private JButton userDoneButton;
    private JButton userResetButton;

    private ImageIcon[] diceIcons = {
        new ImageIcon("pics/dice/terning1.jpg"),
        new ImageIcon("pics/dice/terning2.jpg"),
        new ImageIcon("pics/dice/terning3.jpg"),
        new ImageIcon("pics/dice/terning4.jpg"),
        new ImageIcon("pics/dice/terning5.jpg"),
        new ImageIcon("pics/dice/terning6.jpg")
    };

    private JLabel userDie1;
    private JLabel userDie2;

    private JLabel computerDie1;
    private JLabel computerDie2;

    private JLabel userLabel;
    private JLabel totalPointsLabel;
    private JLabel pointsThisRoundLabel;
    private JLabel totalPointsTextLabel;
    private JLabel pointsThisRoundTextLabel;
    private JLabel computerLabel;
    private JLabel infoLabel;

    private JSeparator separator;

    private JPanel pointsPanel1;
    private JPanel pointsPanel2;
    private JPanel pointsPanel3;
    private JPanel pointsPanel4;

    private JPanel userDicePanel;
    private JPanel computerDicePanel;


    private JPanel userTopPanel;
    private JPanel userCenterPanel;
    private JPanel userBottomPanel;

    private JPanel midTopPanel;
    private JPanel midBottomPanel;
    private JPanel midCenterPanel;
    private JPanel midCenterLeftPanel;
    private JPanel midCenterRightPanel;

    private JPanel computerTopPanel;
    private JPanel computerCenterPanel;
    private JPanel computerBottomPanel;

    private JPanel userPanel;
    private JPanel midPanel;
    private JPanel computerPanel;

    public PigPanel() {

        userRollButton = new JButton("Roll dice");
        userDoneButton = new JButton("Done");
        userResetButton = new JButton("Reset");

        ButtonListener listener = new ButtonListener();
        userRollButton.addActionListener(listener);
        userDoneButton.addActionListener(listener);
        userResetButton.addActionListener(listener);

        userDie1 = new JLabel(diceIcons[5]);
        userDie1.setBorder(BorderFactory.createEtchedBorder());
        userDie2 = new JLabel(diceIcons[5]);
        userDie2.setBorder(BorderFactory.createEtchedBorder());
        computerDie1 = new JLabel(diceIcons[5]);
        computerDie1.setBorder(BorderFactory.createEtchedBorder());
        computerDie2 = new JLabel(diceIcons[5]);
        computerDie2.setBorder(BorderFactory.createEtchedBorder());

        userLabel = new JLabel("You");
        totalPointsLabel = new JLabel("0                                        0");
        pointsThisRoundLabel = new JLabel("0                                        0");
        totalPointsTextLabel = new JLabel("Total points:");
        pointsThisRoundTextLabel = new JLabel("Points this round:");
        computerLabel = new JLabel("Computer");
        infoLabel = new JLabel("Infotext goes here, herp derp.");

        waitTimer = new Timer(1500, new WaitTimerListener());
        rollTimer = new Timer(10, new RollTimerListener());

        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBorder(BorderFactory.createEtchedBorder());
        separator.setMaximumSize(new Dimension(separator.getPreferredSize().width, separator.getMaximumSize().height));

        pointsPanel1 = new JPanel();
        pointsPanel1.add(totalPointsTextLabel);
        pointsPanel2 = new JPanel();
        pointsPanel2.add(totalPointsLabel);
        pointsPanel3 = new JPanel();
        pointsPanel3.add(pointsThisRoundTextLabel);
        pointsPanel4 = new JPanel();
        pointsPanel4.add(pointsThisRoundLabel);

        userDicePanel = new JPanel();
        userDicePanel.add(userDie1);
        userDicePanel.add(userDie2);
        computerDicePanel = new JPanel();
        computerDicePanel.add(computerDie1);
        computerDicePanel.add(computerDie2);

        userTopPanel = new JPanel();
        userTopPanel.add(userLabel);
        userCenterPanel = new JPanel();
        userCenterPanel.setLayout(new BoxLayout(userCenterPanel, BoxLayout.Y_AXIS));
        userCenterPanel.add(Box.createVerticalGlue());
        userCenterPanel.add(userDicePanel);
        userCenterPanel.add(Box.createVerticalGlue());
        userBottomPanel = new JPanel();
        userBottomPanel.add(userRollButton);
        userBottomPanel.add(userDoneButton);
        userBottomPanel.add(userResetButton);

        userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.add(userTopPanel, BorderLayout.NORTH);
        userPanel.add(userBottomPanel, BorderLayout.SOUTH);
        userPanel.add(userCenterPanel, BorderLayout.CENTER);
        userPanel.setPreferredSize(new Dimension(350, 450));


        midCenterLeftPanel = new JPanel();
        midCenterRightPanel = new JPanel();

        midTopPanel = new JPanel();
        midTopPanel.setLayout(new GridLayout(0, 1, 0, 0));
        midTopPanel.add(pointsPanel1);
        midTopPanel.add(pointsPanel2);
        midTopPanel.add(new JPanel());
        midTopPanel.add(pointsPanel3);
        midTopPanel.add(pointsPanel4);
        midTopPanel.setBorder(BorderFactory.createEtchedBorder());
        midCenterPanel = new JPanel();
        midCenterPanel.setLayout(new BoxLayout(midCenterPanel, BoxLayout.X_AXIS));
        midCenterPanel.add(midCenterLeftPanel);
        midCenterPanel.add(separator);
        midCenterPanel.add(midCenterRightPanel);
        midBottomPanel = new JPanel();
        midBottomPanel.add(infoLabel);
        midBottomPanel.setBorder(BorderFactory.createEtchedBorder());

        midPanel = new JPanel();
        midPanel.setMaximumSize(new Dimension(200, separator.getMaximumSize().height));
        midPanel.setLayout(new BorderLayout());
        midPanel.add(midTopPanel, BorderLayout.NORTH);
        midPanel.add(midBottomPanel, BorderLayout.SOUTH);
        midPanel.add(midCenterPanel, BorderLayout.CENTER);
        midPanel.setPreferredSize(new Dimension(250, 450));


        computerTopPanel = new JPanel();
        computerTopPanel.add(computerLabel);
        computerCenterPanel = new JPanel();
        computerCenterPanel.setLayout(new BoxLayout(computerCenterPanel, BoxLayout.Y_AXIS));
        computerCenterPanel.add(Box.createVerticalGlue());
        computerCenterPanel.add(computerDicePanel);
        computerCenterPanel.add(Box.createVerticalGlue());
        computerBottomPanel = new JPanel();

        computerPanel = new JPanel();
        computerPanel.setLayout(new BorderLayout());
        computerPanel.add(computerTopPanel, BorderLayout.NORTH);
        computerPanel.add(computerBottomPanel, BorderLayout.SOUTH);
        computerPanel.add(computerCenterPanel, BorderLayout.CENTER);
        computerPanel.setPreferredSize(new Dimension(350, 450));


        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEtchedBorder());
        add(userPanel);
        add(midPanel);
        add(computerPanel);

        resetGame();
    }

    private void resetGame() {
        isUserPlaying = true;
        rollTimerCount = 0;
        infoLabel.setText("It's now your turn.");
        user.resetPointsInRound();
        user.resetTotalPoints();
        computer.resetPointsInRound();
        computer.resetTotalPoints();
        totalPointsLabel.setText(user.getTotalPoints() + "                                        " + computer.getTotalPoints());
        pointsThisRoundLabel.setText(user.getPointsInRound() + "                                        " + computer.getPointsInRound());
        midTopPanel.updateUI();
    }

    private void continueGame() {
        rollTimerCount = 0;
        infoLabel.setText("It's now the computers turn.");
    }

    private void rollDice() {
        dice.roll();

        if(isUserPlaying) {
            userDie1.setIcon(diceIcons[dice.getFirstDieValue() - 1]);
            userDie2.setIcon(diceIcons[dice.getSecondDieValue() - 1]);
        }
        else {
            computerDie1.setIcon(diceIcons[dice.getFirstDieValue() - 1]);
            computerDie2.setIcon(diceIcons[dice.getSecondDieValue() - 1]);
        }

    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == userRollButton) {
                rollTimerCount = 0;
                rollTimer.restart();


            }
            else if(event.getSource() == userResetButton) {
                resetGame();
            }
            else if(event.getSource() == userDoneButton) {
                continueGame();
                user.addPointsToTotal(user.getPointsInRound());
                user.resetPointsInRound();
                totalPointsLabel.setText(user.getTotalPoints() + "                                        " + computer.getTotalPoints());
                pointsThisRoundLabel.setText(user.getPointsInRound() + "                                        " + computer.getPointsInRound());
                userRollButton.setEnabled(false);
                userDoneButton.setEnabled(false);
                isUserPlaying = false;
                waitTimer.restart();
            }


        }
    }

    private class RollTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            rollDice();

            if(rollTimerCount > 10) {
                rollTimer.stop();

                if(isUserPlaying) {
                    if(dice.getFirstDieValue() == 1 && dice.getSecondDieValue() == 1) {
                        user.resetPointsInRound();
                        user.resetTotalPoints();
                        infoLabel.setText("You have lost your turn.");
                        userRollButton.setEnabled(false);
                        userDoneButton.setEnabled(false);
                        isUserPlaying = false;
                        waitTimer.restart();
                    }
                    else if(dice.getFirstDieValue() == 1 | dice.getSecondDieValue() == 1) {
                        user.resetPointsInRound();
                        infoLabel.setText("You have lost your turn.");
                        userRollButton.setEnabled(false);
                        userDoneButton.setEnabled(false);
                        isUserPlaying = false;
                        waitTimer.restart();
                    }
                    else {
                        user.addPointsInRound(dice.getSum());
                        infoLabel.setText("You now have " + user.getPointsInRound() + " points this round.");

                        if((user.getTotalPoints() + user.getPointsInRound()) >= 100) {
                            infoLabel.setText("Congratulations, you have won.");
                            totalPointsLabel.setText(user.getTotalPoints() + "                                        " + computer.getTotalPoints());
                            pointsThisRoundLabel.setText(user.getPointsInRound() + "                                        " + computer.getPointsInRound());
                            user.addPointsToTotal(user.getPointsInRound());
                            user.resetPointsInRound();
                            userRollButton.setEnabled(false);
                            userDoneButton.setEnabled(false);
                        }
                    }
                }
                else {
                    if(dice.getFirstDieValue() == 1 && dice.getSecondDieValue() == 1) {
                        computer.resetPointsInRound();
                        computer.resetTotalPoints();
                        infoLabel.setText("Computer has lost it's turn.");
                        userRollButton.setEnabled(true);
                        userDoneButton.setEnabled(true);
                        isUserPlaying = true;
                        waitTimer.stop();
                    }
                    else if(dice.getFirstDieValue() == 1 | dice.getSecondDieValue() == 1) {
                        computer.resetPointsInRound();
                        infoLabel.setText("Computer has lost it's turn.");
                        userRollButton.setEnabled(true);
                        userDoneButton.setEnabled(true);
                        isUserPlaying = true;
                        waitTimer.stop();
                    }
                    else {
                        computer.addPointsInRound(dice.getSum());
                        infoLabel.setText("Computer now has " + computer.getPointsInRound() + " points this round.");
                    }
                }

                totalPointsLabel.setText(user.getTotalPoints() + "                                        " + computer.getTotalPoints());
                pointsThisRoundLabel.setText(user.getPointsInRound() + "                                        " + computer.getPointsInRound());
            }

            rollTimerCount++;
        }
    }

    private class WaitTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if((computer.getTotalPoints() + computer.getPointsInRound()) >= 100) {
                computer.addPointsToTotal(computer.getPointsInRound());
                computer.resetPointsInRound();
                infoLabel.setText("Too bad, computer has won..");
                totalPointsLabel.setText(user.getTotalPoints() + "                                        " + computer.getTotalPoints());
                pointsThisRoundLabel.setText(user.getPointsInRound() + "                                        " + computer.getPointsInRound());
                waitTimer.stop();
            }
            else if(computer.getPointsInRound() >= 20) {
                computer.addPointsToTotal(computer.getPointsInRound());
                infoLabel.setText("Computer has received " + computer.getPointsInRound() + " this round.");
                computer.resetPointsInRound();
                totalPointsLabel.setText(user.getTotalPoints() + "                                        " + computer.getTotalPoints());
                pointsThisRoundLabel.setText(user.getPointsInRound() + "                                        " + computer.getPointsInRound());
                userRollButton.setEnabled(true);
                userDoneButton.setEnabled(true);
                isUserPlaying = true;
                waitTimer.stop();
            }
            else {
                rollTimerCount = 0;
                rollTimer.restart();
            }
        }
    }
}
