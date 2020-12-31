package com.company.eight.reverse_string;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private DatagramSocket datagramSocket;
    private final int CLIENT_PORT, SERVER_PORT;

    public Client(int CLIENT_PORT, int SERVER_PORT) {
        this.CLIENT_PORT = CLIENT_PORT;
        this.SERVER_PORT = SERVER_PORT;
        this.datagramSocket = null;
    }

    public void openConnection() {
        try {
            datagramSocket = new DatagramSocket(this.CLIENT_PORT);
            byte[] sendData = new Scanner(System.in).nextLine().getBytes(StandardCharsets.UTF_8);

            InetAddress ipAddress = InetAddress.getByName("localhost");
            DatagramPacket datagramPacket = new DatagramPacket(sendData, sendData.length, ipAddress, this.SERVER_PORT);
            datagramSocket.send(datagramPacket);

            byte[] receiveData = new byte[10000];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);

            System.out.println(new String(receivePacket.getData()));;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
