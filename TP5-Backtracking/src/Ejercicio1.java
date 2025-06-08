import java.util.*;
/* Ejercicio 1

Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente en
un sentido. Una de las salas se denomina entrada y la otra salida. Construir un algoritmo que
permita ir desde la entrada a la salida atravesando la máxima cantidad de salas. Idea: podría
representar el problema mediante un grafo dirigido, donde cada nodo es una habitación, y cada
puerta es un arco dirigido hacia otra habitación. */

public class Ejercicio1 {
    private ArrayList<Integer> mejorCamino = new ArrayList<>();
    private int maxNodosVisitados = 0;

    public ArrayList<Integer> mejorCamino(GrafoDirigido<Integer> grafo, int origen, int destino) {
        ArrayList<Integer> caminoActual = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        caminoActual.add(origen);
        mejorCaminoRec(grafo, caminoActual, visitados, origen, destino);

        return mejorCamino;
    }

    private void mejorCaminoRec(GrafoDirigido<Integer> grafo, ArrayList<Integer> caminoActual, Set<Integer> visitados, int actual, int destino) {
        visitados.add(actual);

        if (actual == destino) {
            if (caminoActual.size() > maxNodosVisitados) {
                maxNodosVisitados = caminoActual.size();
                mejorCamino = new ArrayList<>(caminoActual); // copiamos el mejor
            }
        } else {
            Iterator<Integer> vecinos = grafo.obtenerAdyacentes(actual);
            while (vecinos.hasNext()) {
                int vecino = vecinos.next();
                if (!visitados.contains(vecino)) {
                    mejorCaminoRec(grafo, caminoActual, visitados, vecino, destino);
                }
            }
        }

        // 🔁 Backtracking: deshacemos el paso
        visitados.remove(actual);
        caminoActual.remove(caminoActual.size() - 1);
    }

}
