package sw;

import java.util.Random;

/**
 * Created by surface on 02/07/2016.
 */
public class server implements Runnable{

    client client ;
    int ws =1 ;
    int nf = 100;
    int R = 10;
    int d = 100;
    int v = 150;
    int wr = 1;
    double p = 90;
    int s;
    int e;
    Thread tClient;
    public server() {
        new Thread(this,"server").start();
        client = new client(ws,nf,R,d,v,this);
        tClient = new Thread(client,"client");
        tClient.start();
    }

    public void send(int n, int n1) {
        Random rn = new Random();
        int rand = rn.nextInt(100) + 1;
        if(rand < p){
            client.ack();
            System.out.println("packet "+n+" ack");
            s++;
        }
        else {
            System.out.println("packet "+n+" nack");
            e++;
        }
    }


    @Override
    public void run() {
        int time = 0 ;
        while (time < 50000){
            try {
                time++;
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        tClient.stop();
        System.err.println("link util : "+(float)(client.n*((nf/R)+(d/v)))/time);
        System.err.print("error : " + (float)e/s);
    }

    public static void main(String[] args) {
        for(int c = 0; c< 1 ;c++)
        new server();
    }
}
