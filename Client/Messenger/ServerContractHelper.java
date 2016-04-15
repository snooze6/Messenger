package Messenger;


/**
* Messenger/ServerContractHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* viernes 15 de abril de 2016 19H38' CEST
*/

abstract public class ServerContractHelper
{
  private static String  _id = "IDL:Messenger/ServerContract:1.0";

  public static void insert (org.omg.CORBA.Any a, Messenger.ServerContract that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Messenger.ServerContract extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Messenger.ServerContractHelper.id (), "ServerContract");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Messenger.ServerContract read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ServerContractStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Messenger.ServerContract value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Messenger.ServerContract narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Messenger.ServerContract)
      return (Messenger.ServerContract)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Messenger._ServerContractStub stub = new Messenger._ServerContractStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Messenger.ServerContract unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Messenger.ServerContract)
      return (Messenger.ServerContract)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Messenger._ServerContractStub stub = new Messenger._ServerContractStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
