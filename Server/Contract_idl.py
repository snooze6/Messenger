# Python stubs generated by omniidl from Contract.idl

import omniORB, _omnipy
from omniORB import CORBA, PortableServer
_0_CORBA = CORBA

_omnipy.checkVersion(3,0, __file__)


#
# Start of module "Messenger"
#
__name__ = "Messenger"
_0_Messenger = omniORB.openModule("Messenger", r"Contract.idl")
_0_Messenger__POA = omniORB.openModule("Messenger__POA", r"Contract.idl")


# interface ClientContract
_0_Messenger._d_ClientContract = (omniORB.tcInternal.tv_objref, "IDL:Messenger/ClientContract:1.0", "ClientContract")
omniORB.typeMapping["IDL:Messenger/ClientContract:1.0"] = _0_Messenger._d_ClientContract
_0_Messenger.ClientContract = omniORB.newEmptyClass()
class ClientContract :
    _NP_RepositoryId = _0_Messenger._d_ClientContract[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_Messenger.ClientContract = ClientContract
_0_Messenger._tc_ClientContract = omniORB.tcInternal.createTypeCode(_0_Messenger._d_ClientContract)
omniORB.registerType(ClientContract._NP_RepositoryId, _0_Messenger._d_ClientContract, _0_Messenger._tc_ClientContract)

# ClientContract operations and attributes
ClientContract._d_getName = ((), ((omniORB.tcInternal.tv_string,0), ), None)
ClientContract._d_sendMsg = (((omniORB.tcInternal.tv_string,0), ), ((omniORB.tcInternal.tv_string,0), ), None)

# ClientContract object reference
class _objref_ClientContract (CORBA.Object):
    _NP_RepositoryId = ClientContract._NP_RepositoryId

    def __init__(self):
        CORBA.Object.__init__(self)

    def getName(self, *args):
        return _omnipy.invoke(self, "getName", _0_Messenger.ClientContract._d_getName, args)

    def sendMsg(self, *args):
        return _omnipy.invoke(self, "sendMsg", _0_Messenger.ClientContract._d_sendMsg, args)

    __methods__ = ["getName", "sendMsg"] + CORBA.Object.__methods__

omniORB.registerObjref(ClientContract._NP_RepositoryId, _objref_ClientContract)
_0_Messenger._objref_ClientContract = _objref_ClientContract
del ClientContract, _objref_ClientContract

# ClientContract skeleton
__name__ = "Messenger__POA"
class ClientContract (PortableServer.Servant):
    _NP_RepositoryId = _0_Messenger.ClientContract._NP_RepositoryId


    _omni_op_d = {"getName": _0_Messenger.ClientContract._d_getName, "sendMsg": _0_Messenger.ClientContract._d_sendMsg}

ClientContract._omni_skeleton = ClientContract
_0_Messenger__POA.ClientContract = ClientContract
omniORB.registerSkeleton(ClientContract._NP_RepositoryId, ClientContract)
del ClientContract
__name__ = "Messenger"

# struct credentials
_0_Messenger.credentials = omniORB.newEmptyClass()
class credentials (omniORB.StructBase):
    _NP_RepositoryId = "IDL:Messenger/credentials:1.0"

    def __init__(self, username, password, IOR):
        self.username = username
        self.password = password
        self.IOR = IOR

_0_Messenger.credentials = credentials
_0_Messenger._d_credentials  = (omniORB.tcInternal.tv_struct, credentials, credentials._NP_RepositoryId, "credentials", "username", (omniORB.tcInternal.tv_string,0), "password", (omniORB.tcInternal.tv_string,0), "IOR", (omniORB.tcInternal.tv_string,0))
_0_Messenger._tc_credentials = omniORB.tcInternal.createTypeCode(_0_Messenger._d_credentials)
omniORB.registerType(credentials._NP_RepositoryId, _0_Messenger._d_credentials, _0_Messenger._tc_credentials)
del credentials

# typedef ... usuarios
class usuarios:
    _NP_RepositoryId = "IDL:Messenger/usuarios:1.0"
    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")
_0_Messenger.usuarios = usuarios
_0_Messenger._d_usuarios  = (omniORB.tcInternal.tv_sequence, omniORB.typeMapping["IDL:Messenger/ClientContract:1.0"], 0)
_0_Messenger._ad_usuarios = (omniORB.tcInternal.tv_alias, usuarios._NP_RepositoryId, "usuarios", (omniORB.tcInternal.tv_sequence, omniORB.typeMapping["IDL:Messenger/ClientContract:1.0"], 0))
_0_Messenger._tc_usuarios = omniORB.tcInternal.createTypeCode(_0_Messenger._ad_usuarios)
omniORB.registerType(usuarios._NP_RepositoryId, _0_Messenger._ad_usuarios, _0_Messenger._tc_usuarios)
del usuarios

# interface ServerContract
_0_Messenger._d_ServerContract = (omniORB.tcInternal.tv_objref, "IDL:Messenger/ServerContract:1.0", "ServerContract")
omniORB.typeMapping["IDL:Messenger/ServerContract:1.0"] = _0_Messenger._d_ServerContract
_0_Messenger.ServerContract = omniORB.newEmptyClass()
class ServerContract :
    _NP_RepositoryId = _0_Messenger._d_ServerContract[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_Messenger.ServerContract = ServerContract
_0_Messenger._tc_ServerContract = omniORB.tcInternal.createTypeCode(_0_Messenger._d_ServerContract)
omniORB.registerType(ServerContract._NP_RepositoryId, _0_Messenger._d_ServerContract, _0_Messenger._tc_ServerContract)

# ServerContract operations and attributes
ServerContract._d_login = ((omniORB.typeMapping["IDL:Messenger/credentials:1.0"], ), (omniORB.tcInternal.tv_boolean, ), None)
ServerContract._d_register = ((omniORB.typeMapping["IDL:Messenger/credentials:1.0"], ), (omniORB.tcInternal.tv_boolean, ), None)
ServerContract._d_getUser = (((omniORB.tcInternal.tv_string,0), ), (omniORB.typeMapping["IDL:Messenger/ClientContract:1.0"], ), None)
ServerContract._d_getFriends = (((omniORB.tcInternal.tv_string,0), ), (omniORB.typeMapping["IDL:Messenger/usuarios:1.0"], ), None)

# ServerContract object reference
class _objref_ServerContract (CORBA.Object):
    _NP_RepositoryId = ServerContract._NP_RepositoryId

    def __init__(self):
        CORBA.Object.__init__(self)

    def login(self, *args):
        return _omnipy.invoke(self, "login", _0_Messenger.ServerContract._d_login, args)

    def register(self, *args):
        return _omnipy.invoke(self, "register", _0_Messenger.ServerContract._d_register, args)

    def getUser(self, *args):
        return _omnipy.invoke(self, "getUser", _0_Messenger.ServerContract._d_getUser, args)

    def getFriends(self, *args):
        return _omnipy.invoke(self, "getFriends", _0_Messenger.ServerContract._d_getFriends, args)

    __methods__ = ["login", "register", "getUser", "getFriends"] + CORBA.Object.__methods__

omniORB.registerObjref(ServerContract._NP_RepositoryId, _objref_ServerContract)
_0_Messenger._objref_ServerContract = _objref_ServerContract
del ServerContract, _objref_ServerContract

# ServerContract skeleton
__name__ = "Messenger__POA"
class ServerContract (PortableServer.Servant):
    _NP_RepositoryId = _0_Messenger.ServerContract._NP_RepositoryId


    _omni_op_d = {"login": _0_Messenger.ServerContract._d_login, "register": _0_Messenger.ServerContract._d_register, "getUser": _0_Messenger.ServerContract._d_getUser, "getFriends": _0_Messenger.ServerContract._d_getFriends}

ServerContract._omni_skeleton = ServerContract
_0_Messenger__POA.ServerContract = ServerContract
omniORB.registerSkeleton(ServerContract._NP_RepositoryId, ServerContract)
del ServerContract
__name__ = "Messenger"

# interface Echo
_0_Messenger._d_Echo = (omniORB.tcInternal.tv_objref, "IDL:Messenger/Echo:1.0", "Echo")
omniORB.typeMapping["IDL:Messenger/Echo:1.0"] = _0_Messenger._d_Echo
_0_Messenger.Echo = omniORB.newEmptyClass()
class Echo :
    _NP_RepositoryId = _0_Messenger._d_Echo[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_Messenger.Echo = Echo
_0_Messenger._tc_Echo = omniORB.tcInternal.createTypeCode(_0_Messenger._d_Echo)
omniORB.registerType(Echo._NP_RepositoryId, _0_Messenger._d_Echo, _0_Messenger._tc_Echo)

# Echo operations and attributes
Echo._d_echoString = (((omniORB.tcInternal.tv_string,0), ), ((omniORB.tcInternal.tv_string,0), ), None)

# Echo object reference
class _objref_Echo (CORBA.Object):
    _NP_RepositoryId = Echo._NP_RepositoryId

    def __init__(self):
        CORBA.Object.__init__(self)

    def echoString(self, *args):
        return _omnipy.invoke(self, "echoString", _0_Messenger.Echo._d_echoString, args)

    __methods__ = ["echoString"] + CORBA.Object.__methods__

omniORB.registerObjref(Echo._NP_RepositoryId, _objref_Echo)
_0_Messenger._objref_Echo = _objref_Echo
del Echo, _objref_Echo

# Echo skeleton
__name__ = "Messenger__POA"
class Echo (PortableServer.Servant):
    _NP_RepositoryId = _0_Messenger.Echo._NP_RepositoryId


    _omni_op_d = {"echoString": _0_Messenger.Echo._d_echoString}

Echo._omni_skeleton = Echo
_0_Messenger__POA.Echo = Echo
omniORB.registerSkeleton(Echo._NP_RepositoryId, Echo)
del Echo
__name__ = "Messenger"

#
# End of module "Messenger"
#
__name__ = "Contract_idl"

_exported_modules = ( "Messenger", )

# The end.
