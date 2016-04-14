import sqlite3
import os
import sys

class DAOUsers(object):


    def __init__(self, path):
        super(DAOUsers, self).__init__()
        print("-- Creating new DAOUsers")
        if not os.path.exists(path):
            print("** Database don't exists")
            conn = sqlite3.connect(path)
            if not os.path.exists(path):
                print("++ Database can't be created")
                sys.exit(1)
            else:
                print("-- Database created")
        else:
            conn = sqlite3.connect(path)
        print("-- Connected to database")


if __name__ == "__main__":
    print("< SQLite Tester>")

    dao = DAOUsers('test.db')

    print("</SQLite Tester>")