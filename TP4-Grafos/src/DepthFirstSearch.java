import java.util.HashMap;
import java.util.Iterator;
//revisado por profe
public class DepthFirstSearch {
    private enum Color{BLANCO, AMARILLO, NEGRO}
    private HashMap<Integer, Color> colores;
    private boolean hayCiclo;

    public boolean DFS(GrafoDirigido<Integer> grafo){
        colores = new HashMap<>();
        boolean hayCiclo =false;
        Iterator<Integer> it = grafo.obtenerVertices();

        while(it.hasNext()){
            colores.put(it.next(), Color.BLANCO);
        }
        //Iniciar DFS en cada vertice que no se haya visitado aun.
        for(int vertice: colores.keySet()){

            if(colores.get(vertice) == Color.BLANCO) {
                DFS_Visit(grafo, vertice);
            }
        }
        return hayCiclo;
    }

    private void DFS_Visit(GrafoDirigido<Integer>grafo, int vertice) {
        //Vertice entrante se pone en amarillo para marcar que fue visitado
        colores.put(vertice, Color.AMARILLO);
        //Traigo los adyacentes a mi vertice.
        Iterator<Integer> itAdy = grafo.obtenerAdyacentes(vertice);

        while(itAdy.hasNext() && !hayCiclo){
            //guardo la info del vertice adyacente y sigo recorriendo
            int verticeAdy = itAdy.next();
            // Si es de color blanco(no esta visitado) se visita recursivamente.
            if(colores.get(verticeAdy) == Color.BLANCO) {
                DFS_Visit(grafo, verticeAdy);
            }
            // si ya estaba visitado(color amarillo) signfica que hay un ciclo.
            else if (colores.get(verticeAdy) ==Color.AMARILLO) {
                hayCiclo = true;
            }
        }
        //Si ya no hay adyacentes para visitar, se marca en negro.
        colores.put(vertice, Color.NEGRO);
    }
}
