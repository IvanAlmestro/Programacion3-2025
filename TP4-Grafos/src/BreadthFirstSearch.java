import com.sun.source.tree.WhileLoopTree;
/*Ejercicio 2
Implemente los recorridos Depth-First-Search y Breadth-First-Search.*/
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
//revisado por profe
public class BreadthFirstSearch {
    private enum estado{VISITADO, NO_VISITADO};
    private HashMap<Integer, estado> estados;

    public void BFS(GrafoDirigido<Integer> grafo){
        //creamos lo que seria la pila para almacenar los vertices por nivel
        Queue<Integer> cola = new LinkedList<>();
        estados = new HashMap<>();
        Iterator<Integer> itVertices = grafo.obtenerVertices();
        while(itVertices.hasNext()){
            int vertice = itVertices.next();
            estados.put(vertice, estado.NO_VISITADO);
        }
        for(int vertice: estados.keySet()){

            if(estados.get(vertice) == estado.NO_VISITADO){
                BFS_Visit(grafo, vertice, cola);
            }
        }
    }

    private void BFS_Visit(GrafoDirigido<Integer> grafo, int vertice, Queue<Integer> cola) {
        estados.put(vertice, estado.VISITADO);
        cola.add(vertice);
        Iterator<Integer> itAdyacentes = null;

        while(!cola.isEmpty()){
            int u = cola.poll();
            itAdyacentes = grafo.obtenerAdyacentes(u);
            while(itAdyacentes.hasNext()){
                int v = itAdyacentes.next();
                if(estados.get(v) == estado.NO_VISITADO){
                    estados.put(v, estado.VISITADO);
                    cola.add(v);
                }
            }


        }
    }
}
