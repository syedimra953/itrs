package com.itrs.assignment.tcp.server;

import com.itrs.assignment.tcp.client.ClientHandler;

import java.io.*;
import java.net.*;

import static com.itrs.assignment.tcp.utils.PropertyUtils.SERVER_PORT_NUMBER;

public class TCPServer {

    /**
     * @author - Syed Imran
     * TCPServer.java:  The server application that listens for incoming connections on a specified port and echoes messages back to clients
     * @return
     */
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT_NUMBER);
            System.out.println("Server is listening on port " + SERVER_PORT_NUMBER);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                new Thread(() -> new ClientHandler(clientSocket).run()).start();
            }
        } catch (IOException e) {
            System.err.println("Error in the Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}