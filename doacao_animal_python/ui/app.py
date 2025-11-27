import os
import sys
import tkinter as tk

# Adicionar o diretório raiz do projeto ao sys.path
project_root = os.path.normpath(os.path.join(os.path.dirname(__file__), '..'))
if project_root not in sys.path:
    sys.path.insert(0, project_root)

from ui.components.login import Login
from ui.components.usuario_chat import UsuarioChat
from ui.components.welcome import WelcomeFrame
from ui.components.admin import AdminFrame


class App:
    def __init__(self, root):
        self.root = root
        self.root.title("Doação de Animais")
        self.root.geometry("1000x700")

        # Dicionário para armazenar frames
        self.frames = {}

        # Armazenar usuário atual para persistência de estado
        self.current_user = None
        self.current_tipo = None

        # Instanciar frames com controlador (self)
        self.frames["Login"] = Login(self.root, self)
        self.frames["UsuarioChat"] = UsuarioChat(self.root, self)
        # Welcome precisa de dados dinâmicos, então placeholder ou instanciar depois
        self.frames["Welcome"] = None  # Será criado após login
        self.frames["Admin"] = None  # Será criado quando necessário

        # Mostrar frame inicial
        self.show_frame("Login")

    def show_frame(self, frame_name, **kwargs):
        # Esconder todos os frames
        for frame in self.frames.values():
            if frame:
                frame.pack_forget()

        if frame_name == "Welcome":
            # Usar dados armazenados se não fornecidos
            tipo_usuario = kwargs.get("tipoUsuario", self.current_tipo or "Adotante")
            usuario = kwargs.get("usuario", self.current_user or {})
            self.frames["Welcome"] = WelcomeFrame(self.root, self, tipo_usuario, usuario)
            
            # Atualizar dados atuais se fornecidos
            if "tipoUsuario" in kwargs:
                self.current_tipo = kwargs["tipoUsuario"]
            if "usuario" in kwargs:
                self.current_user = kwargs["usuario"]

        if frame_name == "Admin":
            self.frames["Admin"] = AdminFrame(self.root, self)

        # Mostrar o frame desejado
        frame = self.frames[frame_name]
        if frame:
            frame.pack(fill="both", expand=True)
        else:
            print(f"Frame {frame_name} não encontrado")

    def run(self):
        self.root.mainloop()


def main():
    root = tk.Tk()
    app = App(root)
    app.run()


if __name__ == "__main__":
    main()
    
    
    
    