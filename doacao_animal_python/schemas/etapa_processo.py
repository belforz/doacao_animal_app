from datetime import datetime as dt

from .status_processo import StatusProcesso


class EtapaProcesso:
    def __init__(self, id: int, data: dt, observacoes: str, tipoEtapa: str, id_processo: int, statusEtapa: StatusProcesso):
        self.id = id
        self.data = data
        self.observacoes = observacoes
        self.tipoEtapa = tipoEtapa
        self.id_processo = id_processo
        self.statusEtapa = statusEtapa
        
    def executar(self):
            pass