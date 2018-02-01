package tcp.echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTcpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    serveSocket(socket);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serveSocket(Socket socket) throws IOException {
        System.out.println("serving request from socket: " + socket.getRemoteSocketAddress());
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                String request = dataInputStream.readUTF();
                System.out.println("request : " + request);

                dataOutputStream.writeUTF(request);
                dataOutputStream.flush();
            }
        }
    }
}
