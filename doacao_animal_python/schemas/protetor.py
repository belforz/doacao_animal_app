from .pessoa import Pessoa


class Protetor(Pessoa):
    tipo: str
    
    def __init__(self, id, nome, email, documento, telefone, senha, endereco, tipo):
        super().__init__(id, nome, email, documento, telefone, senha, endereco)
        self.tipo = tipo
        
    def enviarMensagem(self, msg):
        pass