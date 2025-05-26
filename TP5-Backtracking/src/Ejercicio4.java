import java.util.ArrayList;

/* Ejercicio 4
Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición en
dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.
*/
public class Ejercicio4 {
    private final ArrayList<Integer> conjunto;

    public Ejercicio4() {
        this.conjunto = new ArrayList<>();
    }

    public void backParticion(ArrayList<Integer> conjunto) {
        ArrayList<Integer> auxA = new ArrayList<>();
        ArrayList<Integer> auxB = new ArrayList<>();

        // Iniciamos la búsqueda desde el primer elemento
        Solucion s = backParticionRec(conjunto, auxA, auxB, 0, 0, 0);

        if (s != null) {
            s.mostrarSolucion();
        } else {
            System.out.println("No existe partición con suma igual.");
        }
    }

    /**
     * Algoritmo de backtracking para generar todas las particiones posibles en dos subconjuntos
     * y devolver la primera con sumas iguales. No explora más una vez encontrada una solución válida.
     */
    private Solucion backParticionRec(ArrayList<Integer> conjunto, ArrayList<Integer> auxA, ArrayList<Integer> auxB, int suma1, int suma2, int i) {
        if (i == conjunto.size()) {
            // Caso base: ya se procesaron todos los elementos
            if (suma1 == suma2) {
                // Devolvemos una copia de la solución encontrada
                return new Solucion(auxA, auxB);
            }
            return null;
        }

        int j = conjunto.get(i);

        // Intentar agregar el elemento al subconjunto A
        auxA.add(j);
        Solucion s1 = backParticionRec(conjunto, auxA, auxB, suma1 + j, suma2, i + 1);
        if (s1 != null) return s1;  // Si se encontró solución, cortamos
        auxA.remove(auxA.size() - 1);  // Backtrack

        // Intentar agregar el elemento al subconjunto B
        auxB.add(j);
        Solucion s2 = backParticionRec(conjunto, auxA, auxB, suma1, suma2 + j, i + 1);
        if (s2 != null) return s2;
        auxB.remove(auxB.size() - 1);  // Backtrack

        return null;  // No se encontró solución en esta rama
    }

    public static void main(String[] args) {
        Ejercicio4 ej = new Ejercicio4();
        ArrayList<Integer> datos = new ArrayList<>();
        datos.add(1);
        datos.add(5);
        datos.add(11);
        datos.add(5);

        ej.backParticion(datos);
    }
}