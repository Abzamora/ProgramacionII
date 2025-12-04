package biblioteca;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE BIBLIOTECA UMSA ===\n");
        Biblioteca biblioteca = Biblioteca.cargarTodo();
        Libro libro1 = null;
        Libro libro2 = null;

        /*if (biblioteca.getCantidadPrestamos() == 0 && biblioteca.getCantidadLibros() == 0) {
            System.out.println("Primera ejecución → creando tus datos originales...\n");

            Autor autor1 = new Autor("Gabriel García Márquez", "Colombiano");
            Autor autor2 = new Autor("Mario Vargas Llosa", "Peruano");

            Libro.Pagina p1 = new Libro.Pagina(1, "Muchos años después una vaca aparecio...");
            Libro.Pagina p2 = new Libro.Pagina(2, "El mundo era tan reciente...");
            Libro.Pagina p3 = new Libro.Pagina(1, "El cadete Alberto Fernández...");
            Libro.Pagina p4 = new Libro.Pagina(2, "El Jaguar no olvidaba que era un gato...");

            libro1 = new Libro("Cien Años de Soledad", "978-0307474728", Arrays.asList(p1, p2));
            libro2 = new Libro("La Ciudad y los Perros", "978-8420472133", Arrays.asList(p3, p4));

            biblioteca.agregarAutor(autor1);
            biblioteca.agregarAutor(autor2);
            biblioteca.agregarLibro(libro1);
            biblioteca.agregarLibro(libro2);

            Estudiante est1 = new Estudiante("2025001", "Juan Pérez");
            Estudiante est2 = new Estudiante("2025002", "María López");

            biblioteca.prestarLibro(est1, libro1);
            biblioteca.prestarLibro(est2, libro2);

            biblioteca.guardarTodo();
            System.out.println("Datos iniciales guardados en carpeta 'Archivos'\n");
        } else {*/
            libro1 = biblioteca.buscarPorIsbn("978-0307474728");
            libro2 = biblioteca.buscarPorIsbn("978-8420472133");
        /*}*/

        biblioteca.mostrarEstado();
        biblioteca.mostrarPrestamosActivos();
        if (libro1 != null) {
            libro1.leer();
        } else {
            System.out.println("Error: Libro no encontrado");
        }
        biblioteca.cerrarBiblioteca();//cierra y no borra prestamos
        // biblioteca.limpiarPrestamos();//borra prestamos activos
        biblioteca.guardarTodo();

        System.out.println("\nCantidad de Prestamos al final: " + biblioteca.getCantidadPrestamos());
        System.out.println("\n¡Todo guardado! vuelve a ejecutar → los datos siguen ahí");
    }
}