
from datetime import date, datetime

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


def recuperar_id_inserido(tabela: str, coluna_id: str, coluna_filtro: str, valor_filtro: str):
    """
    Recupera o ID do último registro inserido baseado em um filtro único.
    
    Args:
        tabela: Nome da tabela no banco
        coluna_id: Nome da coluna de ID (PK)
        coluna_filtro: Nome da coluna para filtrar (ex: 'email')
        valor_filtro: Valor a ser buscado
        
    Returns:
        ID do registro ou None se não encontrado
    """
    conn = MYSQLConnection.get_connection()
    cursor = conn.cursor(dictionary=True)
    sql = f"SELECT {coluna_id} FROM {tabela} WHERE {coluna_filtro} = %s ORDER BY {coluna_id} DESC LIMIT 1"
    cursor.execute(sql, (valor_filtro,))
    row = cursor.fetchone()
    cursor.close()
    return row[coluna_id] if row else None


def imprimir_secao(titulo: str):
    """Imprime um cabeçalho de seção para organizar a saída."""
    print(f"\n{'='*60}")
    print(f"  {titulo}")
    print(f"{'='*60}")


def main():
    """Função principal que executa todos os testes de CRUD."""
    try:
        # ====================================================================
        # INICIALIZAÇÃO DOS DAOs
        # ====================================================================
        imprimir_secao("Inicializando DAOs")
        
        adotante_dao = AdotanteDAO()
        protetor_dao = ProtetorDAO()
        animal_dao = AnimalDAO()
        foto_animal_dao = FotoAnimalDAO()
        mensagem_dao = MensagemDAO()
        processo_adocao_dao = ProcessoAdocaoDAO()
        suporte_pos_adocao_dao = SuportePosAdocaoDAO()
        etapa_processo_dao = EtapaProcessoDAO()
        adocao_dao = AdocaoDAO()
        
        print("✓ Todos os DAOs inicializados com sucesso!")

        # ====================================================================
        # CRIAÇÃO DE REGISTROS (CREATE)
        # ====================================================================
        data_hoje = date.today()
        data_hora_atual = datetime.now()
        
        imprimir_secao("Criando Adotante")
        adotante = Adotante(
            0, 
            "Maria Silva Costa", 
            "maria.silva@example.com", 
            "456789168796", 
            "11987654321", 
            "senha123", 
            "Av. Paulista, 1000 - São Paulo/SP", 
            "Gatos e Cachorros de porte pequeno"
        )
        adotante_dao.create(adotante)
        id_adotante = recuperar_id_inserido("Adotante", "idAdotante", "email", adotante.email)
        if id_adotante:
            adotante = adotante_dao.read(id_adotante)
            print(f"✓ Adotante '{adotante.nome}' criado com ID: {adotante.id}")

        imprimir_secao("Criando Protetor")
        protetor = Protetor(
            0, 
            "João Pedro Santos", 
            "joao.santos@ongprotecao.org", 
            "789456128697", 
            "11912345678", 
            "protetor456", 
            "Rua das Flores, 500 - Campinas/SP", 
            "ONG"
        )
        protetor_dao.create(protetor)
        id_protetor = recuperar_id_inserido("Protetor", "idProtetor", "email", protetor.email)
        if id_protetor:
            protetor = protetor_dao.read(id_protetor)
            print(f"✓ Protetor '{protetor.nome}' criado com ID: {protetor.id}")

        imprimir_secao("Criando Animal")
        animal = Animal(
            0, 
            "Gato", 
            "Persa", 
            "Calmo e carinhoso", 
            "Vacinado, castrado, vermifugado", 
            "Mimi", 
            "Gata persa de 3 anos, muito dócil e ótima com crianças", 
            False, 
            3, 
            "F", 
            "Disponível", 
            protetor, 
            []
        )
        animal_dao.create(animal)
        id_animal = recuperar_id_inserido("Animal", "idAnimal", "nome", animal.nome)
        if id_animal:
            animal = animal_dao.read(id_animal)
            print(f"✓ Animal '{animal.nome}' ({animal.especie}) criado com ID: {animal.idAnimal}")

        imprimir_secao("Criando Foto do Animal")
        foto_animal = FotoAnimal(
            "https://images.example.com/mimi-foto1.jpg", 
            "Foto de perfil da Mimi", 
            animal.idAnimal, 
            0
        )
        foto_animal_dao.create(foto_animal)
        print(f"✓ Foto do animal '{animal.nome}' cadastrada")

        imprimir_secao("Criando Processo de Adoção")
        processo_adocao = ProcessoAdocao(
            0, 
            animal, 
            adotante, 
            StatusProcesso.ENTREVISTA, 
            data_hoje, 
            animal.idAnimal, 
            adotante.id, 
            [], 
            []
        )
        processo_adocao_dao.create(processo_adocao)
        id_processo = recuperar_id_inserido("ProcessoAdocao", "idPAdocao", "id_animal", animal.idAnimal)
        if id_processo:
            processo_adocao = processo_adocao_dao.read(id_processo)
            status_valor = processo_adocao.statusProcesso.value if hasattr(processo_adocao.statusProcesso, 'value') else str(processo_adocao.statusProcesso)
            print(f"✓ Processo de Adoção iniciado com ID: {processo_adocao.idPAdocao} - Status: {status_valor}")

        imprimir_secao("Criando Mensagem")
        mensagem = Mensagem(
            0, 
            data_hora_atual, 
            "Olá! Estou muito interessada em adotar a Mimi. Podemos agendar uma visita?", 
            adotante.id, 
            protetor.id, 
            "Adotante", 
            "Protetor", 
            processo_adocao, 
            processo_adocao.idPAdocao
        )
        mensagem_dao.create(mensagem)
        print(f"✓ Mensagem enviada de {adotante.nome} para {protetor.nome}")

        imprimir_secao("Criando Etapa do Processo")
        etapa = EtapaProcesso(
            0, 
            data_hoje, 
            "Entrevista inicial agendada para verificar compatibilidade", 
            "ENTREVISTA", 
            processo_adocao.idPAdocao, 
            StatusProcesso.ENTREVISTA.value
        )
        etapa_processo_dao.create(etapa)
        print(f"✓ Etapa '{etapa.tipoEtapa}' criada para o processo {processo_adocao.idPAdocao}")

        imprimir_secao("Criando Adoção")
        adocao = Adocao(
            0, 
            data_hoje, 
            "Adoção da gata Mimi para Maria Silva Costa", 
            "Termo de adoção assinado. Compromisso de cuidados veterinários e bem-estar.", 
            processo_adocao, 
            processo_adocao.idPAdocao, 
            []
        )
        adocao_dao.create(adocao)
       
        id_adocao = recuperar_id_inserido("Adocao", "idAdocao", "id_processo", processo_adocao.idPAdocao)
        if id_adocao:
            adocao = adocao_dao.read(id_adocao)
        print(f"✓ Adoção registrada: {adocao.descricao}")

        imprimir_secao("Criando Suporte Pós-Adoção")
        suporte = SuportePosAdocao(
            0, 
            data_hoje, 
            "Consulta Veterinária", 
            "Primeira consulta veterinária pós-adoção para check-up geral", 
            adocao.idAdocao
        )
        suporte_pos_adocao_dao.create(suporte)
        print(f"✓ Suporte pós-adoção registrado: {suporte.tipoSolicitacao}")

        # ====================================================================
        # LEITURA DE REGISTROS (READ)
        # ====================================================================
        imprimir_secao("Testando Leitura de Registros")
        
        adotante_lido = adotante_dao.read(adotante.id)
        if adotante_lido:
            print(f"✓ Adotante: {adotante_lido.nome} - {adotante_lido.email}")
        
        protetor_lido = protetor_dao.read(protetor.id)
        if protetor_lido:
            print(f"✓ Protetor: {protetor_lido.nome} - Tipo: {protetor_lido.tipo}")
        
        animal_lido = animal_dao.read(animal.idAnimal)
        if animal_lido:
            print(f"✓ Animal: {animal_lido.nome} ({animal_lido.especie} - {animal_lido.raca})")
        
        processo_lido = processo_adocao_dao.read(processo_adocao.idPAdocao)
        if processo_lido:
            status = getattr(processo_lido, 'statusProcesso', getattr(processo_lido, 'status', None))
            print(f"✓ Processo de Adoção: Status {status}")
        
        adocao_lida = adocao_dao.read(adocao.idAdocao)
        if adocao_lida:
            print(f"✓ Adoção: {adocao_lida.descricao}")

        # ====================================================================
        # ATUALIZAÇÃO DE REGISTROS (UPDATE)
        # ====================================================================
        imprimir_secao("Testando Atualização de Registros")
        
        adotante.nome = "Maria Silva Costa Oliveira"
        adotante_dao.update(adotante)
        print(f"✓ Adotante atualizado: {adotante.nome}")
        
        protetor.nome = "João Pedro Santos Silva"
        protetor_dao.update(protetor)
        print(f"✓ Protetor atualizado: {protetor.nome}")
        
        animal.nome = "Mimi (Adotada)"
        animal_dao.update(animal)
        print(f"✓ Animal atualizado: {animal.nome}")

        # ====================================================================
        # EXCLUSÃO DE REGISTROS (DELETE)
        # ====================================================================
        imprimir_secao("Testando Exclusão de Registros")
        
        mensagem_dao.delete(mensagem.idMensagem)
        print(f"✓ Mensagem deletada (ID: {mensagem.idMensagem})")
        
        etapa_processo_dao.delete(etapa.id)
        print(f"✓ Etapa de processo deletada (ID: {etapa.id})")
        
        suporte_pos_adocao_dao.delete(suporte.idSuporte)
        print(f"✓ Suporte pós-adoção deletado (ID: {suporte.idSuporte})")

        imprimir_secao("✅ TODOS OS BATCHS CONCLUÍDOS COM SUCESSO!")

    except CustomException as ce:
        print(f"\n❌ Erro personalizado: {ce}")
    except Exception as ex:
        print(f"\n❌ Erro inesperado: {ex}")
        import traceback
        traceback.print_exc()


if __name__ == "__main__":
    main()
    