import sys

import CosNaming
import omniORB
from omniORB import CORBA

import Messenger
from Conf import completename


class ClientMgr(object):
    def __init__(self):
        print ("-- Iniciado ClientMgr")
        # Initialise the ORB and find the root POA
        # sys.argv.extend(('-ORBtraceLevel', '25'))
        sys.argv.extend(("-ORBInitRef", "NameService=corbaname::127.0.0.1"))  # localhost"))
        self.orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)
        # Obtain a reference to the root naming context
        self.nameservice = self.orb.resolve_initial_references("NameService")
        self.rootContext = self.nameservice._narrow(CosNaming.NamingContext)
        if self.rootContext is None:
            print("++ Failed to narrow the root naming context")
            sys.exit(1)

        try:
            self.obj = self.rootContext.resolve(completename)
        except CosNaming.NamingContext.NotFound, ex:
            print("++ Server object not found in nameserver")
            sys.exit(1)

        # Narrow the object to an Example::Echo
        self.server = self.obj._narrow(Messenger.ServerContract)
        if self.server is None:
            print("++ Object reference is not an Server")
            sys.exit(1)

    def doit(self):
        print("-- Testing the thing")
        self.server.register(Messenger.credentials("juan", "juan","IOR"))
        self.server.login(Messenger.credentials("juan", "juan","IOR"))
        try:
            self.server.getUser("juan")
        except omniORB.CORBA.UNKNOWN:
            print("** No hay usuario")
        try:
            self.server.getFriends("juan")
        except omniORB.CORBA.UNKNOWN:
            print("** No hay usuario")


if __name__ == "__main__":
    print("< CORBA CLIENT>")
    ClientMgr().doit()
    print("</CORBA CLIENT>")
