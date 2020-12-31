package com.company.eight.reverse_string;

public class ServerRun {
    public static void main(String[] args) {
        Server server = new Server(9999);
        server.listening();
    }

}
