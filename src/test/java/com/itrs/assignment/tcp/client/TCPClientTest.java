package com.itrs.assignment.tcp.client;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class TCPClientTest {

    @Test
    public void testClientRunLogic() throws IOException {
        // Create a mock socket
        Socket clientSocket = mock(Socket.class);

        // Set up input and output streams for the mock socket
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Test Message\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(clientSocket.getInputStream()).thenReturn(inputStream);
        when(clientSocket.getOutputStream()).thenReturn(outputStream);

        // Create a Client instance and run the logic
        TCPClient.runClient(clientSocket);

        // Verify the expected response and output
        String response = outputStream.toString();
        assert response.equals("Test Message\n");
    }

    @Test
    public void testClientCleanup() throws IOException {
        // Create a mock socket
        Socket clientSocket = mock(Socket.class);

        // Simulate an IOException during close
        doThrow(new IOException("Error closing socket")).when(clientSocket).close();

        // Run the cleanup method
        TCPClient.cleanupSocketConnections(clientSocket);
    }
}
