import java.util.ArrayList;
import java.util.List;

public class Solucion {
    private final List<Integer> conjuntoA;
    private final List<Integer> conjuntoB;

    public Solucion(List<Integer> conjuntoA, List<Integer> conjuntoB) {
        this.conjuntoA = new ArrayList<>(conjuntoA); // Copia defensiva
        this.conjuntoB = new ArrayList<>(conjuntoB);
    }

    public List<Integer> getConjuntoA() {
        return conjuntoA;
    }

    public List<Integer> getConjuntoB() {
        return conjuntoB;
    }

    public void mostrarSolucion() {
        System.out.println("Esta particion de conjunto suma lo mismo: ");
        System.out.println("Conjunto A: " + conjuntoA);
        System.out.println("Conjunto B: " + conjuntoB);
    }
}