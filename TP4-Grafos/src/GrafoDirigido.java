import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    // Mapa donde cada vértice (clave) tiene una lista de arcos que salen de él
    private HashMap<Integer, LinkedList<Arco<T>>> vertices;


    public GrafoDirigido() {
        this.vertices = new HashMap<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        // Si el vértice no existe todavía, lo agregamos con una lista vacía de arcos salientes
        if (!vertices.containsKey(verticeId))
            vertices.put(verticeId, new LinkedList<>());
    }

    public void borrarVertice(int verticeId) {
        // Recorremos todos los vértices del grafo
        for (Integer vecino : vertices.keySet()) {
            // Obtenemos la lista de arcos salientes desde ese vértice
            LinkedList<Arco<T>> arcos = vertices.get(vecino);

            // Eliminamos todos los arcos que tienen como destino al vértice que queremos borrar
            arcos.removeIf(arco -> arco.getVerticeDestino() == verticeId);
        }

        // Finalmente, eliminamos el vértice y todos sus arcos salientes del mapa
        vertices.remove(verticeId);
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)) {
            System.out.println("El grafo no contiene al menos uno de los vértices.");
        } else {
            vertices.get(verticeId1).add(new Arco<T>(verticeId1, verticeId2, etiqueta));
        }
    }


    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)){
            System.out.println("El Grafo no contiene uno de los dos vertices");
        }else{
            // Elimina el arco de la lista de adyacencia de verticeId1
            LinkedList<Arco<T>> arcos = vertices.get(verticeId1);
            arcos.removeIf(arco-> verticeId2 == arco.getVerticeDestino());
            }
    }


    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.containsKey(verticeId);
    }



    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        // Verificamos si el vértice de origen existe
        if (!vertices.containsKey(verticeId1)) {
            return null;
        }
        // Recorremos solo los arcos salientes del vértice de origen
        for (Arco<T> arco : vertices.get(verticeId1)) {
            if (arco.getVerticeDestino() == verticeId2) {
                return arco;
            }
        }
        // Si no encontramos el arco, devolvemos null
        return null;
    }
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)) {
            System.out.println("El grafo no contiene al menos uno de los vértices.");
            return false;
        } else {
            LinkedList<Arco<T>> arcos = vertices.get(verticeId1);

            for (Arco<T> arco : arcos) {
                if (arco.getVerticeDestino() == verticeId2) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
         int total= 0;
         for(LinkedList<Arco<T>> arcos : vertices.values()){
             total+= arcos.size();
         }
         return total;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        Iterator<Integer> iterador = vertices.keySet().iterator();
        return iterador;
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        if (!vertices.containsKey(verticeId)) {
            return null; // Manejar el caso donde el vértice no está en el grafo
        }
        LinkedList<Integer> vecinos = new LinkedList<>();
        for (Arco<T> arco : vertices.get(verticeId)) {
            vecinos.add(arco.getVerticeDestino());
        }
        return vecinos.iterator();
    }


    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> listaArcos = new LinkedList<>();

        for (LinkedList<Arco<T>> listaAdyacencia : vertices.values()) {
            listaArcos.addAll(listaAdyacencia);
        }

        return listaArcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if(!vertices.containsKey(verticeId)){
            return null;
        }
        return vertices.get(verticeId).iterator();
    }
}