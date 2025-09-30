package Juego1;
import java.util.Scanner;
public class JuegoAdivinaNumero extends Juego{
    private int numeroAAdivinar;
    public JuegoAdivinaNumero(int numeroDeVidas){
        super();
        this.numeroDeVidas = numeroDeVidas;
    }
    public void juega(){
        reiniciaPardtida();
        numeroAAdivinar = (int)(Math.random()*11);
        Scanner lee =  new Scanner(System.in);
        int intentos = 0;
        System.out.println("Bienvenido a Juego Adivinar");
        while (numeroAAdivinar > 0){
            System.out.println("Intente adivinar un numero del 1 al 10: ");
            int entos = lee.nextInt();
            intentos++;
            if (entos == numeroAAdivinar){
                System.out.println("Acertaste!! has ganado.");
                actualizaRecord(intentos);
                break;
            } else {
                if (!quitaVida()) {
                    System.out.println("no te quedan mas vidas, el numero era " + numeroAAdivinar);
                    break;
                }
                if (entos < numeroAAdivinar){
                    System.out.println("El numero es mayor");
                } else {
                    System.out.println("El numero es menor");
                }
                System.out.println("Te quedan "+ numeroDeVidas+" vidas");
            }
        }
        lee.close();
    }
}
