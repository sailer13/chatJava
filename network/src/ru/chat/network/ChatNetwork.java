package ru.chat.network;

import sun.rmi.transport.tcp.TCPConnection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by User on 21.11.2017.
 */
public class ChatNetwork implements TCPConnectionListener
{
    private final Socket socket; //сокет
    private final Thread rxThread;//поток слушающий входящее соединение и генерируетискл
    private final TCPConnectionListener eventListener; //слушотель событий
    private final BufferedReader in;//поток ввода
    private final BufferedWriter out;//поток вывода
    public ChatNetwork (TCPConnectionListener eventListener, Socket socket) throws IOException {//генит сокет и выполняет
        this.socket = socket;
        this.eventListener = eventListener;
        //get для обраьотки исключения
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        //создали поток обработали и создали кл буфер ридер
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        //новый поток слушатель
        rxThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(ChatNetwork.this);//экземпляр именно обромляющего класса
                   while (!rxThread.isInterrupted()) {
                       eventListener.onReceiveString(ChatNetwork.this, in.readLine());
                   }
                } catch (IOException e){
                       eventListener.onConnectionException(ChatNetwork.this, e);

                }finally {
                    eventListener.onConnectionException(ChatNetwork.this, e);
                }
            }
        });
        rxThread.start();
        }
    //отправить сообщение
        public synchronized void sendString(String value) {
            try {
                out.write(value+"\r\n" );//написать сообщение
                out.flush();//сбрасывает буфер и точн отправляет
            } catch (IOException e) {
                eventListener.onConnectionException(ChatNetwork.this, e);
                disconnect();
            }
        }
        //откл
        public synchronized void disconnect() {
            rxThread.interrupt();//прервать
            try {
                socket.close();//закрыть сокет
            } catch (IOException e) {
                eventListener.onConnectionException(ChatNetwork.this, e);
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

    @Override
    public String toString () {
        return "Connection: " + socket.getInetAddress()+": "+socket.getPort();
    }
}
