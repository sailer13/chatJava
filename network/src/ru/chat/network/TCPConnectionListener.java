package ru.chat.network;

/**
 * Created by User on 21.11.2017.
 */
public interface TCPConnectionListener {
    void onConnectionReady(ChatNetwork topConnection);
    void onConnectionString(ChatNetwork topConnection, String value);
    void onConnectionDisconnection(ChatNetwork topConnection);
    void onConnectionException(ChatNetwork topConnection, Exception e);
}
