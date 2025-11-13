from .pessoa import Pessoa


class Adotante(Pessoa):
    preferenciaAdocao: str
    
    def __init__(self, id: int, nome: str, email: str, documento: str, telefone: str, senha: str, endereco: str, preferenciaAdocao: str):
        super().__init__(id, nome, email, documento, telefone, senha, endereco)
        self.preferenciaAdocao = preferenciaAdocao
    
    def enviarMensagem(self, msg):
        return super().enviarMensagem(msg)