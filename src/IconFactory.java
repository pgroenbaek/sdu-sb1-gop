import java.io.File;
import java.io.FilenameFilter;
import javax.swing.ImageIcon;

/**
 *
 */
public class IconFactory {
    private static ImageIcon[] fruitImages = null;
    private static ImageIcon[] smallFruitImages = null;

    public static ImageIcon[] getFruitIcons() {
        if (fruitImages == null) {
            File picDir = new File("pics/fruit");
            String[] picFiles = picDir.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jpg");
                }
            });
            fruitImages = new ImageIcon[picFiles.length];

            for (int i = 0; i < picFiles.length; i++) {
                fruitImages[i] = new ImageIcon(picDir +"/" + picFiles[i]);
            }
        }
        return fruitImages;
    }

    public static ImageIcon[] getSmallFruitIcons() {
        if (smallFruitImages == null) {
            File picDir = new File("pics/smallfruit");
            String[] picFiles = picDir.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jpg");
                }
            });
            smallFruitImages = new ImageIcon[picFiles.length];

            for (int i = 0; i < picFiles.length; i++) {
                smallFruitImages[i] = new ImageIcon(picDir +"/" + picFiles[i]);
            }
        }
        return smallFruitImages;
    }
}
