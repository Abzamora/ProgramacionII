package Examen2;

public class Main {
    public static void main(String[] args) {
        MiTeleferico sistema=new MiTeleferico();

        String[] coloresLineas = {"Roja", "Verde", "Amarilla"};
        for (String color : coloresLineas) {
            System.out.println("Preparando cabinas para la línea " + color);
            for (int i = 0; i < 3; i++) {
                int nro= sistema.agregarCabina(color);
                System.out.println(nro >= 0 ? "Cabina " + nro + " agregada a " + color : "No se pudo agregar cabina a " + color);
            }
        }

        Persona[] personas={
                new Persona("Juan", 32, 78f),
                new Persona("Maria", 22, 60f),
                new Persona("Luis", 64, 70f),
                new Persona("Ana", 28, 55f),
                new Persona("Carla", 19, 50f),
                new Persona("Pedro", 45, 82f),
                new Persona("Amilcar", 22, 60f),
                new Persona("Brandon", 20, 50f)
        };

        for (int i = 0; i < personas.length; i++) {
            String lineaDestino = coloresLineas[i % coloresLineas.length];
            int resultado = sistema.agregarPersonaFila(personas[i], lineaDestino);
            System.out.println("Persona " + personas[i].getNombre() + " a línea " + lineaDestino + " -> resultado " + resultado);
        }

        System.out.println("\nsubiendo primeras personas de cada fila a las  cabinasss:");
        for (String color : coloresLineas) {
            boolean subio = sistema.agregarPrimeraPersonaACabina(color, 1);
            System.out.println("Línea " + color + " cabina 1 -> " + (subio ? "persona siii abordó :D" : "sin abordaje"));
        }

        System.out.println("\nEstado general:");
        System.out.println("ingresos acumulados (todas las tarifas): " + sistema.calcularIngresoTotal());
        System.out.println("línea con mayor ingreso regular: " + sistema.lineaConMasIngresoRegular());
        System.out.println("todas las cabinas cumplen reglas: " + sistema.verificarCabinas());
    }
}

