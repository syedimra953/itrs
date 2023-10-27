package com.itrs.assignment.tcp.client;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAdder;

import static java.util.Objects.nonNull;

/**
 * @author - Syed Imran
 * ClientHandler.java: The class that handles communication between the server and clients.
 * It runs as a separate thread to handle multiple clients concurrently.
 * @return
 */

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final AtomicLong messageCount = new AtomicLong(0);
    private final DoubleAdder avgRoundTripTime = new DoubleAdder();

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try (Socket clientSocket = this.clientSocket;
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String message;
            while (nonNull(message = in.readLine())) {
                calculateAndPrintResults(out, message);
            }
        } catch (IOException e) {
            System.err.println("Error in ClientHandler: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void calculateAndPrintResults(PrintWriter out, String message) {

        long startTime = System.currentTimeMillis();
        out.println("Server: " + message);
        long roundTripTime = System.currentTimeMillis() - startTime;

        avgRoundTripTime.add(roundTripTime);
        long count = messageCount.incrementAndGet();

        System.out.println("Received: " + message);
        System.out.println("Round Trip Time: " + roundTripTime + "ms");
        System.out.println("Running Average Round Trip Time: " + avgRoundTripTime.sum() / count + "ms");
        System.out.println("Running Throughput Rate: " + count / (System.currentTimeMillis() / 1000.0) + " messages per second");
    }

}

