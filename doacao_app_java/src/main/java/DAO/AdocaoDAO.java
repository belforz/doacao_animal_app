package DAO;

import schemas.Adocao;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO {
    private Connection connection;

    public AdocaoDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir uma nova Adocao
    public void create(Adocao adocao) throws CustomException {
        String sql = "INSERT INTO Adocao (dataAdocao, descricao, termos, id_processo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(adocao.getDataAdocao()));
            stmt.setString(2, adocao.getDescricao());
            stmt.setString(3, adocao.getTermos());
            stmt.setInt(4, adocao.getId_processo());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                adocao.setIdAdocao(rs.getInt(1));
            }
            System.out.println("Adocao criada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar Adocao", e);
        }
    }

    // READ: Buscar uma Adocao por ID
    public Adocao read(int id) throws CustomException {
        String sql = "SELECT * FROM Adocao WHERE idAdocao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Adocao(
                    rs.getInt("idAdocao"),
                    rs.getDate("dataAdocao").toLocalDate(),
                    rs.getString("descricao"),
                    rs.getString("termos"),
                    rs.getInt("id_processo")
                );
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar Adocao", e);
        }
        return null;
    }

    // READ ALL: Listar todas as Adocao
    public List<Adocao> readAll() throws CustomException {
        List<Adocao> adocoes = new ArrayList<>();
        String sql = "SELECT * FROM Adocao";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                adocoes.add(new Adocao(
                    rs.getInt("idAdocao"),
                    rs.getDate("dataAdocao").toLocalDate(),
                    rs.getString("descricao"),
                    rs.getString("termos"),
                    rs.getInt("id_processo")
                ));
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar Adocao", e);
        }
        return adocoes;
    }

    // UPDATE: Atualizar uma Adocao
    public void update(Adocao adocao) throws CustomException {
        String sql = "UPDATE Adocao SET dataAdocao = ?, descricao = ?, termos = ?, id_processo = ? WHERE idAdocao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(adocao.getDataAdocao()));
            stmt.setString(2, adocao.getDescricao());
            stmt.setString(3, adocao.getTermos());
            stmt.setInt(4, adocao.getId_processo());
            stmt.setInt(5, adocao.getIdAdocao());
            stmt.executeUpdate();
            System.out.println("Adocao atualizada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar Adocao", e);
        }
    }

    // DELETE: Deletar uma Adocao por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM Adocao WHERE idAdocao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Adocao deletada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar Adocao", e);
        }
    }
}
