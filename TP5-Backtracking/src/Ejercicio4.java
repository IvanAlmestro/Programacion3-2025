import java.lang.reflect.Array;
import java.util.ArrayList;

/*Ejercicio 4
Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición en
dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.
*/
public class Ejercicio4 {
    private ArrayList<Integer> conjunto ;


    public Ejercicio4() {
        this.conjunto = new ArrayList<>();
    }

    public void backParticion(ArrayList<Integer> conjunto){
        ArrayList<Integer> auxA = new ArrayList<>();
        ArrayList<Integer> auxB = new ArrayList<>();
        backParticionRec(conjunto, auxA,auxB, 0, 0, 0);
    }

    private void backParticionRec(ArrayList<Integer> conjunto, ArrayList<Integer> auxA, ArrayList<Integer> auxB, int suma1, int suma2, int i){
        int j= conjunto.get(i);
        auxA.add(j);
        suma1+=j;
        backParticionRec(conjunto, auxA,auxB,suma1,suma2,i+1);
        auxA.remove(conjunto.size()-1);

        auxB.add(j);
        suma2+=j;
        backParticionRec(conjunto, auxA, auxB, suma1, suma2, i+1);
        auxB.remove((conjunto.size()-1));
    }
}
