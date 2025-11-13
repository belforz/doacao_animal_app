from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.mensagem import Mensagem


class MensagemDAO:
    @staticmethod
    def create(mensagem: Mensagem) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO Mensagem (dataMensagem, conteudo, idRemetente, tipoRemetente, idDestinatario, tipoDestinatario, id_processo)
        VALUES (%s, %s, %s, %s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                mensagem.dataMensagem, mensagem.conteudo, mensagem.idRemetente, mensagem.tipoRemetente, mensagem.idDestinatario, mensagem.tipoDestinatario, mensagem.id_processo
            ))
            conn.commit()
            print("Mensagem criada com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao criar Mensagem: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> Mensagem:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Mensagem WHERE idMensagem = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                return Mensagem(
                    id=row['idMensagem'],
                    dataMensagem=row['dataMensagem'],
                    conteudo=row['conteudo'],
                    idRemetente=row['idRemetente'],
                    tipoRemetente=row['tipoRemetente'],
                    idDestinatario=row['idDestinatario'],
                    tipoDestinatario=row['tipoDestinatario'],
                    id_processo=row['id_processo']
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Mensagem: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[Mensagem]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Mensagem"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                Mensagem(
                    id=row['idMensagem'],
                    dataMensagem=row['dataMensagem'],
                    conteudo=row['conteudo'],
                    idRemetente=row['idRemetente'],
                    tipoRemetente=row['tipoRemetente'],
                    idDestinatario=row['idDestinatario'],
                    tipoDestinatario=row['tipoDestinatario'],
                    id_processo=row['id_processo']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao buscar Mensagem: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[Mensagem]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Mensagem"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                Mensagem(
                    id=row['idMensagem'],
                    dataMensagem=row['dataMensagem'],
                    conteudo=row['conteudo'],
                    idRemetente=row['idRemetente'],
                    tipoRemetente=row['tipoRemetente'],
                    idDestinatario=row['idDestinatario'],
                    tipoDestinatario=row['tipoDestinatario'],
                    id_processo=row['id_processo']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar Mensagem: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(mensagem: Mensagem) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE Mensagem SET dataMensagem=%s, conteudo=%s, idRemetente=%s, tipoRemetente=%s, idDestinatario=%s, tipoDestinatario=%s, id_processo=%s
        WHERE idMensagem=%s
        """
        try:
            cursor.execute(sql, (
                mensagem.dataMensagem, mensagem.conteudo, mensagem.idRemetente, mensagem.tipoRemetente, mensagem.idDestinatario, mensagem.tipoDestinatario, mensagem.id_processo, mensagem.id
            ))
            conn.commit()
            print("Mensagem atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Mensagem: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM Mensagem WHERE idMensagem = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Mensagem deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Mensagem: {e}")
        finally:
            cursor.close()