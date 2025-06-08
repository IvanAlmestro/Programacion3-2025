import java.util.ArrayList;
import java.util.List;
/*Ejercicio 10
Utilizando la técnica Backtracking, escriba un algoritmo que dado un conjunto de números enteros,
devuelva (si existen) todos los subconjuntos de tamaño N (dado como parámetro), cuyas sumas
sean exactamente cero. Por ejemplo dado el conjunto {-7, -3, -2, -1, 5, 8 } y N = 3, los subconjuntos
que suman cero son: {-7, -1, 8} y {-3, -2, 5}.*/
public class Ejercicio10 {
    private ArrayList<Integer> conjunto;
    private int N;
    private List<ArrayList<Integer>> soluciones;

    public Ejercicio10(ArrayList<Integer> conjunto, int N) {
        this.conjunto = conjunto;
        this.N = N;
        this.soluciones = new ArrayList<>();
    }

    public List<ArrayList<Integer>> backSubconjunto() {
        ArrayList<Integer> subconjunto = new ArrayList<>();
        backSubconjuntoRec(subconjunto, 0, 0);
        return soluciones;
    }

    private void backSubconjuntoRec(ArrayList<Integer> subconjunto, int actual, int suma) {
        if (subconjunto.size() == N) {
            if (suma == 0) {
                soluciones.add(new ArrayList<>(subconjunto));
            }
            return;
        }

        for (int i = actual; i < conjunto.size(); i++) {
            subconjunto.add(conjunto.get(i));
            suma += conjunto.get(i);

            backSubconjuntoRec(subconjunto, i + 1, suma);

            suma -= conjunto.get(i);
            subconjunto.remove(subconjunto.size() - 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> conjunto = new ArrayList<>();
        conjunto.add(-7);
        conjunto.add(-3);
        conjunto.add(-2);
        conjunto.add(-1);
        conjunto.add(5);
        conjunto.add(8);

        int N = 3;

        Ejercicio10 e = new Ejercicio10(conjunto, N);
        List<ArrayList<Integer>> resultado = e.backSubconjunto();

        System.out.println("Subconjuntos de tamaño " + N + " cuya suma es cero:");
        for (ArrayList<Integer> sub : resultado) {
            System.out.println(sub);
        }
    }
}