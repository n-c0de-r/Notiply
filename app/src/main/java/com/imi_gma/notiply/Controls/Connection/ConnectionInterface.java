package com.imi_gma.notiply.Controls.Connection;

public interface ConnectionInterface {

    /**
     * Sends data to connected peers.
     */
    void sendData();

    /**
     * Receives data from connected peers.
     */
    void receiveData();

    /**
     * Creates a new client instance.
     */
    void setupClient();

    /**
     * Creates a new server instance.
     */
    void setupServer();
}
