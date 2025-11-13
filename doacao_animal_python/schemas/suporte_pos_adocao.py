from datetime import datetime as dt

class SuportePosAdocao:
    
    def __init__(self, idSuporte: int, dataRegistro: dt, tipoSolicitacao: str, descricao: str, idAdocao: int):
        self.idSuporte = idSuporte
        self.dataRegistro = dataRegistro
        self.tipoSolicitacao = tipoSolicitacao
        self.descricao = descricao
        self.idAdocao = idAdocao
        
    
    def registrar(self):
        print(f"Suporte pós-adoção: {self.tipoSolicitacao}")