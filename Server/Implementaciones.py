import Messenger, Messenger__POA


class Client(Messenger__POA.ClientContract):
    def __init__(self, username):
        self.username = username

    def getName(self):
        return self.username

    def sendMsg(self, msg):
        print("Mensaje recibido: " + msg)
        return msg


class Echo_i(Messenger__POA.Echo):
    def echoString(self, mesg):
        print("echoString() called with message:" + mesg)
        return mesg


class Server(Messenger__POA.ServerContract):
    def __init__(self, verbose=True):
        self.v = verbose
        if self.v:
            print ('-- Server creado')

    def login(self, credentials):
        if self.v:
            print ('-- Intentando login <' + credentials.username + '-' + credentials.password + '>')
            print ('   IOR: ' + credentials.IOR)
        return True

    def register(self, credentials):
        if self.v:
            print ('-- Intentando registrar <' + credentials.username + '-' + credentials.password + '>')
            print ('   IOR: ' + credentials.IOR)
        return True

    def getUser(self, username):
        if self.v:
            print ('-- Intentando cojer al usuario: <' + username + '>')
        return None

    def getFriends(self, username):
        if self.v:
            print ('-- Intentando cojer los amigos de <' + username + '>')
        return [None]
