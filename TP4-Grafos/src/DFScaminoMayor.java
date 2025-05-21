
import java.util.Iterator;
import java.util.LinkedList;
//revisado por profesor.
/*Ejercicio 4
Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que el
grafo de entrada es acíclico.
*/
public class DFScaminoMayor {
    private LinkedList<Integer>caminoMasLargo ;

    public DFScaminoMayor(){
        this.caminoMasLargo = new LinkedList<>();
    }

    private void obtenerMayorCamino(GrafoDirigido<Integer> grafo, int vertice1, int vertice2, LinkedList<Integer> caminoActual){

        // si los vertices son iguales significa que se encontro con el final.
        if(vertice1==vertice2){
            //si es mayor que el que tengo guardado (el camino) lo guardo.
            if(caminoMasLargo.size() < caminoActual.size()){
                caminoMasLargo.clear();
                caminoMasLargo.addAll(caminoActual);
            }
        }else{
            Iterator<Integer> it = grafo.obtenerAdyacentes(vertice1);
            while (it.hasNext()){
                int u = it.next();
                caminoActual.add(u);
                //recorro recursivamente ese camino
                obtenerMayorCamino(grafo,vertice1,vertice2,caminoActual);
                // elimino para explorar por el otro camino
                caminoActual.remove(u);
            }
        }

    }
    public LinkedList<Integer> caminoMasLargo(GrafoDirigido<Integer> grafo, int vertice1, int vertice2){
        caminoMasLargo.clear();
        LinkedList<Integer> caminoActual = new LinkedList<>();
        // agrego el primer vertice debido a que en el otro metodo recorro los adyacentes y me falto agregar el origen de todos esos.
        caminoActual.add(vertice1);
        obtenerMayorCamino(grafo,vertice1,vertice2,caminoActual);
        return caminoMasLargo;
    }
}
