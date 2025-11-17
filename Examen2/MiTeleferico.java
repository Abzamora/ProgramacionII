package Examen2;

public class MiTeleferico {
    protected Linea[] lineas;
    protected float cantidadIngresos;

    MiTeleferico() {
        lineas = new Linea[]{
                new Linea("Roja", 4),
                new Linea("Verde", 3),
                new Linea("Amarilla", 5)
        };
    }

    public int agregarPersonaFila(Persona p, String linea) {
        Linea lineaSeleccionada = buscarLinea(linea);
        if (lineaSeleccionada == null) {
            return -1;
        }
        boolean agregada = lineaSeleccionada.agregarPersona(p);
        if (agregada) {
            cantidadIngresos += (float) p.calTarifa();
            return 1;
        }
        return 0;
    }
    public int agregarCabina(String linea){
        Linea lineaSeleccionada = buscarLinea(linea);
        if (lineaSeleccionada == null) {
            return -1;
        }
        Cabina[] cabinas = lineaSeleccionada.getCabinas();
        for (int i = 0; i < cabinas.length; i++) {
            if (cabinas[i] == null) {
                int nroCabina = i + 1;
                Cabina nueva = new Cabina(nroCabina);
                boolean agregada = lineaSeleccionada.agregarCabina(nueva);
                return agregada ? nroCabina : -2;
            }
        }
        return -2;
    }

    public boolean agregarPrimeraPersonaACabina(String linea, int nroCabina) {
        Linea lineaSeleccionada = buscarLinea(linea);
        if (lineaSeleccionada == null) {
            return false;
        }
        return lineaSeleccionada.subirPrimeraPersonaACabina(nroCabina);
    }

    public boolean verificarCabinas() {
        for (Linea linea : lineas) {
            if (linea != null && !linea.verificarCabinas()) {
                return false;
            }
        }
        return true;
    }

    public double calcularIngresoTotal() {
        double total = 0;
        for (Linea linea : lineas) {
            if (linea != null) {
                total += linea.calcularIngresos();
            }
        }
        return total;
    }

    public String lineaConMasIngresoRegular() {
        double maxIngreso = -1;
        Linea lineaMax = null;
        for (Linea linea : lineas) {
            if (linea != null) {
                double ingresosRegulares = linea.calcularIngresosRegulares();
                if (ingresosRegulares > maxIngreso) {
                    maxIngreso = ingresosRegulares;
                    lineaMax = linea;
                }
            }
        }
        return lineaMax != null ? lineaMax.getColor() : null;
    }

    private Linea buscarLinea(String color) {
        if (color == null) {
            return null;
        }
        for (Linea linea : lineas) {
            if (linea != null && linea.getColor().equalsIgnoreCase(color)) {
                return linea;
            }
        }
        return null;
    }

    public float getCantidadIngresos() {
        return cantidadIngresos;
    }
}
