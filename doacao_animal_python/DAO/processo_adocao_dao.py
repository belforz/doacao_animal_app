from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.processo_adocao import ProcessoAdocao


class ProcessoAdocaoDAO:
    @staticmethod
    def create(processo_adocao: ProcessoAdocao) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO ProcessoAdocao (id_animal, id_adotante, status, dataInicio)
        VALUES (%s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                processo_adocao.id_animal, processo_adocao.id_adotante, processo_adocao.statusProcesso.value, processo_adocao.dataInicio
            ))
            conn.commit()
            print("Processo de adoção criado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao criar Processo de Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> ProcessoAdocao:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM ProcessoAdocao WHERE idPAdocao = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                from DAO.animal_dao import AnimalDAO
                animal = AnimalDAO.read(row['id_animal'])
                from DAO.adotante_dao import AdotanteDAO
                adotante = AdotanteDAO.read(row['id_adotante'])
                etapas = []
                mensagens = []
                return ProcessoAdocao(
                    idPAdocao=row['idPAdocao'],
                    animal=animal,
                    adotante=adotante,
                    statusProcesso=row['statusProcesso'],
                    dataInicio=row['dataInicio'],
                    id_animal=row['id_animal'],
                    id_adotante=row['id_adotante'],
                    etapas=etapas,
                    mensagens=mensagens
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Processo de Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[ProcessoAdocao]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM ProcessoAdocao"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            processos = []
            for row in rows:
                from DAO.animal_dao import AnimalDAO
                animal = AnimalDAO.read(row['id_animal'])
                from DAO.adotante_dao import AdotanteDAO
                adotante = AdotanteDAO.read(row['id_adotante'])
                etapas = []
                mensagens = []
                processos.append(ProcessoAdocao(
                    idPAdocao=row['idPAdocao'],
                    animal=animal,
                    adotante=adotante,
                    statusProcesso=row['statusProcesso'],
                    dataInicio=row['dataInicio'],
                    id_animal=row['id_animal'],
                    id_adotante=row['id_adotante'],
                    etapas=etapas,
                    mensagens=mensagens
                ))
            return processos
        except Exception as e:
            raise CustomException(f"Erro ao listar Processos de Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(processo_adocao: ProcessoAdocao) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE ProcessoAdocao SET id_animal=%s, id_adotante=%s, status=%s, dataInicio=%s
        WHERE id=%s
        """
        try:
            cursor.execute(sql, (
                processo_adocao.id_animal, processo_adocao.id_adotante, processo_adocao.statusProcesso.value, processo_adocao.dataInicio, processo_adocao.idPAdocao
            ))
            conn.commit()
            print("Processo de Adoção atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Processo de Adoção: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM ProcessoAdocao WHERE id = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Processo de Adoção deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Processo de Adoção: {e}")
        finally:
            cursor.close()