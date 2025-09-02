
/* Ejercicio 8
Coloreo de un grafo. Dado un grafo se desea colorear cada uno de sus vértices utilizando la menor
cantidad posible de colores totales, sabiendo que dos vértices adyacentes no podrán utilizar el
mismo color
*/

import java.util.*;

public class Colorear {

    private HashMap<Integer, Integer> colorPorVertice;// vértice -> color asignado
    private ArrayList<Integer> colores;
    private boolean hayCiclo;



    public void colorearGrafo(Grafo<Integer> grafo) {
        colorPorVertice = new HashMap<>();

        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
            int v = it.next();
            colorPorVertice.put(v, 0); // 0 = sin color
        }

        for (int v : colorPorVertice.keySet()) {
            if (colorPorVertice.get(v) == 0) {
                colorearDFS(grafo, v);
            }
        }
    }

    private void colorearDFS(GrafoNoDirigido<Integer> grafo, int v) {
        // Obtener colores ya usados por los vecinos
        Set<Integer> coloresVecinos = new HashSet<>();
        Iterator<Integer> itAdy = grafo.obtenerAdyacentes(v);
        while (itAdy.hasNext()) {
            int vecino = itAdy.next();
            int colorVecino = 0;

            // Si el vecino ya tiene color asignado, lo recuperamos
            if (colorPorVertice.containsKey(vecino)) {
                colorVecino = colorPorVertice.get(vecino);
            }

            if (colorVecino != 0) {
                coloresVecinos.add(colorVecino);
            }
        }

        // Asignar el color más bajo posible que no esté en uso
        int color = 1;
        while (coloresVecinos.contains(color)) {
            color++;
        }
        colorPorVertice.put(v, color);

        // Visitar vecinos aún no coloreados
        itAdy = grafo.obtenerAdyacentes(v);
        while (itAdy.hasNext()) {
            int vecino = itAdy.next();

            // Chequeamos si el vecino ya fue coloreado
            if (!colorPorVertice.containsKey(vecino) || colorPorVertice.get(vecino) == 0) {
                colorearDFS(grafo, vecino);
            }
        }
    }
}
