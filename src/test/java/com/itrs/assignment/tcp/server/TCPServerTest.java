package com.itrs.assignment.tcp.server;

import com.itrs.assignment.tcp.client.ClientHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class TCPServerTest {
    @Test
    public void testServerMain() throws IOException {
        // Create a mock server socket
        ServerSocket serverSocket = mock(ServerSocket.class);
        Socket clientSocket = mock(Socket.class);

        // Set up the accept method to return a mock client socket
        when(serverSocket.accept()).thenReturn(clientSocket);

        // Run the Server's main method
        TCPServer.main(new String[]{"8080"});
    }

    @Test
    public void testServerClientHandler() {
        // Create a mock client socket
        Socket clientSocket = mock(Socket.class);

        // Run a ClientHandler in a separate thread
        new Thread(new ClientHandler(clientSocket)).start();
    }
}
