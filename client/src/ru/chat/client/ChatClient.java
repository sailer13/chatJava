package ru.chat.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 21.11.2017.
 */
public class ChatClient extends  JFrame{

    private static  final String IP_ADRES = "192.168.1.5";
    private static  final  int PORT = 8187;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public  static void main(String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatClient();
            }
        });
    }

    private  final JTextArea log = new JTextArea();
    private  final JTextField fieldName = new JTextField("1234");
    private  final JTextField fieldInput = new JTextField();

    private ChatClient() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        log.setEditable(false);
        log.setLineWrap(true);

        add(log, BorderLayout.CENTER);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldName, BorderLayout.NORTH);
        
        setVisible(true);

    }
}
