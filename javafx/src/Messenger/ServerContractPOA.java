package Messenger;


/**
* Messenger/ServerContractPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* domingo 17 de abril de 2016 16H24' CEST
*/

public abstract class ServerContractPOA extends org.omg.PortableServer.Servant
 implements Messenger.ServerContractOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("login", new Integer (0));
    _methods.put ("register", new Integer (1));
    _methods.put ("getUser", new Integer (2));
    _methods.put ("getFriends", new Integer (3));
    _methods.put ("makeFriends", new Integer (4));
    _methods.put ("getFriendRequest", new Integer (5));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    Integer __method = (Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Messenger/ServerContract/login
       {
         Messenger.credentials cred = Messenger.credentialsHelper.read (in);
         boolean $result = false;
         $result = this.login (cred);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // Messenger/ServerContract/register
       {
         Messenger.credentials cred = Messenger.credentialsHelper.read (in);
         boolean $result = false;
         $result = this.register (cred);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // Messenger/ServerContract/getUser
       {
         String username = in.read_string ();
         Messenger.ClientContract $result = null;
         $result = this.getUser (username);
         out = $rh.createReply();
         Messenger.ClientContractHelper.write (out, $result);
         break;
       }


  // Soon to be deprecated
       case 3:  // Messenger/ServerContract/getFriends
       {
         String username = in.read_string ();
         Messenger.ClientContract $result[] = null;
         $result = this.getFriends (username);
         out = $rh.createReply();
         Messenger.usuariosHelper.write (out, $result);
         break;
       }

       case 4:  // Messenger/ServerContract/makeFriends
       {
         String user1 = in.read_string ();
         String user2 = in.read_string ();
         boolean $result = false;
         $result = this.makeFriends (user1, user2);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 5:  // Messenger/ServerContract/getFriendRequest
       {
         String user = in.read_string ();
         String $result[] = null;
         $result = this.getFriendRequest (user);
         out = $rh.createReply();
         Messenger.requestsHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Messenger/ServerContract:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ServerContract _this()
  {
    return ServerContractHelper.narrow(
    super._this_object());
  }

  public ServerContract _this(org.omg.CORBA.ORB orb)
  {
    return ServerContractHelper.narrow(
    super._this_object(orb));
  }


} // class ServerContractPOA