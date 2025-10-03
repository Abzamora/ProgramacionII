package Figura;
public class Circulo extends Figura implements Coloreado {
    private double radio;
    public Circulo(String color, double radio) {
        super(color);
        this.radio = radio;
    }
    @Override
    public double area() {
        return Math.PI * radio * radio;
    }
    @Override
    public double perimetro() {
        return 2 *Math.PI * radio;
    }
    @Override
    public String comoColorear() {
        return "colorear el circulo";
    }
    @Override
    public String toString() {
        return "Circulo: " + super.toString()+
                "\nradio: " + radio+
                "\narea: " + area()+
                "\nperimetro: " + perimetro();
    }
}