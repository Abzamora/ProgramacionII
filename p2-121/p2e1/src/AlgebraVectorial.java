public class AlgebraVectorial {
    private double x;
    private double y;

    public AlgebraVectorial() {
        this.x = 0;
        this.y = 0;
    }
    public AlgebraVectorial(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double productoEscalar(AlgebraVectorial vector) {
        return x* vector.x + y* vector.y;
    }
    public double magnitud() {
        return Math.sqrt(x*x + y*y);
    }
    //si dos son perpendiculares usando |a+b| = |a−b|
    public boolean perpendicular1(AlgebraVectorial vector) {
        AlgebraVectorial suma= sumar(vector);
        AlgebraVectorial diferencia= restar(vector);
        return Math.abs(suma.magnitud()- diferencia.magnitud()) < 1e-10; //1e-10 es el valor de toleranciapara comparaciones de numeros de punto flotante
    }
    //si dos son perpendiculares usando |a−b| = |b−a|
    public boolean perpendicular2(AlgebraVectorial vector) {
        AlgebraVectorial dif1= restar(vector);
        AlgebraVectorial dif2= vector.restar(this);
        return Math.abs(dif1.magnitud()- dif2.magnitud()) < 1e-10;
    }
    //si son perpendiculares usando a · b = 0
    public boolean perpendicular3(AlgebraVectorial vector) {
        return Math.abs(productoEscalar(vector)) < 1e-10;
    }
    //son perpendiculares si |a+b|^2 = |a|^2 + |b|^2
    public boolean perpendicular4(AlgebraVectorial vector) {
        AlgebraVectorial suma= sumar(vector);
        double sumaCuadrados =suma.magnitud()*suma.magnitud();
        double magnitudesCuadradas= magnitud()*magnitud()+ vector.magnitud()*vector.magnitud();
        return Math.abs(sumaCuadrados- magnitudesCuadradas) < 1e-10;
    }
    //paralelos si a = rb
    public boolean paralela1(AlgebraVectorial vector) {
        if(x == 0 && y == 0 || vector.x == 0 && vector.y == 0) return true;

        double r = productoEscalar(vector) / (vector.productoEscalar(vector));
        return Math.abs(x- r * vector.x)< 1e-10 && Math.abs(y- r * vector.y)< 1e-10;
    }
    //paralelo si a × b = 0
    public boolean paralela2(AlgebraVectorial vector) {
        return Math.abs(x* vector.y- y* vector.x) < 1e-10;
    }
    //proyección de a sobre b
    public AlgebraVectorial proyeccion(AlgebraVectorial vector) {
        double escalar = productoEscalar(vector)/ (vector.productoEscalar(vector));
        return new AlgebraVectorial(escalar* vector.x, escalar* vector.y);
    }

    //componente de a en la direccion de b
    public double componente(AlgebraVectorial vector) {
        return productoEscalar(vector)/ vector.magnitud();
    }
    //sumar dos vectores
    public AlgebraVectorial sumar(AlgebraVectorial vector) {
        return new AlgebraVectorial(x+ vector.x, y+ vector.y);
    }
    //restar dos vectores
    public AlgebraVectorial restar(AlgebraVectorial vector) {
        return new AlgebraVectorial(x- vector.x, y- vector.y);
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}