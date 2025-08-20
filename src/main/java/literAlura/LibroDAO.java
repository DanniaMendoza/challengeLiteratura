package literAlura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private static final String URL = "jdbc:sqlite:literalura.db";

    public LibroDAO() {
        crearTabla();
    }

    private void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS libros (id TEXT PRIMARY KEY, titulo TEXT, autor TEXT, descripcion TEXT)";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarLibro(Libro libro) {
        String sql = "INSERT OR IGNORE INTO libros (id, titulo, autor, descripcion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, libro.getId());
            pstmt.setString(2, libro.getTitulo());
            pstmt.setString(3, libro.getAutor());
            pstmt.setString(4, libro.getDescripcion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Libro> obtenerLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                libros.add(new Libro(
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("descripcion"),
                    rs.getString("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
