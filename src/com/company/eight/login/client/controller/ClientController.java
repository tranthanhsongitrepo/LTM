package com.company.eight.login.client.controller;

import com.company.eight.login.client.view.LoginView;
import com.company.eight.login.model.LoginModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientController {
    public DatagramSocket datagramSocket;
    private final int CLIENT_PORT, SERVER_PORT;
    private LoginView loginView;
    public ClientController(int clientPort, int serverPort, LoginView loginView) {
        this.CLIENT_PORT = clientPort;
        this.SERVER_PORT = serverPort;
        this.loginView = loginView;
        try {
            this.datagramSocket = new DatagramSocket(this.CLIENT_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        loginView.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkLogin(loginView.getUser())) {
                    loginView.showMessage("Success");
                }
                else {
                    loginView.showMessage("Fail");
                }
            }
        });
    }

    public boolean checkLogin(LoginModel user) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.flush();

            InetAddress inetAddress = InetAddress.getByName("localhost");
            byte[] sendData = baos.toByteArray();
            DatagramPacket datagramPacket = new DatagramPacket(sendData, sendData.length, inetAddress, this.SERVER_PORT);
            datagramSocket.send(datagramPacket);

            byte[] receiveData = new byte[10000];
            datagramPacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(datagramPacket);

            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(receiveData));
            return (boolean) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
