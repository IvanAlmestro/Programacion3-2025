import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/*Ejercicio 7
Dado un grafo no orientado que modela las rutas de la provincia de Buenos Aires, devolver
todos los caminos alternativos que se pueden tomar para ir desde la ciudad de Buenos Aires
a la ciudad de Tandil, considerando que en el tramo Las Flores-Rauch está cortado al tránsito.*/
public class DFSCaminosPosibles {
    private Set<LinkedList<Integer>> caminosPosibles;



    public Set<LinkedList<Integer>> DFScaminosCorrectos(GrafoNoDirigido grafo, int corteInicio,int corteHasta, int origen, int destino){
        caminosPosibles.clear();
        LinkedList<Integer>caminoActual = new LinkedList<>();
        caminoActual.add(origen);
        DFScaminosCorrectoRec(grafo, origen,corteInicio,corteHasta, destino, caminoActual);
        return caminosPosibles;
    }

    private void DFScaminosCorrectoRec(GrafoNoDirigido grafo,int corteInicio,int corteHasta, int actual, int destino, LinkedList<Integer>caminoActual){
        if(actual == destino){
            caminosPosibles.add(new LinkedList<>(caminoActual));
            return;

        }else{
            Iterator<Integer> itAdya= grafo.obtenerAdyacentes(actual);
            while (itAdya.hasNext()){
                int u = itAdya.next();
                // evito que agarre el tramo incorrecto y chequeo q no haya ciclo
                if (!((corteInicio == actual && corteHasta == u) || (corteInicio == u && corteHasta == actual)) && !caminoActual.contains(u)) {
                    caminoActual.add(u);
                    DFScaminosCorrectoRec(grafo, corteInicio, corteHasta,u, destino, caminoActual);
                    caminoActual.remove(caminoActual.size() - 1);
                }
            }
        }

    }
}
