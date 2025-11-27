import tkinter as tk
from tkinter import ttk, messagebox
import os
import sys
from datetime import datetime, date

project_root = os.path.normpath(os.path.join(os.path.dirname(__file__), '..'))
if project_root not in sys.path:
    sys.path.insert(0, project_root)

from schemas.protetor import Protetor
from schemas.adotante import Adotante
from schemas.animal import Animal
from schemas.processo_adocao import ProcessoAdocao
from schemas.adocao import Adocao
from schemas.status_processo import StatusProcesso
from DAO.adotante_dao import AdotanteDAO
from DAO.animal_dao import AnimalDAO
from DAO.protetor_dao import ProtetorDAO
from DAO.processo_adocao_dao import ProcessoAdocaoDAO
from DAO.adocao_dao import AdocaoDAO
from exceptions.custom_exception import CustomException


class AdminFrame(tk.Frame):
    def __init__(self, parent, controller):
        super().__init__(parent)
        self.controller = controller

        # Estilos ttk consistentes
        style = ttk.Style(self)
        style.theme_use('clam')
        style.configure('Card.TFrame', background='#E9ECEF')
        style.configure('Heading.TLabel', background='#E9ECEF', font=('Segoe UI', 16, 'bold'), foreground='#222')
        style.configure('Label.TLabel', background='#E9ECEF', font=('Segoe UI', 11), foreground='#111')
        style.configure('TEntry', padding=6, font=('Segoe UI', 11))
        style.configure('Accent.TButton', background="#4B6EF6", foreground='white', font=('Segoe UI', 11, 'bold'))
        style.map('Accent.TButton',
              background=[('active', '#3751c8'), ('pressed', '#2d43a6'), ('!active', '#4B6EF6')],
              foreground=[('disabled', '#bdbdbd'), ('!disabled', 'white')])
        
        self.configure(bg="#E9ECEF")

        # Título
        title_label = ttk.Label(self, text="Sistema de Adoção Animal - Administração", style='Heading.TLabel')
        title_label.pack(pady=10)

        # Notebook para abas
        self.notebook = ttk.Notebook(self)
        self.notebook.pack(fill="both", expand=True, padx=10, pady=10)

        # Aba Protetor
        self.create_protetor_tab()

        # Aba Adotante
        self.create_adotante_tab()

        # Aba Animal
        self.create_animal_tab()

        # Aba Processo Adocao
        self.create_processo_adocao_tab()

        # Aba Adocao
        self.create_adocao_tab()
        

        # Botão Sair
        logout_button = ttk.Button(self, text="Sair", command=self.logout, style='Accent.TButton')
        logout_button.pack(pady=10)
        
        #DAOS
        self.protetor_dao = ProtetorDAO()
        self.animal_dao = AnimalDAO()
        self.adotante_dao = AdotanteDAO()
        self.processo_adocao_dao = ProcessoAdocaoDAO()
        self.adocao_dao = AdocaoDAO()

    def create_protetor_tab(self):
        tab = ttk.Frame(self.notebook, style='Card.TFrame')
        self.notebook.add(tab, text="Protetor")

        # Campos
        fields = [
            ("ID:", "id_protetor"),
            ("Nome:", "nome_protetor"),
            ("Email:", "email_protetor"),
            ("Documento:", "documento_protetor"),
            ("Telefone:", "telefone_protetor"),
            ("Endereço:", "endereco_protetor"),
            ("Senha:", "senha_protetor"),
            ("Tipo:", "tipo_protetor")
        ]

        self.protetor_entries = {}
        for i, (label_text, key) in enumerate(fields):
            label = ttk.Label(tab, text=label_text, style='Label.TLabel')
            label.grid(row=i, column=0, sticky="w", padx=5, pady=5)
            entry = ttk.Entry(tab, width=30)
            # ID sempre editável para permitir busca por ID específico
            entry.grid(row=i, column=1, padx=5, pady=5)
            self.protetor_entries[key] = entry

        # Botões
        buttons_frame = ttk.Frame(tab, style='Card.TFrame')
        buttons_frame.grid(row=len(fields), column=0, columnspan=2, pady=10)

        buttons = [
            ("Criar", self.criar_entidade),
            ("Ler", self.ler_entidade),
            ("Atualizar", self.atualizar_entidade),
            ("Deletar", self.deletar_entidade),
            ("Limpar", self.clear_protetor),
            ("Voltar", self.on_back)
        ]

        for i, (text, command) in enumerate(buttons):
            button = ttk.Button(buttons_frame, text=text, command=command, width=10, style='Accent.TButton')
            button.grid(row=0, column=i, padx=5)

    def create_adotante_tab(self):
        tab = ttk.Frame(self.notebook, style='Card.TFrame')
        self.notebook.add(tab, text="Adotante")

        # Campos
        fields = [
            ("ID:", "id_adotante"),
            ("Nome:", "nome_adotante"),
            ("Email:", "email_adotante"),
            ("Documento:", "documento_adotante"),
            ("Telefone:", "telefone_adotante"),
            ("Endereço:", "endereco_adotante"),
            ("Senha:", "senha_adotante"),
            ("Preferência:", "preferencia_adotante")
        ]

        self.adotante_entries = {}
        for i, (label_text, key) in enumerate(fields):
            label = ttk.Label(tab, text=label_text, style='Label.TLabel')
            label.grid(row=i, column=0, sticky="w", padx=5, pady=5)
            entry = ttk.Entry(tab, width=30)
            # ID sempre editável para permitir busca por ID específico
            entry.grid(row=i, column=1, padx=5, pady=5)
            self.adotante_entries[key] = entry

        # Botões
        buttons_frame = ttk.Frame(tab, style='Card.TFrame')
        buttons_frame.grid(row=len(fields), column=0, columnspan=2, pady=10)

        buttons = [
            ("Criar", self.criar_entidade),
            ("Ler", self.ler_entidade),
            ("Atualizar", self.atualizar_entidade),
            ("Deletar", self.deletar_entidade),
            ("Limpar", self.clear_adotante),
            ("Voltar", self.on_back)
        ]

        for i, (text, command) in enumerate(buttons):
            button = ttk.Button(buttons_frame, text=text, command=command, width=10, style='Accent.TButton')
            button.grid(row=0, column=i, padx=5)

    def create_animal_tab(self):
        tab = ttk.Frame(self.notebook, style='Card.TFrame')
        self.notebook.add(tab, text="Animal")

        # Campos na ordem do Java
        fields = [
            ("ID:", "id_animal", "entry"),
            ("Nome:", "nome_animal", "entry"),
            ("Espécie:", "especie_animal", "entry"),
            ("Raça:", "raca_animal", "entry"),
            ("Temperamento:", "temperamento_animal", "entry"),
            ("Histórico Saúde:", "historico_saude_animal", "entry"),
            ("Descrição:", "descricao_animal", "entry"),
            ("Especial:", "especial_animal", "check"),
            ("Idade:", "idade_animal", "entry"),
            ("Sexo:", "sexo_animal", "combo"),
            ("Status:", "status_animal", "entry"),
            ("ID Protetor:", "id_protetor_animal", "entry")
        ]

        self.animal_entries = {}
        for i, (label_text, key, widget_type) in enumerate(fields):
            label = ttk.Label(tab, text=label_text, style='Label.TLabel')
            label.grid(row=i, column=0, sticky="w", padx=5, pady=5)
            if widget_type == "check":
                var = tk.BooleanVar()
                check = ttk.Checkbutton(tab, variable=var)
                check.grid(row=i, column=1, padx=5, pady=5, sticky="w")
                self.animal_entries[key] = var
            elif widget_type == "combo":
                combo = ttk.Combobox(tab, values=["M", "F"], width=27)
                combo.grid(row=i, column=1, padx=5, pady=5)
                self.animal_entries[key] = combo
            else:  # entry
                entry = ttk.Entry(tab, width=30)
                # ID sempre editável para permitir busca por ID específico
                entry.grid(row=i, column=1, padx=5, pady=5)
                self.animal_entries[key] = entry

        # Botões
        buttons_frame = ttk.Frame(tab, style='Card.TFrame')
        buttons_frame.grid(row=len(fields), column=0, columnspan=2, pady=10)

        buttons = [
            ("Criar", self.criar_entidade),
            ("Ler", self.ler_entidade),
            ("Atualizar", self.atualizar_entidade),
            ("Deletar", self.deletar_entidade),
            ("Limpar", self.clear_animal),
            ("Voltar", self.on_back)
        ]

        for i, (text, command) in enumerate(buttons):
            button = ttk.Button(buttons_frame, text=text, command=command, width=10, style='Accent.TButton')
            button.grid(row=0, column=i, padx=5)
            
    def create_processo_adocao_tab(self):
        tab = ttk.Frame(self.notebook, style='Card.TFrame')
        self.notebook.add(tab, text="Processo Adocao")

        # Campos
        fields = [
            ("ID:", "id_processo_adocao", "entry"),
            ("ID Animal:", "id_animal_processo", "entry"),
            ("ID Adotante:", "id_adotante_processo", "entry"),
            ("Status:", "status_processo", "combo"),
            ("Data Inicio:", "data_inicio_processo", "entry")
        ]

        self.processo_adocao_entries = {}
        for i, (label_text, key, widget_type) in enumerate(fields):
            label = ttk.Label(tab, text=label_text, style='Label.TLabel')
            label.grid(row=i, column=0, sticky="w", padx=5, pady=5)
            if widget_type == "combo":
                combo = ttk.Combobox(tab, values=[s.value for s in StatusProcesso], width=27)
                combo.grid(row=i, column=1, padx=5, pady=5)
                self.processo_adocao_entries[key] = combo
            else:  # entry
                entry = ttk.Entry(tab, width=30)
                entry.grid(row=i, column=1, padx=5, pady=5)
                self.processo_adocao_entries[key] = entry

        # Botões
        buttons_frame = ttk.Frame(tab, style='Card.TFrame')
        buttons_frame.grid(row=len(fields), column=0, columnspan=2, pady=10)

        buttons = [
            ("Criar", self.criar_entidade),
            ("Ler", self.ler_entidade),
            ("Atualizar", self.atualizar_entidade),
            ("Deletar", self.deletar_entidade),
            ("Limpar", self.clear_processo_adocao),
            ("Voltar", self.on_back)
        ]

        for i, (text, command) in enumerate(buttons):
            button = ttk.Button(buttons_frame, text=text, command=command, width=10, style='Accent.TButton')
            button.grid(row=0, column=i, padx=5)
    
    def create_adocao_tab(self):
        tab = ttk.Frame(self.notebook, style='Card.TFrame')
        self.notebook.add(tab, text="Adocao")

        # Campos
        fields = [
            ("ID:", "id_adocao", "entry"),
            ("Data Adocao:", "data_adocao", "entry"),
            ("Descricao:", "descricao_adocao", "entry"),
            ("Termos:", "termos_adocao", "entry"),
            ("ID Processo:", "id_processo_adocao", "entry")
        ]

        self.adocao_entries = {}
        for i, (label_text, key, widget_type) in enumerate(fields):
            label = ttk.Label(tab, text=label_text, style='Label.TLabel')
            label.grid(row=i, column=0, sticky="w", padx=5, pady=5)
            entry = ttk.Entry(tab, width=30)
            entry.grid(row=i, column=1, padx=5, pady=5)
            self.adocao_entries[key] = entry

        # Botões
        buttons_frame = ttk.Frame(tab, style='Card.TFrame')
        buttons_frame.grid(row=len(fields), column=0, columnspan=2, pady=10)

        buttons = [
            ("Criar", self.criar_entidade),
            ("Ler", self.ler_entidade),
            ("Atualizar", self.atualizar_entidade),
            ("Deletar", self.deletar_entidade),
            ("Limpar", self.clear_adocao),
            ("Voltar", self.on_back)
        ]

        for i, (text, command) in enumerate(buttons):
            button = ttk.Button(buttons_frame, text=text, command=command, width=10, style='Accent.TButton')
            button.grid(row=0, column=i, padx=5)
        
        
        
        
    def criar_entidade(self):
        entity_type = self.notebook.tab(self.notebook.select(), "text")
        try:
            if entity_type == "Protetor":
                nome = self.protetor_entries["nome_protetor"].get()
                email = self.protetor_entries["email_protetor"].get()
                documento = self.protetor_entries["documento_protetor"].get()
                telefone = self.protetor_entries["telefone_protetor"].get()
                senha = self.protetor_entries["senha_protetor"].get()
                endereco = self.protetor_entries["endereco_protetor"].get()
                tipo = self.protetor_entries["tipo_protetor"].get()
                if not all([nome, email, documento, telefone, senha, endereco, tipo]):
                    messagebox.showerror("Erro", "Preencha todos os campos.")
                    return
                protetor = Protetor(None, nome, senha, endereco, email, documento, telefone, tipo)
                protetor = self.protetor_dao.create(protetor)
                # Preencher ID após criação
                self.protetor_entries["id_protetor"].delete(0, tk.END)
                self.protetor_entries["id_protetor"].insert(0, str(protetor.id))
                messagebox.showinfo("Sucesso", "Protetor criado!")
            elif entity_type == "Adotante":
                nome = self.adotante_entries["nome_adotante"].get()
                email = self.adotante_entries["email_adotante"].get()
                documento = self.adotante_entries["documento_adotante"].get()
                telefone = self.adotante_entries["telefone_adotante"].get()
                senha = self.adotante_entries["senha_adotante"].get()
                endereco = self.adotante_entries["endereco_adotante"].get()
                preferencia = self.adotante_entries["preferencia_adotante"].get()
                if not all([nome, email, documento, telefone, senha, endereco, preferencia]):
                    messagebox.showerror("Erro", "Preencha todos os campos.")
                    return
                adotante = Adotante(None, nome, senha, endereco, email, documento, telefone, preferencia)
                adotante = self.adotante_dao.create(adotante)
                # Preencher ID após criação
                self.adotante_entries["id_adotante"].delete(0, tk.END)
                self.adotante_entries["id_adotante"].insert(0, str(adotante.id))
                messagebox.showinfo("Sucesso", "Adotante criado!")
            elif entity_type == "Animal":
                especie = self.animal_entries["especie_animal"].get()
                raca = self.animal_entries["raca_animal"].get()
                temperamento = self.animal_entries["temperamento_animal"].get()
                historico_saude = self.animal_entries["historico_saude_animal"].get()
                nome = self.animal_entries["nome_animal"].get()
                descricao = self.animal_entries["descricao_animal"].get()
                es_especial = self.animal_entries["especial_animal"].get()
                idade = int(self.animal_entries["idade_animal"].get() or 0)
                sexo = self.animal_entries["sexo_animal"].get()
                status = self.animal_entries["status_animal"].get()
                id_protetor = int(self.animal_entries["id_protetor_animal"].get() or 0)
                if not all([especie, raca, temperamento, historico_saude, nome, descricao, sexo, status, id_protetor]):
                    messagebox.showerror("Erro", "Preencha todos os campos.")
                    return
                protetor = self.protetor_dao.read(id_protetor)
                if not protetor:
                    messagebox.showerror("Erro", "Protetor não encontrado.")
                    return
                animal = Animal(None, especie, raca, temperamento, historico_saude, nome, descricao, es_especial, idade, sexo, status, protetor)
                animal = self.animal_dao.create(animal)
                # Preencher ID após criação
                self.animal_entries["id_animal"].delete(0, tk.END)
                self.animal_entries["id_animal"].insert(0, str(animal.idAnimal))
                messagebox.showinfo("Sucesso", "Animal criado!")
            elif entity_type == "Processo Adocao":
                id_animal = int(self.processo_adocao_entries["id_animal_processo"].get() or 0)
                id_adotante = int(self.processo_adocao_entries["id_adotante_processo"].get() or 0)
                status_str = self.processo_adocao_entries["status_processo"].get()
                data_inicio_str = self.processo_adocao_entries["data_inicio_processo"].get()
                if not all([id_animal, id_adotante, status_str, data_inicio_str]):
                    messagebox.showerror("Erro", "Preencha todos os campos.")
                    return
                animal = self.animal_dao.read(id_animal)
                if not animal:
                    messagebox.showerror("Erro", "Animal não encontrado.")
                    return
                adotante = self.adotante_dao.read(id_adotante)
                if not adotante:
                    messagebox.showerror("Erro", "Adotante não encontrado.")
                    return
                status = StatusProcesso(status_str)
                data_inicio = datetime.strptime(data_inicio_str, '%d-%m-%Y').date()
                processo = ProcessoAdocao(None, animal, adotante, status, data_inicio, id_animal, id_adotante)
                processo = self.processo_adocao_dao.create(processo)
                self.processo_adocao_entries["id_processo_adocao"].delete(0, tk.END)
                self.processo_adocao_entries["id_processo_adocao"].insert(0, str(processo.idPAdocao))
                messagebox.showinfo("Sucesso", "Processo de Adoção criado!")
            elif entity_type == "Adocao":
                data_adocao_str = self.adocao_entries["data_adocao"].get()
                descricao = self.adocao_entries["descricao_adocao"].get()
                termos = self.adocao_entries["termos_adocao"].get()
                id_processo = int(self.adocao_entries["id_processo_adocao"].get() or 0)
                if not all([data_adocao_str, descricao, termos, id_processo]):
                    messagebox.showerror("Erro", "Preencha todos os campos.")
                    return
                processo = self.processo_adocao_dao.read(id_processo)
                if not processo:
                    messagebox.showerror("Erro", "Processo não encontrado.")
                    return
                data_adocao = datetime.strptime(data_adocao_str, '%d-%m-%Y').date()
                adocao = Adocao(None, data_adocao, descricao, termos, processo, id_processo)
                adocao = self.adocao_dao.create(adocao)
                self.adocao_entries["id_adocao"].delete(0, tk.END)
                self.adocao_entries["id_adocao"].insert(0, str(adocao.idAdocao))
                messagebox.showinfo("Sucesso", "Adoção criada!")
        except Exception as e:
            messagebox.showerror("Erro", str(e))
    
    def atualizar_entidade(self):
        entity_type = self.notebook.tab(self.notebook.select(), "text")
        try:
            if entity_type == "Protetor":
                id_val = int(self.protetor_entries["id_protetor"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para atualizar.")
                    return
                protetor = self.protetor_dao.read(id_val)
                if not protetor:
                    messagebox.showerror("Erro", "Protetor não encontrado.")
                    return
                protetor.nome = self.protetor_entries["nome_protetor"].get()
                protetor.email = self.protetor_entries["email_protetor"].get()
                protetor.documento = self.protetor_entries["documento_protetor"].get()
                protetor.telefone = self.protetor_entries["telefone_protetor"].get()
                protetor.senha = self.protetor_entries["senha_protetor"].get()
                protetor.endereco = self.protetor_entries["endereco_protetor"].get()
                protetor.tipo = self.protetor_entries["tipo_protetor"].get()
                self.protetor_dao.update(protetor)
                messagebox.showinfo("Sucesso", "Protetor atualizado!")
            elif entity_type == "Adotante":
                id_val = int(self.adotante_entries["id_adotante"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para atualizar.")
                    return
                adotante = self.adotante_dao.read(id_val)
                if not adotante:
                    messagebox.showerror("Erro", "Adotante não encontrado.")
                    return
                adotante.nome = self.adotante_entries["nome_adotante"].get()
                adotante.email = self.adotante_entries["email_adotante"].get()
                adotante.documento = self.adotante_entries["documento_adotante"].get()
                adotante.telefone = self.adotante_entries["telefone_adotante"].get()
                adotante.senha = self.adotante_entries["senha_adotante"].get()
                adotante.endereco = self.adotante_entries["endereco_adotante"].get()
                adotante.preferenciaAdocao = self.adotante_entries["preferencia_adotante"].get()
                self.adotante_dao.update(adotante)
                messagebox.showinfo("Sucesso", "Adotante atualizado!")
            elif entity_type == "Animal":
                id_val = int(self.animal_entries["id_animal"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para atualizar.")
                    return
                animal = self.animal_dao.read(id_val)
                if not animal:
                    messagebox.showerror("Erro", "Animal não encontrado.")
                    return
                animal.especie = self.animal_entries["especie_animal"].get()
                animal.raca = self.animal_entries["raca_animal"].get()
                animal.temperamento = self.animal_entries["temperamento_animal"].get()
                animal.historicoSaude = self.animal_entries["historico_saude_animal"].get()
                animal.nome = self.animal_entries["nome_animal"].get()
                animal.descricao = self.animal_entries["descricao_animal"].get()
                animal.esEspecial = self.animal_entries["especial_animal"].get()
                animal.idade = int(self.animal_entries["idade_animal"].get() or 0)
                animal.sexo = self.animal_entries["sexo_animal"].get()
                animal.status = self.animal_entries["status_animal"].get()
                id_protetor = int(self.animal_entries["id_protetor_animal"].get() or 0)
                protetor = self.protetor_dao.read(id_protetor)
                if not protetor:
                    messagebox.showerror("Erro", "Protetor não encontrado.")
                    return
                animal.protetor = protetor
                self.animal_dao.update(animal)
                messagebox.showinfo("Sucesso", "Animal atualizado!")
            elif entity_type == "Processo Adocao":
                id_val = int(self.processo_adocao_entries["id_processo_adocao"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para atualizar.")
                    return
                processo = self.processo_adocao_dao.read(id_val)
                if not processo:
                    messagebox.showerror("Erro", "Processo não encontrado.")
                    return
                id_animal = int(self.processo_adocao_entries["id_animal_processo"].get() or 0)
                id_adotante = int(self.processo_adocao_entries["id_adotante_processo"].get() or 0)
                status_str = self.processo_adocao_entries["status_processo"].get()
                data_inicio_str = self.processo_adocao_entries["data_inicio_processo"].get()
                animal = self.animal_dao.read(id_animal)
                if not animal:
                    messagebox.showerror("Erro", "Animal não encontrado.")
                    return
                adotante = self.adotante_dao.read(id_adotante)
                if not adotante:
                    messagebox.showerror("Erro", "Adotante não encontrado.")
                    return
                processo.animal = animal
                processo.adotante = adotante
                processo.statusProcesso = StatusProcesso(status_str)
                processo.dataInicio = datetime.strptime(data_inicio_str, '%d-%m-%Y').date()
                processo.id_animal = id_animal
                processo.id_adotante = id_adotante
                self.processo_adocao_dao.update(processo)
                messagebox.showinfo("Sucesso", "Processo de Adoção atualizado!")
            elif entity_type == "Adocao":
                id_val = int(self.adocao_entries["id_adocao"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para atualizar.")
                    return
                adocao = self.adocao_dao.read(id_val)
                if not adocao:
                    messagebox.showerror("Erro", "Adoção não encontrada.")
                    return
                data_adocao_str = self.adocao_entries["data_adocao"].get()
                descricao = self.adocao_entries["descricao_adocao"].get()
                termos = self.adocao_entries["termos_adocao"].get()
                id_processo = int(self.adocao_entries["id_processo_adocao"].get() or 0)
                processo = self.processo_adocao_dao.read(id_processo)
                if not processo:
                    messagebox.showerror("Erro", "Processo não encontrado.")
                    return
                adocao.dataAdocao = datetime.strptime(data_adocao_str, '%d-%m-%Y').date()
                adocao.descricao = descricao
                adocao.termos = termos
                adocao.processoAdocao = processo
                adocao.id_processo = id_processo
                self.adocao_dao.update(adocao)
                messagebox.showinfo("Sucesso", "Adoção atualizada!")
        except Exception as e:
            messagebox.showerror("Erro", str(e))
    
    def ler_entidade(self):
        entity_type = self.notebook.tab(self.notebook.select(), "text")
        try:
            if entity_type == "Protetor":
                entity_id_str = self.protetor_entries["id_protetor"].get().strip()
                if not entity_id_str:
                    # Listar todos
                    protetores = self.protetor_dao.readAll()
                    lista = "\n".join([f"ID: {p.id} - Nome: {p.nome} - Email: {p.email}" for p in protetores])
                    messagebox.showinfo("Lista de Protetores", lista if lista else "Nenhum protetor encontrado.")
                else:
                    entity_id = int(entity_id_str)
                    protetor = self.protetor_dao.read(entity_id)
                    if protetor:
                        self.protetor_entries["nome_protetor"].delete(0, tk.END)
                        self.protetor_entries["nome_protetor"].insert(0, protetor.nome)
                        self.protetor_entries["email_protetor"].delete(0, tk.END)
                        self.protetor_entries["email_protetor"].insert(0, protetor.email)
                        self.protetor_entries["documento_protetor"].delete(0, tk.END)
                        self.protetor_entries["documento_protetor"].insert(0, protetor.documento)
                        self.protetor_entries["telefone_protetor"].delete(0, tk.END)
                        self.protetor_entries["telefone_protetor"].insert(0, protetor.telefone)
                        self.protetor_entries["endereco_protetor"].delete(0, tk.END)
                        self.protetor_entries["endereco_protetor"].insert(0, protetor.endereco)
                        self.protetor_entries["senha_protetor"].delete(0, tk.END)
                        self.protetor_entries["senha_protetor"].insert(0, protetor.senha)
                        self.protetor_entries["tipo_protetor"].delete(0, tk.END)
                        self.protetor_entries["tipo_protetor"].insert(0, protetor.tipo)
                        messagebox.showinfo("Sucesso", "Protetor carregado!")
                    else:
                        messagebox.showerror("Erro", "Protetor não encontrado.")
            elif entity_type == "Adotante":
                entity_id_str = self.adotante_entries["id_adotante"].get().strip()
                if not entity_id_str:
                    # Listar todos
                    adotantes = self.adotante_dao.readAll()
                    lista = "\n".join([f"ID: {a.id} - Nome: {a.nome} - Email: {a.email}" for a in adotantes])
                    messagebox.showinfo("Lista de Adotantes", lista if lista else "Nenhum adotante encontrado.")
                else:
                    entity_id = int(entity_id_str)
                    adotante = self.adotante_dao.read(entity_id)
                    if adotante:
                        self.adotante_entries["nome_adotante"].delete(0, tk.END)
                        self.adotante_entries["nome_adotante"].insert(0, adotante.nome)
                        self.adotante_entries["email_adotante"].delete(0, tk.END)
                        self.adotante_entries["email_adotante"].insert(0, adotante.email)
                        self.adotante_entries["documento_adotante"].delete(0, tk.END)
                        self.adotante_entries["documento_adotante"].insert(0, adotante.documento)
                        self.adotante_entries["telefone_adotante"].delete(0, tk.END)
                        self.adotante_entries["telefone_adotante"].insert(0, adotante.telefone)
                        self.adotante_entries["endereco_adotante"].delete(0, tk.END)
                        self.adotante_entries["endereco_adotante"].insert(0, adotante.endereco)
                        self.adotante_entries["senha_adotante"].delete(0, tk.END)
                        self.adotante_entries["senha_adotante"].insert(0, adotante.senha)
                        self.adotante_entries["preferencia_adotante"].delete(0, tk.END)
                        self.adotante_entries["preferencia_adotante"].insert(0, adotante.preferenciaAdocao)
                        messagebox.showinfo("Sucesso", "Adotante carregado!")
                    else:
                        messagebox.showerror("Erro", "Adotante não encontrado.")
            elif entity_type == "Animal":
                entity_id_str = self.animal_entries["id_animal"].get().strip()
                if not entity_id_str:
                    # Listar todos
                    animais = self.animal_dao.readAll()
                    lista = "\n".join([f"ID: {an.idAnimal} - Nome: {an.nome} - Espécie: {an.especie}" for an in animais])
                    messagebox.showinfo("Lista de Animais", lista if lista else "Nenhum animal encontrado.")
                else:
                    entity_id = int(entity_id_str)
                    animal = self.animal_dao.read(entity_id)
                    if animal:
                        self.animal_entries["nome_animal"].delete(0, tk.END)
                        self.animal_entries["nome_animal"].insert(0, animal.nome)
                        self.animal_entries["especie_animal"].delete(0, tk.END)
                        self.animal_entries["especie_animal"].insert(0, animal.especie)
                        self.animal_entries["especial_animal"].set(animal.esEspecial)
                        self.animal_entries["raca_animal"].delete(0, tk.END)
                        self.animal_entries["raca_animal"].insert(0, animal.raca)
                        self.animal_entries["temperamento_animal"].delete(0, tk.END)
                        self.animal_entries["temperamento_animal"].insert(0, animal.temperamento)
                        self.animal_entries["historico_saude_animal"].delete(0, tk.END)
                        self.animal_entries["historico_saude_animal"].insert(0, animal.historicoSaude)
                        self.animal_entries["descricao_animal"].delete(0, tk.END)
                        self.animal_entries["descricao_animal"].insert(0, animal.descricao)
                        self.animal_entries["idade_animal"].delete(0, tk.END)
                        self.animal_entries["idade_animal"].insert(0, str(animal.idade))
                        self.animal_entries["sexo_animal"].set(animal.sexo)
                        self.animal_entries["status_animal"].delete(0, tk.END)
                        self.animal_entries["status_animal"].insert(0, animal.status)
                        self.animal_entries["id_protetor_animal"].delete(0, tk.END)
                        self.animal_entries["id_protetor_animal"].insert(0, str(animal.protetor.id))
                        messagebox.showinfo("Sucesso", "Animal carregado!")
                    else:
                        messagebox.showerror("Erro", "Animal não encontrado.")
            elif entity_type == "Processo Adocao":
                entity_id_str = self.processo_adocao_entries["id_processo_adocao"].get().strip()
                if not entity_id_str:
                    processos = self.processo_adocao_dao.readAll()
                    lista = "\n".join([f"ID: {p.idPAdocao} - Animal: {p.animal.nome} - Adotante: {p.adotante.nome}" for p in processos])
                    messagebox.showinfo("Lista de Processos", lista if lista else "Nenhum processo encontrado.")
                else:
                    entity_id = int(entity_id_str)
                    processo = self.processo_adocao_dao.read(entity_id)
                    if processo:
                        self.processo_adocao_entries["id_animal_processo"].delete(0, tk.END)
                        self.processo_adocao_entries["id_animal_processo"].insert(0, str(processo.id_animal))
                        self.processo_adocao_entries["id_adotante_processo"].delete(0, tk.END)
                        self.processo_adocao_entries["id_adotante_processo"].insert(0, str(processo.id_adotante))
                        self.processo_adocao_entries["status_processo"].set(processo.statusProcesso.value)
                        self.processo_adocao_entries["data_inicio_processo"].delete(0, tk.END)
                        self.processo_adocao_entries["data_inicio_processo"].insert(0, processo.dataInicio.strftime('%d-%m-%Y'))
                        messagebox.showinfo("Sucesso", "Processo carregado!")
                    else:
                        messagebox.showerror("Erro", "Processo não encontrado.")
            elif entity_type == "Adocao":
                entity_id_str = self.adocao_entries["id_adocao"].get().strip()
                if not entity_id_str:
                    adocoes = self.adocao_dao.readAll()
                    lista = "\n".join([f"ID: {a.idAdocao} - Data: {a.dataAdocao.strftime('%d-%m-%Y')}" for a in adocoes])
                    messagebox.showinfo("Lista de Adoções", lista if lista else "Nenhuma adoção encontrada.")
                else:
                    entity_id = int(entity_id_str)
                    adocao = self.adocao_dao.read(entity_id)
                    if adocao:
                        self.adocao_entries["data_adocao"].delete(0, tk.END)
                        self.adocao_entries["data_adocao"].insert(0, adocao.dataAdocao.strftime('%d-%m-%Y'))
                        self.adocao_entries["descricao_adocao"].delete(0, tk.END)
                        self.adocao_entries["descricao_adocao"].insert(0, adocao.descricao)
                        self.adocao_entries["termos_adocao"].delete(0, tk.END)
                        self.adocao_entries["termos_adocao"].insert(0, adocao.termos)
                        self.adocao_entries["id_processo_adocao"].delete(0, tk.END)
                        self.adocao_entries["id_processo_adocao"].insert(0, str(adocao.id_processo))
                        messagebox.showinfo("Sucesso", "Adoção carregada!")
                    else:
                        messagebox.showerror("Erro", "Adoção não encontrada.")
        except ValueError:
            messagebox.showerror("Erro", "ID deve ser um número válido.")
        except Exception as e:
            messagebox.showerror("Erro", str(e))
            
    def deletar_entidade(self):
        entity_type = self.notebook.tab(self.notebook.select(), "text")
        try:
            if entity_type == "Protetor":
                id_val = int(self.protetor_entries["id_protetor"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para deletar.")
                    return
                resposta = messagebox.askyesno("Confirmação", "Deseja deletar este protetor?")
                if resposta:
                    self.protetor_dao.delete(id_val)
                    messagebox.showinfo("Sucesso", "Protetor deletado!")
                    self.clear_protetor()
            elif entity_type == "Adotante":
                id_val = int(self.adotante_entries["id_adotante"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para deletar.")
                    return
                resposta = messagebox.askyesno("Confirmação", "Deseja deletar este adotante?")
                if resposta:
                    self.adotante_dao.delete(id_val)
                    messagebox.showinfo("Sucesso", "Adotante deletado!")
                    self.clear_adotante()
            elif entity_type == "Animal":
                id_val = int(self.animal_entries["id_animal"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para deletar.")
                    return
                resposta = messagebox.askyesno("Confirmação", "Deseja deletar este animal?")
                if resposta:
                    self.animal_dao.delete(id_val)
                    messagebox.showinfo("Sucesso", "Animal deletado!")
                    self.clear_animal()
            elif entity_type == "Processo Adocao":
                id_val = int(self.processo_adocao_entries["id_processo_adocao"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para deletar.")
                    return
                resposta = messagebox.askyesno("Confirmação", "Deseja deletar este processo?")
                if resposta:
                    self.processo_adocao_dao.delete(id_val)
                    messagebox.showinfo("Sucesso", "Processo deletado!")
                    self.clear_processo_adocao()
            elif entity_type == "Adocao":
                id_val = int(self.adocao_entries["id_adocao"].get() or 0)
                if not id_val:
                    messagebox.showerror("Erro", "ID necessário para deletar.")
                    return
                resposta = messagebox.askyesno("Confirmação", "Deseja deletar esta adoção?")
                if resposta:
                    self.adocao_dao.delete(id_val)
                    messagebox.showinfo("Sucesso", "Adoção deletada!")
                    self.clear_adocao()
        except Exception as e:
            messagebox.showerror("Erro", str(e))
        
        
    def clear_protetor(self):
        for entry in self.protetor_entries.values():
            if isinstance(entry, ttk.Entry):
                entry.delete(0, tk.END)

    def clear_adotante(self):
        for entry in self.adotante_entries.values():
            if isinstance(entry, ttk.Entry):
                entry.delete(0, tk.END)

    def clear_animal(self):
        for key, widget in self.animal_entries.items():
            if isinstance(widget, ttk.Entry):
                widget.delete(0, tk.END)
            elif isinstance(widget, tk.BooleanVar):
                widget.set(False)
            elif isinstance(widget, ttk.Combobox):
                widget.set("")

    def clear_processo_adocao(self):
        for key, widget in self.processo_adocao_entries.items():
            if isinstance(widget, ttk.Entry):
                widget.delete(0, tk.END)
            elif isinstance(widget, ttk.Combobox):
                widget.set("")

    def clear_adocao(self):
        for entry in self.adocao_entries.values():
            if isinstance(entry, ttk.Entry):
                entry.delete(0, tk.END)

    def logout(self):
        # Simular logout - voltar para login
        self.controller.show_frame("Login")
        
    def on_back(self):
        if self.controller:
            self.controller.show_frame("Welcome")
        