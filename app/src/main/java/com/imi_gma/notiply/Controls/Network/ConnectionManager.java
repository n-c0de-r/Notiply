package com.imi_gma.notiply.Controls.Network;

import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager extends Thread implements Connection{
    static final int MESSAGE_READ = 1;
    final private Client client;
    final private Server server;

    public ConnectionManager(InetAddress hostAddress, int port) {
        client = new Client(hostAddress, port);
        server = new Server(port);
    }

    @Override
    public void sendData() {

    }

    @Override
    public void receiveData() {

    }

    // INNER CLASSES
    private class Client extends Thread{
        private SenderReceiver senderReceiver;
        private Socket socket;
        private String hostAdd;
        private final int port;

        public Client(InetAddress hostAddress, int port) {
            this.hostAdd = hostAddress.getHostAddress();
            this.socket = new Socket();
            this.port = port;
        }

        @Override
        public void run() {
            try {
                socket.connect(new InetSocketAddress(hostAdd, port), 500);
                senderReceiver = new SenderReceiver(socket);
                senderReceiver.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Server extends Thread {
        private SenderReceiver senderReceiver;
        private ServerSocket serverSocket;
        private Socket socket;
        private int port;

        public Server (int port) {
            this.port = port;
        }
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                senderReceiver = new SenderReceiver(socket);
                senderReceiver.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }




    private class SenderReceiver extends Thread {
        private Handler handler;
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        public SenderReceiver(Socket socket) {
            this.socket = socket;

            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while(socket != null) {
                try {
                    bytes = inputStream.read(buffer);
                    if (bytes > 0) {
                        handler.obtainMessage(MESSAGE_READ, bytes,-1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void write(byte[] bytes) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
