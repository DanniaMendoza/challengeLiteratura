package literAlura;

public class Libro {
    private String titulo;
    private String autor;
    private String descripcion;
    private String id;

    public Libro(String titulo, String autor, String descripcion, String id) {
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getDescripcion() { return descripcion; }
    public String getId() { return id; }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nDescripción: " + descripcion + "\nID: " + id;
    }
}
