package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
    public static void main(String[]args){
        String url = "jdbc:mysql://localhost:3306/doacao_animal";
        String user = "root";
        String password = "123456";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso!");
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
