package Messenger;


/**
* Messenger/ClientContractPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* sábado 16 de abril de 2016 17H51' CEST
*/

public abstract class ClientContractPOA extends org.omg.PortableServer.Servant
 implements Messenger.ClientContractOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getName", new java.lang.Integer (0));
    _methods.put ("sendMsg", new java.lang.Integer (1));
    _methods.put ("transfer", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Messenger/ClientContract/getName
       {
         String $result = null;
         $result = this.getName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // Messenger/ClientContract/sendMsg
       {
         String msg = in.read_string ();
         String $result = null;
         $result = this.sendMsg (msg);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // Messenger/ClientContract/transfer
       {
         String path = in.read_string ();
         String $result = null;
         $result = this.transfer (path);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Messenger/ClientContract:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ClientContract _this() 
  {
    return ClientContractHelper.narrow(
    super._this_object());
  }

  public ClientContract _this(org.omg.CORBA.ORB orb) 
  {
    return ClientContractHelper.narrow(
    super._this_object(orb));
  }


} // class ClientContractPOA
