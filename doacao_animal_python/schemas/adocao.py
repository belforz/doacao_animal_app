from dataclasses import dataclass, field
from datetime import datetime as dt
from typing import List

from .processo_adocao import ProcessoAdocao
from .suporte_pos_adocao import SuportePosAdocao

@dataclass
class Adocao:
    idAdocao: int
    dataAdocao: dt
    descricao: str
    termos: str
    processoAdocao: ProcessoAdocao
    id_processo: int
    suportes: List[SuportePosAdocao] = field(default_factory=list)
