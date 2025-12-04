package biblioteca;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Autor{
    private String nombre;
    private String nacionalidad;
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    public void mostrarInfo() {
        System.out.println("Autor: " + nombre + ", Nacionalidad: " + nacionalidad);
    }
    @Override
    public String toString(){
        return "Autor('" + nombre + "')";
    }

    //Persistencia
    private String getFileName() {
        return "Archivos/autor_" + nombre.replace(" ", "_") + ".json";
    }

    public void guardar() {
        Util.asegurarCarpetaArchivos();
        Gson gson = new Gson();
        try (FileWriter w = new FileWriter(getFileName())) {
            gson.toJson(this, w);
            System.out.println("Autor guardado: " + nombre);
        } catch (IOException e) {
            System.err.println("Error guardando autor: " + e.getMessage());
        }
    }
    public static Autor cargar(String nombreArchivo) {
        Gson gson = new Gson();
        try (FileReader r = new FileReader("Archivos/" + nombreArchivo)) {
            return gson.fromJson(r, Autor.class);
        } catch (Exception e) {
            System.out.println("No se pudo cargar autor: " + nombreArchivo);
            return null;
        }
    }
}