package chat.common;

public interface ConnectionListener {

    void onConnectionCreated(Connection connection);

    void onConnectionClosed(Connection connection);

    void onConnectionException(Connection connection, Exception e);

    void onMessageReceived(Message message);
}
