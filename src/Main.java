import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Info", new InfoPanel());
        tp.addTab("Snake eyes", new SnakeEyesPanel());
        tp.addTab("High/low", new HiLoPanel());
        tp.addTab("Rock, paper, scissors", new RockScissorsPaperPanel());
        tp.addTab("Pig", new PigPanel());
        tp.addTab("Bandit", new OneArmedBanditPanel());

        frame.add(tp);

        frame.pack();
        frame.setMinimumSize(new Dimension(740, 480));
        frame.setSize(new Dimension(740, 480));
        frame.setVisible(true);
    }
}
