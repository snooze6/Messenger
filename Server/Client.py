import Messenger, Messenger__POA

class Client(Messenger__POA.ClientContract):
    def __init__(self, username):
        self.username = username
    def getName(self):
        return self.username
    def sendMsg(self, msg):
        print("Mensaje recibido: "+msg)
        return msg


class Echo_i(Messenger__POA.Echo):
    def echoString(self, mesg):
        print("echoString() called with message:"+mesg)
        return mesg