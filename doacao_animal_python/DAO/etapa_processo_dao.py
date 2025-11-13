from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.etapa_processo import EtapaProcesso


class EtapaProcessoDAO:
    @staticmethod
    def create(etapa_processo: EtapaProcesso) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO EtapaProcesso (data, observacoes, id_processo, statusEtapa, tipoEtapa)
        VALUES (%s, %s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                etapa_processo.data, etapa_processo.observacoes, etapa_processo.id_processo,
                etapa_processo.statusEtapa, etapa_processo.tipoEtapa
            ))
            conn.commit()
            print("EtapaProcesso criado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao criar EtapaProcesso: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> EtapaProcesso:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM EtapaProcesso WHERE idEtapaProcesso = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                return EtapaProcesso(
                    id=row['idEtapaProcesso'],
                    data=row['data'],
                    observacoes=row['observacoes'],
                    id_processo=row['id_processo'],
                    statusEtapa=row['statusEtapa'],
                    tipoEtapa=row['tipoEtapa']
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar EtapaProcesso: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[EtapaProcesso]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM EtapaProcesso"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                EtapaProcesso(
                    id=row['idEtapaProcesso'],
                    data=row['data'],
                    observacoes=row['observacoes'],
                    id_processo=row['id_processo'],
                    statusEtapa=row['statusEtapa'],
                    tipoEtapa=row['tipoEtapa']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao buscar EtapaProcesso: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[EtapaProcesso]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM EtapaProcesso"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                EtapaProcesso(
                    id=row['idEtapaProcesso'],
                    data=row['data'],
                    observacoes=row['observacoes'],
                    id_processo=row['id_processo'],
                    statusEtapa=row['statusEtapa'],
                    tipoEtapa=row['tipoEtapa']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar EtapaProcesso: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(etapa_processo: EtapaProcesso) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE EtapaProcesso SET data=%s, observacoes=%s, id_processo=%s, statusEtapa=%s, tipoEtapa=%s
        WHERE idEtapaProcesso=%s
        """
        try:
            cursor.execute(sql, (
                etapa_processo.data, etapa_processo.observacoes, etapa_processo.id_processo,
                etapa_processo.statusEtapa, etapa_processo.tipoEtapa, etapa_processo.id
            ))
            conn.commit()
            print("EtapaProcesso atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar EtapaProcesso: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM EtapaProcesso WHERE idEtapaProcesso = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("EtapaProcesso deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar EtapaProcesso: {e}")
        finally:
            cursor.close()