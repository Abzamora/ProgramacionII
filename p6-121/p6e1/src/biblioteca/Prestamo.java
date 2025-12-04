package biblioteca;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    //persistencia
    private String getFileName() {
        String codigo = estudiante != null ? estudiante.toString().hashCode() + "" : "null";
        String isbn = libro != null ? libro.toString().hashCode() + "" : "null";
        return "Archivos/prestamo_" + codigo + "_" + isbn + "_" + System.currentTimeMillis() + ".json";
    }

    public void guardar() {
        Util.asegurarCarpetaArchivos();
        Gson gson = new Gson();
        try (FileWriter w = new FileWriter(getFileName())) {
            gson.toJson(this, w);
            System.out.println("Préstamo guardado individualmente");
        } catch (IOException e) {
            System.err.println("Error guardando préstamo: " + e.getMessage());
        }
    }
    public static Prestamo cargar(String nombreArchivo) {
        Gson gson = new Gson();
        String ruta = "Archivos/" + nombreArchivo;
        try (FileReader r = new FileReader(ruta)) {
            Prestamo p = gson.fromJson(r, Prestamo.class);
            System.out.println("Préstamo cargado: " + p);
            return p;
        } catch (Exception e) {
            System.out.println("No se pudo cargar préstamo: " + nombreArchivo);
            return null;
        }
    }
}