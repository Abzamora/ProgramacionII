import java.util.Scanner;

public class Cuadratica {
    private double a;
    private double b;
    private double c;
    private double r1;
    private double r2;

    public Cuadratica(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void leer(){
        Scanner lee = new Scanner(System.in);
        System.out.print("valor de a: ");
        this.a = lee.nextDouble();
        while(this.a == 0) {
            System.out.println("Error: 'a' no puede ser cero.");
            System.out.print("valor de a: ");
            this.a = lee.nextDouble();
        }
        System.out.print("valor de b: ");
        this.b = lee.nextDouble();
        System.out.print("valor de c: ");
        this.c = lee.nextDouble();
    }

    public void Raiz1(){
        double discriminante = (b * b) - (4 * a * c);
        if(discriminante <= 0) {
            r1 = 0;
            return;
        }
        r1 = (-b+Math.sqrt(discriminante))/(2*a);
    }
    public void Raiz2(){
        double discriminante = (b * b) - (4 * a * c);
        if(discriminante <= 0) {
            r2 = 0;
            return;
        }
        r2 = (-b-Math.sqrt(discriminante))/(2*a);
    }
    public void Discriminante(){
        double discriminante = (b * b) - (4 * a * c);

        if(discriminante>0){
            Raiz1();
            Raiz2();
            System.out.println("La ecuacion tiene dos raıces "+r1+" "+r2);
        } else if (discriminante == 0) {
            Raiz1();
            System.out.println("La ecuacion tiene una raız "+ r1);
        } else {
            System.out.println("La ecuacion no tiene raıces reales");
        }
    }
}