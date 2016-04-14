import sys
from omniORB import CORBA
import Example

def doit():
    orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)
    # ior = sys.argv[1]
    ior = raw_input("Input IOR: ")
    print(ior)

    obj = orb.string_to_object(ior)

    eo = obj._narrow(Example.Echo)

    if eo is None:
        print("Object reference is not an Example::Echo")
        sys.exit(1)

    message = "Hello from Python"

    result = eo.echoString(message)

    print ("I said '"+message+"'. The object said '"+result+"'.")

    orb.shutdown(True)

if __name__ == "__main__":
    print("< CORBA CLIENT>")
    doit()
    print("</CORBA CLIENT>")