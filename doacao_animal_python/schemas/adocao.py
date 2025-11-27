from dataclasses import dataclass, field
from datetime import date as dt
from typing import List, Optional
import time

from .processo_adocao import ProcessoAdocao
from .suporte_pos_adocao import SuportePosAdocao
from .status_processo import StatusProcesso

@dataclass
class Adocao:
    idAdocao: int
    dataAdocao: dt
    descricao: str
    termos: str
    processoAdocao: Optional[ProcessoAdocao]
    id_processo: int
    suportes: List[SuportePosAdocao] = field(default_factory=list)

    def mudarStatus(self, novo_status: StatusProcesso):
        if self.processoAdocao:
            self.processoAdocao.mudarStatus(novo_status)
            self.descricao = novo_status.value
        else:
            raise ValueError("Processo de adoção não associado.")
