package Messenger;


/**
* Messenger/credentials.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* sábado 16 de abril de 2016 17H51' CEST
*/

public final class credentials implements org.omg.CORBA.portable.IDLEntity
{
  public String username = null;
  public String password = null;
  public String IOR = null;

  public credentials ()
  {
  } // ctor

  public credentials (String _username, String _password, String _IOR)
  {
    username = _username;
    password = _password;
    IOR = _IOR;
  } // ctor

} // class credentials
