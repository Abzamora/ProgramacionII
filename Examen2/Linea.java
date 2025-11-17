package Examen2;

public class Linea {
    protected int cantidadCabinas;
    protected Cabina[] cabinas;
    protected String color;
    protected Persona[] filaPersonas;

    public Linea(String color, int cantidadCabinas) {
        this.color = color;
        this.cantidadCabinas = Math.max(1, cantidadCabinas);
        this.cabinas = new Cabina[this.cantidadCabinas];
        int capacidadFila = this.cantidadCabinas * Cabina.MAX_PERSONAS;
        this.filaPersonas = new Persona[Math.max(1, capacidadFila)];
    }

    public boolean agregarPersona(Persona p) {
        if (p == null) {
            return false;
        }
        for (int i = 0; i < filaPersonas.length; i++) {
            if (filaPersonas[i] == null) {
                filaPersonas[i] = p;
                return true;
            }
        }
        return false;
    }

    public boolean agregarCabina(Cabina c) {
        if (c == null) {
            return false;
        }
        for (int i = 0; i < cabinas.length; i++) {
            if (cabinas[i] == null) {
                cabinas[i] = c;
                return true;
            }
        }
        return false;
    }

    public String getColor() { return color; }

    public Cabina[] getCabinas() {
        return cabinas;
    }

    public Persona[] getFilaPersonas() {
        return filaPersonas;
    }

    public boolean subirPrimeraPersonaACabina(int nroCabina) {
        if (nroCabina <= 0 || nroCabina > cabinas.length) {
            return false;
        }

        Cabina cabinaSeleccionada = cabinas[nroCabina - 1];
        if (cabinaSeleccionada == null) {
            return false;
        }

        int indicePersona = obtenerIndicePrimeraPersona();
        if (indicePersona == -1) {
            return false;
        }

        Persona persona = filaPersonas[indicePersona];
        boolean agregada = cabinaSeleccionada.agregarPersona(persona);
        if (agregada) {
            eliminarPersonaDeFila(indicePersona);
        }
        return agregada;
    }

    private int obtenerIndicePrimeraPersona() {
        for (int i = 0; i < filaPersonas.length; i++) {
            if (filaPersonas[i] != null) {
                return i;
            }
        }
        return -1;
    }

    private void eliminarPersonaDeFila(int indice) {
        for (int i = indice; i < filaPersonas.length - 1; i++) {
            filaPersonas[i] = filaPersonas[i + 1];
        }
        filaPersonas[filaPersonas.length - 1] = null;
    }

    public boolean verificarCabinas() {
        for (Cabina cabina : cabinas) {
            if (cabina != null && !cabina.cumpleReglas()) {
                return false;
            }
        }
        return true;
    }

    public double calcularIngresos() {
        double total = 0;
        for (Persona persona : filaPersonas) {
            if (persona != null) {
                total += persona.calTarifa();
            }
        }
        for (Cabina cabina : cabinas) {
            if (cabina != null) {
                for (Persona persona : cabina.getPersonas()) {
                    total += persona.calTarifa();
                }
            }
        }
        return total;
    }

    public double calcularIngresosRegulares() {
        double total = 0;
        for (Persona persona : filaPersonas) {
            if (persona != null && persona.esTarifaRegular()) {
                total += 3.0;
            }
        }
        for (Cabina cabina : cabinas) {
            if (cabina != null) {
                for (Persona persona : cabina.getPersonas()) {
                    if (persona.esTarifaRegular()) {
                        total += 3.0;
                    }
                }
            }
        }
        return total;
    }
}
