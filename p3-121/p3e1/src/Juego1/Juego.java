package Juego1;
public class Juego {
    protected int numeroDeVidas;
    protected int record;
    public void reiniciaPardtida(){
        numeroDeVidas = 5;
        record = 0;
    }
    public void actualizaRecord(int intentos){
        if (intentos < record || record == 0 ){
            record = intentos;
        }
    }
    public boolean quitaVida(){
        numeroDeVidas--;
        return numeroDeVidas > 0;
    }
}
