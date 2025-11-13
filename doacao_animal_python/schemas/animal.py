from dataclasses import dataclass, field
from typing import List
from .foto_animal import FotoAnimal
from .protetor import Protetor


@dataclass
class Animal:
    idAnimal: int
    especie: str
    raca: str
    temperamento: str
    historicoSaude: str
    nome: str
    descricao: str
    esEspecial: bool
    idade: int
    sexo: str
    status: str
    protetor: Protetor
    foto_animal: List[FotoAnimal] = field(default_factory=list)
        
        
    