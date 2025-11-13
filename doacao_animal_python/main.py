# import tkinter as tk



# janela = tk.Tk()
# janela.title("Minha primeira janela")
# janela.geometry("300x300")

# rotulo = tk.Label(janela, text="Olá, mundo!")
# rotulo.pack()

# janela.mainloop()

# def main():
#     print("Hello, World!")

from repository.mysql_connection import MYSQLConnection


if __name__ == "__main__":
    conn = MYSQLConnection.get_connection()
    
    mysql_query = "SELECT * from Adotante"
    cursor = conn.cursor()
    cursor.execute(mysql_query)
    results = cursor.fetchall()
    for row in results:
        print(row)
    cursor.close()
    conn.close()
    