 
 
#  LOGIN: Verificar credenciais de Adotante e Protetor por senha e email via READ do dao
from schemas.protetor import Protetor
from schemas.adotante import Adotante
from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException

class LoginDAO:
    def loginAdotante(self, email: str, senha: str) -> Adotante:
        connection = MYSQLConnection.get_connection()
        cursor = connection.cursor(dictionary=True)
        sql = "SELECT * FROM Adotante WHERE email = %s AND senha = %s"
        try:
            cursor.execute(sql, (email, senha))
            row = cursor.fetchone()
            if row:
                return Adotante(
                    id=row['idAdotante'],
                    nome=row['nome'],
                    senha=row['senha'],
                    endereco=row['endereco'],
                    email=row['email'],
                    documento=row['documento'],
                    telefone=row['telefone'],
                    preferenciaAdocao=row['preferenciaAdocao']
                )
        except Exception as e:
            raise CustomException("Erro ao buscar Adotante", e)
        return None

    def loginProtetor(self, email: str, senha: str):
        connection = MYSQLConnection.get_connection()
        cursor = connection.cursor(dictionary=True)
        sql = "SELECT * FROM Protetor WHERE email = %s AND senha = %s"
        try:
            cursor.execute(sql, (email, senha))
            row = cursor.fetchone()
            if row:
                return Protetor(
                        id=row['idProtetor'],
                        nome=row['nome'],
                        senha=row['senha'],
                        endereco=row['endereco'],
                        email=row['email'],
                        documento=row['documento'],
                        telefone=row['telefone'],
                        tipo=row['tipo']
                )
        except Exception as e:
            raise CustomException("Erro ao buscar Protetor", e)
        return None