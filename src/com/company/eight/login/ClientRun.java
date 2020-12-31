package com.company.eight.login;

import com.company.eight.login.client.controller.ClientController;
import com.company.eight.login.client.view.LoginView;

public class ClientRun {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        ClientController clientController = new ClientController(9998, 9999, loginView);
    }
}
