package Messenger;


/**
* Messenger/ClientContractOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contract.idl
* sábado 16 de abril de 2016 17H51' CEST
*/

public interface ClientContractOperations 
{
  String getName ();
  String sendMsg (String msg);
  String transfer (String path);
} // interface ClientContractOperations