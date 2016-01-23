package CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kalee on 1/22/2016.
 */
public class BackgroundTextPane extends JTextPane {

    String imagePath;

    public BackgroundTextPane(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image icon = new ImageIcon(imagePath).getImage();
        try {
            g.drawImage(icon, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

    }
}
