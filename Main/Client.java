package Main;

import CustomComponents.BackgroundTextPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kalee on 1/22/2016.
 */
public class Client extends JFrame implements Runnable {

    private JPanel mainPanel;
    private JButton connectButton;
    private JLabel chatBackgroundLabel;
    private JTextField chatLine;
    private JButton sendButton;
    private JLabel chattingWithLabel;
    private JLabel remoteInfoLabel;
    private JLabel postalCodeLabel;
    private JLabel remoteDisplayNameLabel;
    private JLabel remoteCountryLabel;
    private JLabel remoteCityLabel;
    private JLabel remotePostalCodeLabel;
    private JLabel cityLabel;
    private JLabel countryLabel;
    private JTextPane chatWindow;
    private JButton disconnectButton;
    private JButton nextButton;
    private JLabel buttonLabel;

    private Socket connection;
    private BufferedReader input;
    private PrintWriter output;

    private String remoteIP;
    private int remotePort;

    private Boolean isConnected = false;

    private Box chatBox = Box.createVerticalBox();

    private Color receivedBackgroundColor = Color.BLACK;
    private Color receivedForegroundColor = Color.CYAN;
    private Color sentBackgroundColor = Color.WHITE;
    private Color sentForegroundColor = Color.ORANGE;


    public Client(String remoteIP, int remotePort){

        this.remoteIP = remoteIP;
        this.remotePort = remotePort;

        setContentPane(mainPanel);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        init();
    }

    private void init(){
        chatWindow.insertComponent(chatBox);
        createBackgrounds();
        createListeners();
        chatBox.setPreferredSize(chatWindow.getPreferredSize());
        chatBox.setMinimumSize(chatWindow.getPreferredSize());
        chatBox.setMaximumSize(chatWindow.getMaximumSize());





    }

    private void createBackgrounds(){
        remoteInfoLabel.setIcon(new ImageIcon(new ImageIcon("images/blackbackground.png").getImage().getScaledInstance(remoteInfoLabel.getWidth(), remoteInfoLabel.getHeight(), Image.SCALE_SMOOTH)));
        buttonLabel.setIcon(new ImageIcon(new ImageIcon("images/blackborderedbackground.png").getImage().getScaledInstance(remoteInfoLabel.getWidth(), remoteInfoLabel.getHeight(), Image.SCALE_SMOOTH)));

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem newInstance = new JMenuItem("New");
        newInstance.addActionListener(ActionEvent ->{
            int response = JOptionPane.showConfirmDialog(this, "Would you like to start a new instance of the Messenger?", "New Instance", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                new Client(null, 0);
            }
        });
        file.add(newInstance);

        JMenuItem saveLog = new JMenuItem("Save log");
        saveLog.addActionListener(ActionEvent ->{
            JFileChooser fileChooser = new JFileChooser();                    // This is the Popup Window that displays your computer files/folder in a nice little gui to allow easy selection
            fileChooser.setDialogTitle("Save Log");                           // Titlebar for the popup.
            int retrival = fileChooser.showDialog(this, "Select");            // "This" is what the popup is set relative to. This case being the JFrame. "Select" basically being the name of the "confirm" button.
            if(retrival == JFileChooser.APPROVE_OPTION){                      // If the button the enduser clicks is the approve button (The button I made say "select"), it'll change directories with the below code.

                String path = fileChooser.getSelectedFile().toString();       // Gets the designated file.
            }
        });

        file.add(saveLog);
        JMenu settings = new JMenu("Settings");
        JMenu preferences = new JMenu("Preferences");

        menuBar.add(settings);
        menuBar.add(preferences);

        setJMenuBar(menuBar);



    }

    private void createListeners(){          // Creates the actionlisteners for all components that require it. Including all buttons, chatline, etc.

        ActionListener sendListener = e -> { // Creates the actionlistener that will send the text to the server, clears the chatline of text and plays a sound with the respective "MS (Message Sent)" tag to allow the correct sound to be played if sound is enabled.
            if(!chatLine.getText().equals("")) {
                String message = chatLine.getText();
                send(message);                                // Sends the text that's inside of the chatline.
                chatLine.setText("");                         // Sets the text of the chatline to nothing after message is sent.
            }

        };

        sendButton.addActionListener(sendListener); // Adds the above created actionlistener to the sendButton so that it can successfully send text to the server.
        chatLine.addActionListener(sendListener);   // Adds the above created actionlistener to the chatLine so that it can successfully send text to the server.

        connectButton.addActionListener(ActionEvent ->{
            run();
        });
    }

    private void whileConnected(){

        do{
            try {
                String message = input.readLine();
                JLabel tempLabel = new JLabel(message);
                tempLabel.setOpaque(true);
                tempLabel.setBackground(receivedBackgroundColor);
                tempLabel.setForeground(receivedForegroundColor);

                chatBox.add(tempLabel, BorderLayout.WEST);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while(isConnected);
    }

    private void send(String message){
        try {
            output.println(message); // Sends this message to the server, which will then be sent to the other client(s).
            JLabel tempLabel = new JLabel(message);
            tempLabel.setOpaque(true);
            tempLabel.setBackground(sentBackgroundColor);
            tempLabel.setForeground(sentForegroundColor);
            chatBox.add(tempLabel, BorderLayout.EAST);
            playSound("MS");                              // Attempts to play the sound, passing in the "MS (Message Sent) tag."


        }
        catch(NullPointerException e){
            e.printStackTrace();
            chatBox.add(new JLabel("[No Outputstream Detected]"), BorderLayout.EAST);
        }

    }


    private void playSound(String tag){ // Receives a tag telling it what sound it should attempt to play.




    }

    public static void main(String[] args){

        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel"); // Sets Synthetica custom Look & Feel
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Client("127.0.0.1", 1201);

    }

    @Override
    public void run() {
        try {

            connection = new Socket(remoteIP, remotePort);
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = new PrintWriter(connection.getOutputStream());

            isConnected = true;

            whileConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createUIComponents() {
        chatWindow = new BackgroundTextPane("Images/blackborderedbackground.png");
        // TODO: place custom component creation code here
    }
}
