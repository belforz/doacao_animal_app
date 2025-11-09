package DAO;

import schemas.EtapaProcesso;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtapaProcessoDAO {
    private Connection connection;

    public EtapaProcessoDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir uma nova EtapaProcesso
    public void create(EtapaProcesso etapa) throws CustomException {
        String sql = "INSERT INTO EtapaProcesso (data, observacoes, id_processo, statusEtapa, tipoEtapa) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(etapa.getData()));
            stmt.setString(2, etapa.getObservacoes());
            stmt.setInt(3, etapa.getId_processo());
            stmt.setString(4, etapa.getStatusEtapa());
            stmt.setString(5, etapa.getTipoEtapa());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                etapa.setId(rs.getInt(1));
            }
            System.out.println("EtapaProcesso criada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar EtapaProcesso", e);
        }
    }

    // READ: Buscar uma EtapaProcesso por ID
    public EtapaProcesso read(int id) throws CustomException {
        String sql = "SELECT * FROM EtapaProcesso WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EtapaProcesso etapa = new EtapaProcesso();
                etapa.setId(rs.getInt("id"));
                etapa.setData(rs.getDate("data").toLocalDate());
                etapa.setObservacoes(rs.getString("observacoes"));
                etapa.setId_processo(rs.getInt("id_processo"));
                etapa.setStatusEtapa(rs.getString("statusEtapa"));
                etapa.setTipoEtapa(rs.getString("tipoEtapa"));
                return etapa;
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar EtapaProcesso", e);
        }
        return null;
    }

    // READ ALL: Listar todas as EtapaProcesso
    public List<EtapaProcesso> readAll() throws CustomException {
        List<EtapaProcesso> etapas = new ArrayList<>();
        String sql = "SELECT * FROM EtapaProcesso";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EtapaProcesso etapa = new EtapaProcesso();
                etapa.setId(rs.getInt("id"));
                etapa.setData(rs.getDate("data").toLocalDate());
                etapa.setObservacoes(rs.getString("observacoes"));
                etapa.setId_processo(rs.getInt("id_processo"));
                etapa.setStatusEtapa(rs.getString("statusEtapa"));
                etapa.setTipoEtapa(rs.getString("tipoEtapa"));
                etapas.add(etapa);
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar EtapaProcesso", e);
        }
        return etapas;
    }

    // UPDATE: Atualizar uma EtapaProcesso
    public void update(EtapaProcesso etapa) throws CustomException {
        String sql = "UPDATE EtapaProcesso SET data = ?, observacoes = ?, id_processo = ?, statusEtapa = ?, tipoEtapa = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(etapa.getData()));
            stmt.setString(2, etapa.getObservacoes());
            stmt.setInt(3, etapa.getId_processo());
            stmt.setString(4, etapa.getStatusEtapa());
            stmt.setString(5, etapa.getTipoEtapa());
            stmt.setInt(6, etapa.getId());
            stmt.executeUpdate();
            System.out.println("EtapaProcesso atualizada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar EtapaProcesso", e);
        }
    }

    // DELETE: Deletar uma EtapaProcesso por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM EtapaProcesso WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("EtapaProcesso deletada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar EtapaProcesso", e);
        }
    }
}
