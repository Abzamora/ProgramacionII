package biblioteca;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Biblioteca {
    private String nombre;
    private Horario horario;
    private List<Libro> libros;
    private List<Autor> autores;
    private List<Prestamo> prestamos;
    private List<Estudiante> estudiantes;

    public Biblioteca(String nombre, String diasApertura, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.horario = new Horario(diasApertura, horaApertura, horaCierre);
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado: " + libro);
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
        System.out.println("Autor registrado: " + autor);
    }

    public void agregarEstudiante(Estudiante estudiante) {
        asegurarListaEstudiantes();
        estudiantes.add(estudiante);
        System.out.println("Estudiante registrado: " + estudiante);
    }

    public Prestamo prestarLibro(Estudiante estudiante, Libro libro) {
        if (libros.contains(libro)) {
            Prestamo prestamo = new Prestamo(estudiante, libro);
            prestamos.add(prestamo);
            System.out.println("Préstamo realizado: " + prestamo);
            return prestamo;
        } else {
            System.out.println("Error: El libro no está en la biblioteca.");
            return null;
        }
    }

    public void mostrarEstado() {
        asegurarListaEstudiantes();
        System.out.println("\n=== ESTADO DE LA BIBLIOTECA: " + nombre + " ===");
        horario.mostrarHorario();
        System.out.println("Libros disponibles: " + libros.size());
        for (Libro l : libros) System.out.println("  - " + l);
        System.out.println("Autores registrados: " + autores.size());
        for (Autor a : autores) System.out.println("  - " + a);
        System.out.println("Estudiantes registrados: " + estudiantes.size());
        for (Estudiante e : estudiantes) System.out.println("  - " + e);
        System.out.println("Préstamos activos: " + prestamos.size());
        for (Prestamo p : prestamos) p.mostrarInfo();
    }

    public void cerrarBiblioteca() {
        System.out.println("\nCerrando la biblioteca " + nombre + "...");
        System.out.println("Los préstamos permanecen vigentes.");
        System.out.println("Biblioteca cerrada.");
    }

    public int getCantidadPrestamos() {
        return prestamos.size();
    }

    public int getCantidadLibros() {
        return libros.size();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public Libro buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(l -> isbn.equals(l.getIsbn()))
                .findFirst()
                .orElse(null);
    }

    public boolean isLibrosEmpty() {
        return libros.isEmpty();
    }

    // Clase interna Horario: composición
    static class Horario {
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        public Horario(String diasApertura, String horaApertura, String horaCierre) {
            this.diasApertura = diasApertura;
            this.horaApertura = horaApertura;
            this.horaCierre = horaCierre;
        }

        public void mostrarHorario() {
            System.out.printf("Horario: %s, %s - %s%n", diasApertura, horaApertura, horaCierre);
        }
    }

    //GESTIONAR PRÉSTAMOS
    //devolver un libro específico
    public void devolverLibro(Prestamo prestamo) {
        if (prestamos.remove(prestamo)) {
            System.out.println("Libro devuelto: " + prestamo);
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    //ver préstamos activos
    public void mostrarPrestamosActivos() {
        System.out.println("\n=== PRÉSTAMOS ACTIVOS ===");
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            for (Prestamo p : prestamos) {
                p.mostrarInfo();
            }
        }
    }

    //limpiar préstamos
    public void limpiarPrestamos() {
        prestamos.clear();
        System.out.println("Todos los préstamos han sido eliminados.");
    }

    // Asegura que la lista de estudiantes nunca sea null (por ejemplo, al cargar desde JSON antiguo)
    private void asegurarListaEstudiantes() {
        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
    }

    // PERSISTENCIA
    public void guardarTodo() {
        Util.asegurarCarpetaArchivos();
        Gson gson = new Gson();
        String ruta = "Archivos/biblioteca_estado.json";
        try (FileWriter w = new FileWriter(ruta)) {
            gson.toJson(this, w);
            System.out.println("Estado completo de la biblioteca guardado en " + ruta);
        } catch (IOException e) {
            System.err.println("Error guardando biblioteca: " + e.getMessage());
        }
    }

    public static Biblioteca cargarTodo() {
        Gson gson = new Gson();
        String ruta = "Archivos/biblioteca_estado.json";
        try (FileReader r = new FileReader(ruta)) {
            Biblioteca bib = gson.fromJson(r, Biblioteca.class);
            System.out.println("Estado anterior cargado exitosamente.");
            return bib;
        } catch (Exception e) {
            System.out.println("No hay estado previo. Creando biblioteca nueva.");
            return new Biblioteca("Biblioteca Central UMSA", "Lunes a Viernes", "08:00", "18:00");
        }
    }
}