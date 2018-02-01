package chat.server;

import chat.common.Connection;
import chat.common.ConnectionImpl;
import chat.common.ConnectionListener;
import chat.common.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;

import static chat.common.Connection.PORT;
import static java.lang.String.format;

public class Server implements ConnectionListener {

    private Set<Connection> connections;
    private ServerSocket serverSocket;
    private int port;

    public void start() {
        System.out.println(format("server started on port = %s ", port));

        while (true) {
            Socket socket = getSocket();
            connections.add(new ConnectionImpl(socket, this));
        }
    }

    private Socket getSocket() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Server() {
        this.port = PORT;
        this.connections = new LinkedHashSet<>();
        this.serverSocket = initServerSocket();
    }

    @Override
    public void onConnectionCreated(Connection connection) {
        connections.add(connection);

        System.out.println(format("Connection %s added to pool", connection));
    }

    @Override
    public void onConnectionClosed(Connection connection) {
        connections.add(connection);
        connection.close();

        System.out.println(format("Connection %s removed from pool", connection));
    }

    @Override
    public void onConnectionException(Connection connection, Exception e) {
        connections.add(connection);
        connection.close();

        System.out.println(format("Connection %s removed from pool", connection));
        System.out.println(format("exception occurred: %s", e.getMessage()));
    }

    @Override
    public void onMessageReceived(Message message) {
        for (Connection connection : connections) {
            connection.send(message);
        }
    }

    private ServerSocket initServerSocket() {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
