package chat.common;

public interface Connection {

    int PORT = 3333;

    void send(Message message);

    void close();
}
