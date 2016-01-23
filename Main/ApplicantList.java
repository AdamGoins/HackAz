package HackAz.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kalee on 1/23/2016.
 */
public class ApplicantList extends JFrame{ //hey
    private JPanel mainPanel;
    private JLabel backgroundLabel;
    private JTable applicantTable;
    private JLabel profilePictureLabel;


    public ApplicantList(){
        setContentPane(mainPanel);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        init();
    }

    private void init(){
        setupBackgrounds();



    }

    private void setupBackgrounds(){
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu settings = new JMenu("Settings");
        JMenu preferences = new JMenu("Preferences");
        JMenu help = new JMenu("Help");

        menuBar.add(file);
        menuBar.add(settings);
        menuBar.add(preferences);
        menuBar.add(help);

        backgroundLabel.setIcon(new ImageIcon(new ImageIcon("images/simplebluebackground.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));



    }


    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel"); // Sets Synthetica custom Look & Feel
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ApplicantList();

    }


}
