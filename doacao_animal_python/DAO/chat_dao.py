
from exceptions.custom_exception import CustomException
from repository.mysql_connection import MYSQLConnection
from schemas.mensagem import Mensagem
from datetime import datetime as dt
from DAO.mensagem_dao import MensagemDAO

class ChatDAO:
    @staticmethod
    def send_message(id_remetente: int, id_destinatario: int, conteudo: str, tipo_remetente: str = None, tipo_destinatario: str = None, id_processo: int = None) -> None:
        mensagem = Mensagem(
            idMensagem=0,  
            dataMensagem=dt.now(),
            conteudo=conteudo,
            idRemetente=id_remetente,
            idDestinatario=id_destinatario,
            tipoRemetente=tipo_remetente,
            tipoDestinatario=tipo_destinatario,
            processoAdocao=None,
            id_processo=id_processo 
        )
        try: 
            MensagemDAO.create(mensagem)
            print("Mensagem enviada com sucesso!")
        except Exception as e:
              raise CustomException(f"Erro ao enviar mensagem: {e}")
          
    @staticmethod
    def get_conversation(id_user1: int, id_user2: int) -> list[Mensagem]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = """
        SELECT * FROM Mensagem 
        WHERE (idRemetente = %s AND idDestinatario = %s) 
           OR (idRemetente = %s AND idDestinatario = %s)
        ORDER BY dataMensagem ASC
        """
        try:
            cursor.execute(sql, (id_user1, id_user2, id_user2, id_user1))
            rows = cursor.fetchall()
            return [Mensagem(**row) for row in rows]
        except Exception as e:
            raise CustomException(f"Erro ao buscar conversa: {e}")
        finally:
            cursor.close()