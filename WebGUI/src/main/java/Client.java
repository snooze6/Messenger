import Messenger.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

public class Client {

    private ServerContract server;
    private String username, password, IOR;

    public Client(String username, String password) {
        this.username = username;
        this.password = password;

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

            {
                // get reference to rootpoa & activate the POAManager
                POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                rootpoa.the_POAManager().activate();

                // create servant and register it with the ORB
                ClientImpl client = new ClientImpl(username);
                client.setORB(orb);

                // get object reference from the servant
                org.omg.CORBA.Object ref = rootpoa.servant_to_reference(client);
                ClientContract href = ClientContractHelper.narrow(ref);

                // bind the Object Reference in Naming
                NameComponent path[] = ncRef.to_name(username);
                ncRef.rebind(path, href);

                IOR = orb.object_to_string(href);

                // wait for invocations from clients
                (new Thread(new ClientTask(orb))).start();
            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    public boolean login(){
        return server.login(new credentials(username, password, IOR));
    }

    public boolean register(){
        return server.login(new credentials(username, password, IOR));
    }

    public ServerContract getServer() {
        return server;
    }

    public static void main(String args[]) {
        Client c = new Client("pedro", "pedro");
        System.out.println(c.getServer().getUser("juan").getName());
    }

}
