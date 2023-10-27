package com.itrs.assignment.tcp.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TCPClientHandlerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testClientHandlerRun() {
        // Create a client socket and simulate input and output streams
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Test Message\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Socket clientSocket = new Socket();

        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        ClientHandler clientHandler = new ClientHandler(clientSocket);
        clientHandler.run();

        // Verify the output captured
        String expectedOutput = "Received: Test Message\n";
        assertEquals(expectedOutput, outContent.toString());

        // Verify that the response is sent to the client
        String response = outputStream.toString();
        assertEquals("Test Message\n", response);
    }
}
