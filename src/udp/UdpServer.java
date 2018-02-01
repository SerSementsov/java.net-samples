package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UdpServer {
    public static void main(String[] args) {
        byte[] buffer = new byte[1];

        while (true) {
            try (DatagramSocket socket = new DatagramSocket(9999)) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                System.out.println("received packet: " + Arrays.toString(buffer));
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
