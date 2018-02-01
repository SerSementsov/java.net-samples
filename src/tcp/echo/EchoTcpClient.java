package tcp.echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class EchoTcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 8989)) {
            try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                 DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

                dataOutputStream.writeUTF("Hello Echo Server!");
                dataOutputStream.flush();

                String response = dataInputStream.readUTF();
                System.out.println("server response: " + response);
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
