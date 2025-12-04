package biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    private Util() {} // Nadie puede crear instancias

    public static void asegurarCarpetaArchivos() {
        try {
            Files.createDirectories(Paths.get("Archivos"));
        } catch (IOException e) {
            System.err.println("Error creando carpeta Archivos: " + e.getMessage());
        }
    }
}