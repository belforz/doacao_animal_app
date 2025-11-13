from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException
from schemas.animal import Animal


class AnimalDAO:
    @staticmethod
    def create(animal: Animal) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        INSERT INTO Animal (especie, raca, temperamento, historicoSaude, nome, descricao, esEspecial, idade, sexo, status, id_protetor)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """
        try:
            cursor.execute(sql, (
                animal.especie, animal.raca, animal.temperamento, animal.historicoSaude,
                animal.nome, animal.descricao, animal.esEspecial, animal.idade,
                animal.sexo, animal.status, animal.protetor.id
            ))
            conn.commit()
            print("Animal criado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao criar Animal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def read(id: int) -> Animal:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Animal WHERE idAnimal = %s"
        try:
            cursor.execute(sql, (id,))
            row = cursor.fetchone()
            if row:
                # Buscar Protetor
                from DAO.protetor_dao import ProtetorDAO
                protetor = ProtetorDAO.read(row['id_protetor'])
                # Buscar Fotos
                from DAO.foto_animal_dao import FotoAnimalDAO
                fotos = FotoAnimalDAO.read_by_animal(id)
                return Animal(
                    idAnimal=row['idAnimal'],
                    especie=row['especie'],
                    raca=row['raca'],
                    temperamento=row['temperamento'],
                    historicoSaude=row['historicoSaude'],
                    nome=row['nome'],
                    descricao=row['descricao'],
                    esEspecial=row['esEspecial'],
                    idade=row['idade'],
                    sexo=row['sexo'],
                    status=row['status'],
                    protetor=protetor,
                    foto_animal=fotos
                )
            return None
        except Exception as e:
            raise CustomException(f"Erro ao buscar Animal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def readAll() -> list[Animal]:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor(dictionary=True)
        sql = "SELECT * FROM Animal"
        try:
            cursor.execute(sql)
            rows = cursor.fetchall()
            animais = []
            for row in rows:
                # Buscar Protetor e Fotos para cada
                from DAO.protetor_dao import ProtetorDAO
                protetor = ProtetorDAO.read(row['id_protetor'])
                from DAO.foto_animal_dao import FotoAnimalDAO
                fotos = FotoAnimalDAO.read_by_animal(row['idAnimal'])
                animais.append(Animal(
                    idAnimal=row['idAnimal'], especie=row['especie'], raca=row['raca'],
                    temperamento=row['temperamento'], historicoSaude=row['historicoSaude'],
                    nome=row['nome'], descricao=row['descricao'], esEspecial=row['esEspecial'],
                    idade=row['idade'], sexo=row['sexo'], status=row['status'], protetor=protetor, foto_animal=fotos
                ))
            return animais
        except Exception as e:
            raise CustomException(f"Erro ao listar Animais: {e}")
        finally:
            cursor.close()

    @staticmethod
    def update(animal: Animal) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = """
        UPDATE Animal SET especie=%s, raca=%s, temperamento=%s, historicoSaude=%s, nome=%s, descricao=%s, esEspecial=%s, idade=%s, sexo=%s, status=%s, id_protetor=%s
        WHERE idAnimal=%s
        """
        try:
            cursor.execute(sql, (
                animal.especie, animal.raca, animal.temperamento, animal.historicoSaude,
                animal.nome, animal.descricao, animal.esEspecial, animal.idade,
                animal.sexo, animal.status, animal.protetor.id, animal.idAnimal
            ))
            conn.commit()
            print("Animal atualizado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao atualizar Animal: {e}")
        finally:
            cursor.close()

    @staticmethod
    def delete(id: int) -> None:
        conn = MYSQLConnection.get_connection()
        cursor = conn.cursor()
        sql = "DELETE FROM Animal WHERE idAnimal = %s"
        try:
            cursor.execute(sql, (id,))
            conn.commit()
            print("Animal deletado com sucesso!")
        except Exception as e:
            raise CustomException(f"Erro ao deletar Animal: {e}")
        finally:
            cursor.close()