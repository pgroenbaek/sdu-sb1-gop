import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Peter - peand13@student.sdu.dk
 */
public class ImageRowPanel extends JPanel {

    ImageIcon[] imageIcons = IconFactory.getSmallFruitIcons();
    ImageIcon emptyIcon = new ImageIcon("pics/other/empty.jpg");

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel prizeLabel;

    public ImageRowPanel(int icon1, int icon2, int icon3, int payout) {

        label1 = new JLabel(imageIcons[icon1]);
        label2 = new JLabel(imageIcons[icon2]);
        label3 = new JLabel(imageIcons[icon3]);
        prizeLabel = new JLabel(payout + "$");

        add(label1);
        add(label2);
        add(label3);
        add(prizeLabel);
    }

    public ImageRowPanel(int icon1, int icon2, int payout) {

        label1 = new JLabel(imageIcons[icon1]);
        label2 = new JLabel(imageIcons[icon2]);
        label3 = new JLabel(emptyIcon);
        prizeLabel = new JLabel(payout + "$");

        add(label1);
        add(label2);
        add(label3);
        add(prizeLabel);
    }

    public ImageRowPanel(int icon1, int payout) {

        label1 = new JLabel(imageIcons[icon1]);
        label2 = new JLabel(emptyIcon);
        label3 = new JLabel(emptyIcon);
        prizeLabel = new JLabel(" " + payout + "$");

        add(label1);
        add(label2);
        add(label3);
        add(prizeLabel);
    }

    public void setGlowing(boolean b) {
        if(b) {
            setBackground(Color.YELLOW);
        }
        else {
            setBackground(null);
        }
    }
}
