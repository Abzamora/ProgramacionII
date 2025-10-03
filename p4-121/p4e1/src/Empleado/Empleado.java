package Empleado;
public abstract class Empleado {
    protected String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }
    public abstract double CalcularSalarioMensual();
    @Override
    public String toString() {
        return "Nombre: " + nombre;
    }
}
