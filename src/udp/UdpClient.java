package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.util.Arrays;

public class UdpClient {
    public static void main(String[] args) {
        byte[] bytes = new byte[1];
        bytes[0] = 42;

        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket packet = new DatagramPacket(bytes,
                    bytes.length,
                    Inet4Address.getByName("localhost"),
                    9999);
            socket.send(packet);

            System.out.println("sent packet over UDP: " + Arrays.toString(bytes));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
