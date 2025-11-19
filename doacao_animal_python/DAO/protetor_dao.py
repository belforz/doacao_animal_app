from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.protetor import Protetor

class ProtetorDAO:
    @staticmethod
    def create(protetor: Protetor) -> Protetor:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO Protetor (nome, email, documento, telefone, senha, endereco, tipo)
        VALUES (%s, %s, %s, %s, %s, %s, %s)
        """
        try: 
            cursor.execute(sql, (
                protetor.nome, protetor.email, protetor.documento, protetor.telefone,
                protetor.senha, protetor.endereco, protetor.tipo
            ))
            conn.commit()
            # Pegar o ID gerado
            protetor_id = cursor.lastrowid
            # Retornar protetor com ID preenchido
            protetor.id = protetor_id
            print("Protetor criado com sucesso!")
            return protetor
        except Exception as e:
            raise CustomException(f"Erro ao criar Protetor: {e}")
        finally: 
            cursor.close()
        
    @staticmethod
    def read(id: int) -> Protetor:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Protetor WHERE idProtetor = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                return Protetor(
                    id=row['idProtetor'],
                    nome=row['nome'],
                    email=row['email'],
                    documento=row['documento'],
                    telefone=row['telefone'],
                    senha=row['senha'],
                    endereco=row['endereco'],
                    tipo=row['tipo']
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Protetor: {e}")
        finally:
            cursor.close()
    
    @staticmethod
    def readAll() -> list[Protetor]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Protetor"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                Protetor(
                    id=row['idProtetor'],
                    nome=row['nome'],
                    email=row['email'],
                    documento=row['documento'],
                    telefone=row['telefone'],
                    senha=row['senha'],
                    endereco=row['endereco'],
                    tipo=row['tipo']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar Protetores: {e}")
        finally:
            cursor.close()
        
    @staticmethod
    def update(protetor: Protetor) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE Protetor SET nome=%s, email=%s, documento=%s, telefone=%s, senha=%s, endereco=%s, tipo=%s WHERE idProtetor=%s
        """
        try:
            cursor.execute(sql, (
                protetor.nome, protetor.email, protetor.documento, protetor.telefone,
                protetor.senha, protetor.endereco, protetor.tipo, protetor.id
            ))
            conn.commit()
            print("Protetor atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Protetor: {e}")
        finally:
            cursor.close()
    
    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM Protetor WHERE idProtetor = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Protetor deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Protetor: {e}")
        finally:
            cursor.close()