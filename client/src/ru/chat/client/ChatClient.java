package ru.chat.client;

import ru.chat.network.ChatNetwork;
import ru.chat.network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by User on 21.11.2017.
 */
public class ChatClient extends  JFrame implements ActionListener, TCPConnectionListener{
    

    private static  final String IP_ADRES = "192.168.1.5";
    private static  final  int PORT = 8187;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private  final JTextArea log = new JTextArea();
    private  final JTextField fieldName = new JTextField("1234");
    private  final JTextField fieldInput = new JTextField();

    private ChatNetwork network;

    private ChatClient() {
        super("ChatClient");
        addWindowListener(factory.getWindowL());

        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        log.setEditable(false); //РЅРёР·СЏ РїРёСЃР°С‚СЊ С‚Р°Рј РґРµ РІС‹РІРѕРґРёС‚ СЃРѕРѕР±Р¶РµРЅРёСЏ
        log.setLineWrap(true);

        add(log, BorderLayout.CENTER);


        add(fieldInput, BorderLayout.SOUTH);
        add(fieldName, BorderLayout.NORTH);

        fieldInput.addActionListener(factory.getFieldInput());


        setVisible(true);

        try {
            network = new ChatNetwork(this, IP_ADRES, PORT);
        } catch (IOException e) {
            printMessage("Cpnnection exception "+e);
        }

    }

    class ListenerFactory {
        public ActionListener getFieldInput() {
            return new ActionListener() {
                public   void actionPerformed(ActionEvent e) {

                    String msg = fieldInput.getText();
                    if(msg.equals(""))return;
                    fieldInput.setText(null);
                    network.sendString(fieldName.getText()+": "+msg);
                }
            };
        }
        public WindowListener getWindowL() {
            return new WindowL();
        }
        class WindowL extends WindowAdapter {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
    }





    public  static void main(String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatClient();
            }
        });
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
