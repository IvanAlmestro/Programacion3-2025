import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
Ejercicio 6
Supongamos que una ciudad se encuentra modelada mediante un grafo, donde cada nodo
es una esquina, y las aristas representan las calles. Diseñe un algoritmo tal que dadas dos
esquinas, devuelva el camino más corto entre ambas de manera de caminar la menor
cantidad de cuadras posible.

*/
public class DFScaminoCorto {
    private LinkedList<Integer>caminoCorto;

    public DFScaminoCorto() {
        caminoCorto = new LinkedList<>();
    }

    public LinkedList<Integer> DFSbuscarCamino(Grafo<Integer> grafo, int origen, int destino) {
        caminoCorto.clear();
        LinkedList<Integer>  caminoActual =new LinkedList<>();
        caminoActual.add(origen);
        DFSbuscarCaminoRec(grafo, origen, destino, caminoActual);

        return caminoCorto;
    }

    private void DFSbuscarCaminoRec(Grafo<Integer> grafo, int origen, int destino, LinkedList<Integer> caminoActual) {
        if (origen == destino) {
            if (caminoCorto.isEmpty() || caminoActual.size() < caminoCorto.size()) {
                caminoCorto.clear();
                caminoCorto.addAll(caminoActual);
            }
        } else {
            Iterator<Integer> adya = grafo.obtenerAdyacentes(origen);
            while (adya.hasNext()) {
                int u = adya.next();
                if (!caminoActual.contains(u)) {
                    caminoActual.add(u);
                    DFSbuscarCaminoRec(grafo, u, destino, caminoActual);
                    caminoActual.remove(caminoActual.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        GrafoNoDirigido<Integer> grafo = new GrafoNoDirigido<>();

        // Agregar esquinas (vértices)
        for (int i = 1; i <= 6; i++) {
            grafo.agregarVertice(i);
        }

        // Agregar calles (aristas)
        grafo.agregarArco(1, 2, 0);
        grafo.agregarArco(1, 3, 0);
        grafo.agregarArco(2, 4, 0);
        grafo.agregarArco(3, 4, 0);
        grafo.agregarArco(4, 5, 0);
        grafo.agregarArco(5, 6, 0);

        // Buscar camino más corto entre 1 y 6
        DFScaminoCorto buscador = new DFScaminoCorto();
        LinkedList<Integer>  camino = buscador.DFSbuscarCamino(grafo, 1, 6);

        System.out.println("Camino más corto de 1 a 6: " + camino);
    }
}

