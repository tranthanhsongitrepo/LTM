package com.company.eight.reverse_string;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    public String reverseString(String reverse) {
        StringBuilder result = new StringBuilder();

        for (int i = reverse.length() - 1; i >= 0; i--) {
            result.append(reverse.charAt(i));
        }
        return result.toString();
    }

    private DatagramSocket datagramSocket;
    private final int PORT;

    public Server(int port) {
        PORT = port;
        try {
            this.datagramSocket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void listening() {
        while (true) {
            byte[] receiveData = new byte[10000];
            byte[] sendData;

            DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String result = new String(datagramPacket.getData());

            InetAddress inetAddress = datagramPacket.getAddress();

            int port = datagramPacket.getPort();

            sendData = reverseString(result).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
            try {
                datagramSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
