import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class SpinnerPanel extends JPanel {

    private int currentImageIcon = 0;
    private ImageIcon[] imageIcons = IconFactory.getFruitIcons();

    private JButton stopButton;
    private JLabel imageLabel;
    private JPanel imagePanel;

    private Timer timer;

    public SpinnerPanel(int spinRate) {

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        imageLabel = new JLabel(imageIcons[currentImageIcon]);

        ActionListener buttonListener = new ButtonListener();
        stopButton.addActionListener(buttonListener);

        timer = new Timer(spinRate, new TimerListener());

        imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.add(imageLabel);
        imagePanel.setPreferredSize(new Dimension(120, 125));

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        add(imagePanel, BorderLayout.CENTER);
        add(stopButton, BorderLayout.SOUTH);
    }

    public void startSpinning() {
        timer.start();
        stopButton.setEnabled(true);
    }

    public void stopSpinning() {
        timer.stop();
    }

    public boolean isSpinning() {
        return timer.isRunning();
    }

    public int getImageId() {
        return currentImageIcon;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == stopButton) {
                stopSpinning();
                stopButton.setEnabled(false);
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(currentImageIcon >= imageIcons.length - 1) {
                currentImageIcon = 0;
            }
            else {
                currentImageIcon++;
            }

            imageLabel.setIcon(imageIcons[currentImageIcon]);
        }
    }
}
