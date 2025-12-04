package biblioteca;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Estudiante {
    private String codigo;
    private String nombre;
    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public void mostrarInfo() {
        System.out.println("Estudiante: " +nombre + ", Código: " + codigo);
    }
    @Override
    public String toString(){
        return "Estudiante('" +nombre + "', " + codigo+ ")";
    }
    //persitencia
    private String getFileName() {
        return "Archivos/estudiante_" + codigo + ".json";
    }

    public void guardar() {
        Util.asegurarCarpetaArchivos();
        Gson gson = new Gson();
        try (FileWriter w = new FileWriter(getFileName())) {
            gson.toJson(this, w);
            System.out.println("Estudiante guardado: " + codigo);
        } catch (IOException e) {
            System.err.println("Error guardando estudiante: " + e.getMessage());
        }
    }

    public static Estudiante cargar(String codigo) {
        Gson gson = new Gson();
        String ruta = "Archivos/estudiante_" + codigo + ".json";
        try (FileReader r = new FileReader(ruta)) {
            return gson.fromJson(r, Estudiante.class);
        } catch (Exception e) {
            System.out.println("Estudiante no encontrado: " + codigo);
            return null;
        }
    }
}