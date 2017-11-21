package ru.chat.network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by User on 21.11.2017.
 */
public class ChatNetwork {
    private final Socket socket; //сокет
    private final Thread rxThread;//поток слушающий входящее соединение и генерируетискл
    private final BufferedReader in;//поток ввода
    private final BufferedWriter out;//поток вывода
    public ChatNetwork (Socket socket) throws IOException {//генит сокет и выполняет
        this.socket = socket;
        //get для обраьотки исключения
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        //создали поток обработали и создали кл буфер ридер
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        //новый поток слушатель
        rxThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String msg = in.readLine()
                } catch (IOException){

                }finally {
                    
                }
            }
        });
        rxThread.start();
    }
}
