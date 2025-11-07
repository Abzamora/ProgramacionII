package biblioteca;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prestamo {
    private Estudiante estudiante;
    private Libro libro;
    private String fechaPrestamo;
    private String fechaDevolucion;
    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.fechaDevolucion = null;
    }
    public void mostrarInfo() {
        System.out.println("Préstamo: " + estudiante + " -> " + libro);
        System.out.println("   Fecha préstamo: " + fechaPrestamo);
        System.out.println("   Devuelto: " + (fechaDevolucion == null ? "No" : "Sí"));
    }
    @Override
    public String toString() {
        return "Préstamo(" + estudiante + ", " + libro+ ")";
    }
}