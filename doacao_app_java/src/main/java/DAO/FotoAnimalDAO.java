package DAO;

import schemas.FotoAnimal;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FotoAnimalDAO {
    private Connection connection;

    public FotoAnimalDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir uma nova FotoAnimal
    public void create(FotoAnimal foto) throws CustomException {
        String sql = "INSERT INTO FotoAnimal (urlAnimal, descricaoFoto, idAnimal) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, foto.getUrlAnimal());
            stmt.setString(2, foto.getDescricaoFoto());
            stmt.setInt(3, foto.getIdAnimal());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                foto.setIdFotoAnimal(rs.getInt(1));
            }
            System.out.println("FotoAnimal criada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar FotoAnimal", e);
        }
    }

    // READ: Buscar uma FotoAnimal por ID
    public FotoAnimal read(int id) throws CustomException {
        String sql = "SELECT * FROM FotoAnimal WHERE idFotoAnimal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FotoAnimal(
                    rs.getString("urlAnimal"),
                    rs.getString("descricaoFoto"),
                    rs.getInt("idAnimal"),
                    rs.getInt("idFotoAnimal")
                );
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar FotoAnimal", e);
        }
        return null;
    }

    // READ ALL: Listar todas as FotoAnimal
    public List<FotoAnimal> readAll() throws CustomException {
        List<FotoAnimal> fotos = new ArrayList<>();
        String sql = "SELECT * FROM FotoAnimal";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                fotos.add(new FotoAnimal(
                    rs.getString("urlAnimal"),
                    rs.getString("descricaoFoto"),
                    rs.getInt("idAnimal"),
                    rs.getInt("idFotoAnimal")
                ));
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar FotoAnimal", e);
        }
        return fotos;
    }

    // UPDATE: Atualizar uma FotoAnimal
    public void update(FotoAnimal foto) throws CustomException {
        String sql = "UPDATE FotoAnimal SET urlAnimal = ?, descricaoFoto = ?, idAnimal = ? WHERE idFotoAnimal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, foto.getUrlAnimal());
            stmt.setString(2, foto.getDescricaoFoto());
            stmt.setInt(3, foto.getIdAnimal());
            stmt.setInt(4, foto.getIdFotoAnimal());
            stmt.executeUpdate();
            System.out.println("FotoAnimal atualizada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar FotoAnimal", e);
        }
    }

    // DELETE: Deletar uma FotoAnimal por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM FotoAnimal WHERE idFotoAnimal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("FotoAnimal deletada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar FotoAnimal", e);
        }
    }
}
