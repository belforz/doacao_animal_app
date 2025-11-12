package DAO;

import schemas.Adotante;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdotanteDAO {
    private Connection connection;

    public AdotanteDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir um novo Adotante
    public void create(Adotante adotante) throws CustomException {
        String sql = "INSERT INTO Adotante (nome, email, documento, telefone, senha, endereco, preferenciaAdocao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, adotante.getNome());
            stmt.setString(2, adotante.getEmail());
            stmt.setString(3, adotante.getDocumento());
            stmt.setString(4, adotante.getTelefone());
            stmt.setString(5, adotante.getSenha());
            stmt.setString(6, adotante.getEndereco());
            stmt.setString(7, adotante.getPreferenciaAdocao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                adotante.setId(rs.getInt(1));
            }
            System.out.println("Adotante criado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar Adotante", e);
        }
    }

    // READ: Buscar um Adotante por ID
    public Adotante read(int id) throws CustomException {
        String sql = "SELECT * FROM Adotante WHERE idAdotante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
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

    // READ ALL: Listar todos os Adotantes
    public List<Adotante> readAll() throws CustomException {
        List<Adotante> adotantes = new ArrayList<>();
        String sql = "SELECT * FROM Adotante";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                adotantes.add(new Adotante(
                    rs.getInt("idAdotante"),
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
            throw new CustomException("Erro ao listar Adotantes", e);
        }
        return adotantes;
    }

    // UPDATE: Atualizar um Adotante
    public void update(Adotante adotante) throws CustomException {
        String sql = "UPDATE Adotante SET nome = ?, email = ?, documento = ?, telefone = ?, senha = ?, endereco = ?, preferenciaAdocao = ? WHERE idAdotante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, adotante.getNome());
            stmt.setString(2, adotante.getEmail());
            stmt.setString(3, adotante.getDocumento());
            stmt.setString(4, adotante.getTelefone());
            stmt.setString(5, adotante.getSenha());
            stmt.setString(6, adotante.getEndereco());
            stmt.setString(7, adotante.getPreferenciaAdocao());
            stmt.setInt(8, adotante.getId());
            stmt.executeUpdate();
            System.out.println("Adotante atualizado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar Adotante", e);
        }
    }

    // DELETE: Deletar um Adotante por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM Adotante WHERE idAdotante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Adotante deletado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar Adotante", e);
        }
    }
}
