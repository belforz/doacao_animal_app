from DAO.adotante_dao import AdotanteDAO
from DAO.protetor_dao import ProtetorDAO
from DAO.animal_dao import AnimalDAO
from DAO.foto_animal_dao import FotoAnimalDAO
from DAO.mensagem_dao import MensagemDAO
from DAO.processo_adocao_dao import ProcessoAdocaoDAO
from DAO.suport_pos_adocao_dao import SuportePosAdocaoDAO
from DAO.etapa_processo_dao import EtapaProcessoDAO
from DAO.adocao_dao import AdocaoDAO

from schemas.adotante import Adotante
from schemas.protetor import Protetor
from schemas.animal import Animal
from schemas.foto_animal import FotoAnimal
from schemas.mensagem import Mensagem
from schemas.processo_adocao import ProcessoAdocao
from schemas.suporte_pos_adocao import SuportePosAdocao
from schemas.etapa_processo import EtapaProcesso
from schemas.adocao import Adocao
from schemas.status_processo import StatusProcesso

from repository.mysql_connection import MYSQLConnection
from exceptions.custom_exception import CustomException

from datetime import date, datetime


if __name__ == "__main__":
    try:
        # Teste de criação do DAO
        adotantedao = AdotanteDAO()
        protetordao = ProtetorDAO()
        animaldao = AnimalDAO()
        fotoanimaldao = FotoAnimalDAO()
        mensagemdao = MensagemDAO()
        processoadocaodao = ProcessoAdocaoDAO()
        suporteposadocaodao = SuportePosAdocaoDAO()
        etapaprocessodao = EtapaProcessoDAO()
        adocaodao = AdocaoDAO()

        print("DAO Adotante criado com sucesso!" + str(adotantedao))
        print("DAO Protetor criado com sucesso!" + str(protetordao))
        print("DAO Animal criado com sucesso!" + str(animaldao))
        print("DAO FotoAnimal criado com sucesso!" + str(fotoanimaldao))
        print("DAO Mensagem criado com sucesso!" + str(mensagemdao))
        print("DAO ProcessoAdocao criado com sucesso!" + str(processoadocaodao))
        print("DAO SuportePosAdocao criado com sucesso!" + str(suporteposadocaodao))
        print("DAO EtapaProcesso criado com sucesso!" + str(etapaprocessodao))
        print("DAO Adocao criado com sucesso!" + str(adocaodao))

        data = date.today()
        dt = datetime.now()

        # Teste de criar
        # Adotante
        adotante = Adotante(id=0, nome="Leandro", documento="123456898", endereco="Rua B, 488", email="macedobeiramar@hotmail.com", telefone="04165672651", senha="11111111", preferenciaAdocao="Familia")
        adotantedao.create(adotante)
        print("Adotante criado com ID: " + str(adotante.id))
        # Protetor
        protetor = Protetor(id=0, nome="Leandro Protetor", documento="23456788798", endereco="Rua C, 123", email="macedobeiramaru@hotmail.com", telefone="4566567658765", senha="22222222", tipo="Familia")
        protetordao.create(protetor)
        print("Protetor criado com ID: " + str(protetor.id))
        # Animal
        animal = Animal(id=0, nome="Rex", especie="Cachorro", raca="Labrador", temperamento="Bom", sexo="Masculino", porte="Grande", castrado=False, idade=2, status='M', descricao="BEM", protetor_id=protetor.id)
        animaldao.create(animal)
        print("Animal criado com ID: " + str(animal.id))
        # FotoAnimal
        fotoAnimal = FotoAnimal(id_foto_animal=0, url="http://example.com/foto1.jpg", descricao="Foto frontal", animal_id=animal.id, protetor_id=protetor.id)
        fotoanimaldao.create(fotoAnimal)
        print("FotoAnimal criado com ID: " + str(fotoAnimal.id_foto_animal))

        # ProcessoAdocao
        processoAdocao = ProcessoAdocao(id_p_adocao=0, animal=animal, adotante=adotante, status=StatusProcesso.ENTREVISTA, data_inicio=data, animal_id=animal.id, adotante_id=adotante.id)
        processoadocaodao.create(processoAdocao)
        # Mensagem
        mensagem = Mensagem(id_mensagem=0, data_mensagem=dt, conteudo="Olá, estou interessado em adotar o Rex.", id_remetente=adotante.id, id_destinatario=protetor.id, tipo_remetente="Adotante", tipo_destinatario="Protetor", processo_adocao=processoAdocao.id_p_adocao)
        mensagemdao.create(mensagem)
        print("Mensagem criada com ID: " + str(mensagem.id_mensagem))

        # EtapaProcesso
        etapa = EtapaProcesso(id=0, data=data, descricao="Entrevista inicial", status="PENDENTE", tipo="ENTREVISTA", processo_adocao_id=processoAdocao.id_p_adocao)
        etapaprocessodao.create(etapa)
        print("EtapaProcesso criada com ID: " + str(etapa.id))

        #Adocao
        adocao = Adocao(id_adocao=0, data_adocao=data, descricao="Adoção concluída", termos="Termos aceitos", processo_adocao_id=processoAdocao.id_p_adocao)
        adocaodao.create(adocao)
        print("Adocao criada com ID: " + str(adocao.id_adocao))

        # SuportePosAdocao
        suporte = SuportePosAdocao(id_suporte=0, data_suporte=data, tipo_suporte="Consulta veterinária", descricao="Ajuda com vacinas", adocao_id=adocao.id_adocao)
        suporteposadocaodao.create(suporte)
        print("SuportePosAdocao criado com ID: " + str(suporte.id_suporte))

        # Teste de leitura
        adt = adotantedao.read(adotante.id)
        if adt:
            print("Adotante lido: " + adt.nome)
        prt = protetordao.read(protetor.id)
        if prt:
            print("Protetor lido: " + prt.nome)
        # Teste de leitura para Animal
        anm = animaldao.read(animal.id)
        if anm:
            print("Animal lido: " + anm.nome)
        # Teste de leitura para ProcessoAdocao
        proc = processoadocaodao.read(processoAdocao.id_p_adocao)
        if proc:
            print("ProcessoAdocao lido: " + str(proc.status))
        # Teste de leitura para Adocao
        ado = adocaodao.read(adocao.id_adocao)
        if ado:
            print("Adocao lida: " + ado.descricao)

        # Teste de atualizar
        adotante.nome = "Leandro Atualizado 2"
        adotantedao.update(adotante)
        print("Adotante atualizado para: " + adotante.nome)
        # Teste de atualizar Protetor
        protetor.nome = "Protetor Atualizado 2"
        protetordao.update(protetor)
        print("Protetor atualizado para: " + protetor.nome)
        # Teste de atualizar Animal
        animal.nome = "Rex Atualizado 2 "
        animaldao.update(animal)
        print("Animal atualizado para: " + animal.nome)

        # Teste de deletar
        mensagemdao.delete(mensagem.id_mensagem)
        print("Mensagem deletada com ID: " + str(mensagem.id_mensagem))
        # Teste de deletar EtapaProcesso
        etapaprocessodao.delete(etapa.id)
        print("EtapaProcesso deletada com ID: " + str(etapa.id))
        # Teste de deletar SuportePosAdocao
        suporteposadocaodao.delete(suporte.id_suporte)
        print("SuportePosAdocao deletado com ID: " + str(suporte.id_suporte))

    except CustomException as ce:
        print("Erro personalizado: " + str(ce))
    except Exception as ex:
        print("Erro inesperado: " + str(ex))
    