package DAO;

import schemas.*;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessoAdocaoDAO {
     private Connection connection;

    public ProcessoAdocaoDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir um novo ProcessoAdocao
    public void create(ProcessoAdocao processo) throws CustomException {
        String sql = "INSERT INTO ProcessoAdocao (id_animal, id_adotante, status, dataInicio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, processo.getId_animal());
            stmt.setInt(2, processo.getId_adotante());
            stmt.setString(3, processo.getStatus().name());
            stmt.setDate(4, Date.valueOf(processo.getDataIncio()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                processo.setIdPAdocao(rs.getInt(1));
            }
            System.out.println("ProcessoAdocao criado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar ProcessoAdocao", e);
        }
    }

    // READ: Buscar um ProcessoAdocao por ID
    public ProcessoAdocao read(int id) throws CustomException {
        String sql = "SELECT * FROM ProcessoAdocao WHERE idPAdocao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProcessoAdocao processo = new ProcessoAdocao();
                    processo.setIdPAdocao(rs.getInt("idPAdocao"));
                    processo.setId_animal(rs.getInt("id_animal"));
                    processo.setId_adotante(rs.getInt("id_adotante"));
                    processo.setStatus(StatusProcesso.valueOf(rs.getString("status")));
                    processo.setDataIncio(rs.getDate("dataInicio").toLocalDate());

                    AnimalDAO animalDAO = new AnimalDAO();
                    processo.setAnimal(animalDAO.read(processo.getId_animal()));

                    AdotanteDAO adotanteDAO = new AdotanteDAO();
                    processo.setAdotante(adotanteDAO.read(processo.getId_adotante()));

                    return processo;
                }
            }
        } catch (Exception e) {
            throw new CustomException("Erro ao buscar ProcessoAdocao", e);
        }
        return null;
    }

    // READ ALL: Listar todos os ProcessoAdocao
    public List<ProcessoAdocao> readAll() throws CustomException {
        List<ProcessoAdocao> processos = new ArrayList<>();
        String sql = "SELECT * FROM ProcessoAdocao";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            AnimalDAO animalDAO = new AnimalDAO();
            AdotanteDAO adotanteDAO = new AdotanteDAO();

            while (rs.next()) {
                ProcessoAdocao processo = new ProcessoAdocao();
                processo.setIdPAdocao(rs.getInt("idPAdocao"));
                processo.setId_animal(rs.getInt("id_animal"));
                processo.setId_adotante(rs.getInt("id_adotante"));
                processo.setStatus(StatusProcesso.valueOf(rs.getString("status")));
                processo.setDataIncio((rs.getDate("dataInicio").toLocalDate()));

                processo.setAnimal(animalDAO.read(processo.getId_animal()));
                processo.setAdotante(adotanteDAO.read(processo.getId_adotante()));

                processos.add(processo);
            }
        } catch (Exception e) {
            throw new CustomException("Erro ao listar ProcessoAdocao", e);
        }
        return processos;
    }


    // UPDATE: Atualizar um ProcessoAdocao
    public void update(ProcessoAdocao processo) throws CustomException {
        String sql = "UPDATE ProcessoAdocao SET id_animal = ?, id_adotante = ?, status = ?, dataInicio = ? WHERE idPAdocao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, processo.getId_animal());
            stmt.setInt(2, processo.getId_adotante());
            stmt.setString(3, processo.getStatus().name());
            stmt.setDate(4, Date.valueOf(processo.getDataIncio()));
            stmt.setInt(5, processo.getIdPAdocao());
            stmt.executeUpdate();
            System.out.println("ProcessoAdocao atualizado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar ProcessoAdocao", e);
        }
    }

    // DELETE: Deletar um ProcessoAdocao por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM ProcessoAdocao WHERE idPAdocao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("ProcessoAdocao deletado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar ProcessoAdocao", e);
        }
    }
}
