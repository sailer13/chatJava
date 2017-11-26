package ru.chat.client;

import javax.swing.*;

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
    private ChatClient() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        setVisible(true);

    }
}
