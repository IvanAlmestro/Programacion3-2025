import java.util.Iterator;
import java.util.LinkedList;

/*Ejercicio 5
Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo, devuelva una lista
con todos los vértices a partir de los cuales exista un camino en G que termine en v.
*/
public class DFScaminoConDestinoV {
    private LinkedList<Integer> verticesQueCumplen;

    public DFScaminoConDestinoV(){
        this.verticesQueCumplen = new LinkedList<>();
    }

    public LinkedList<Integer> getVerticesCaminoHasta(GrafoDirigido<Integer> grafo, int destino){
        LinkedList<Integer> verticesQueCumplen = new LinkedList<>();
        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()){
            int origen = it.next();
            if(origen != destino && dfsHasta(grafo, origen, destino, new LinkedList<>())){
                verticesQueCumplen.add(origen);
            }
        }

        return verticesQueCumplen;
    }

    private boolean dfsHasta(GrafoDirigido<Integer> grafo, int actual, int destino, LinkedList<Integer> visitados){
        if(actual==destino){
            return true;
        }
        visitados.add(actual);
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
        while (adyacentes.hasNext()){
            int siguiente = adyacentes.next();
            if(!visitados.contains(siguiente) && dfsHasta(grafo,siguiente,destino,visitados)){
                return true;
            }

        }
        return false;

    }

    public static void main(String[] args) {
        GrafoDirigido<Integer> grafoDirigidoMap = new GrafoDirigido<>();
        grafoDirigidoMap.agregarVertice(1);
        grafoDirigidoMap.agregarVertice(2);
        grafoDirigidoMap.agregarVertice(3);
        grafoDirigidoMap.agregarVertice(4);
        grafoDirigidoMap.agregarVertice(5);
        grafoDirigidoMap.agregarVertice(6);

        grafoDirigidoMap.agregarArco(1, 5, 0);
        grafoDirigidoMap.agregarArco(2, 5, 0);
        grafoDirigidoMap.agregarArco(1, 2, 0);
        grafoDirigidoMap.agregarArco(2, 3, 0);
        grafoDirigidoMap.agregarArco(3, 6, 0);
        grafoDirigidoMap.agregarArco(1, 4, 0);

        DFScaminoConDestinoV ej5 = new DFScaminoConDestinoV();
        LinkedList<Integer> caminoHasta = ej5.getVerticesCaminoHasta(grafoDirigidoMap, 5);

        System.out.println("vertices con camino con camino al destino 5:");
        for (Integer nodo : caminoHasta) {
            System.out.print(nodo + " ");
        }
}}
