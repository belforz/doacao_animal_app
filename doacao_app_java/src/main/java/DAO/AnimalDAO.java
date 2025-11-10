package DAO;

import schemas.Animal;
import repository.MYSQLConnection;
import exceptions.CustomException;
import schemas.Protetor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    private Connection connection;

    public AnimalDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir um novo Animal
    public void create(Animal animal) throws CustomException {
        String sql = "INSERT INTO Animal (especie, raca, temperamento, historicoSaude, nome, descricao, esEspecial, idade, sexo, status, id_protetor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getEspecie());
            stmt.setString(2, animal.getRaca());
            stmt.setString(3, animal.getTemperamento());
            stmt.setString(4, animal.getHistoricoSaude());
            stmt.setString(5, animal.getNome());
            stmt.setString(6, animal.getDescricao());
            stmt.setBoolean(7, animal.getEsEspecial());
            stmt.setInt(8, animal.getIdade());
            stmt.setString(9, String.valueOf(animal.getSexo()));
            stmt.setString(10, animal.getStatus());
            stmt.setInt(11, animal.getProtetor().getId());
            stmt.executeUpdate();
            System.out.println("Animal criado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar Animal", e);
        }
    }

    // READ: Buscar um Animal por ID
    public Animal read(int idAnimal) throws CustomException {
        String sql = "SELECT * FROM Animal WHERE idAnimal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAnimal);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("idAnimal"));
                animal.setEspecie(rs.getString("especie"));
                animal.setRaca(rs.getString("raca"));
                animal.setTemperamento(rs.getString("temperamento"));
                animal.setHistoricoSaude(rs.getString("historicoSaude"));
                animal.setNome(rs.getString("nome"));
                animal.setDescricao(rs.getString("descricao"));
                animal.setEsEspecial(rs.getBoolean("esEspecial"));
                animal.setIdade(rs.getInt("idade"));
                animal.setSexo(rs.getString("sexo").charAt(0));
                animal.setStatus(rs.getString("status"));

                // Buscar o objeto agregado Protetor
                ProtetorDAO protetordao = new ProtetorDAO();
                Protetor protetor = protetordao.read(rs.getInt("id_protetor"));
                animal.setProtetor(protetor);

                return animal;
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar Animal", e);
        }
        return null;
    }

    // Listar todos os Animals
    public List<Animal> readAll() throws CustomException {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM Animal";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("idAnimal"));
                animal.setEspecie(rs.getString("especie"));
                animal.setRaca(rs.getString("raca"));
                animal.setTemperamento(rs.getString("temperamento"));
                animal.setHistoricoSaude(rs.getString("historicoSaude"));
                animal.setNome(rs.getString("nome"));
                animal.setDescricao(rs.getString("descricao"));
                animal.setEsEspecial(rs.getBoolean("esEspecial"));
                animal.setIdade(rs.getInt("idade"));
                animal.setSexo(rs.getString("sexo").charAt(0));
                animal.setStatus(rs.getString("status"));


                ProtetorDAO protetordao = new ProtetorDAO();
                Protetor protetor = protetordao.read(rs.getInt("id_protetor"));
                animal.setProtetor(protetor);

                animais.add(animal);
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar Animais", e);
        }
        return animais;
    }

    public void update(Animal animal) throws CustomException {
        String sql = "UPDATE Animal SET especie = ?, raca = ?, temperamento = ?, historicoSaude = ?, nome = ?, descricao = ?, esEspecial = ?, idade = ?, sexo = ?, status = ?, id_protetor = ? WHERE idAnimal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getEspecie());
            stmt.setString(2, animal.getRaca());
            stmt.setString(3, animal.getTemperamento());
            stmt.setString(4, animal.getHistoricoSaude());
            stmt.setString(5, animal.getNome());
            stmt.setString(6, animal.getDescricao());
            stmt.setBoolean(7, animal.getEsEspecial());
            stmt.setInt(8, animal.getIdade());
            stmt.setString(9, String.valueOf(animal.getSexo()));
            stmt.setString(10, animal.getStatus());
            stmt.setInt(11, animal.getProtetor().getId());
            stmt.setInt(12, animal.getId());
            stmt.executeUpdate();
            System.out.println("Animal atualizado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar Animal", e);
        }

    }

    // DELETE: Deletar um Animal por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM Animal WHERE idAnimal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Animal deletado com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar Animal", e);
        }
    }
}
