package biblioteca;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public String toString() {
        return "Libro('" +titulo+ "', ISBN: " +isbn+ ")";
    }

    // la clase interna pagina: composicion
    static class Pagina {
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
}