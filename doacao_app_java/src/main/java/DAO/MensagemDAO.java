package DAO;

import schemas.Mensagem;
import repository.MYSQLConnection;
import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {
    private Connection connection;

    public MensagemDAO() throws CustomException {
        try {
            this.connection = MYSQLConnection.getConnection();
        } catch (Exception e) {
            throw new CustomException("Erro ao conectar ao banco de dados", e);
        }
    }

    // CREATE: Inserir uma nova Mensagem
    public void create(Mensagem mensagem) throws CustomException {
        String sql = "INSERT INTO Mensagem (dataMensagem, conteudo, idRemetente, tipoRemetente, idDestinatario, tipoDestinatario, id_processo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, new Timestamp(mensagem.getDataMensagem().getTime()));
            stmt.setString(2, mensagem.getConteudo());
            stmt.setInt(3, mensagem.getIdRemetente());
            stmt.setString(4, mensagem.getTipoRemetente());
            stmt.setInt(5, mensagem.getIdDestinario());
            stmt.setString(6, mensagem.getTipoDestinario());
            stmt.setInt(7, mensagem.getId_processo());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                mensagem.setIdMensagem(rs.getInt(1));
            }
            System.out.println("Mensagem criada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao criar Mensagem", e);
        }
    }

    // READ: Buscar uma Mensagem por ID
    public Mensagem read(int id) throws CustomException {
        String sql = "SELECT * FROM Mensagem WHERE idMensagem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Mensagem mensagem = new Mensagem(
                    rs.getDate("dataMensagem"),
                    rs.getString("conteudo"),
                    rs.getInt("idRemetente"),
                    rs.getInt("idDestinatario"),
                    rs.getString("tipoRemetente"),
                    rs.getString("tipoDestinatario"),
                    rs.getInt("id_processo")
                );
                mensagem.setIdMensagem(rs.getInt("idMensagem"));
                return mensagem;
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao buscar Mensagem", e);
        }
        return null;
    }

    // READ ALL: Listar todas as Mensagem
    public List<Mensagem> readAll() throws CustomException {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM Mensagem";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mensagem mensagem = new Mensagem(
                    rs.getDate("dataMensagem"),
                    rs.getString("conteudo"),
                    rs.getInt("idRemetente"),
                    rs.getInt("idDestinatario"),
                    rs.getString("tipoRemetente"),
                    rs.getString("tipoDestinatario"),
                    rs.getInt("id_processo")
                );
                mensagem.setIdMensagem(rs.getInt("idMensagem"));
                mensagens.add(mensagem);
            }
        } catch (SQLException e) {
            throw new CustomException("Erro ao listar Mensagem", e);
        }
        return mensagens;
    }

    // UPDATE: Atualizar uma Mensagem
    public void update(Mensagem mensagem) throws CustomException {
        String sql = "UPDATE Mensagem SET dataMensagem = ?, conteudo = ?, idRemetente = ?, tipoRemetente = ?, idDestinatario = ?, tipoDestinatario = ?, id_processo = ? WHERE idMensagem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(mensagem.getDataMensagem().getTime()));
            stmt.setString(2, mensagem.getConteudo());
            stmt.setInt(3, mensagem.getIdRemetente());
            stmt.setString(4, mensagem.getTipoRemetente());
            stmt.setInt(5, mensagem.getIdDestinario());
            stmt.setString(6, mensagem.getTipoDestinario());
            stmt.setInt(7, mensagem.getId_processo());
            stmt.setInt(8, mensagem.getIdMensagem());
            stmt.executeUpdate();
            System.out.println("Mensagem atualizada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao atualizar Mensagem", e);
        }
    }

    // DELETE: Deletar uma Mensagem por ID
    public void delete(int id) throws CustomException {
        String sql = "DELETE FROM Mensagem WHERE idMensagem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Mensagem deletada com sucesso!");
        } catch (SQLException e) {
            throw new CustomException("Erro ao deletar Mensagem", e);
        }
    }
}
