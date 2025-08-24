import java.util.Scanner;

public class Estadistica {
    private float[] datos;
    private float promedio;
    private float desviacion;

    public Estadistica() {
        this.datos = new float[10];
        this.promedio = 0;
        this.desviacion = 0;
    }

    public void leer() {
        Scanner lee = new Scanner(System.in);
        System.out.println("Ingrese 10 numeros: ");
        for (int i = 0; i<10; i++) {
            datos[i] = lee.nextFloat();
        }
        lee.close();
    }

    public float cPromedio() {
        float suma = 0;
        for (int i =0; i< datos.length; i++){
            suma += datos[i];
        }
        return suma /datos.length;
    }

    public float cDesviacion(){
        float media = cPromedio();
        float eleCuadrado = 0;
        for (int i =0;i<datos.length;i++){
            float dife= datos[i]-media;
            eleCuadrado +=dife*dife;
        }
        return (float) Math.sqrt(eleCuadrado /(datos.length-1));
    }

    public void resultado() {
        promedio = cPromedio();
        desviacion = cDesviacion();
        System.out.println("El Promedio es: " + promedio);
        System.out.println("La Desviacion Estandar es: " + desviacion);
    }
}
