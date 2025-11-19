from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.adotante import Adotante


class AdotanteDAO:
    @staticmethod
    def create(adotante: Adotante) -> Adotante:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO Adotante (nome, email, documento, telefone, senha, endereco, preferenciaAdocao)
        VALUES (%s, %s, %s, %s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                adotante.nome, adotante.email, adotante.documento, adotante.telefone,
                adotante.senha, adotante.endereco, adotante.preferenciaAdocao
            ))
            conn.commit()
            # Pegar o ID gerado
            adotante_id = cursor.lastrowid
            # Retornar adotante com ID preenchido
            adotante.id = adotante_id
            print("Adotante criado com sucesso!")
            return adotante
        except Exception as e:
            raise CustomException(f"Erro ao criar Adotante: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> Adotante:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Adotante WHERE idAdotante = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                return Adotante(
                    id=row['idAdotante'],
                    nome=row['nome'],
                    email=row['email'],
                    documento=row['documento'],
                    telefone=row['telefone'],
                    senha=row['senha'],
                    endereco=row['endereco'],
                    preferenciaAdocao=row['preferenciaAdocao']
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Adotante: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[Adotante]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Adotante"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                Adotante(
                    id=row['idAdotante'], nome=row['nome'], email=row['email'],
                    documento=row['documento'], telefone=row['telefone'], senha=row['senha'],
                    endereco=row['endereco'], preferenciaAdocao=row['preferenciaAdocao']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar Adotantes: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(adotante: Adotante) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE Adotante SET nome=%s, email=%s, documento=%s, telefone=%s, senha=%s, endereco=%s, preferenciaAdocao=%s
        WHERE idAdotante=%s
        """
        try:
            cursor.execute(sql, (
                adotante.nome, adotante.email, adotante.documento, adotante.telefone,
                adotante.senha, adotante.endereco, adotante.preferenciaAdocao, adotante.id
            ))
            conn.commit()
            print("Adotante atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Adotante: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM Adotante WHERE idAdotante = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Adotante deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Adotante: {e}")
        finally:
            cursor.close()