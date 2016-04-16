# coding=utf-8
import sqlite3
import os
import sys

from Conf import dbpath, op_sucess, op_exists, op_failed


class User(object):
    def __init__(self, username, password):
        self.username = username
        self.password = password

class DAO(object):
    def __init__(self, path=dbpath):
        super(DAO, self).__init__()
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

    def getUser(self, user):
        print("-- Buscando usuario " + user)
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        result = c.execute('SELECT * FROM users WHERE name LIKE "' + user + '" LIMIT 1;')
        for j in result:
            # print ("  -- Usuario: "+j[0]+"-"+j[1])
            self.conn.close()
            return User(j[0], j[1])
        return None

    def registerUser(self, user):
        self.conn = sqlite3.connect(self.path)
        print("-- Registrando usuario " + user.username + " - contraseña " + user.password)
        c = self.conn.cursor()
        try:
            c.execute('INSERT INTO users VALUES ("' + user.username + '","' + user.password + '")')
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

    def _createtables(self):
        print("  -- Creando tablas")
        self.conn = sqlite3.connect(self.path)
        c = self.conn.cursor()
        c.execute(
            "CREATE TABLE users(" +
            "name VARCHAR(256)," +
            "pass VARCHAR(256)," +
            "PRIMARY KEY (name)" +
            ");"
        )
        self.conn.commit()
        self.conn.close()

    def _deletedb(self):
        print("-- Eliminando base de datos")
        os.remove(self.path)


if __name__ == "__main__":
    print("< SQLite Tester>")

    dao = DAO('test.db')
    user = dao.getUser('juan')
    if not user is None:
        print ('-- Está')
        print ("-- Usuario: " + user.username + "-" + user.password)
    else:
        print ('-- No Está')

    resop = dao.registerUser(User('juan', 'nauj'))
    if resop==op_sucess:
        print('-- Registrado')
    elif resop==op_exists:
        print('++ Usuario ya existe')
    elif resop==op_failed:
        print('++ Ha fallado el registro')

    resop = dao.registerUser(User('pedro', 'pedro'))
    if resop==op_sucess:
        print('-- Registrado')
    elif resop==op_exists:
        print('++ Usuario ya existe')
    elif resop==op_failed:
        print('++ Ha fallado el registro')

    user = dao.getUser('pedro')
    if not user is None:
        print ('-- Está')
        print ("-- Usuario: " + user.username + "-" + user.password)
    else:
        print ('-- No Está')

    print("</SQLite Tester>")
