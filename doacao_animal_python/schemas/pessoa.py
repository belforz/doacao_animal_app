class Pessoa:
    id: int
    nome: str
    email: str
    documento: str
    telefone: str
    senha: str
    endereco: str
    
    def __init__(self, id: int, nome: str, email: str, documento: str, telefone: str, senha: str, endereco: str):
        self.id = id
        self.nome = nome
        self.email = email
        self.documento = documento
        self.telefone = telefone
        self.senha = senha
        self.endereco = endereco
        
    def enviarMensagem(self, msg: str): 
        pass