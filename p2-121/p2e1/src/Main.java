public class Main {
    public static void main(String[] args) {
        AlgebraVectorial v1 = new AlgebraVectorial(3, 4);
        AlgebraVectorial v2 = new AlgebraVectorial(-4, 3);
        AlgebraVectorial v3 = new AlgebraVectorial(6, 8);
        AlgebraVectorial v4 = new AlgebraVectorial(1, 0);//vector unitariox
        System.out.println("Vectores de prueba:");
        System.out.println("v1 = "+ v1);
        System.out.println("v2 = "+ v2);
        System.out.println("v3 = "+ v3);
        System.out.println("v4 = "+ v4);
        System.out.println("\nVerificar si es perpendicular en:");
        System.out.println("¿Son v1 y v2 perpendiculares?");
        System.out.println("Metodo 1: "+ v1.perpendicular1(v2));
        System.out.println("Metodo 2: "+ v1.perpendicular2(v2));
        System.out.println("Metodo 3: "+ v1.perpendicular3(v2));
        System.out.println("Metodo 4: "+ v1.perpendicular4(v2));
        System.out.println("\nVerificar si son paralelos:");
        System.out.println("¿Son v1 y v3 paralelos?");
        System.out.println("Metodo 1: "+ v1.paralela1(v3));
        System.out.println("Metodo 2: "+ v1.paralela2(v3));
        System.out.println("\nProyecciom y componente:");
        AlgebraVectorial proyeccion= v1.proyeccion(v2);
        double componente = v1.componente(v2);
        System.out.println("Proyeccion de v1 sobre v2: "+ proyeccion);
        System.out.println("Componente de v1 en direccion de v2: "+ componente);
    }
}