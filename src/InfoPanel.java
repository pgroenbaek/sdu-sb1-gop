import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class InfoPanel extends JPanel {

    private JLabel label;

    public InfoPanel() {

        label = new JLabel("Press a tab above to play a game.");


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEtchedBorder());
        add(label);
    }

}
