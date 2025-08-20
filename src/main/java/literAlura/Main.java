package literAlura;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibroService libroService = new LibroService();
        LibroDAO libroDAO = new LibroDAO();
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Catálogo LiterAlura ---");
            System.out.println("1. Buscar libro por título o autor");
            System.out.println("2. Listar libros guardados");
            System.out.println("3. Buscar libros guardados por autor");
            System.out.println("4. Guardar libro en catálogo");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    System.out.print("Introduce el título o autor a buscar: ");
                    String consulta = scanner.nextLine();
                    List<Libro> resultados = libroService.buscarLibros(consulta);
                    if (resultados.isEmpty()) {
                        System.out.println("No se encontraron libros.");
                    } else {
                        for (int i = 0; i < resultados.size(); i++) {
                            System.out.println("[" + i + "] " + resultados.get(i).getTitulo() + " - " + resultados.get(i).getAutor());
                        }
                    }
                    break;
                case "2":
                    List<Libro> guardados = libroDAO.obtenerLibros();
                    if (guardados.isEmpty()) {
                        System.out.println("No hay libros guardados.");
                    } else {
                        for (Libro libro : guardados) {
                            System.out.println(libro);
                            System.out.println("----------------------");
                        }
                    }
                    break;
                case "3":
                    System.out.print("Introduce el nombre del autor: ");
                    String autor = scanner.nextLine();
                    List<Libro> porAutor = libroDAO.obtenerLibros();
                    boolean encontrado = false;
                    for (Libro libro : porAutor) {
                        if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                            System.out.println(libro);
                            System.out.println("----------------------");
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No se encontraron libros de ese autor.");
                    }
                    break;
                case "4":
                    System.out.print("Introduce el título o autor a buscar para guardar: ");
                    String busqueda = scanner.nextLine();
                    List<Libro> librosParaGuardar = libroService.buscarLibros(busqueda);
                    if (librosParaGuardar.isEmpty()) {
                        System.out.println("No se encontraron libros.");
                    } else {
                        for (int i = 0; i < librosParaGuardar.size(); i++) {
                            System.out.println("[" + i + "] " + librosParaGuardar.get(i).getTitulo() + " - " + librosParaGuardar.get(i).getAutor());
                        }
                        System.out.print("Elige el número del libro a guardar: ");
                        try {
                            int idx = Integer.parseInt(scanner.nextLine());
                            if (idx >= 0 && idx < librosParaGuardar.size()) {
                                libroDAO.guardarLibro(librosParaGuardar.get(idx));
                                System.out.println("Libro guardado exitosamente.");
                            } else {
                                System.out.println("Índice inválido.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida.");
                        }
                    }
                    break;
                case "5":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
        System.out.println("¡Hasta luego!");
    }
}
