import sys

import CosNaming
from omniORB import CORBA

from Conf import contextname, servername
from Implementaciones import Server


class ServerMgr(object):
    def __init__(self):
        # Initialise the ORB and find the root POA
        # sys.argv.extend(('-ORBtraceLevel', '25'))
        sys.argv.extend(("-ORBInitRef", "NameService=corbaname::127.0.0.1"))  # localhost"))
        self.orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)
        self.poa = self.orb.resolve_initial_references("RootPOA")
        self.poaManager = self.poa._get_the_POAManager()
        self.poaManager.activate()

        # Create an instance of Server and an Server object reference
        self.server = Server()
        self.serverref = self.server._this()

        self.poa.servant_to_reference(self.server)

        # Obtain a reference to the root naming context
        self.obj = self.orb.resolve_initial_references("NameService")

        # obj = orb.resolve_initial_references("NameService")
        self.rootContext = self.obj._narrow(CosNaming.NamingContext)
        if self.rootContext is None:
            print "Failed to narrow the root naming context"
            sys.exit(1)

        # Bind a context named "test.my_context" to the root context
        try:
            self.testContext = self.rootContext.bind_new_context(contextname)
            print "New test context bound"

        except CosNaming.NamingContext.AlreadyBound, ex:
            print "Test context already exists"
            self.obj = self.rootContext.resolve(contextname)
            self.testContext = self.obj._narrow(CosNaming.NamingContext)
            if self.testContext is None:
                print "test.mycontext exists but is not a NamingContext"
                sys.exit(1)

        # Bind the Echo object to the test context

        try:
            self.testContext.bind(servername, self.serverref)
            print "New ExampleEcho object bound"

        except CosNaming.NamingContext.AlreadyBound:
            self.testContext.rebind(servername, self.serverref)
            print "ExampleEcho binding already existed -- rebound"

        # Activate the POA
        self.poaManager = self.poa._get_the_POAManager()
        self.poaManager.activate()

    def doit(self):
        # Block for ever (or until the ORB is shut down)
        self.orb.run()

if __name__ == "__main__":
    print("< CORBA SERVER>")
    ServerMgr().doit()
    print("</CORBA SERVER>")