package ru.chat.server;

import ru.chat.network.ChatNetwork;
import ru.chat.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Created by User on 21.11.2017.
 */
public class ChatServer implements TCPConnectionListener{
    public  static void main(String[]args){
        new ChatServer();

    }
    //список слушателей
    private static ArrayList<ChatNetwork> connections = new ArrayList<>();
    private  ChatServer() {
            System.out.println("Server running...");
            try (ServerSocket serverSocket = new ServerSocket(8187)) {//подкл и слушаем8187
                while (true) {
                    try {
                        new ChatNetwork(this, serverSocket.accept());

                    } catch (IOException e) {
                        System.out.println("TCPConnection exception " + e);
                    }
                }
            } catch(IOException e){
                throw new RuntimeException(e);

            }
        }


    @Override
    public void onConnectionReady(ChatNetwork topConnection) {

    }

    @Override
    public void onConnectionString(ChatNetwork topConnection, String value) {

    }

    @Override
    public void onConnectionDisconnection(ChatNetwork topConnection) {

    }

    @Override
    public void onConnectionException(ChatNetwork topConnection, Exception e) {

    }
}
