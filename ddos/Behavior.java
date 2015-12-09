package brain;

class Behavior {

    int Timing = 200000;
    String ip;
    int scannernumbers = 64;
    int maxports = 10;
    int Timeout = 100;
    int[] ports = {53, 7, 13, 37, 51, 63, 120, 123, 153, 16, 162, 434, 2, 3, 5, 9, 11, 27, 29, 31};
    Ping threadPing;

    public Behavior(String IP) {
        this.ip = IP;
    }

    public void ping() {
        threadPing = new Ping(this.ip, Timing);
        threadPing.start();
    }

    public synchronized void Annoy() {
        try {
            int port, i, j = maxports - 1, ended = 0;
            boolean begin = true;
            int space = 65536 / scannernumbers;
            int[] init = new int[scannernumbers];
            int[] end = new int[scannernumbers];
            Port_Scan[] threadArray = new Port_Scan[scannernumbers];
            Be_Annoying[] AnnoyingThreads = new Be_Annoying[maxports];
            for (i = 0; i < scannernumbers; i++) {
                init[i] = space * i;
                end[i] = init[i] + space;
            }
            for (i = 0; i < scannernumbers; i++) {
                threadArray[i] = new Port_Scan(ip, init[i], end[i], Timeout);
                threadArray[i].start();
            }
            while (ended < scannernumbers && j > -1) {
                for (i = 0; i < scannernumbers; i++) {
                    if (threadArray[i].getState() == Thread.State.TERMINATED) {
                        port = threadArray[i].getopen();
                        System.out.println("Port: "+port);
                        if (port != -1 && j > -1) {
                            ports[j] = port;
                            threadArray[i] = new Port_Scan(ip, port + 1, end[i], Timeout);
                            threadArray[i].start();
                            j--;
                        } else {
                            ended++;
                        }
                    }
                }
            }
            for (i = 0; i < scannernumbers; i++) {
                threadArray[i].join();
            }
            for (i = 0; i < maxports; i++) {
                AnnoyingThreads[i] = new Be_Annoying(ip, ports[i], Timing);
                AnnoyingThreads[i].start();
            }
            for (i = 0; i < maxports; i++) {
                AnnoyingThreads[i].join();
            }
            threadPing.join();
        } catch (InterruptedException ex) {
            System.err.println("GoodBye");
        }
    }

    /*
     void annoy() {
     int i;
     AnnoyThread[] threadArray = new AnnoyThread[50];
     for (i = 0; i < 50; i++) {
     threadArray[i] = new AnnoyThread(this.ip, 100000);
     threadArray[i].start();
     }
     for (i = 0; i < 50; i++) {
     try {
     threadArray[i].join();
     } catch (InterruptedException ex) {
     System.out.println("Shit on Thread");
     }
     }
     return;
     }
     */
}
