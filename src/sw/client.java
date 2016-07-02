package sw;

/**
 * Created by surface on 02/07/2016.
 */
public class client implements Runnable {

    int ws ;
    int nf;
    int R;
    int d;
    int v;
    int ack = 0;
    int n = 0;
    server server;
    int time ;
    public client(int ws, int nf, int r, int d, int v, sw.server server) {
        this.ws = ws;
        this.nf = nf;
        R = r;
        this.d = d;
        this.v = v;
        this.server = server;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep((nf/R)+(d/v));
                server.send(n,n);
                System.out.println("sending : "+n+"to"+n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = 0;
        while(time != 100 && ack!=n+1){
            try {
                Thread.sleep(1);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(time < 1000)
            n++;

        }
    }

    public void ack() {
        ack = n+1;
    }
}
