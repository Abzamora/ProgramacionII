package Figura;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Figura[] figufigu= new Figura[5];
        Random aleat = new Random();
        //5figuras aleatorias
        for(int i=0; i<5;i++){
            if(aleat.nextBoolean()){//true=cuadrado,false=circulo
                double lado = aleat.nextDouble()*10+1;
                String[] cores={"rojo","verde","azul","amarillo","morado"};
                String coor= cores[aleat.nextInt(cores.length)];
                figufigu[i]=new Cuadrado(coor, lado);
            }else{
                double radio=aleat.nextDouble()*5+1;
                String[] cores={"rojo","verde","azul","amarillo","morado"};
                String coor= cores[aleat.nextInt(cores.length)];
                figufigu[i]=new Circulo(coor, radio);
            }
        }
        System.out.println("++++figuras++++");
        for(Figura fingu: figufigu){
            System.out.println(fingu.toString());
            if(fingu instanceof Cuadrado){
                System.out.println(((Coloreado)fingu).comoColorear());
            }
            System.out.println("----figura----");
        }
    }
}
