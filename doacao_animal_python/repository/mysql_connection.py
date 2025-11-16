import mysql.connector
from mysql.connector import Error

class MYSQLConnection():
    _connection = None
    
    @staticmethod
    def get_connection():
        if MYSQLConnection._connection is None:
            try:
                MYSQLConnection._connection = mysql.connector.connect(
                    host= "localhost",
                    database= "doacao_animal",
                    user= "root",
                    password="123456"
                )
                print("Conexão bem-sucedida ao banco de dados MySQL")
            except Error as e:
                print(f"Erro ao conectar ao MySQL: {e}")
        return MYSQLConnection._connection

    @staticmethod
    def close_connection():
        if MYSQLConnection._connection is not None and MYSQLConnection._connection.is_connected():
            MYSQLConnection._connection.close()
            MYSQLConnection._connection = None
            print("Conexão ao banco de dados MySQL fechada")
    