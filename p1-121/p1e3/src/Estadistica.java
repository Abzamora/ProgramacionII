import java.util.Scanner;

public class Estadistica {
    private float[] datos = new float[10];
    private float promedio;
    private float desviacion;

    public void leer(){
        Scanner lee = new Scanner(System.in);
        System.out.println("Ingrese 10 numeros: ");
        for (int i=0;i<10;i++){
            datos[i] = lee.nextFloat();
        }
        lee.close();
    }
    public void cPromedio(){
        float suma = 0;
        for (int i=0; i<datos.length; i++){
            suma += datos[i];
        }
        promedio = suma/datos.length;
    }

    public void cDesviacion(){
        float media = promedio;
        float alCuadrado = 0;
        for (int i=0; i<datos.length; i++){
            float diferencia = datos[i]- media;
            alCuadrado += diferencia * diferencia;
        }
        desviacion = (float) Math.sqrt(alCuadrado/(datos.length-1));
    }

    public void resultado() {
        System.out.println("El proedio es: " + promedio);
        System.out.print("La desviacion estandar es: " + desviacion);
    }
}