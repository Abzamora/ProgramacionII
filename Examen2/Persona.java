package Examen2;

public class Persona {
    protected String nombre;
    protected int edad;
    protected float peso;

    public Persona(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public float getPeso() { return peso; }

    public double calTarifa() {
        if (edad < 25 || edad >= 60) {
            return 1.5;
        }
        return 3.0;
    }

    public boolean esTarifaRegular() {
        return edad >= 25 && edad < 60;
    }
}