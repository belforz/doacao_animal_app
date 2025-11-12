import tkinter as tk

janela = tk.Tk()
janela.title("Minha primeira janela")
janela.geometry("300x300")

rotulo = tk.Label(janela, text="Olá, mundo!")
rotulo.pack()

janela.mainloop()

# def main():
#     print("Hello, World!")

# if __name__ == "__main__":
#     main()
    
