package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kalee on 1/23/2016.
 */
public class ApplicantProfile extends JFrame {

    private int id;
    private JPanel mainPanel;
    private JLabel backgroundLabel;
    private JLabel applicantPictureLabel;

    public ApplicantProfile(int id){
        this.id = id;
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        pack();

        init();
    }

    private void init(){
        backgroundLabel.setIcon(new ImageIcon(new ImageIcon("Images/blackbackground.png").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));

    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel"); // Sets Synthetica custom Look & Feel
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ApplicantProfile(101);

    }
}
