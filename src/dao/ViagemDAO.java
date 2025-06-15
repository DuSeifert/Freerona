package dao;

import model.Viagem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
    public boolean criarViagem(Viagem viagem) {
        String sql = "INSERT INTO viagens (destino, data, descricao, idUsuarioCriador) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, viagem.getDestino());
            stmt.setString(2, viagem.getData());
            stmt.setString(3, viagem.getDescricao());
            stmt.setInt(4, viagem.getIdUsuarioCriador());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar viagem: " + e.getMessage());
            return false;
        }
    }

    public List<Viagem> listarViagens() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM viagens";
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                viagens.add(new Viagem(
                    rs.getInt("id"),
                    rs.getString("destino"),
                    rs.getString("data"),
                    rs.getString("descricao"),
                    rs.getInt("idUsuarioCriador")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar viagens: " + e.getMessage());
        }
        return viagens;
    }

    public boolean editarViagem(Viagem viagem) {
        String sql = "UPDATE viagens SET destino = ?, data = ?, descricao = ? WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, viagem.getDestino());
            stmt.setString(2, viagem.getData());
            stmt.setString(3, viagem.getDescricao());
            stmt.setInt(4, viagem.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar viagem: " + e.getMessage());
            return false;
        }
    }

    public boolean deletarViagem(int idViagem) {
        String sql = "DELETE FROM viagens WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idViagem);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar viagem: " + e.getMessage());
            return false;
        }
    }

    public boolean aceitarViagem(int idUsuario, int idViagem) {
        String sql = "INSERT OR IGNORE INTO participantes (idUsuario, idViagem) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idViagem);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao aceitar viagem: " + e.getMessage());
            return false;
        }
    }

    public List<Viagem> listarViagensPorUsuario(int idUsuario) {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT v.* FROM viagens v JOIN participantes p ON v.id = p.idViagem WHERE p.idUsuario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                viagens.add(new Viagem(
                    rs.getInt("id"),
                    rs.getString("destino"),
                    rs.getString("data"),
                    rs.getString("descricao"),
                    rs.getInt("idUsuarioCriador")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar viagens do usuário: " + e.getMessage());
        }
        return viagens;
    }

    public Viagem buscarPorId(int id) {
        String sql = "SELECT * FROM viagens WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Viagem(
                    rs.getInt("id"),
                    rs.getString("destino"),
                    rs.getString("data"),
                    rs.getString("descricao"),
                    rs.getInt("idUsuarioCriador")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar viagem: " + e.getMessage());
        }
        return null;
    }
} 