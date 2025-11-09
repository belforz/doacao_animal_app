import DAO.AdotanteDAO;
import exceptions.CustomException;
import schemas.Adotante;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            // Teste de criação do DAO
            AdotanteDAO dao = new AdotanteDAO();
            System.out.println("DAO criado com sucesso!");

            // Teste com adotante
            Adotante adotante = new Adotante(22,"Leandro", "123456", "Rua B, 488", "macedobeiramar@hotmail.com","04137536201","11111111", "Adotante");
            dao.create(adotante);
            System.out.println("Adotante criado com ID: " + adotante.getId());

            // Teste de leitura
            Adotante adt = dao.read(adotante.getId());
            if (adt != null) {
                System.out.println("Adotante lido: " + adt.getNome());
            }

        } catch (CustomException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
    }
}