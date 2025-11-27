import tkinter as tk
from tkinter import messagebox
import tkinter.ttk as ttk

class WelcomeFrame(tk.Frame):
    def __init__(self, parent, controller, tipoUsuario: str, usuario: object):
        super().__init__(parent)
        self.controller = controller
        self.tipoUsuario = tipoUsuario
        self.usuario = usuario

        # Estilos padronizados
        style = ttk.Style(self)
        style.theme_use('clam')
        style.configure('Card.TFrame', background='#E9ECEF')
        style.configure('Heading.TLabel', background='#E9ECEF', font=('Segoe UI', 18, 'bold'), foreground='#222')
        style.configure('Label.TLabel', background='#E9ECEF', font=('Segoe UI', 14, 'bold'), foreground='#111')
        style.configure('TEntry', padding=6, font=('Segoe UI', 12))
        style.configure('Accent.TButton', background="#4B6EF6", foreground='white', font=('Segoe UI', 14, 'bold'))
        style.map('Accent.TButton',
              background=[('active', '#3751c8'), ('pressed', '#2d43a6'), ('!active', '#4B6EF6')],
              foreground=[('disabled', '#bdbdbd'), ('!disabled', 'white')])

        # Painel principal
        painel = ttk.Frame(self, style='Card.TFrame')
        painel.pack(fill=tk.BOTH, expand=True, padx=24, pady=20)

        # Título
        self.label_boas_vindas = ttk.Label(painel, text="Bem-vindo!", style='Heading.TLabel')
        self.label_boas_vindas.grid(row=0, column=1, columnspan=4, pady=20)

        # Campos de informação do usuário (simulando read-only)
        self.label_nome = ttk.Label(painel, text="Nome:", style='Label.TLabel')
        self.label_nome.grid(row=1, column=0, columnspan=2, sticky="e", padx=10)
        self.entry_nome = ttk.Entry(painel, state="readonly")
        self.entry_nome.grid(row=1, column=2, columnspan=3, sticky="ew", padx=10, pady=5)

        self.label_email = ttk.Label(painel, text="Email:", style='Label.TLabel')
        self.label_email.grid(row=2, column=0, columnspan=2, sticky="e", padx=10)
        self.entry_email = ttk.Entry(painel, state="readonly")
        self.entry_email.grid(row=2, column=2, columnspan=3, sticky="ew", padx=10, pady=5)

        self.label_documento = ttk.Label(painel, text="Documento:", style='Label.TLabel')
        self.label_documento.grid(row=3, column=0, columnspan=2, sticky="e", padx=10)
        self.entry_documento = ttk.Entry(painel, state="readonly")
        self.entry_documento.grid(row=3, column=2, columnspan=3, sticky="ew", padx=10, pady=5)

        self.label_telefone = ttk.Label(painel, text="Telefone:", style='Label.TLabel')
        self.label_telefone.grid(row=4, column=0, columnspan=2, sticky="e", padx=10)
        self.entry_telefone = ttk.Entry(painel, state="readonly")
        self.entry_telefone.grid(row=4, column=2, columnspan=3, sticky="ew", padx=10, pady=5)

        self.label_endereco = ttk.Label(painel, text="Endereço:", style='Label.TLabel')
        self.label_endereco.grid(row=5, column=0, columnspan=2, sticky="e", padx=10)
        self.entry_endereco = ttk.Entry(painel, state="readonly")
        self.entry_endereco.grid(row=5, column=2, columnspan=3, sticky="ew", padx=10, pady=5)

        self.label_especifico = ttk.Label(painel, text="", style='Label.TLabel')
        self.label_especifico.grid(row=6, column=0, columnspan=2, sticky="e", padx=10)
        self.entry_especifico = ttk.Entry(painel, state="readonly")
        self.entry_especifico.grid(row=6, column=2, columnspan=3, sticky="ew", padx=10, pady=5)
        
        if "Protetor" == tipoUsuario:
            self.label_especifico.config(text="Tipo: ")
        elif "Adotante" == tipoUsuario:
            self.label_especifico.config(text="Preferência de Adoção:")
        else:
            self.label_especifico.config(text="")  # For Admin or others
            

        # Botões
        self.button_ver_animais = ttk.Button(painel, text="Ver Animais", style='Accent.TButton', command=self.ver_animais)
        self.button_ver_animais.grid(row=7, column=2, pady=10)

        self.button_ver_mensagens = ttk.Button(painel, text="Ver Mensagens", style='Accent.TButton', command=self.ver_mensagens)
        self.button_ver_mensagens.grid(row=7, column=3, pady=10)

        if self.tipoUsuario == "Admin":
            self.button_admin = ttk.Button(painel, text="Admin", style='Accent.TButton', command=self.ir_para_admin)
            self.button_admin.grid(row=7, column=4, pady=10)

        self.button_logout = ttk.Button(painel, text="Sair", style='Accent.TButton', command=self.logout)
        self.button_logout.grid(row=8, column=2, columnspan=2, pady=10)

        # Configurar grid
        painel.grid_columnconfigure(2, weight=1)
        painel.grid_columnconfigure(3, weight=1)
        
        # Preencher dados do usuário
        self.preencher_dados_usuario()
    
    def preencher_dados_usuario(self):
        try:
            if not self.usuario:
                return
            
            usuario_dados = self.usuario if isinstance(self.usuario, dict) else vars(self.usuario)
            self.entry_nome.config(state="normal")
            self.entry_nome.delete(0, tk.END)
            self.entry_nome.insert(0, usuario_dados.get("nome", ""))
            self.entry_nome.config(state="readonly")

            self.entry_email.config(state="normal")
            self.entry_email.delete(0, tk.END)
            self.entry_email.insert(0, usuario_dados.get("email", ""))
            self.entry_email.config(state="readonly")

            self.entry_documento.config(state="normal")
            self.entry_documento.delete(0, tk.END)
            self.entry_documento.insert(0, usuario_dados.get("documento", ""))
            self.entry_documento.config(state="readonly")

            self.entry_telefone.config(state="normal")
            self.entry_telefone.delete(0, tk.END)
            self.entry_telefone.insert(0, usuario_dados.get("telefone", ""))
            self.entry_telefone.config(state="readonly")

            self.entry_endereco.config(state="normal")
            self.entry_endereco.delete(0, tk.END)
            self.entry_endereco.insert(0, usuario_dados.get("endereco", ""))
            self.entry_endereco.config(state="readonly")

            especifico_key = "preferenciaAdocao" if self.tipoUsuario == "Adotante" else ("tipo" if self.tipoUsuario == "Protetor" else None)
            self.entry_especifico.config(state="normal")
            self.entry_especifico.delete(0, tk.END)
            if especifico_key:
                self.entry_especifico.insert(0, usuario_dados.get(especifico_key, ""))
            self.entry_especifico.config(state="readonly")
        except Exception as e:
            messagebox.showerror("Erro", f"Erro ao preencher dados: {e}")

    def ver_animais(self):
        print("Navegando para Ver Animais")

    def ver_mensagens(self):
        messagebox.showinfo("Mensagens", "Visualizando mensagens")
        if self.controller:
            self.controller.show_frame("UsuarioChat")

    def ir_para_admin(self):
        if self.controller:
            self.controller.show_frame("Admin")

    def logout(self):
        messagebox.showinfo("Logout", "Logout realizado")
        if self.controller:
            self.controller.show_frame("Login")


