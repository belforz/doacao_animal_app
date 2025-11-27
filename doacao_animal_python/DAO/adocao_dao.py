from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.adocao import Adocao


class AdocaoDAO:
    @staticmethod
    def create(adocao: Adocao) -> Adocao:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO Adocao (dataAdocao, descricao, termos, id_processo) 
        VALUES (%s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                adocao.dataAdocao, adocao.descricao, adocao.termos, adocao.id_processo
            ))
            conn.commit()
            adocao.idAdocao = cursor.lastrowid
            print("Adoção criada com sucesso!")
            return adocao
        except Exception as e:
            raise CustomException(f"Erro ao criar Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> Adocao:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Adocao WHERE idAdocao = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                from DAO.processo_adocao_dao import ProcessoAdocaoDAO
                processo = ProcessoAdocaoDAO.read(row['id_processo'])
                return Adocao(
                    idAdocao=row['idAdocao'],
                    dataAdocao=row['dataAdocao'].date(),
                    descricao=row['descricao'],
                    termos=row['termos'],
                    processoAdocao=processo,
                    id_processo=row['id_processo'],
                    suportes=[]
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[Adocao]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Adocao"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                Adocao(
                    idAdocao=row['idAdocao'],
                    dataAdocao=row['dataAdocao'].date(),
                    descricao=row['descricao'],
                    termos=row['termos'],
                    processoAdocao=None,
                    id_processo=row['id_processo']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar Adoções: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(adocao: Adocao) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE Adocao SET dataAdocao=%s, descricao=%s, termos=%s, id_processo=%s
        WHERE idAdocao=%s
        """
        try:
            cursor.execute(sql, (
                adocao.dataAdocao, adocao.descricao, adocao.termos, adocao.id_processo, adocao.idAdocao
            ))
            conn.commit()
            print("Adoção atualizada com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM Adocao WHERE idAdocao = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Adoção deletada com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Adoção: {e}")
        finally:
            cursor.close()