package biblioteca;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE BIBLIOTECA UMSA ===\n");
        //se crea biblioteca y su horario (COMPOSICION)
        Biblioteca biblioteca = new Biblioteca(
                "Biblioteca Central UMSA",
                "Lunes a Viernes",
                "08:00",
                "18:00"
        );
        Autor autor1 = new Autor("Gabriel García Márquez", "Colombiano");
        Autor autor2 = new Autor("Mario Vargas Llosa", "Peruano");
        Libro.Pagina p1 = new Libro.Pagina(1, "Muchos años después...");
        Libro.Pagina p2 = new Libro.Pagina(2, "El mundo era tan reciente...");
        Libro.Pagina p3 = new Libro.Pagina(1, "El cadete Alberto Fernández...");
        Libro.Pagina p4 = new Libro.Pagina(2, "El Jaguar no olvidaba...");
        Libro libro1 = new Libro("Cien Años de Soleedad", "978-0307474728", Arrays.asList(p1, p2));
        Libro libro2 = new Libro("La Ciudad y los Perros", "978-8420472133", Arrays.asList(p3, p4));
        biblioteca.agregarAutor(autor1);//(AGREGACIÓN)
        biblioteca.agregarAutor(autor2);
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        Estudiante est1 = new Estudiante("2025001", "Juan Pérez");
        Estudiante est2 = new Estudiante("2025002", "María López");
        Prestamo prestamo1 = biblioteca.prestarLibro(est1, libro1); //(ASOCIACIÓN)
        Prestamo prestamo2 = biblioteca.prestarLibro(est2, libro2);
        biblioteca.mostrarEstado();
        libro1.leer();  //si cumple COMPOSICIoN: leer páginas
        biblioteca.cerrarBiblioteca(); //al cerrar la biblioteca (los préstamos dejan de existir)
        System.out.println("\nPrestamos activos despues de cerrar: " + biblioteca.getCantidadPrestamos());
    }
}