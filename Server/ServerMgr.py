import sys
from omniORB import CORBA, PortableServer
import CosNaming
import Messenger, Messenger__POA
from Client import Echo_i

class Server(object):
    def doit(self):
        # Initialise the ORB and find the root POA
        # sys.argv.extend(('-ORBtraceLevel', '25'))
        sys.argv.extend(("-ORBInitRef", "NameService=corbaname::127.0.0.1"))  # localhost"))
        orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)
        poa = orb.resolve_initial_references("RootPOA")
        poaManager = poa._get_the_POAManager()
        poaManager.activate()

        # Create an instance of Echo_i and an Echo object reference
        ei = Echo_i()
        eo = ei._this()

        ref = poa.servant_to_reference(ei)

        # Obtain a reference to the root naming context
        obj = orb.resolve_initial_references("NameService")
        # obj = orb.resolve_initial_references("NameService")
        rootContext = obj._narrow(CosNaming.NamingContext)

        if rootContext is None:
            print "Failed to narrow the root naming context"
            sys.exit(1)

        # Bind a context named "test.my_context" to the root context
        name = [CosNaming.NameComponent("test", "my_context")]
        try:
            testContext = rootContext.bind_new_context(name)
            print "New test context bound"

        except CosNaming.NamingContext.AlreadyBound, ex:
            print "Test context already exists"
            obj = rootContext.resolve(name)
            testContext = obj._narrow(CosNaming.NamingContext)
            if testContext is None:
                print "test.mycontext exists but is not a NamingContext"
                sys.exit(1)

        # Bind the Echo object to the test context
        name = [CosNaming.NameComponent("ExampleEcho", "Object")]
        try:
            testContext.bind(name, eo)
            print "New ExampleEcho object bound"

        except CosNaming.NamingContext.AlreadyBound:
            testContext.rebind(name, eo)
            print "ExampleEcho binding already existed -- rebound"

        # Activate the POA
        poaManager = poa._get_the_POAManager()
        poaManager.activate()

        # Block for ever (or until the ORB is shut down)
        orb.run()

if __name__ == "__main__":
    print("< CORBA SERVER>")
    Server().doit()
    print("</CORBA SERVER>")