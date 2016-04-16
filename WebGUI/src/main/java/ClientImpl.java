import Messenger.ClientContractPOA;
import org.omg.CORBA.ORB;

/**
 * Created by snooze on 4/16/16.
 */



public class ClientImpl extends ClientContractPOA{

    private ORB orb;
    private String name;

    public ClientImpl(String name) {
        this.name = name;
    }

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public String getName() {
        System.out.println("-- Pedido el nombre: "+name);
        return name;
    }

    public String sendMsg(String username, String msg) {
        System.out.println("-- Recibido mensaje: "+msg);
        System.out.println("-- De: "+username);
        return msg;
    }

    public String transfer(String path) {
        System.out.println("-- Recibido archivo: "+path);
        return path;
    }
}
