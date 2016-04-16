import CORBA
import sys

import Messenger, Messenger__POA
from Conf import dbpath, op_sucess
from SQLite import DAO, User


class Client(Messenger__POA.ClientContract):
    def __init__(self, username):
        self.username = username

    def getName(self):
        return self.username

    def sendMsg(self, msg):
        print("Mensaje recibido: " + msg)
        return msg

    def transfer(self, path):
        print("Archivo recibido: " + path)
        return path


class Echo_i(Messenger__POA.Echo):
    def echoString(self, mesg):
        print("echoString() called with message:" + mesg)
        return mesg


class Server(Messenger__POA.ServerContract):
    def __init__(self, verbose=True):
        self.v = verbose
        self.dao = DAO(dbpath)
        if self.v:
            print ('-- Server creado')

    def login(self, credentials):
        if self.v:
            print ('-- Intentando login <' + credentials.username + '-' + credentials.password + '>')
            print ('   IOR: ' + credentials.IOR)
        return self.dao.login(User(credentials.username, credentials.password, credentials.IOR))

    def register(self, credentials):
        if self.v:
            print ('-- Intentando registrar <' + credentials.username + '-' + credentials.password + '>')
            print ('   IOR: ' + credentials.IOR)
        return self.dao.registerUser(User(credentials.username, credentials.password, credentials.IOR)) == op_sucess

    def getUser(self, username):
        if self.v:
            print ('-- Intentando cojer al usuario: <' + username + '>')
        user = self.dao.getUser(username)
        return self._bindUser(user)

    def getFriends(self, username):
        if self.v:
            print ('-- Intentando cojer los amigos de <' + username + '>')
        return [None]

    def _bindUser(self, user):
        print(user)
        orb = CORBA.ORB_init(("-ORBInitRef", "NameService=corbaname::127.0.0.1"), CORBA.ORB_ID)
        ior = user.ior
        obj = orb.string_to_object(ior)
        eo = obj._narrow(Messenger.ClientContract)
        if eo is None:
            print("++ Object reference is not an User")
            sys.exit(1)
        # message = "Hello from Python"
        # result = eo.echoString(message)
        # print "I said '%s'. The object said '%s'." % (message, result)
        return eo

