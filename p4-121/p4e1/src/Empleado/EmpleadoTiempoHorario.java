package Empleado;
public class EmpleadoTiempoHorario extends Empleado {
    private double horas_trabajadas, tarifa_por_hora;
    public EmpleadoTiempoHorario(String nombre, double horas_trabajadas, double tarifa_por_hora) {
        super(nombre);
        this.horas_trabajadas = horas_trabajadas;
        this.tarifa_por_hora = tarifa_por_hora;
    }
    @Override
    public double CalcularSalarioMensual(){
        return horas_trabajadas * tarifa_por_hora;
    }
    @Override
    public String toString() {
        return super.toString() + "tiempo Horario" +
                "\nhoras trabajadas: " + horas_trabajadas +
                "\ntarifa por hora: " + tarifa_por_hora +
                "\nSalario mensual: " + CalcularSalarioMensual();
    }
}
