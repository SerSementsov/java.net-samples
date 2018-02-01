package chat.common;

import java.io.*;
import java.net.Socket;

public class ConnectionImpl implements Connection, Runnable {

    private Socket socket;
    private OutputStream out;
    private ConnectionListener listener;
    private boolean needToRun = true;

    public ConnectionImpl(Socket socket, ConnectionListener listener) {
        this.socket = socket;
        this.listener = listener;
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    @Override
    public void send(Message message) {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() {
        needToRun = false;
    }

    @Override
    public void run() {
        while (needToRun) {
            try {
                InputStream inputStream = socket.getInputStream();
                if (inputStream.available() != 0) {
                    ObjectInputStream ois = new ObjectInputStream(inputStream);
                    Message message = (Message) ois.readObject();

                    listener.onMessageReceived(message);
                } else {
                    Thread.sleep(1000L);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}