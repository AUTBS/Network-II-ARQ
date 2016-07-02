package gbn;

/**
 * Created by surface on 02/07/2016.
 */
public class client implements Runnable {

    int ws ;
    int wr =  1;
    int nf;
    int R;
    int d;
    int v;
    int ack = 0;
    int n = 0;
    gbn.server server;
    int time ;
    int l;
    public client(int ws, int nf, int r, int d, int v, gbn.server server) {
        this.ws = ws;
        this.nf = nf;
        R = r;
        this.d = d;
        this.v = v;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(ws * (nf / R) + (d / v));
                l = ack;
                System.out.println("sending : " + n + " to " + (n + ws) + " l: " + l+" ack: "+ack);
                server.send(n, n + ws);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = 0;
            while (time != 100 && ack < n + ws - wr  ) {
                try {
                    Thread.sleep(1);
                    time++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            n += ack - l;
        }
    }


    public void ack(int ack) {
        for(int c = 0; c< 1 ;c++)
        this.ack = ack;
    }
}
