import java.util.Scanner;

public class EcuacionLineal {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private double x;
    private double y;

    public EcuacionLineal(double a, double b, double c, double d, double e, double f) {
        this.a = (float) a;
        this.b = (float) b;
        this.c = (float) c;
        this.d = (float) d;
        this.e = (float) e;
        this.f = (float) f;
    }

    public void leer(){
        Scanner lee = new Scanner(System.in);
        System.out.println("Ingrese a, b, c, d, e, f: ");
        a = lee.nextFloat();
        b = lee.nextFloat();
        c = lee.nextFloat();
        d = lee.nextFloat();
        e = lee.nextFloat();
        f = lee.nextFloat();
        lee.close();
    }

    public void X(){
        x = ((e*d)-(b*f))/((a*d)-(b*c));
    }
    public void Y(){
        y = ((a*f)-(e*c))/((a*d)-(b*c));
    }


    public void tieneSolucion() {
        if ((a*d)-(b*c) != 0){
            X();
            Y();
            System.out.println(x + ", " + y);
        }
        else
            System.out.println("La ecuación no tiene solucion");
    }
}
