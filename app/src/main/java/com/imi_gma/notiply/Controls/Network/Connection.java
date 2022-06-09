package com.imi_gma.notiply.Controls.Network;

public interface Connection {

    /**
     * Sends data to connected peers.
     */
    void sendData();

    /**
     * Receives data from connected peers.
     */
    void receiveData();
}
