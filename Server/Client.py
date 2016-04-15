import Messenger, Messenger__POA

class Echo_i(Messenger__POA.Echo):
    def echoString(self, mesg):
        print("echoString() called with message:"+mesg)
        return mesg