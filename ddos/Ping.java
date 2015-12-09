package brain;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping extends Thread {

    String Adress;
    long threadId;
    int totalTime;

    public Ping(String adress, int totaltime) {
        this.Adress = adress;
        this.totalTime = totaltime;
    }

    @Override
    public void run() {
        Annoy_ping();
    }

    private void Annoy_ping() {
        threadId = Thread.currentThread().getId();
        long startTime = System.currentTimeMillis();
        boolean toFinish = false;
        while (!toFinish) {
            try {
                InetAddress address = InetAddress.getByName(Adress);
                //               System.out.println("Name: " + address.getHostName());
                //              System.out.println("Addr: " + address.getHostAddress());
                System.out.println("Thread: " + threadId + " Reach: " + address.isReachable(500));
            } catch (UnknownHostException e) {
                System.err.println("Unable to lookup");
            } catch (IOException e) {
                System.err.println("Unable to reach");
            }
            toFinish = (System.currentTimeMillis() - startTime >= totalTime);
        }
    }
}

/*
    private void option2() throws IOException {
        String result = "";
        threadId = Thread.currentThread().getId();
        long startTime = System.currentTimeMillis();
        boolean toFinish = false;
        while (!toFinish) {
            try {
                URL siteURL = new URL(Adress);
                HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int code = connection.getResponseCode();
                if (code == 200) {
                    result = "Green";
                }
            } catch (Exception e) {
                result = "->Red<-";
            }
            System.out.println(result);
            toFinish = (System.currentTimeMillis() - startTime >= totalTime);
        }
    }
*/