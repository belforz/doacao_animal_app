from datetime import datetime as dt
from typing import List

from .adotante import Adotante
from .animal import Animal
from .etapa_processo import EtapaProcesso
from .mensagem import Mensagem
from .status_processo import StatusProcesso

class ProcessoAdocao:
    def __init__(self, idPAdocao: int, animal: Animal, adotante: Adotante, statusProcesso: StatusProcesso, dataInicio: dt, id_animal: int, id_adotante: int, etapas: List[EtapaProcesso] = [], mensagens: List[Mensagem] = []):
        self.idPAdocao = idPAdocao
        self.animal = animal
        self.adotante = adotante
        self.statusProcesso = statusProcesso
        self.dataInicio = dataInicio
        self.etapas = etapas
        self.mensagens = mensagens
        self.id_animal = id_animal
        self.id_adotante = id_adotante
        
    def adicionarEtapa(self, etapa: EtapaProcesso):
        self.etapas.append(etapa)
    
    def mudarStatus(self, novo_status: StatusProcesso):
        self.statusProcesso = novo_status
        print(f"Status do processo {self.idPAdocao} atualizado para: {self.statusProcesso.value}")
    