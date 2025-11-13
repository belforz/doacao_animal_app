from dataclasses import dataclass
from datetime import datetime as dt

from .processo_adocao import ProcessoAdocao


@dataclass
class Mensagem:
    idMensagem: int
    dataMensagem: dt
    conteudo: str
    idRemetente: int
    idDestinatario: int
    tipoRemetente: str
    tipoDestinatario: str
    processoAdocao: ProcessoAdocao
    id_processo: int