public class Vector3D {
    private double x;
    private double y;
    private double z;
    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    //producto escalar (punto)
    // a · b = a₁b₁ + a₂b₂ + a₃b₃
    public double productoEscalar(Vector3D vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }
    //magnitud (longitud) del vector
    // |a| = √(a₁² + a₂² + a₃²)
    public double magnitud() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    //normaliza el vector
    // â = a/|a|
    public Vector3D normalizar() {
        double mag = magnitud();
        if (mag == 0) {
            throw new ArithmeticException("No se puede normalizar el vector cero");
        }
        return new Vector3D(x/mag, y/mag, z/mag);
    }
    // c = a + b = (a₁+b₁, a₂+b₂, a₃+b₃)
    public Vector3D sumar(Vector3D vector) {
        return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
    }
    // c = a - b = (a₁-b₁, a₂-b₂, a₃-b₃)
    public Vector3D restar(Vector3D vector) {
        return new Vector3D(x - vector.x, y - vector.y, z - vector.z);
    }
    //multiplicacion por escalar
    // b = ra = (ra₁, ra₂, ra₃)
    public Vector3D multiplicarPorEscalar(double escalar) {
        return new Vector3D(x * escalar, y * escalar, z * escalar);
    }
    //producto vectorial
    // a× b =(a₂b₃-a₃b₂, a₃b₁-a₁b₃, a₁b₂-a₂b₁)
    public Vector3D productoVectorial(Vector3D vector) {
        return new Vector3D(
                y * vector.z - z * vector.y,
                z * vector.x - x * vector.z,
                x * vector.y - y * vector.x
        );
    }
    //proyección ortogonal de a sobre b
    // proj_b(a) = (a·b/|b|²)b
    public Vector3D proyeccion(Vector3D vector) {
        double escalar = productoEscalar(vector) / (vector.productoEscalar(vector));
        return new Vector3D(
                escalar * vector.x,
                escalar * vector.y,
                escalar * vector.z
        );
    }
    //componente de a en la dirección de b
    // comp_b(a) = (a·b)/|b|
    public double componente(Vector3D vector) {
        return productoEscalar(vector) / vector.magnitud();
    }
    //verificacion
    // si |a + b| = |a − b|
    public boolean esPerpendicular1(Vector3D vector) {
        Vector3D suma = sumar(vector);
        Vector3D diferencia = restar(vector);
        return Math.abs(suma.magnitud() - diferencia.magnitud()) < 1e-10;
    }

    //son perpendicular si|a − b| = |b − a|
    public boolean esPerpendicular2(Vector3D vector) {
        Vector3D dif1 = restar(vector);
        Vector3D dif2 = vector.restar(this);
        return Math.abs(dif1.magnitud() - dif2.magnitud()) < 1e-10;
    }
    //perpendiculares si a · b = 0
    public boolean esPerpendicular3(Vector3D vector) {
        return Math.abs(productoEscalar(vector)) < 1e-10;
    }
    //perpendiculares si |a + b|^2 = |a|^2 + |b|^2
    public boolean esPerpendicular4(Vector3D vector) {
        Vector3D suma = sumar(vector);
        double sumaCuadrados = suma.magnitud()*suma.magnitud();
        double magnitudesCuadradas = magnitud()*magnitud() + vector.magnitud()*vector.magnitud();
        return Math.abs(sumaCuadrados - magnitudesCuadradas) < 1e-10;
    }
    //son paralelos si a = rb
    public boolean esParalelo1(Vector3D vector) {
        if(x == 0 && y == 0 && z == 0 ||
                vector.x == 0 && vector.y == 0 && vector.z == 0) return true;
        double r = productoEscalar(vector) / (vector.productoEscalar(vector));
        return Math.abs(x - r * vector.x) < 1e-10 &&
                Math.abs(y - r * vector.y) < 1e-10 &&
                Math.abs(z - r * vector.z) < 1e-10;
    }
    //son paralelos si a × b = 0
    public boolean esParalelo2(Vector3D vector) {
        Vector3D producto = productoVectorial(vector);
        return Math.abs(producto.x) < 1e-10 &&
                Math.abs(producto.y) < 1e-10 &&
                Math.abs(producto.z) < 1e-10;
    }
    //toString representa como string
    @Override
    public String toString() {
        return "("+ x+ ", "+ y+ ", "+ z+ ")";
    }
}