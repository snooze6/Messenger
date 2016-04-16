import Messenger.Echo;
import Messenger.ServerContract;
import Messenger.ServerContractHelper;
import Messenger.credentials;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;

public class Client {

    ServerContract server;

    public Client() {
        try {
            // create and initialize the ORB
            //ORB orb = ORB.init(args, null);
            Properties aProperties = new Properties();
            aProperties.put("org.omg.CORBA.ORBInitialHost", Conf.ip);
            aProperties.put("org.omg.CORBA.ORBInitialPort", Conf.port);
            ORB orb = ORB.init((String[]) null, aProperties);


            // get the root naming context
            Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "test.my_context/Server.Object";
            server = ServerContractHelper.narrow(ncRef.resolve_str(name));
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    public void doit(){
        server.login(new credentials("juan", "nauj", "IOR"));
    }

    public static void main(String args[]) {
        new Client().doit();
    }

}
