import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {

    private List<Vertice<T>> vertices;
    private List<Arco<T>> arcos;

    public GrafoDirigido() {
        this.vertices = new ArrayList<>();
        this.arcos = new ArrayList<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        Vertice<T> v = new Vertice<>(verticeId);
        if (!vertices.contains(verticeId)) // o(n)
            vertices.add(v);
    }

    public void borrarVertice(int verticeId) {
        // Elimina todos los arcos asociados al vértice (como origen o destino)
        // Creamos un iterador para recorrer la lista de arcos
        Iterator<Arco<T>> it = arcos.iterator();
        // Mientras haya un siguiente arco
        while (it.hasNext()) {
            // Obtenemos el siguiente arco
            Arco<T> arco = it.next();
            if (arco.getVerticeOrigen() == verticeId ||
                    arco.getVerticeDestino() == verticeId) {
                // Si el arco está conectado al vértice, lo eliminamos
                it.remove();
            }
        }

        // Finalmente, eliminamos el vértice del mapa de vértices
        vertices.remove(verticeId);
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(vertices.contains(verticeId1)&& vertices.contains(verticeId2)){
            Arco<T> arcoNuevo = new Arco<>();
            arcoNuevo.setVerticeOrigen(verticeId1);
            arcoNuevo.setVerticeDestino(verticeId2);
            arcoNuevo.setEtiqueta(etiqueta);


            arcos.add(arcoNuevo);
        }

    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if (existeArco(verticeId1, verticeId2)){
            vertices.remove(verticeId1);
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) { // o(n)
        for (Vertice<T> vertice: vertices) {
            if (vertice.getId() == verticeId){
                return true;
            }
        }
        return false;
    }

    //O(n) cuadrado
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        // si existe el vertice
        if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
            Vertice<T> v1 = vertices.get(verticeId1); // si tiene de adyacente a v2 hay arco

            // si son adyacentes, deberia preguntar si existe arco desde verticeId1 a verticeId2 o al reves?
            // o si existe arco entre ambos sin importar el orden de direccion?(eso pal gnd)
            return v1.esAdyacente(verticeId2);
        }
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if (existeArco(verticeId1, verticeId2)){
            return vertices.get(verticeId1).getAdyacentes().get(verticeId2);
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int count = 0;
        for (Vertice<T> v : vertices){
            count += v.getAdyacentes().size();
        }
        return count;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        List<Integer> result = new ArrayList<>();

        for (Vertice v : vertices)
            result.add(v.getId());

        return result.iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Integer> adyacentesIds = new ArrayList<>();
        for (Arco<T> arco : vertices.get(verticeId).getAdyacentes()) {
            adyacentesIds.add(arco.getVerticeDestino());
        }
        return adyacentesIds.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return null;
    }
}