# coding=utf-8
import sqlite3
import os
import sys

from Conf import dbpath


class User(object):
    def __init__(self, username, password):
        self.username = username
        self.password = password

class DAO(object):

    def __init__(self, path=dbpath):
        super(DAO, self).__init__()
        self.path=path
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

    def getUser(self, user):
        print("-- Buscando usuario "+user.username)
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        result = c.execute('SELECT * FROM users WHERE name LIKE "'+user.username+'" LIMIT 1;')
        for j in result:
            print j
        self.conn.close()


    def registerUser(self, user):
        self.getUser(user)

        self.conn = sqlite3.connect(self.path)
        print("-- Registrando usuario " + user.username + " - contraseña " + user.password)
        c = self.conn.cursor()
        result = c.execute('INSERT INTO users VALUES ("'+user.username+'","'+user.password+'")')
        self.conn.close()

        self.getUser(user)

    def _createtables(self):
        print("  -- Creando tablas")
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        c.execute(
                  "CREATE TABLE users("+
                  "name VARCHAR(256),"+
                  "pass VARCHAR(256),"+
                  "PRIMARY KEY (name)"+
                  ");"
                  )
        self.conn.close()

    def deletedb(self):
        print("-- Eliminando base de datos")
        os.remove(self.path)

if __name__ == "__main__":
    print("< SQLite Tester>")

    dao = DAO('test.db')
    dao.registerUser(User('juan','nauj'))
    dao.deletedb()

    print("</SQLite Tester>")