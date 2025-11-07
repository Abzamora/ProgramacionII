package biblioteca;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaGUI extends JFrame {
    private final Biblioteca biblioteca;

    private final DefaultListModel<Autor> autoresModel = new DefaultListModel<>();
    private final DefaultListModel<Libro> librosModel = new DefaultListModel<>();
    private final DefaultListModel<Prestamo> prestamosModel = new DefaultListModel<>();

    // Mantener un reflejo de páginas de cada libro para poder "leer" en la GUI
    private final Map<Libro, List<String>> libroPaginasTexto = new IdentityHashMap<>();

    // Datos de horario mostrables en la pestaña Estado
    private final String nombreBiblioteca;
    private final String diasApertura;
    private final String horaApertura;
    private final String horaCierre;

    private final JTextArea estadoTextArea = new JTextArea(12, 60);

    public BibliotecaGUI() {
        super("Sistema de Biblioteca - GUI");
        this.nombreBiblioteca = "Biblioteca Central UMSA";
        this.diasApertura = "Lunes a Viernes";
        this.horaApertura = "08:00";
        this.horaCierre = "18:00";
        this.biblioteca = new Biblioteca(nombreBiblioteca, diasApertura, horaApertura, horaCierre);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Autores", buildAutoresPanel());
        tabs.addTab("Libros", buildLibrosPanel());
        tabs.addTab("Préstamos", buildPrestamosPanel());
        tabs.addTab("Estado", buildEstadoPanel());

        setContentPane(tabs);
        setLocationRelativeTo(null);
        refreshEstado();
    }

    private JPanel buildAutoresPanel() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nombreField = new JTextField(20);
        JTextField nacionalidadField = new JTextField(20);
        JButton addBtn = new JButton("Agregar Autor");

        gc.gridx = 0; gc.gridy = 0; form.add(new JLabel("Nombre:"), gc);
        gc.gridx = 1; gc.gridy = 0; form.add(nombreField, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(new JLabel("Nacionalidad:"), gc);
        gc.gridx = 1; gc.gridy = 1; form.add(nacionalidadField, gc);
        gc.gridx = 1; gc.gridy = 2; form.add(addBtn, gc);

        JList<Autor> lista = new JList<>(autoresModel);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);

        addBtn.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String nac = nacionalidadField.getText().trim();
            if (nombre.isEmpty() || nac.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete nombre y nacionalidad.");
                return;
            }
            Autor autor = new Autor(nombre, nac);
            biblioteca.agregarAutor(autor);
            autoresModel.addElement(autor);
            nombreField.setText("");
            nacionalidadField.setText("");
            refreshEstado();
        });

        root.add(form, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        return root;
    }

    private JPanel buildLibrosPanel() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField tituloField = new JTextField(20);
        JTextField isbnField = new JTextField(20);
        JTextArea paginasArea = new JTextArea(5, 20);
        paginasArea.setLineWrap(true);
        paginasArea.setWrapStyleWord(true);
        paginasArea.setToolTipText("Una línea por página. Formato opcional: numero: contenido");

        JButton addBtn = new JButton("Agregar Libro");
        JButton leerBtn = new JButton("Leer Libro Seleccionado");

        gc.gridx = 0; gc.gridy = 0; form.add(new JLabel("Título:"), gc);
        gc.gridx = 1; gc.gridy = 0; form.add(tituloField, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(new JLabel("ISBN:"), gc);
        gc.gridx = 1; gc.gridy = 1; form.add(isbnField, gc);
        gc.gridx = 0; gc.gridy = 2; form.add(new JLabel("Páginas (una por línea):"), gc);
        gc.gridx = 1; gc.gridy = 2; form.add(new JScrollPane(paginasArea), gc);
        gc.gridx = 1; gc.gridy = 3; form.add(addBtn, gc);
        gc.gridx = 1; gc.gridy = 4; form.add(leerBtn, gc);

        JList<Libro> lista = new JList<>(librosModel);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);

        addBtn.addActionListener(e -> {
            String titulo = tituloField.getText().trim();
            String isbn = isbnField.getText().trim();
            if (titulo.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete título e ISBN.");
                return;
            }

            List<Libro.Pagina> paginas = new ArrayList<>();
            List<String> paginasTexto = new ArrayList<>();
            String[] lineas = paginasArea.getText().split("\n");
            if (lineas.length == 1 && lineas[0].trim().isEmpty()) {
                paginas = Arrays.asList(new Libro.Pagina(1, "Contenido no especificado"));
                paginasTexto = Arrays.asList("Página 1: Contenido no especificado");
            } else {
                int autoNumero = 1;
                for (String linea : lineas) {
                    String trimmed = linea.trim();
                    if (trimmed.isEmpty()) continue;
                    int colon = trimmed.indexOf(":");
                    if (colon > 0) {
                        try {
                            int numero = Integer.parseInt(trimmed.substring(0, colon).trim());
                            String contenido = trimmed.substring(colon + 1).trim();
                            paginas.add(new Libro.Pagina(numero, contenido));
                            paginasTexto.add("Página " + numero + ": " + contenido);
                        } catch (NumberFormatException ex) {
                            paginas.add(new Libro.Pagina(autoNumero, trimmed));
                            paginasTexto.add("Página " + autoNumero + ": " + trimmed);
                            autoNumero++;
                        }
                    } else {
                        paginas.add(new Libro.Pagina(autoNumero, trimmed));
                        paginasTexto.add("Página " + autoNumero + ": " + trimmed);
                        autoNumero++;
                    }
                }
            }

            Libro libro = new Libro(titulo, isbn, paginas);
            biblioteca.agregarLibro(libro);
            librosModel.addElement(libro);
            libroPaginasTexto.put(libro, paginasTexto);
            tituloField.setText("");
            isbnField.setText("");
            paginasArea.setText("");
            refreshEstado();
        });

        leerBtn.addActionListener(e -> {
            Libro seleccionado = lista.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un libro de la lista.");
                return;
            }
            List<String> paginasTxt = libroPaginasTexto.get(seleccionado);
            if (paginasTxt == null || paginasTxt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El libro no tiene páginas registradas.");
                return;
            }
            JTextArea lectura = new JTextArea();
            lectura.setEditable(false);
            lectura.setLineWrap(true);
            lectura.setWrapStyleWord(true);
            StringBuilder sb = new StringBuilder();
            sb.append("--- Leyendo libro: ").append(seleccionado.toString()).append(" ---\n");
            for (String linea : paginasTxt) sb.append(linea).append('\n');
            lectura.setText(sb.toString());
            lectura.setCaretPosition(0);
            JScrollPane sp = new JScrollPane(lectura);
            sp.setPreferredSize(new Dimension(600, 350));
            JOptionPane.showMessageDialog(this, sp, "Lectura de Libro", JOptionPane.PLAIN_MESSAGE);
        });

        root.add(form, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        return root;
    }

    private JPanel buildPrestamosPanel() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<Libro> librosCombo = new JComboBox<>();
        JButton refreshLibrosBtn = new JButton("↻");
        JTextField codEstField = new JTextField(15);
        JTextField nomEstField = new JTextField(15);
        JButton prestarBtn = new JButton("Prestar Libro");

        gc.gridx = 0; gc.gridy = 0; form.add(new JLabel("Libro:"), gc);
        gc.gridx = 1; gc.gridy = 0; form.add(librosCombo, gc);
        gc.gridx = 2; gc.gridy = 0; form.add(refreshLibrosBtn, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(new JLabel("Código Estudiante:"), gc);
        gc.gridx = 1; gc.gridy = 1; form.add(codEstField, gc);
        gc.gridx = 0; gc.gridy = 2; form.add(new JLabel("Nombre Estudiante:"), gc);
        gc.gridx = 1; gc.gridy = 2; form.add(nomEstField, gc);
        gc.gridx = 1; gc.gridy = 3; form.add(prestarBtn, gc);

        JList<Prestamo> lista = new JList<>(prestamosModel);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);

        refreshLibrosBtn.addActionListener(e -> {
            librosCombo.removeAllItems();
            for (int i = 0; i < librosModel.size(); i++) {
                librosCombo.addItem(librosModel.get(i));
            }
        });

        prestarBtn.addActionListener(e -> {
            Libro libroSel = (Libro) librosCombo.getSelectedItem();
            String cod = codEstField.getText().trim();
            String nom = nomEstField.getText().trim();
            if (libroSel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un libro.");
                return;
            }
            if (cod.isEmpty() || nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete código y nombre del estudiante.");
                return;
            }
            Estudiante est = new Estudiante(cod, nom);
            Prestamo pr = biblioteca.prestarLibro(est, libroSel);
            if (pr != null) {
                prestamosModel.addElement(pr);
                codEstField.setText("");
                nomEstField.setText("");
                refreshEstado();
            }
        });

        root.add(form, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        return root;
    }

    private JPanel buildEstadoPanel() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        estadoTextArea.setEditable(false);
        estadoTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JButton cerrarBtn = new JButton("Cerrar Biblioteca");
        cerrarBtn.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(this, "¿Cerrar biblioteca? Se limpiarán préstamos.",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                biblioteca.cerrarBiblioteca();
                prestamosModel.clear();
                refreshEstado();
            }
        });

        root.add(new JScrollPane(estadoTextArea), BorderLayout.CENTER);
        root.add(cerrarBtn, BorderLayout.SOUTH);
        return root;
    }

    private void refreshEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTADO DE LA BIBLIOTECA: ").append(nombreBiblioteca).append(" ===\n");
        sb.append(String.format("Horario: %s, %s - %s\n\n", diasApertura, horaApertura, horaCierre));

        sb.append("Libros registrados (" ).append(librosModel.size()).append("):\n");
        for (int i = 0; i < librosModel.size(); i++) {
            Libro l = librosModel.get(i);
            List<String> pags = libroPaginasTexto.get(l);
            int numPags = pags == null ? 0 : pags.size();
            sb.append("  - ").append(l).append(" | Páginas: ").append(numPags).append('\n');
        }
        sb.append('\n');

        sb.append("Autores registrados (" ).append(autoresModel.size()).append("):\n");
        for (int i = 0; i < autoresModel.size(); i++) {
            sb.append("  - ").append(autoresModel.get(i)).append('\n');
        }
        sb.append('\n');

        sb.append("Préstamos activos (" ).append(prestamosModel.size()).append("):\n");
        for (int i = 0; i < prestamosModel.size(); i++) {
            Prestamo p = prestamosModel.get(i);
            // No tenemos getters de fecha ni estado, pero el sistema no marca devoluciones,
            // por lo tanto mostramos "Devuelto: No".
            sb.append("  - ").append(p.toString()).append(" | Devuelto: No").append('\n');
        }
        estadoTextArea.setText(sb.toString());
        estadoTextArea.setCaretPosition(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaGUI().setVisible(true));
    }
}


