public class Main {
    public static void main(String[] args) {
        Vector3D v1 = new Vector3D(1, 2, 3);   // Vector (1, 2, 3)
        Vector3D v2 = new Vector3D(4, 5, 6);   // Vector (4, 5, 6)
        Vector3D v3 = new Vector3D(2, 4, 6);   // Vector paralelo a v1
        Vector3D v4 = new Vector3D(1, 0, 0);   // Vector unitario en x
        System.out.println("Vectores de Prueba:");
        System.out.println("v1 = "+ v1);
        System.out.println("v2 = "+ v2);
        System.out.println("v3 = "+ v3);
        System.out.println("v4 = "+ v4);
        System.out.println("\nVerificacion de perpendicularidad:");
        System.out.println("¿Son v1 y v2 perpendiculares?");
        System.out.println("Metodo 1: " +v1.esPerpendicular1(v2));
        System.out.println("Metodo 2: " +v1.esPerpendicular2(v2));
        System.out.println("Metodo 3: " +v1.esPerpendicular3(v2));
        System.out.println("Metodo 4: " +v1.esPerpendicular4(v2));
        System.out.println("\nVerificación de paralelismo:");
        System.out.println("¿Son v1 y v3 paralelos?");
        System.out.println("Método 1: " +v1.esParalelo1(v3));
        System.out.println("Método 2: " +v1.esParalelo2(v3));
        System.out.println("\nProyeccion y componente:");
        Vector3D proyeccion =v1.proyeccion(v2);
        double componente =v1.componente(v2);
        System.out.println("Proyección de v1 sobre v2: " + proyeccion);
        System.out.println("Componente de v1 en dirección de v2: " + componente);
        System.out.println("\nOperaciones basicas:");
        Vector3D suma= v1.sumar(v2);
        Vector3D resta= v1.restar(v2);
        Vector3D multiplicado = v1.multiplicarPorEscalar(2);
        Vector3D normalizado = v1.normalizar();
        System.out.println("Suma v1 + v2: " +suma);
        System.out.println("Resta v1 - v2: " +resta);
        System.out.println("v1 multiplicado por 2: " + multiplicado);
        System.out.println("v1 normalizado: " + normalizado);
        System.out.println("\nProducto Vectorial:");
        Vector3D productoVectorial= v1.productoVectorial(v2);
        System.out.println("v1 × v2: "+ productoVectorial);
        System.out.println("\nMagnitud de los vectores:");
        System.out.println("|v1|: "+ v1.magnitud());
        System.out.println("|v2|: "+ v2.magnitud());
        System.out.println("|v3|: "+ v3.magnitud());
        System.out.println("|v4|: "+ v4.magnitud());
        System.out.println("\nProducto escalar:");
        System.out.println("v1 · v2: " + v1.productoEscalar(v2));
        System.out.println("v1 · v3: " + v1.productoEscalar(v3));
        System.out.println("v1 · v4: " + v1.productoEscalar(v4));
        System.out.println("\nVerificacion de que v3 es múltiplo de v1:");
        System.out.println("v3 = " + v3);
        System.out.println("v1 multiplicado por 2: "+ v1.multiplicarPorEscalar(2));
        System.out.println("¿Son iguales? "+ v3.equals(v1.multiplicarPorEscalar(2)));
        System.out.println("\nVerificacion de que v4 es un vector unitario:");
        System.out.println("|v4|: "+ v4.magnitud());
        System.out.println("¿Es unitario? "+ (Math.abs(v4.magnitud() - 1) < 1e-10));
        System.out.println("\nNormalizacion:");
        System.out.println("v2: "+ v2);
        System.out.println("v2 normalizado: "+ v2.normalizar());
        System.out.println("magnitud de v2 normalizado: "+ v2.normalizar().magnitud());
    }
}