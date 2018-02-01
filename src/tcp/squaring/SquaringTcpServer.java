package tcp.squaring;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SquaringTcpServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    serveSocket(socket);
                }
            }
        }
    }

    private static void serveSocket(Socket socket) throws IOException {
        System.out.println("serving socket: " + socket.getInetAddress());

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        while (true) {
            int request = inputStream.read();
            if (request == -1) {
                break;
            }

            System.out.println("request: " + request);
            outputStream.write(request * request);
            outputStream.flush();
        }
    }
}
