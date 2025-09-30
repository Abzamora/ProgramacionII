package Juego2;
import java.util.Scanner;
public class JuegoAdivinaNumero extends Juego {
    private int numeroAAdivinar;
    public JuegoAdivinaNumero(int numeroDeVidas) {
        super();
        this.numeroDeVidas = numeroDeVidas;
    }
    protected boolean validaNumero(int numero) {
        return numero >= 0 && numero <= 10;
    }
    public void juega() {
        reiniciaPartida();
        numeroAAdivinar = (int)(Math.random() * 11);
        Scanner lee = new Scanner(System.in);
        while (numeroDeVidas > 0) {
            System.out.println("\nIngresa un numero entre 0 y 10:");
            int entos = lee.nextInt();

            if (!validaNumero(entos)) {
                System.out.println("El numero debe estar entre 0 y 10");
                continue;
            }
            if (entos == numeroAAdivinar) {
                System.out.println("Acertaste!! has ganado.");
                actualizaRecord();
                break;
            } else {
                if (!quitaVida()) {
                    System.out.println("¡Has perdido! El número era " + numeroAAdivinar);
                    break;
                }
                if (entos < numeroAAdivinar) {
                    System.out.println("El numero es mayor");
                } else {
                    System.out.println("El numero es menor");
                }
                System.out.println("Te quedan "+numeroDeVidas+" vidas");
            }
        }
        lee.close();
    }
}
