package com.company.eight.login;

import com.company.eight.login.server.controller.ServerController;

public class ServerRun {
    public static void main(String[] args) {
        ServerController serverController = new ServerController(9999);
        serverController.openConnection();
        serverController.listen();
    }
}
