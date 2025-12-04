package ventana;

import biblioteca.Biblioteca;
import biblioteca.Libro;
import biblioteca.Autor;
import biblioteca.Estudiante;
import biblioteca.Prestamo;
import biblioteca.Libro.Pagina;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal {

    private JFrame frame;
    private Biblioteca biblioteca;
    private JTextArea consoleArea;
    private JLabel statusLabel;

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si Nimbus no está disponible, se usa el look & feel por defecto
        }

        EventQueue.invokeLater(() -> {
            VentanaPrincipal window = new VentanaPrincipal();
            window.frame.setVisible(true);
        });
    }

    public VentanaPrincipal() {
        biblioteca = Biblioteca.cargarTodo();
        initialize();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                biblioteca.guardarTodo();
            }
        });
    }

    private void initialize() {
        frame = new JFrame("Sistema de Biblioteca UMSA");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("Archivo");
        menuBar.add(mnFile);

        JMenuItem mntmGuardar = new JMenuItem("Guardar");
        mntmGuardar.addActionListener(e -> biblioteca.guardarTodo());
        mnFile.add(mntmGuardar);

        JMenuItem mntmVerJson = new JMenuItem("Ver JSON");
        mntmVerJson.addActionListener(e -> verJson());
        mnFile.add(mntmVerJson);

        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(e -> {
            biblioteca.guardarTodo();
            System.exit(0);
        });
        mnFile.add(mntmSalir);

        JMenu mnBiblioteca = new JMenu("Biblioteca");
        menuBar.add(mnBiblioteca);

        JMenuItem mntmMostrarEstado = new JMenuItem("Mostrar Estado");
        mntmMostrarEstado.addActionListener(e -> biblioteca.mostrarEstado());
        mnBiblioteca.add(mntmMostrarEstado);

        JMenuItem mntmCerrar = new JMenuItem("Cerrar Biblioteca");
        mntmCerrar.addActionListener(e -> biblioteca.cerrarBiblioteca());
        mnBiblioteca.add(mntmCerrar);

        JMenu mnLibros = new JMenu("Libros");
        menuBar.add(mnLibros);

        JMenuItem mntmAgregarLibro = new JMenuItem("Agregar Libro");
        mntmAgregarLibro.addActionListener(e -> agregarLibro());
        mnLibros.add(mntmAgregarLibro);

        JMenuItem mntmAgregarPagina = new JMenuItem("Agregar Página a Libro");
        mntmAgregarPagina.addActionListener(e -> agregarPaginaALibro());
        mnLibros.add(mntmAgregarPagina);

        JMenuItem mntmLeerLibro = new JMenuItem("Leer Libro");
        mntmLeerLibro.addActionListener(e -> leerLibro());
        mnLibros.add(mntmLeerLibro);

        JMenu mnAutores = new JMenu("Autores");
        menuBar.add(mnAutores);

        JMenuItem mntmAgregarAutor = new JMenuItem("Agregar Autor");
        mntmAgregarAutor.addActionListener(e -> agregarAutor());
        mnAutores.add(mntmAgregarAutor);

        JMenu mnEstudiantes = new JMenu("Estudiantes");
        menuBar.add(mnEstudiantes);

        JMenuItem mntmAgregarEstudiante = new JMenuItem("Agregar Estudiante");
        mntmAgregarEstudiante.addActionListener(e -> agregarEstudiante());
        mnEstudiantes.add(mntmAgregarEstudiante);

        JMenu mnPrestamos = new JMenu("Préstamos");
        menuBar.add(mnPrestamos);

        JMenuItem mntmPrestarLibro = new JMenuItem("Prestar Libro");
        mntmPrestarLibro.addActionListener(e -> prestarLibro());
        mnPrestamos.add(mntmPrestarLibro);

        // Área de consola para mostrar la "terminal" dentro de la ventana
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setLineWrap(true);
        consoleArea.setWrapStyleWord(true);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        consoleArea.setBackground(new Color(20, 20, 20));
        consoleArea.setForeground(new Color(230, 230, 230));
        consoleArea.setBorder(BorderFactory.createTitledBorder("Salida de la consola"));
        JScrollPane consoleScrollPane = new JScrollPane(consoleArea);
        frame.getContentPane().add(consoleScrollPane, BorderLayout.CENTER);

        redirectSystemStreams();

        statusLabel = new JLabel("  Listo - Libros: " + biblioteca.getCantidadLibros() +
                " | Préstamos: " + biblioteca.getCantidadPrestamos());
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(240, 240, 240));
        frame.getContentPane().add(statusLabel, BorderLayout.SOUTH);
    }

    private void agregarLibro() {
        String titulo = JOptionPane.showInputDialog(frame, "Título del libro:");
        if (titulo == null) return;
        String isbn = JOptionPane.showInputDialog(frame, "ISBN del libro:");
        if (isbn == null) return;
        String numPagStr = JOptionPane.showInputDialog(frame, "Número de páginas:");
        if (numPagStr == null) return;
        int numPag = Integer.parseInt(numPagStr);
        List<Pagina> paginas = new ArrayList<>();
        for (int i = 1; i <= numPag; i++) {
            String contenido = JOptionPane.showInputDialog(frame, "Contenido de la página " + i + ":");
            if (contenido == null) return;
            paginas.add(new Pagina(i, contenido));
        }
        Libro libro = new Libro(titulo, isbn, paginas);
        biblioteca.agregarLibro(libro);
        biblioteca.guardarTodo();
    }

    private void agregarPaginaALibro() {
        if (biblioteca.isLibrosEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay libros disponibles.");
            return;
        }
        String[] isbns = biblioteca.getLibros().stream().map(Libro::getIsbn).toArray(String[]::new);
        String selectedIsbn = (String) JOptionPane.showInputDialog(frame, "Selecciona el ISBN del libro:",
                "Agregar Página", JOptionPane.QUESTION_MESSAGE, null, isbns, isbns[0]);
        if (selectedIsbn == null) return;
        Libro libro = biblioteca.buscarPorIsbn(selectedIsbn);
        if (libro != null) {
            int nextNum = libro.getPaginas().size() + 1;
            String contenido = JOptionPane.showInputDialog(frame, "Contenido de la nueva página " + nextNum + ":");
            if (contenido == null) return;
            libro.getPaginas().add(new Pagina(nextNum, contenido));
            biblioteca.guardarTodo();
        }
    }

    private void leerLibro() {
        if (biblioteca.isLibrosEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay libros disponibles.");
            return;
        }
        String[] isbns = biblioteca.getLibros().stream().map(Libro::getIsbn).toArray(String[]::new);
        String selectedIsbn = (String) JOptionPane.showInputDialog(frame, "Selecciona el ISBN del libro a leer:",
                "Leer Libro", JOptionPane.QUESTION_MESSAGE, null, isbns, isbns[0]);
        if (selectedIsbn == null) return;
        Libro libro = biblioteca.buscarPorIsbn(selectedIsbn);
        if (libro != null) {
            libro.leer();
        }
    }

    private void agregarAutor() {
        String nombre = JOptionPane.showInputDialog(frame, "Nombre del autor:");
        if (nombre == null) return;
        String nacionalidad = JOptionPane.showInputDialog(frame, "Nacionalidad del autor:");
        if (nacionalidad == null) return;
        Autor autor = new Autor(nombre, nacionalidad);
        biblioteca.agregarAutor(autor);
        biblioteca.guardarTodo();
    }

    private void agregarEstudiante() {
        String codigo = JOptionPane.showInputDialog(frame, "Código del estudiante:");
        if (codigo == null) return;

        String nombre = JOptionPane.showInputDialog(frame, "Nombre del estudiante:");
        if (nombre == null) return;

        Estudiante estudiante = new Estudiante(codigo, nombre);
        biblioteca.agregarEstudiante(estudiante);
        biblioteca.guardarTodo(); // Guardar cambios en el estado de la biblioteca
    }

    private void prestarLibro() {
        String codigo = JOptionPane.showInputDialog(frame, "Código del estudiante:");
        if (codigo == null) return;
        Estudiante estudiante = Estudiante.cargar(codigo);
        if (estudiante == null) {
            String nombre = JOptionPane.showInputDialog(frame, "Nombre del estudiante:");
            if (nombre == null) return;
            estudiante = new Estudiante(codigo, nombre);
            estudiante.guardar();
        }
        if (biblioteca.isLibrosEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay libros disponibles.");
            return;
        }
        String[] isbns = biblioteca.getLibros().stream().map(Libro::getIsbn).toArray(String[]::new);
        String selectedIsbn = (String) JOptionPane.showInputDialog(frame, "Selecciona el ISBN del libro a prestar:",
                "Prestar Libro", JOptionPane.QUESTION_MESSAGE, null, isbns, isbns[0]);
        if (selectedIsbn == null) return;
        Libro libro = biblioteca.buscarPorIsbn(selectedIsbn);
        if (libro != null) {
            Prestamo prestamo = biblioteca.prestarLibro(estudiante, libro);
            if (prestamo != null) {
                biblioteca.guardarTodo();
            }
        }
    }

    private void verJson() {
        try {
            String content = Files.readString(Paths.get("Archivos/biblioteca_estado.json"));
            JTextArea textArea = new JTextArea(content);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));
            JOptionPane.showMessageDialog(frame, scrollPane, "Contenido del Archivo JSON", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al leer el archivo: " + ex.getMessage());
        }
    }

    // Redirigir System.out y System.err a la consola dentro de la ventana
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                appendToConsole(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) {
                appendToConsole(new String(b, off, len));
            }
        };

        PrintStream ps = new PrintStream(out, true);
        System.setOut(ps);
        System.setErr(ps);
    }

    private void appendToConsole(String text) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append(text);
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }
}