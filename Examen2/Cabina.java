package Examen2;

import java.util.ArrayList;
import java.util.List;

public class Cabina {
    protected List<Persona> personasAbordo;
    protected int nroCabina;
    protected static final int MAX_PERSONAS = 10;
    protected static final float MAX_PESO = 850f;

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
        this.personasAbordo = new ArrayList<>();
    }

    public boolean agregarPersona(Persona p) {
        if (p == null) {
            return false;
        }

        float pesoActual = getPesoTotal();
        if (pesoActual + p.getPeso() > MAX_PESO) {
            System.out.println("ya no pueden subir mas: excede el peso de 850 kg");
            return false;
        }

        if (personasAbordo.size() >= MAX_PERSONAS) {
            System.out.println("cabina llena (máximo 10 personas)");
            return false;
        }

        personasAbordo.add(p);
        return true;
    }

    public float getPesoTotal() {
        float total = 0;
        for (Persona p : personasAbordo) {
            total += p.getPeso();
        }
        return total;
    }

    public int getCantidadPersonas() {
        return personasAbordo.size();
    }

    public List<Persona> getPersonas() {
        return personasAbordo;
    }

    public int getNroCabina() {
        return nroCabina;
    }

    public boolean cumpleReglas() {
        if (personasAbordo.size() > MAX_PERSONAS) {
            return false;
        }
        return getPesoTotal() <= MAX_PESO;
    }
}
