package DAO;

import schemas.Protetor;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProtetorDAO {
    private Connection connection;

    public ProtetorDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir um novo Protetor
    public void create(Protetor protetor) throws CustomException {
        String sql = "INSERT INTO Protetor (nome, email, documento, telefone, senha, endereco, preferenciaAdocao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, protetor.getNome());
            stmt.setString(2, protetor.getEmail());
            stmt.setString(3, protetor.getDocumento());
            stmt.setString(4, protetor.getTelefone());
            stmt.setString(5, protetor.getSenha());
            stmt.setString(6, protetor.getEndereco());
            stmt.setString(7, protetor.getPreferenciaAdocao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                protetor.setId(rs.getInt(1));
            }
            System.out.println("Protetor criado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar Protetor", e);
        }
    }

    // READ: Buscar um Protetor por ID
    public Protetor read(int id) throws CustomException {
        String sql = "SELECT * FROM Protetor WHERE idProtetor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Protetor(
                    rs.getInt("idProtetor"),
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
            throw new CustomException("Erro ao buscar Protetor", e);
        }
        return null;
    }

    // READ ALL: Listar todos os Protetores
    public List<Protetor> readAll() throws CustomException {
        List<Protetor> protetores = new ArrayList<>();
        String sql = "SELECT * FROM Protetor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                protetores.add(new Protetor(
                    rs.getInt("idProtetor"),
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getString("endereco"),
                    rs.getString("email"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("preferenciaAdocao")
                ));
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar Protetores", e);
        }
        return protetores;
    }

    // UPDATE: Atualizar um Protetor
    public void update(Protetor protetor) throws CustomException {
        String sql = "UPDATE Protetor SET nome = ?, email = ?, documento = ?, telefone = ?, senha = ?, endereco = ?, preferenciaAdocao = ? WHERE idProtetor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, protetor.getNome());
            stmt.setString(2, protetor.getEmail());
            stmt.setString(3, protetor.getDocumento());
            stmt.setString(4, protetor.getTelefone());
            stmt.setString(5, protetor.getSenha());
            stmt.setString(6, protetor.getEndereco());
            stmt.setString(7, protetor.getPreferenciaAdocao());
            stmt.setInt(8, protetor.getId());
            stmt.executeUpdate();
            System.out.println("Protetor atualizado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar Protetor", e);
        }
    }

    // DELETE: Deletar um Protetor por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM Protetor WHERE idProtetor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Protetor deletado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar Protetor", e);
        }
    }
}
