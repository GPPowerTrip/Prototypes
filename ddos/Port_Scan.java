package brain;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Port_Scan extends Thread {
    int end;
    int start;
    String Adress;
    long threadId;
    int totalTime;
    int open;

    public Port_Scan(String adress,int start, int end, int totaltime) {
        this.Adress = adress;
        this.start=start;
        this.end=end;
        this.totalTime = totaltime;
    }

    @Override
    public void run() {
        portScan();
    }

    private void portScan() {
        for (int i = start; i < end; i++) {
            if (udpScan(Adress, i, totalTime)) {
                this.open=i;
                return;
            }
        }
        this.open=-1;
    }
    
    public int getopen(){
        return open;
    }
        
    private boolean udpScan(String host, int port, int timeOut) {
        boolean flag = false;
        DatagramSocket socket = null;
        byte[] data = host.getBytes();
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(timeOut);
            socket.setTrafficClass(0x02 | 0x04 | 0x08 | 0x10);
            socket.connect(new InetSocketAddress(host, port));
            socket.send(new DatagramPacket(data, data.length));
            while (true) {
                byte[] receive = new byte[4096];
                DatagramPacket response = new DatagramPacket(receive, 4096);
                socket.receive(response);
                if (response != null && response.getData() != null) {
                    flag = true;
                    System.out.println(threadId +" "+port);
                    break;
                }
            }
        } catch (Exception e) {                        //e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }
        return flag;
    }
}
