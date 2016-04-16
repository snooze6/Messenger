package Messenger;


/**
* Messenger/EchoHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* viernes 15 de abril de 2016 19H38' CEST
*/

abstract public class EchoHelper
{
  private static String  _id = "IDL:Messenger/Echo:1.0";

  public static void insert (org.omg.CORBA.Any a, Messenger.Echo that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Messenger.Echo extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Messenger.EchoHelper.id (), "Echo");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Messenger.Echo read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_EchoStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Messenger.Echo value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Messenger.Echo narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Messenger.Echo)
      return (Messenger.Echo)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Messenger._EchoStub stub = new Messenger._EchoStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Messenger.Echo unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Messenger.Echo)
      return (Messenger.Echo)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Messenger._EchoStub stub = new Messenger._EchoStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}