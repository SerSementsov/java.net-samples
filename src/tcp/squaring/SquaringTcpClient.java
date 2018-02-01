package tcp.squaring;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SquaringTcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 8989)) {
            for (int i = 0; i < 10; i++) {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                outputStream.write(i);
                outputStream.flush();

                System.out.println("server response: " + inputStream.read());

            }
        } catch (IOException e) {
            System.out.println("Error happened: " + e);
        }
    }
}
