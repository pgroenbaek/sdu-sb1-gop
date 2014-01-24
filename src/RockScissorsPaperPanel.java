import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class RockScissorsPaperPanel extends JPanel {

    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;

    private int wins;
    private int losses;
    private int ties;

    private int userSelection;
    private int computerSelection;

    private int timerCount;
    private Timer timer;
    private Random generator = new Random();

    private JButton userGoButton;
    private JButton userNextButton;
    private JButton userResetButton;

    private ImageIcon[] rockPaperScissorsIcons = {
        new ImageIcon("pics/rps/rock.jpg"),
        new ImageIcon("pics/rps/paper.jpg"),
        new ImageIcon("pics/rps/scissor.jpg")
    };

    private ImageIcon[] smallRockPaperScissorsIcons = {
        new ImageIcon("pics/rps/rock_small.jpg"),
        new ImageIcon("pics/rps/paper_small.jpg"),
        new ImageIcon("pics/rps/scissor_small.jpg")
    };

    private ImageIcon[] mirroredRockPaperScissorsIcons = {
        new ImageIcon("pics/rps/rock_mirrored.jpg"),
        new ImageIcon("pics/rps/paper_mirrored.jpg"),
        new ImageIcon("pics/rps/scissor_mirrored.jpg")
    };

    private String[] rockPaperScissorsStrings = {
        "Rock",
        "Paper",
        "Scissors"
    };

    private JLabel rockPaperScissorsLabel;
    private JLabel smallRockLabel;
    private JLabel smallPaperLabel;
    private JLabel smallScissorsLabel;
    private JLabel mirroredRockPaperScissorsLabel;
    private JLabel userTextLabel;
    private JLabel computerTextLabel;
    private JLabel unrevealedComputerTextLabel;

    private JRadioButton rockRadioButton;
    private JRadioButton paperRadioButton;
    private JRadioButton scissorsRadioButton;
    private ButtonGroup rockPaperScissors;

    private JLabel userLabel;
    private JLabel userWinsLabel;
    private JLabel userLossesLabel;
    private JLabel userTiesLabel;
    private JLabel computerLabel;
    private JLabel infoLabel;

    private JSeparator separator;

    private JPanel userSelectionRockPanel;
    private JPanel userSelectionPaperPanel;
    private JPanel userSelectionScissorsPanel;
    private JPanel userSelectionPanel;
    private JPanel userHasSelectedPanel;

    private JPanel computerUnrevealedPanel;
    private JPanel computerRevealedPanel;

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

    public RockScissorsPaperPanel() {

        userGoButton = new JButton("Go");
        userNextButton = new JButton("Next round");
        userResetButton = new JButton("Reset");

        ButtonListener listener = new ButtonListener();
        userGoButton.addActionListener(listener);
        userNextButton.addActionListener(listener);
        userResetButton.addActionListener(listener);

        rockPaperScissorsLabel = new JLabel();
        smallRockLabel = new JLabel(smallRockPaperScissorsIcons[0]);
        smallPaperLabel = new JLabel(smallRockPaperScissorsIcons[1]);
        smallScissorsLabel = new JLabel(smallRockPaperScissorsIcons[2]);
        mirroredRockPaperScissorsLabel = new JLabel();
        userTextLabel = new JLabel();
        computerTextLabel = new JLabel();
        unrevealedComputerTextLabel = new JLabel("?");
        unrevealedComputerTextLabel.setFont(new Font(unrevealedComputerTextLabel.getFont().getFontName(), unrevealedComputerTextLabel.getFont().getStyle(), 72));

        timer = new Timer(500, new TimerListener());

        rockRadioButton = new JRadioButton("Rock");
        paperRadioButton = new JRadioButton("Paper");
        scissorsRadioButton = new JRadioButton("Scissors");
        rockPaperScissors = new ButtonGroup();
        rockPaperScissors.add(rockRadioButton);
        rockPaperScissors.add(paperRadioButton);
        rockPaperScissors.add(scissorsRadioButton);

        userLabel = new JLabel("You");
        userWinsLabel = new JLabel("Wins: 0  ");
        userLossesLabel = new JLabel("Losses: 0  ");
        userTiesLabel = new JLabel("Ties: 0");
        computerLabel = new JLabel("Computer");
        infoLabel = new JLabel("Make your choice and press 'Go'.");

        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBorder(BorderFactory.createEtchedBorder());
        separator.setMaximumSize(new Dimension(separator.getPreferredSize().width, separator.getMaximumSize().height));

        userSelectionRockPanel = new JPanel();
        userSelectionRockPanel.add(smallRockLabel);
        userSelectionRockPanel.add(rockRadioButton);
        userSelectionPaperPanel = new JPanel();
        userSelectionPaperPanel.add(smallPaperLabel);
        userSelectionPaperPanel.add(paperRadioButton);
        userSelectionScissorsPanel = new JPanel();
        userSelectionScissorsPanel.add(smallScissorsLabel);
        userSelectionScissorsPanel.add(scissorsRadioButton);

        userSelectionPanel = new JPanel();
        userSelectionPanel.setLayout(new GridLayout(0, 1, 0, 0));
        userSelectionPanel.add(userSelectionRockPanel);
        userSelectionPanel.add(userSelectionPaperPanel);
        userSelectionPanel.add(userSelectionScissorsPanel);

        userHasSelectedPanel = new JPanel();
        userHasSelectedPanel.add(rockPaperScissorsLabel);
        userHasSelectedPanel.add(userTextLabel);

        computerUnrevealedPanel = new JPanel();
        computerUnrevealedPanel.add(unrevealedComputerTextLabel);

        computerRevealedPanel = new JPanel();
        computerRevealedPanel.add(computerTextLabel);
        computerRevealedPanel.add(mirroredRockPaperScissorsLabel);


        userTopPanel = new JPanel();
        userTopPanel.add(userLabel);
        userCenterPanel = new JPanel();
        userCenterPanel.setLayout(new BoxLayout(userCenterPanel, BoxLayout.Y_AXIS));
        userCenterPanel.add(Box.createVerticalGlue());
        userCenterPanel.add(userHasSelectedPanel);
        userCenterPanel.add(Box.createVerticalGlue());
        userBottomPanel = new JPanel();
        userBottomPanel.add(userGoButton);
        userBottomPanel.add(userNextButton);
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
        midTopPanel.add(userWinsLabel);
        midTopPanel.add(userLossesLabel);
        midTopPanel.add(userTiesLabel);
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
        computerCenterPanel.add(computerRevealedPanel);
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
        timerCount = 3;
        wins = 0;
        losses = 0;
        ties = 0;
        userWinsLabel.setText("Wins: 0  ");
        userLossesLabel.setText("Losses: 0  ");
        userTiesLabel.setText("Ties: 0");
        infoLabel.setText("Make your choice and press 'Go'.");
        userCenterPanel.remove(userHasSelectedPanel);
        userCenterPanel.add(userSelectionPanel, 1);
        unrevealedComputerTextLabel.setText("?");
        computerCenterPanel.remove(computerRevealedPanel);
        computerCenterPanel.add(computerUnrevealedPanel, 1);
        rockPaperScissors.clearSelection();
        userNextButton.setEnabled(false);
        userGoButton.setEnabled(true);
    }

    private void nextRound() {
        timerCount = 3;
        userCenterPanel.remove(userHasSelectedPanel);
        userCenterPanel.add(userSelectionPanel, 1);
        unrevealedComputerTextLabel.setText("?");
        infoLabel.setText("Make your choice and press 'Go'.");
        computerCenterPanel.remove(computerRevealedPanel);
        computerCenterPanel.add(computerUnrevealedPanel, 1);
        rockPaperScissors.clearSelection();
        userNextButton.setEnabled(false);
        userGoButton.setEnabled(true);
    }

    private void performUserReveal(int handType) {
        userSelection = handType;
        userGoButton.setEnabled(false);
        userResetButton.setEnabled(false);
        userNextButton.setEnabled(false);
        infoLabel.setText("You have selected " + rockPaperScissorsStrings[userSelection] + ".");
        rockPaperScissorsLabel.setIcon(rockPaperScissorsIcons[userSelection]);
        userTextLabel.setText(rockPaperScissorsStrings[userSelection]);
        computerSelection = generator.nextInt(3);
        userCenterPanel.remove(userSelectionPanel);
        userCenterPanel.add(userHasSelectedPanel, 1);
        timer.start();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == userGoButton) {
                if(rockRadioButton.isSelected()) {
                    performUserReveal(ROCK);
                }
                else if(paperRadioButton.isSelected()) {
                    performUserReveal(PAPER);
                }
                else if(scissorsRadioButton.isSelected()) {
                    performUserReveal(SCISSORS);
                }
                else {
                    infoLabel.setText("You have to select first!");
                }
            }
            else if(event.getSource() == userNextButton) {
                nextRound();
                computerCenterPanel.updateUI();
            }
            else if(event.getSource() == userResetButton) {
                resetGame();
                computerCenterPanel.updateUI();
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(timerCount > 0) {
                unrevealedComputerTextLabel.setText(timerCount + "..");
            }
            else {
                computerTextLabel.setText(rockPaperScissorsStrings[computerSelection]);
                mirroredRockPaperScissorsLabel.setIcon(mirroredRockPaperScissorsIcons[computerSelection]);
                computerCenterPanel.remove(computerUnrevealedPanel);
                computerCenterPanel.add(computerRevealedPanel, 1);
                computerCenterPanel.updateUI();
                userResetButton.setEnabled(true);
                userNextButton.setEnabled(true);

                if(userSelection == SCISSORS && computerSelection == ROCK) {
                    infoLabel.setText("You have lost.");
                    losses++;
                }
                else if(userSelection == ROCK && computerSelection == SCISSORS) {
                    infoLabel.setText("Congratulations, you have won!");
                    wins++;
                }
                else if(userSelection == ROCK && computerSelection == PAPER) {
                    infoLabel.setText("You have lost.");
                    losses++;
                }
                else if(userSelection == PAPER && computerSelection == ROCK) {
                    infoLabel.setText("Congratulations, you have won!");
                    wins++;
                }
                else if(userSelection == PAPER && computerSelection == SCISSORS) {
                    infoLabel.setText("You have lost.");
                    losses++;
                }
                else if(userSelection == SCISSORS && computerSelection == PAPER) {
                    infoLabel.setText("Congratulations, you have won!");
                    wins++;
                }
                else {
                    infoLabel.setText("It's a tie!");
                    ties++;
                }

                userWinsLabel.setText("Wins: " + wins + "  ");
                userLossesLabel.setText("Losses: " + losses + "  ");
                userTiesLabel.setText("Ties: " + ties + "");

                timer.stop();
            }

            timerCount--;
        }
    }
}
