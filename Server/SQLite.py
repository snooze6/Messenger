# coding=utf-8
import sqlite3
import os
import sys

from Conf import dbpath, op_sucess, op_exists, op_failed


class User(object):
    def __init__(self, username, password, ior):
        self.username = username
        self.password = password
        self.ior = ior


class DAOGeneral(object):
    def __init__(self, path=dbpath):
        super(DAOGeneral, self).__init__()
        self.path = path
        print("-- Creating new DAOUsers")
        if not os.path.exists(path):
            print("** Database don't exists")
            self.conn = sqlite3.connect(path)
            if not os.path.exists(path):
                print("++ Database can't be created")
                sys.exit(1)
            else:
                print("-- Database created")
                self._createtables()
        else:
            self.conn = sqlite3.connect(path)
        print("-- Connected to database")
        self.conn.close()

    def _createtables(self):
        print("  -- Creando tablas")
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        c.execute(
            "CREATE TABLE users(" +
            "name VARCHAR(256)," +
            "pass VARCHAR(256)," +
            "ior  VARCHAR(512)," +
            "PRIMARY KEY (name)" +
            ");"
        )
        c = self.conn.cursor()
        c.execute(
            "CREATE TABLE friends(" +
            "user1 VARCHAR(256)," +
            "user2 VARCHAR(256)," +
            "PRIMARY KEY (user1, user2)" +
            "FOREIGN KEY (user1) REFERENCES users(name)" +
            "FOREIGN KEY (user2) REFERENCES users(name)" +
            ");"
        )
        self.conn.commit()
        self.conn.close()

    def _deletedb(self):
        print("** Eliminando base de datos")
        os.remove(self.path)


class DAOUsers(DAOGeneral):
    def __init__(self, path=dbpath):
        super(DAOUsers, self).__init__(path)

    def login(self, user):
        print("-- Logueando usuario " + user.username + ":" + user.password)
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        result = c.execute(
            'SELECT * FROM users WHERE name LIKE "' + user.username + '" AND pass LIKE "' + user.password + '" LIMIT 1;')
        for j in result:
            print ("  -- Usuario: " + j[0] + " - " + j[1] + " - " + j[2])
            self.conn.close()
            self._updateIOR(user)
            return True
        self.conn.close()
        return False

    def getUser(self, user):
        print("-- Buscando usuario " + user)
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        result = c.execute('SELECT * FROM users WHERE name LIKE "' + user + '" LIMIT 1;')
        for j in result:
            print ("  -- Usuario: " + j[0] + " - " + j[1] + " - " + j[2])
            self.conn.close()
            return User(j[0], j[1], j[2])
        self.conn.close()
        return None

    def registerUser(self, user):
        self.conn = sqlite3.connect(self.path)
        print("-- Registrando usuario " + user.username + " - contrasena " + user.password)
        c = self.conn.cursor()
        try:
            c.execute('INSERT INTO users VALUES ("' + user.username + '","' + user.password + '","' + user.ior + '")')
            res = op_sucess
        except sqlite3.IntegrityError:
            print ('++ Usuario ya existe')
            res = op_exists
        except sqlite3.Error:
            print ('++ Error desconocido')
            res = op_failed
        self.conn.commit()
        self.conn.close()
        return res

    def _updateIOR(self, user):
        self.conn = sqlite3.connect(self.path)
        print("-- Actualizando usuario " + user.username + " - contrasena " + user.password + " - " + user.ior)
        c = self.conn.cursor()
        string = 'UPDATE users SET ior="' + user.ior + '" WHERE name LIKE "' + user.username + '";'
        # print string
        c.execute(string)
        res = op_sucess
        self.conn.commit()
        self.conn.close()
        return res

    def _updateUser(self, user):
        self.conn = sqlite3.connect(self.path)
        print("-- Actualizando usuario " + user.username + " - contrasena " + user.password + " - " + user.ior)
        c = self.conn.cursor()
        string = 'UPDATE users SET pass="' + user.password + '", ior="' + user.ior + '" WHERE name LIKE "' + user.username + '";'
        # print string
        c.execute(string)
        self.conn.commit()
        self.conn.close()
        return op_sucess


class DAOAmigos(DAOGeneral):
    def __init__(self, path=dbpath):
        super(DAOAmigos, self).__init__(path)

    def areFriends(self, user1, user2):
        print ("-- Son " + user1 + " y " + user2 + " amigos?")
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        result = c.execute(
            'SELECT * FROM friends WHERE user1 LIKE "' + user1 + '" AND user2 LIKE "' + user2 + '" LIMIT 1;')
        for j in result:
            self.conn.close()
            return True
        self.conn.close()
        return False

    def makeFriends(self, user1, user2):
        print ("-- Hacer a " + user1 + " y " + user2 + " amigos")
        self.conn = sqlite3.connect(self.path)
        try:
            c = self.conn.cursor()
            c.execute('INSERT INTO friends VALUES ("' + user1 + '", "' + user2 + '");')
            res = op_sucess
        except sqlite3.IntegrityError:
            res = op_exists
        except sqlite3.Error:
            res = op_failed
        self.conn.commit()
        self.conn.close()
        return res

    def deleteFriends(self, user1, user2):
        print ("-- Deshacer a " + user1 + " y " + user2 + " de amigos")
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        c.execute('DELETE FROM friends WHERE user1 LIKE "' + user1 + '" AND user2 LIKE "' + user2 + '";')
        self.conn.commit()
        self.conn.close()
        return op_sucess

    def getRequests(self, user):
        print ("-- Obteniendo peticiones de amistad del usuario "+user)
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        string = 'SELECT user1 FROM friends WHERE user2 LIKE "' + user + '" AND user1 NOT IN (SELECT user2 FROM friends WHERE user1 LIKE "'+ user +'");'
        # print (string)
        result = c.execute(string)
        ret = []
        for j in result:
            print ("  -- "+j[0])
            ret.append(j[0])
        self.conn.close()
        return ret




class DAO(DAOUsers, DAOAmigos):
    def __init__(self, path=dbpath):
        super(DAOUsers, self).__init__(path)


if __name__ == "__main__":
    print("< SQLite Tester>")

    dao = DAO('test.db')
    # dao._deletedb()

    # user = dao.getUser('juan')
    # if not user is None:
    #     print ('-- Está')
    #     print ("-- Usuario: " + user.username + "-" + user.password)
    # else:
    #     print ('-- No Está')
    #
    # resop = dao.registerUser(User('juan', 'nauj', 'ior'))
    # if resop==op_sucess:
    #     print('-- Registrado')
    # elif resop==op_exists:
    #     print('++ Usuario ya existe')
    # elif resop==op_failed:
    #     print('++ Ha fallado el registro')
    #
    # resop = dao.registerUser(User('pedro', 'pedro', 'ior'))
    # if resop==op_sucess:
    #     print('-- Registrado')
    # elif resop==op_exists:
    #     print('++ Usuario ya existe')
    # elif resop==op_failed:
    #     print('++ Ha fallado el registro')
    #
    # user = dao.getUser('pedro')
    # if not user is None:
    #     print ('-- Está')
    #     print ("-- Usuario: " + user.username + "-" + user.password)
    # else:
    #     print ('-- No Está')

    # user = dao.getUser('asdf')
    # if not user is None:
    #     print ('-- Está')
    #     print ("-- Usuario: " + user.username + " - " + user.password + " - " + user.ior)
    # else:
    #     print ('-- No Está')
    # dao._updateUser(User('asdf', 'asdf', 'asdf'))

    # print dao.areFriends('juan','pedro')
    print dao.makeFriends('juan', 'pedro')
    # print dao.deleteFriends('juan','pedro')
    # print dao.deleteFriends('juan', 'pedro')
    print dao.areFriends('juan','pedro')

    # print dao.login(User('juan', 'nauj', 'newior'))
    # print dao.getUser('juan').ior
    # print dao.login(User('juan', 'wrong', 'wrong'))

    friendrequests = dao.getRequests('pedro')
    for j in friendrequests:
        print ("-- "+j)

    print("</SQLite Tester>")
