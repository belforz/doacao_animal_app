package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
    private static Connection connection;

    private MYSQLConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/doacao_animal";
                String user = "root";
                String password = "123456";
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão realizada com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args){
        getConnection();
        closeConnection();
    }
}
