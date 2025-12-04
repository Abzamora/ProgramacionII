package biblioteca;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Libro {
    private String titulo;
    private String isbn;
    private List<Pagina> paginas; // COMPOSICIÓN
    public Libro(String titulo, String isbn, List<Pagina> paginas) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new ArrayList<>(paginas); // Copia defensiva
    }
    public void leer() {
        System.out.println("\n--- Leyendo libro: " +titulo+ " ---");
        for (Pagina p : paginas){
            p.mostrar();
        }
    }
    public String getIsbn() {
        return isbn;
    }
    @Override
    public String toString() {
        return "Libro('" +titulo+ "', ISBN: " +isbn+ ")";
    }

    public List<Pagina> getPaginas() {
        return paginas;
    }

    // la clase interna pagina: composicion
    public static class Pagina {
        private int numero;
        private String contenido;

        public Pagina(int numero, String contenido) {
            this.numero = numero;
            this.contenido = contenido;
        }

        public void mostrar(){
            System.out.println("Página " + numero + ": " + contenido);
        }
    }

//    peristencia
    private String getFileName() {
        return "Archivos/libro_" + isbn + ".json";
    }

    public void guardar() {
        Util.asegurarCarpetaArchivos();
        Gson gson = new Gson();
        try (FileWriter w = new FileWriter(getFileName())) {
            gson.toJson(this, w);
            System.out.println("Libro guardado: " + titulo);
        } catch (IOException e) {
            System.err.println("Error guardando libro: " + e.getMessage());
        }
    }

    public static Libro cargar(String isbn) {
        Gson gson = new Gson();
        String ruta = "Archivos/libro_" + isbn + ".json";
        try (FileReader r = new FileReader(ruta)) {
            return gson.fromJson(r, Libro.class);
        } catch (Exception e) {
            System.out.println("Libro no encontrado: " + isbn);
            return null;
        }
    }
}