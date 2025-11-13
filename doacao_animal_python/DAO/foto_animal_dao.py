from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.foto_animal import FotoAnimal


class FotoAnimalDAO:
    @staticmethod
    def create(foto_animal: FotoAnimal) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO FotoAnimal (urlAnimal, descricaoFoto, idAnimal)
        VALUES (%s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                foto_animal.urlAnimal, foto_animal.descricaoFoto, foto_animal.idAnimal
            ))
            conn.commit()
            print("FotoAnimal criado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao criar FotoAnimal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> FotoAnimal:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM FotoAnimal WHERE idFotoAnimal = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                return FotoAnimal(
                    urlAnimal=row['urlAnimal'],
                    descricaoFoto=row['descricaoFoto'],
                    idAnimal=row['idAnimal'],
                    idFotoAnimal=row['idFotoAnimal']
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar FotoAnimal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[FotoAnimal]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM FotoAnimal"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            return [
                FotoAnimal(
                    urlAnimal=row['urlAnimal'],
                    descricaoFoto=row['descricaoFoto'],
                    idAnimal=row['idAnimal'],
                    idFotoAnimal=row['idFotoAnimal']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao listar FotoAnimal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(foto_animal: FotoAnimal) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE FotoAnimal SET urlAnimal=%s, descricaoFoto=%s, idAnimal=%s
        WHERE idFotoAnimal=%s
        """
        try:
            cursor.execute(sql, (
                foto_animal.urlAnimal, foto_animal.descricaoFoto, foto_animal.idAnimal,
                foto_animal.idFotoAnimal
            ))
            conn.commit()
            print("FotoAnimal atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar FotoAnimal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read_by_animal(id_animal: int) -> list[FotoAnimal]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM FotoAnimal WHERE idAnimal = %s"
        try:
            cursor.execute(sql, (id_animal,))
            rows = cursor.fetchall()
            return [
                FotoAnimal(
                    urlAnimal=row['urlAnimal'],
                    descricaoFoto=row['descricaoFoto'],
                    idAnimal=row['idAnimal'],
                    idFotoAnimal=row['idFotoAnimal']
                ) for row in rows
            ]
        except Exception as e:
            raise CustomException(f"Erro ao buscar Fotos por Animal: {e}")
        finally:
            cursor.close()