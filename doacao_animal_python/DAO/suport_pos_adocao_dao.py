from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.suporte_pos_adocao import SuportePosAdocao


class SuportePosAdocaoDAO:
    @staticmethod
    def create(suporte_pos_adocao: SuportePosAdocao) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO SuportePosAdocao (dataRegistro, tipoSolicitacao, descricao, idAdocao)
        VALUES (%s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                suporte_pos_adocao.dataRegistro, suporte_pos_adocao.tipoSolicitacao, suporte_pos_adocao.descricao, suporte_pos_adocao.idAdocao
            ))
            conn.commit()
            print("Suporte pós adoção criado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao criar Suporte pós adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> SuportePosAdocao:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM SuportePosAdocao WHERE id = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                return SuportePosAdocao(
                    id=row['id'],
                    dataRegistro=row['dataRegistro'],
                    tipoSolicitacao=row['tipoSolicitacao'],
                    descricao=row['descricao'],
                    idAdocao=row['idAdocao']
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Suporte pós adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[SuportePosAdocao]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM SuportePosAdocao"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                SuportePosAdocao(
                    id=row['id'],
                    dataRegistro=row['dataRegistro'],
                    tipoSolicitacao=row['tipoSolicitacao'],
                    descricao=row['descricao'],
                    idAdocao=row['idAdocao']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao buscar Suporte pós adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[SuportePosAdocao]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM SuportePosAdocao"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                SuportePosAdocao(
                    id=row['id'],
                    dataRegistro=row['dataRegistro'],
                    tipoSolicitacao=row['tipoSolicitacao'],
                    descricao=row['descricao'],
                    idAdocao=row['idAdocao']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar Suporte pós adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(suporte_pos_adocao: SuportePosAdocao) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE SuportePosAdocao SET dataRegistro=%s, tipoSolicitacao=%s, descricao=%s, idAdocao=%s
        WHERE id=%s
        """
        try:
            cursor.execute(sql, (
                suporte_pos_adocao.dataRegistro, suporte_pos_adocao.tipoSolicitacao, suporte_pos_adocao.descricao, suporte_pos_adocao.idAdocao, suporte_pos_adocao.id
            ))
            conn.commit()
            print("Suporte pós adoção atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Suporte pós adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM SuportePosAdocao WHERE idSuporte = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Suporte pós adoção deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Suporte pós adoção: {e}")
        finally:
            cursor.close()