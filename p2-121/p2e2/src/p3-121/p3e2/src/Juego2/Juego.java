package Juego2;

public class Juego {
    protected int numeroDeVidas;
    protected int record;
    public void reiniciaPartida() {
        numeroDeVidas = 5;
        record = 0;
    }
    public void actualizaRecord() {
        record++;
    }
    public boolean quitaVida() {
        numeroDeVidas--;
        return numeroDeVidas > 0;
    }
}