import Example.Echo;
import Example.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;
import java.util.Scanner;

public class HelloClient
{
  static Echo helloImpl;

  public static void main(String args[])
    {
      try{
        // create and initialize the ORB
	    //ORB orb = ORB.init(args, null);
          Properties aProperties = new Properties();
          aProperties.put("org.omg.CORBA.ORBInitialHost", "localhost");
          aProperties.put("org.omg.CORBA.ORBInitialPort", "2809");
          ORB orb = ORB.init((String[]) null, aProperties);

//          Scanner scan= new Scanner(System.in);
//          System.out.print("Input IOR: ");
//          String ior = scan.nextLine();
//          System.out.println("IOR: "+ior);
//
//          Object obj = orb.string_to_object(ior);
//          System.out.println(obj.getClass());
//
//          Echo e = EchoHelper.narrow(obj);
//          e.echoString("SUUU");

          {
              // get the root naming context
              org.omg.CORBA.Object objRef =
                      orb.resolve_initial_references("NameService");
              // Use NamingContextExt instead of NamingContext. This is
              // part of the Interoperable naming Service.
              NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

              // resolve the Object Reference in Naming
              String name = "Hello";
              helloImpl = EchoHelper.narrow(ncRef.resolve_str(name));

              System.out.println("Obtained a handle on server object: " + helloImpl);
              System.out.println(helloImpl.echoString("Suuuu"));

              //helloImpl.shutdown();
          }

	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }

}
