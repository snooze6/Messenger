package sample;

import org.omg.CORBA.ORB;

/**
 * Created by snooze on 4/16/16.
 */
public class ClientTask implements Runnable {
    private ORB orb;

    public ClientTask(ORB orb) {
        this.orb = orb;
    }

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public void run() {
        orb.run();
    }
}
