package DAO;

import schemas.SuportePosAdocao;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuportePosAdocaoDAO {
    private Connection connection;

    public SuportePosAdocaoDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir um novo SuportePosAdocao
    public void create(SuportePosAdocao suporte) throws CustomException {
        String sql = "INSERT INTO SuportePosAdocao (dataRegistro, tipoSolicitacao, descricao, idAdocao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(suporte.getDataRegistro()));
            stmt.setString(2, suporte.getTipoSolicitacao());
            stmt.setString(3, suporte.getDescricao());
            stmt.setInt(4, suporte.getIdAdocao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                suporte.setIdSuporte(rs.getInt(1));
            }
            System.out.println("SuportePosAdocao criado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar SuportePosAdocao", e);
        }
    }

    // READ: Buscar um SuportePosAdocao por ID
    public SuportePosAdocao read(int id) throws CustomException {
        String sql = "SELECT * FROM SuportePosAdocao WHERE idSuporte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SuportePosAdocao(
                    rs.getInt("idSuporte"),
                    rs.getDate("dataRegistro").toLocalDate(),
                    rs.getString("tipoSolicitacao"),
                    rs.getString("descricao"),
                    rs.getInt("idAdocao")
                );
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar SuportePosAdocao", e);
        }
        return null;
    }

    // READ ALL: Listar todos os SuportePosAdocao
    public List<SuportePosAdocao> readAll() throws CustomException {
        List<SuportePosAdocao> suportes = new ArrayList<>();
        String sql = "SELECT * FROM SuportePosAdocao";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                suportes.add(new SuportePosAdocao(
                    rs.getInt("idSuporte"),
                    rs.getDate("dataRegistro").toLocalDate(),
                    rs.getString("tipoSolicitacao"),
                    rs.getString("descricao"),
                    rs.getInt("idAdocao")
                ));
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar SuportePosAdocao", e);
        }
        return suportes;
    }

    // UPDATE: Atualizar um SuportePosAdocao
    public void update(SuportePosAdocao suporte) throws CustomException {
        String sql = "UPDATE SuportePosAdocao SET dataRegistro = ?, tipoSolicitacao = ?, descricao = ?, idAdocao = ? WHERE idSuporte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(suporte.getDataRegistro()));
            stmt.setString(2, suporte.getTipoSolicitacao());
            stmt.setString(3, suporte.getDescricao());
            stmt.setInt(4, suporte.getIdAdocao());
            stmt.setInt(5, suporte.getIdSuporte());
            stmt.executeUpdate();
            System.out.println("SuportePosAdocao atualizado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar SuportePosAdocao", e);
        }
    }

    // DELETE: Deletar um SuportePosAdocao por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM SuportePosAdocao WHERE idSuporte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("SuportePosAdocao deletado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar SuportePosAdocao", e);
        }
    }
}
