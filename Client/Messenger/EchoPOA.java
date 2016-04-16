package Messenger;


/**
* Messenger/EchoPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* viernes 15 de abril de 2016 19H38' CEST
*/

public abstract class EchoPOA extends org.omg.PortableServer.Servant
 implements Messenger.EchoOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("echoString", new java.lang.Integer (0));
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
       case 0:  // Messenger/Echo/echoString
       {
         String mesg = in.read_string ();
         String $result = null;
         $result = this.echoString (mesg);
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
    "IDL:Messenger/Echo:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Echo _this() 
  {
    return EchoHelper.narrow(
    super._this_object());
  }

  public Echo _this(org.omg.CORBA.ORB orb) 
  {
    return EchoHelper.narrow(
    super._this_object(orb));
  }


} // class EchoPOA