package Empleado;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner lee = new Scanner(System.in);
        Empleado[] empleados = new Empleado[5];
        //3empleados de tiempo completo
        System.out.println("ingrese los datos de los empleados de tiempo completo");
        for(int i = 0; i <3; i++){
            System.out.println("ingrese el empleado "+(i+1)+": ");
            String nombre = lee.nextLine();
            System.out.println("Salario anual: ");
            double salario_anual = lee.nextDouble();
            lee.nextLine();
            empleados[i]=new EmpleadoTiempoCompleto(nombre,salario_anual);
        }
        //2empleados tiempo horario
        System.out.println("ingrese los datos de los empleados de tiempo horario");
        for (int i = 3; i <5;i++){
            System.out.println("ingrese el empleado "+(i+1)+": ");
            String nombre = lee.nextLine();
            System.out.println("Horas trabajadas: ");
            double horas_trabajadas = lee.nextDouble();
            System.out.println("Tarifa por hora: ");
            double tarifa_por_hora = lee.nextDouble();
            lee.nextLine();
            empleados[i]=new EmpleadoTiempoHorario(nombre,horas_trabajadas,tarifa_por_hora);
        }
        System.out.println("+++Empleados+++");
        for(Empleado empi : empleados){
            System.out.println(empi.toString());
            System.out.println("--+Empleados+++");
        }
        lee.close();
    }
}
