package gbn;

import java.util.Random;

/**
 * Created by surface on 02/07/2016.
 */
public class server implements Runnable{

    gbn.client client ;
    int ws = 5 ;
    int nf = 100;
    int R = 10;
    int d = 100;
    int v = 150;
    int s ;
    int e;
    int wr = 1;
    double p = 90;
    Thread tClient;
    public server() {
        new Thread(this,"server").start();
        client = new client(ws,nf,R,d,v,this);
        tClient = new Thread(client,"client");
        tClient.start();
    }

    public void send(int n, int n1) {
        int c = 0;
        for( c = 0; c < ws;c++) {
            Random rn = new Random();
            int rand = rn.nextInt(100) + 1;
            if (rand < p) {
                s++;
                System.out.println("packet " + (n+c) + " acked");
            } else {
                e++;
                client.ack(n+c);
                System.out.println("packet " + (n+c) + " nack");
                break;
            }
        }
        if(c == ws)
            client.ack(n+c);

    }


    @Override
    public void run() {
        int time = 0 ;
        while (time <50000){
            try {
                time++;
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        tClient.stop();
        System.err.println("link util : "+(float)((client.n*(nf/R))+(client.n/ws)*(d/v))/time);
        System.err.println("error : "+(float)e/s);
    }

    public static void main(String[] args) {
        new server();
    }
}
