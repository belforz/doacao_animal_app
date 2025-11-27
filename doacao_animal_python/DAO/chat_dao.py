
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
    def get_conversation(id_user1: int, id_user2: int, id_processo: int = None) -> list[Mensagem]:
        conn = MYSQLConnection.get_connection()
        if conn is None:
            raise CustomException("Erro ao buscar conversa: sem conexão com o banco de dados")
        cursor = conn.cursor(dictionary=True)
        base_sql = """
        SELECT * FROM Mensagem 
        WHERE ((idRemetente = %s AND idDestinatario = %s) 
           OR (idRemetente = %s AND idDestinatario = %s))
        """
        params = [id_user1, id_user2, id_user2, id_user1]
        if id_processo:
            base_sql += " AND id_processo = %s"
            params.append(id_processo)
        base_sql += " ORDER BY dataMensagem ASC"
        try:
            cursor.execute(base_sql, tuple(params))
            rows = cursor.fetchall()
            messages = []
            for row in rows:
                messages.append(Mensagem(
                    idMensagem=row.get('idMensagem'),
                    dataMensagem=row.get('dataMensagem'),
                    conteudo=row.get('conteudo'),
                    idRemetente=row.get('idRemetente'),
                    idDestinatario=row.get('idDestinatario'),
                    tipoRemetente=row.get('tipoRemetente'),
                    tipoDestinatario=row.get('tipoDestinatario'),
                    processoAdocao=None,
                    id_processo=row.get('id_processo')
                ))
            return messages
        except Exception as e:
            raise CustomException(f"Erro ao buscar conversa: {e}")
        finally:
            cursor.close()