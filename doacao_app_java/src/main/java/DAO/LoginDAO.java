package DAO;

import schemas.Adotante;
import schemas.Protetor;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;

public class LoginDAO {
    private Connection connection;

    public LoginDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // LOGIN: Verificar credenciais de Adotante e Protetor por senha e email via READ do dao


    public Adotante loginAdotante(String email, String senha) throws CustomException {
        String sql = "SELECT * FROM Adotante WHERE email = ? AND senha = ?";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setString(1, email);
            smt.setString(2, senha);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                return new Adotante(
                        rs.getInt("idAdotante"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("documento"),
                        rs.getString("telefone"),
                        rs.getString("preferenciaAdocao")
                );
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar Adotante", e);
        }
        return null;
    }

    public Protetor loginProtetor(String email, String senha) throws CustomException {
        String sql = "SELECT * FROM Protetor WHERE email = ? AND senha = ?";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setString(1, email);
            smt.setString(2, senha);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                return new Protetor(
                        rs.getInt("idProtetor"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("documento"),
                        rs.getString("telefone"),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar Protetor", e);
        }
        return null;
    }
}
