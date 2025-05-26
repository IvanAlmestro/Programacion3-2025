import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*Ejercicio 3
Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
combinaciones de esos números tal que la suma sea igual a M.
*/
public class Ejercicio3 {
    private ArrayList<Integer> conjuntoDado ;
    private List<Integer> soluciones;
    private int valor =12;

    public Ejercicio3(){
        conjuntoDado = new ArrayList<>();
    }

    public List<Integer> backSumaConjunto(ArrayList<Integer>conjuntoDado){
        ArrayList<Integer> auxiliar = new ArrayList<>();


        backSumaConjuntoRec(conjuntoDado, 0,0,auxiliar);
        return soluciones;
    }
    public void backSumaConjuntoRec(ArrayList<Integer>conjuntoDado, int i, int suma, ArrayList<Integer> auxiliar){
        if(suma == valor){
            soluciones.addAll(new ArrayList<>(auxiliar));
            return; //corto la recursion
        }else{
            int j = conjuntoDado.get(i);
            auxiliar.add(j);
            suma +=j;
            // teniendo en cuenta el arbol binario que plante con dos ramas, se corresponde mas con este codigo que con un
            // for por cada valor, que se corresponderia a un arbol mucho mas ancho. Entonces tengo 2 llamados recursivos
            // uno con el valor actual agregado y otro sin el valor.
            if(suma<valor)
                backSumaConjuntoRec(conjuntoDado, i+1, suma, auxiliar);

            suma -= j;
            auxiliar.remove(j);
            backSumaConjuntoRec(conjuntoDado,i+1,suma,auxiliar);

        }
    }
}
