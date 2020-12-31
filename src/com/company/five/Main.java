package com.company.five;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        Scanner scanner = new Scanner(System.in);
        LoginModel loginModel = new LoginModel(scanner.nextLine(), scanner.nextLine());
        LoginController loginController = new LoginController();
    }
}
