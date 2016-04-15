import sys

import CosNaming
from omniORB import CORBA
import Messenger

def doit():
    # Initialise the ORB and find the root POA
    # sys.argv.extend(('-ORBtraceLevel', '25'))
    sys.argv.extend(("-ORBInitRef", "NameService=corbaname::127.0.0.1"))  # localhost"))
    orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)

    # Obtain a reference to the root naming context
    obj = orb.resolve_initial_references("NameService")
    rootContext = obj._narrow(CosNaming.NamingContext)

    if rootContext is None:
        print "Failed to narrow the root naming context"
        sys.exit(1)

    # Resolve the name "test.my_context/ExampleEcho.Object"
    name = [CosNaming.NameComponent("test", "my_context"),
            CosNaming.NameComponent("ExampleEcho", "Object")]
    try:
        obj = rootContext.resolve(name)

    except CosNaming.NamingContext.NotFound, ex:
        print "Name not found"
        sys.exit(1)

    # Narrow the object to an Example::Echo
    eo = obj._narrow(Messenger.Echo)

    if eo is None:
        print "Object reference is not an Example::Echo"
        sys.exit(1)

    # Invoke the echoString operation
    message = "Hello from Python"
    result = eo.echoString(message)

    print "I said '%s'. The object said '%s'." % (message, result)

if __name__ == "__main__":
    print("< CORBA CLIENT>")
    doit()
    print("</CORBA CLIENT>")