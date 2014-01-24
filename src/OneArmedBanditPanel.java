import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class OneArmedBanditPanel extends JPanel {

    private static final int MONEY_DECREMENT_PER_ROLL = 5;
    private int money = 100;

    private JButton startButton;
    private JButton moneyButton;

    private Timer spinCheckTimer;

    private JLabel moneyLabel;

    private JSeparator separator;

    private SpinnerPanel spinnerPanel1;
    private SpinnerPanel spinnerPanel2;
    private SpinnerPanel spinnerPanel3;

    private ImageRowPanel[] imageRowPanels = {
        new ImageRowPanel(9, 9, 9, 500),
        new ImageRowPanel(8, 8, 8, 250),
        new ImageRowPanel(7, 7, 7, 150),
        new ImageRowPanel(6, 6, 6, 100),
        new ImageRowPanel(5, 5, 5, 75),
        new ImageRowPanel(4, 4, 4, 60),
        new ImageRowPanel(3, 3, 3, 45),
        new ImageRowPanel(1, 1, 1, 30),
        new ImageRowPanel(0, 0, 0, 20),
        new ImageRowPanel(2, 2, 2, 15),
        new ImageRowPanel(2, 2, 10),
        new ImageRowPanel(2, 5)
    };
    private JPanel payoutBoxPanel;

    private JPanel centerLeftPanel;
    private JPanel centerRightPanel;
    private JPanel centerRightInnerPanel;

    private JPanel centerPanel;
    private JPanel bottomPanel;

    public OneArmedBanditPanel() {
        startButton = new JButton("Start");
        moneyButton = new JButton("Insert more money");

        ActionListener buttonlistener = new ButtonListener();
        startButton.addActionListener(buttonlistener);
        moneyButton.addActionListener(buttonlistener);

        spinCheckTimer = new Timer(100, new TimerListener());

        moneyLabel = new JLabel("Money: 100$");
        moneyLabel.setFont(new Font(moneyLabel.getFont().getFontName(), moneyLabel.getFont().getStyle(), 36));
        moneyLabel.setAlignmentX(CENTER_ALIGNMENT);

        separator = new JSeparator();
        separator.setBorder(BorderFactory.createEtchedBorder());
        separator.setMaximumSize(new Dimension(separator.getPreferredSize().width, separator.getMaximumSize().height));


        payoutBoxPanel = new JPanel();
        payoutBoxPanel.setBorder(BorderFactory.createEtchedBorder());
        payoutBoxPanel.setLayout(new BoxLayout(payoutBoxPanel, BoxLayout.Y_AXIS));
        for(JPanel panel : imageRowPanels) {
            payoutBoxPanel.add(panel);
        }

        spinnerPanel1 = new SpinnerPanel(20);
        spinnerPanel2 = new SpinnerPanel(45);
        spinnerPanel3 = new SpinnerPanel(75);

        centerRightInnerPanel = new JPanel();
        centerRightInnerPanel.add(spinnerPanel1);
        centerRightInnerPanel.add(spinnerPanel2);
        centerRightInnerPanel.add(spinnerPanel3);

        centerLeftPanel = new JPanel();
        centerLeftPanel.setPreferredSize(new Dimension(118, 300));
        centerLeftPanel.add(payoutBoxPanel);
        centerRightPanel = new JPanel();
        centerRightPanel.setLayout(new BoxLayout(centerRightPanel, BoxLayout.Y_AXIS));
        centerRightPanel.add(Box.createVerticalGlue());
        centerRightPanel.add(moneyLabel);
        centerRightPanel.add(Box.createVerticalGlue());
        centerRightPanel.add(centerRightInnerPanel);
        centerRightPanel.add(Box.createVerticalGlue());

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerLeftPanel, BorderLayout.WEST);
        centerPanel.add(centerRightPanel, BorderLayout.CENTER);
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        bottomPanel.add(startButton);
        bottomPanel.add(moneyButton);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        moneyButton.setEnabled(false);
    }

    private void setMoney(int money) {
        this.money = money;
        moneyLabel.setText("Money: " + this.money + "$");
    }

    private void addMoney(int money) {
        this.money += money;
        moneyLabel.setText("Money: " + this.money + "$");
    }

    private void subtractMoney(int money) {
        this.money -= money;
        moneyLabel.setText("Money: " + this.money + "$");
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == startButton) {
                subtractMoney(MONEY_DECREMENT_PER_ROLL);

                startButton.setEnabled(false);

                spinnerPanel1.startSpinning();
                spinnerPanel2.startSpinning();
                spinnerPanel3.startSpinning();
                spinCheckTimer.start();
            }
            else if(event.getSource() == moneyButton) {
                setMoney(100);

                moneyButton.setEnabled(false);
                startButton.setEnabled(true);
            }

            for(ImageRowPanel panel : imageRowPanels) {
                panel.setGlowing(false);
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(!spinnerPanel1.isSpinning() && !spinnerPanel2.isSpinning() && !spinnerPanel3.isSpinning()) {

                int firstIcon = spinnerPanel1.getImageId();
                int secondIcon = spinnerPanel2.getImageId();
                int thirdIcon = spinnerPanel3.getImageId();

                if(firstIcon == 9 && secondIcon == 9 && thirdIcon == 9) {
                    addMoney(500);
                    imageRowPanels[0].setGlowing(true);
                }
                else if(firstIcon == 8 && secondIcon == 8 && thirdIcon == 8) {
                    addMoney(250);
                    imageRowPanels[1].setGlowing(true);
                }
                else if(firstIcon == 7 && secondIcon == 7 && thirdIcon == 7) {
                    addMoney(150);
                    imageRowPanels[2].setGlowing(true);
                }
                else if(firstIcon == 6 && secondIcon == 6 && thirdIcon == 6) {
                    addMoney(100);
                    imageRowPanels[3].setGlowing(true);
                }
                else if(firstIcon == 5 && secondIcon == 5 && thirdIcon == 5) {
                    addMoney(75);
                    imageRowPanels[4].setGlowing(true);
                }
                else if(firstIcon == 4 && secondIcon == 4 && thirdIcon == 4) {
                    addMoney(60);
                    imageRowPanels[5].setGlowing(true);
                }
                else if(firstIcon == 3 && secondIcon == 3 && thirdIcon == 3) {
                    addMoney(45);
                    imageRowPanels[6].setGlowing(true);
                }
                else if(firstIcon == 1 && secondIcon == 1 && thirdIcon == 1) {
                    addMoney(30);
                    imageRowPanels[7].setGlowing(true);
                }
                else if(firstIcon == 0 && secondIcon == 0 && thirdIcon == 0) {
                    addMoney(20);
                    imageRowPanels[8].setGlowing(true);
                }
                else if(firstIcon == 2 && secondIcon == 2 && thirdIcon == 2) {
                    addMoney(15);
                    imageRowPanels[9].setGlowing(true);
                }
                else if(firstIcon == 2 && secondIcon == 2) {
                    addMoney(10);
                    imageRowPanels[10].setGlowing(true);
                }
                else if(firstIcon == 2) {
                    addMoney(5);
                    imageRowPanels[11].setGlowing(true);
                }

                spinCheckTimer.stop();
                if(money <= 0) {
                    moneyButton.setEnabled(true);
                }
                else {
                    startButton.setEnabled(true);
                }
            }
        }
    }


}
