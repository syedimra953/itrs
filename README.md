# TCP Network Socket Communication

This Java application demonstrates a simple client-server architecture for communication over a TCP network socket. The project consists of three components: `TCPServer`, `TCPClient`, and `ClientHandler`.

## Dependencies

- Java 17 or later
- JUnit (for running tests)
- maven 3

## How to Run

1. **Compile the Code**: Ensure that all Java source files are successfully compiled maven `mvn clean install`  or using `javac` .

2. **Run the Server (TCPServer)**: 

   - Please note : TCPServer is configured to run on localhost (127.0.0.1) and port is 8080
   - Simple way to run the application is from IDE, right click on TCPServer class and run it will launch Server terminal. 
   - OR
   - Open a terminal.
   - Navigate to the directory where `TCPServer.java` is located.
   - Run the `TCPServer` class with the desired port number as an argument. For example, to run the server on port 12345, use the following command:
     ```
     java TCPServer 8080
     ```

3. **Run the Client (TCPClient)**:

   - Simple way to run the application is from IDE, right click on TCPClient class and run it will launch Client terminal and start testing.
   - OR
   - Open another terminal.
   - Navigate to the directory where `TCPClient.java` is located.
   - Run the `TCPClient` class.
   - Or from IDE right click and run TCPClient

5. **Interact with the Client**:
   - Once the `TCPClient` is running, you can interact with it by entering messages in the client terminal.
   - Type a message and press Enter. The message will be sent to the server.
   - You will receive responses from the server, and the round trip time and throughput rate will be displayed.

6. **To Exit the Client**: Type "exit" and press Enter to quit the client.

7. **To Stop the Server**: You can stop the server by terminating the server terminal.

## Project Structure

- `TCPServer.java`: The server application that listens for incoming connections on a specified port and echoes messages back to clients.

- `TCPClient.java`: The client application that establishes a connection to the server and sends messages to it. It also calculates and displays round trip times and throughput rates.

- `ClientHandler.java`: The class that handles communication between the server and clients. It runs as a separate thread to handle multiple clients concurrently.

- `application.properties`: Configuration file for specifying server address, port, and other properties. Update this file as needed.

## Testing

The project includes JUnit test classes for the `ClientHandler`, `TCPServer`, and `TCPClient` components. You can run these tests to verify the functionality of the code.
