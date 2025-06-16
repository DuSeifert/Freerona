package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:freerona.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver SQLite: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializar() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Tabela de usuários
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL)");
            // Tabela de viagens
            stmt.execute("CREATE TABLE IF NOT EXISTS viagens (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "destino TEXT NOT NULL," +
                    "data TEXT NOT NULL," +
                    "descricao TEXT," +
                    "idUsuarioCriador INTEGER NOT NULL," +
                    "FOREIGN KEY(idUsuarioCriador) REFERENCES usuarios(id))");
            // Tabela de participantes
            stmt.execute("CREATE TABLE IF NOT EXISTS participantes (" +
                    "idUsuario INTEGER NOT NULL," +
                    "idViagem INTEGER NOT NULL," +
                    "PRIMARY KEY(idUsuario, idViagem)," +
                    "FOREIGN KEY(idUsuario) REFERENCES usuarios(id)," +
                    "FOREIGN KEY(idViagem) REFERENCES viagens(id))");
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }
} 