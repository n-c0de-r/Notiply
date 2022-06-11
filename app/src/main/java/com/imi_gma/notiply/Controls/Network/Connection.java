package com.imi_gma.notiply.Controls.Network;

public interface Connection {

    /**
     * Sends data to connected peers.
     */
    void sendData(byte[] bytes);

    /**
     * Receives data from connected peers.
     */
    void receiveData();
}
