from __future__ import annotations

from dataclasses import dataclass
from datetime import datetime as dt
from typing import Optional
import time


@dataclass
class Mensagem:
    idMensagem: int
    dataMensagem: dt
    conteudo: str
    idRemetente: int
    idDestinatario: int
    tipoRemetente: Optional[str] = None
    tipoDestinatario: Optional[str] = None
    processoAdocao: Optional["ProcessoAdocao"] = None
    id_processo: Optional[int] = None