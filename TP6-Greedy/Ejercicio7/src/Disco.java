import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Disco {

    private ArrayList<Cancion> canciones;
    private ArrayList<Cancion> solucion;
    private int capacidadMaxima;
    private int cantMaxGenero;
    public enum Variante {
        A, // Maximizar capacidad ocupada
        B  // Maximizar cantidad de canciones
    }

    public Disco(int capacidadMaxima, int cantMaxGenero) {
        this.capacidadMaxima = capacidadMaxima;
        this.canciones = new ArrayList<>();
        this.solucion = new ArrayList<>();
        this.cantMaxGenero = cantMaxGenero;
    }

    public ArrayList<Cancion> greedy(ArrayList<Cancion> c, Variante variante){
        ArrayList<Cancion> copia = new ArrayList<>(c); // no modificar original
        if (variante == Variante.A) {
            // Variante A: ordenar de mayor a menor por KB
            copia.sort(Comparator.comparingInt(Cancion::getKylobytes).reversed());
        } else if (variante == Variante.B) {
            // Variante B: ordenar de menor a mayor por KB
            copia.sort(Comparator.comparingInt(Cancion::getKylobytes));
        }
        //ordenar de mayor a menor para la estrategia A
        // Ordenar de menoa a mayor para la estrategia B
        //canciones.sort();
        int i =0;
        while (!copia.isEmpty()) {
            Cancion elegida = seleccionar(copia);
            copia.remove(elegida);
            if (esFactible(elegida)) {
                solucion.add(elegida);
            }
        }

            return solucion;

    }


    public Cancion seleccionar(ArrayList<Cancion>c){
        return c.get(0);
    }

    public boolean esFactible(Cancion elegida){
        int totalKBs=0;
        int contador =1;
        for (Cancion actual: solucion){
            if(Objects.equals(actual.getGenero(), elegida.getGenero())){
                contador++;

            }
            totalKBs += actual.getKylobytes();
        }
        return contador <cantMaxGenero && totalKBs + elegida.getKylobytes()<= capacidadMaxima;
    }
}
