import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;
import java.util.Scanner;

public class HelloClient implements Runnable
{
  static Hello helloImpl;
    ORB orb;
    public static void main(String args[])
    {
      try{
        // create and initialize the ORB
          Properties aProperties = new Properties();
          aProperties.put("org.omg.CORBA.ORBInitialHost", "localhost");
          aProperties.put("org.omg.CORBA.ORBInitialPort", "2930");
          ORB orb = ORB.init((String[]) null, aProperties);
        // get the root naming context
        org.omg.CORBA.Object objRef = 
	    orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

          // get reference to rootpoa & activate the POAManager
          POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
          rootpoa.the_POAManager().activate();




//------------------->>>>>>>>>Parte servidor del cliente
          
          // create servant and register it with the ORB
          HelloImpl clienteChat = new HelloImpl();
          clienteChat.setORB(orb);

          // get object reference from the servant
          org.omg.CORBA.Object ref = rootpoa.servant_to_reference(clienteChat);
          Hello href = HelloHelper.narrow(ref);

          // bind the Object Reference in Naming
          String cliente1Servidor = "cliente1Servidor";
          NameComponent path[] = ncRef.to_name( cliente1Servidor );
          ncRef.rebind(path, href);
            HelloClient helloClient = new HelloClient();
          helloClient.orb=orb;
          (new Thread(helloClient)).start();

          Thread.sleep(4000);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Parte cliente del cliente
          // resolve the Object Reference in Naming
          String cliente2Servidor = "cliente2Servidor";
          helloImpl = HelloHelper.narrow(ncRef.resolve_str(cliente2Servidor));
          System.out.println("Obtained a handle on server object: " + helloImpl);
          boolean flag=false;
          while(flag==false){
              Scanner keyboard = new Scanner(System.in);
              System.out.println("Introduzca el mensaje");
              String mensaje = keyboard.nextLine();
              helloImpl.sayHello(mensaje);
        }


       //helloImpl.shutdown();

	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }

    @Override
    public void run() {
        orb.run();
    }
}
