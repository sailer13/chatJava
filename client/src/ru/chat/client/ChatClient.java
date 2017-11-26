package ru.chat.client;

import ru.chat.network.ChatNetwork;
import ru.chat.network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by User on 21.11.2017.
 */
public class ChatClient extends  JFrame implements ActionListener, TCPConnectionListener{

    private ChatNetwork network;

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

        fieldInput.addActionListener(this);

        add(log, BorderLayout.CENTER);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldName, BorderLayout.NORTH);

        setVisible(true);

        try {
            network = new ChatNetwork(this, IP_ADRES, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if(msg.equals(""))return;
        fieldInput.setText(null);
        network.sendString(fieldName.getText()+"!: "+msg);
    }

    @Override
    public void onConnectionReady(ChatNetwork topConnection) {
        printMessage("Cpnnection ready...");
    }

    @Override
    public void onConnectionString(ChatNetwork topConnection, String value) {
        printMessage(value);
    }

    @Override
    public void onConnectionDisconnection(ChatNetwork topConnection) {
        printMessage("Cpnnection close");
    }

    @Override
    public void onConnectionException(ChatNetwork topConnection, Exception e) {
        printMessage("Cpnnection exception "+e);
    }

    private synchronized void printMessage (String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg+"\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
