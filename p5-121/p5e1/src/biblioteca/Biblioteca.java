package biblioteca;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private Horario horario; // COMPOSICIÓN
    private List<Libro> libros;     // AGREGACIÓN
    private List<Autor> autores;    // AGREGACIÓN
    private List<Prestamo> prestamos;
    public Biblioteca(String nombre, String diasApertura, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.horario = new Horario(diasApertura, horaApertura, horaCierre); // COMPOSICIÓN
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    public void agregarLibro(Libro libro){
        libros.add(libro);
        System.out.println("Libro agregado: " +libro);
    }
    public void agregarAutor(Autor autor){
        autores.add(autor);
        System.out.println("Autor registrado: " +autor);
    }
    public Prestamo prestarLibro(Estudiante estudiante, Libro libro){
        if (libros.contains(libro)){
            Prestamo prestamo = new Prestamo(estudiante, libro);
            prestamos.add(prestamo);
            System.out.println("Préstamo realizado: " +prestamo);
            return prestamo;
        } else {
            System.out.println("Error: El libro no está en la biblioteca.");
            return null;
        }
    }
    public void mostrarEstado() {
        System.out.println("\n=== ESTADO DE LA BIBLIOTECA: " + nombre + " ===");
        horario.mostrarHorario();
        System.out.println("Libros disponibles: " + libros.size());
        for (Libro l : libros) System.out.println("  - " + l);
        System.out.println("Autores registrados: " + autores.size());
        for (Autor a : autores) System.out.println("  - " + a);
        System.out.println("Préstamos activos: " + prestamos.size());
        for (Prestamo p : prestamos) p.mostrarInfo();
    }
    public void cerrarBiblioteca(){
        System.out.println("\nCerrando la biblioteca " + nombre + "...");
        System.out.println("Todos los préstamos han sido anulados.");
        prestamos.clear(); // Los prestamos tambien dejan de existir
        System.out.println("Biblioteca cerrada.");
    }
    public int getCantidadPrestamos(){
        return prestamos.size();
    }
    // la clase interna horario: composición
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
}