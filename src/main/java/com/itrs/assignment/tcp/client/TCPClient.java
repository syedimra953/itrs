package com.itrs.assignment.tcp.client;

import java.io.*;
import java.net.*;

import static com.itrs.assignment.tcp.utils.PropertyUtils.CLIENT_PORT_NUMBER;
import static com.itrs.assignment.tcp.utils.PropertyUtils.SERVER_IP_ADDRESS;
import static java.util.Objects.nonNull;

/**
 * @author - Syed Imran
 * TCPClient.java:  The client application that establishes a connection to the server and sends messages to it.
 * It also calculates and displays round trip times and throughput rates.
 * @return
 */

public class TCPClient {

    public static void main(String[] args) {

        Socket clientSocket = null;

        try {
            clientSocket = new Socket(SERVER_IP_ADDRESS, CLIENT_PORT_NUMBER);
            runClient(clientSocket);
        } catch (UnknownHostException e) {
            System.err.println("Server IP Address is unknown  :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error in the client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanupSocketConnections(clientSocket);
        }
    }

    public static void runClient(final Socket clientSocket) {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String message;
            while (true) {
                System.out.print("Enter a message (or type 'exit' to quit): ");
                message = reader.readLine();

                if (nonNull(message) && message.equalsIgnoreCase("exit")) {
                    // Exit the loop
                    break;
                }
                calculateAndPrintMetrics(in, out, message);
            }
        } catch (IOException e) {
            System.err.println("Error in the client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     * calculates and displays round trip times and throughput rates.
     * @param bufferedReader
     * @param printWriter
     * @param message
     * @throws IOException
     */

    private static void calculateAndPrintMetrics(BufferedReader bufferedReader, PrintWriter printWriter,
                                                 String message) throws IOException {
        long startTime = System.currentTimeMillis();
        printWriter.println(message);
        String response = bufferedReader.readLine();
        long roundTripTime = System.currentTimeMillis() - startTime;

        System.out.println("Received: " + response);
        System.out.println("Round Trip Time: " + roundTripTime + "ms");
    }

    /**
     * Cleanup the socket connections to avoid socket connection hanging around
     *
     * @param socket
     */
    protected static void cleanupSocketConnections(Socket socket) {
        if (nonNull(socket) && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing the client socket: " + e.getMessage());
            }
        }
    }
}
