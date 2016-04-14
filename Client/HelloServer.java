import Example.Echo;
import Example.EchoHelper;
import Example.EchoPOA;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

class HelloImpl extends EchoPOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }

  @Override
  public String echoString(String mesg) {
    System.out.println("Communication sucessfull: "+mesg);
    return mesg;
  }
}


public class HelloServer {

  public static void main(String args[]) {
    try{
      // create and initialize the ORB
      //System.out.println(args);
      //ORB orb = ORB.init(args, null);
      Properties aProperties = new Properties();
      aProperties.put("org.omg.CORBA.ORBInitialHost", "localhost");
      aProperties.put("org.omg.CORBA.ORBInitialPort", "2930");
      ORB orb = ORB.init((String[]) null, aProperties);
      // get reference to rootpoa & activate the POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // create servant and register it with the ORB
      HelloImpl helloImpl = new HelloImpl();
      helloImpl.setORB(orb); 

      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
      Echo href = EchoHelper.narrow(ref);
	  
      // get the root naming context
      // NameService invokes the name service
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Use NamingContextExt which is part of the Interoperable
      // Naming Service (INS) specification.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // bind the Object Reference in Naming
      String name = "Hello";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("HelloServer ready and waiting ...");

      // wait for invocations from clients
      orb.run();
    } 
	
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
	finally{
	System.out.println("Cuernos, joder, hostia, mierda, cuernos --------------------------___>");
}
	  
      System.out.println("HelloServer Exiting ...");
	
  }
}
