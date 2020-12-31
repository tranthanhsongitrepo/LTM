package com.company.eight.login.server.controller;

import com.company.eight.login.model.LoginModel;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.*;

public class ServerController {
    private int PORT;
    private DatagramSocket datagramSocket;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    public void openConnection() {
        try {
            datagramSocket = new DatagramSocket(this.PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    public ServerController(int PORT) {
        this.datagramSocket = null;
        this.PORT = PORT;
    }

    public void listen() {
        while (true) {
            byte[] buffer = new byte[10000];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                is = new ObjectInputStream(bais);
                LoginModel loginModel = (LoginModel) is.readObject();
                boolean result = checkLogin(loginModel);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(result);
                oos.flush();

                byte[] sendData = baos.toByteArray();
                datagramSocket.send(new DatagramPacket(sendData, sendData.length, datagramPacket.getAddress(), datagramPacket.getPort()));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean checkLogin(LoginModel loginModel) {
        try {
            String sql = "SELECT * FROM LTM.tbl_nguoiChoi WHERE tenDangNhap = ? AND matKhau = ? LIMIT 1";
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?useSSL=false", "root", "password");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, loginModel.getUsername());
            ps.setString(2, loginModel.getPassword());
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
