import tkinter as tk


class TelaPrincipal:
    def __init__(self):
        self.janela = tk.Tk()
        self.janela.title("Minha primeira janela")
        self.janela.geometry("300x300")

        rotulo = tk.Label(self.janela, text="Olá, mundo!")
        rotulo.pack()

        self.janela.mainloop()