import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*Ejercicio 3
Maximizar el número de actividades compatibles. Se tienen n actividades que necesitan utilizar un
recurso, tal como una sala de conferencias, en exclusión mutua. Cada actividad i tiene asociado un
tiempo de comienzo ci y un tiempo de finalización fi de utilización del recurso, con ci < fi. Si la
actividad i es seleccionada se llevará a cabo durante el intervalo [ci, fi). Las actividades i y j son
compatibles si los intervalos [ci, fi) y [cj, fj) no se superponen (es decir, ci > fj o cj > fi). El problema
consiste en encontrar la cantidad máxima de actividades compatibles entre sí.
*/
public class Ejercicio3 {
    private List<Actividad> S;


    public List<Actividad> greedy(List<Actividad> C){
        S = new ArrayList<>();
        // Ordeno por tiempo de finalización
        C.sort(Comparator.comparingInt(Actividad::getTfinal));

        int i =0;
        while(i<C.size()){
            Actividad act = seleccionar(C, i);
            if(esFactible(S, act)){
                S.add(act);
            }
            else{
                i++;
            }


        }
        if (solucion(S)){
            return S;
        }else{
            return null;
        }
    }

    private boolean solucion(List<Actividad> solucion) {
        return !solucion.isEmpty();
    }

    public Actividad seleccionar(List<Actividad>C,int i ){
        return C.get(i);
    }
    public boolean esFactible(List<Actividad> S, Actividad a){
        //aca no es necesario recorrer todas las actividades, sino fijarnos si es factible la actual para agregarla a la solucion
        if (S.isEmpty()){
            return true;
        }else{
            return a.getTcomienzo() > S.get(S.size()-1).getTfinal();
        }

        /*
        for(Actividad act : S){
            if(S.isEmpty()){
                return true;
            }
            else{
                if(act.getTcomienzo() > a.getTfinal()){
                    return true;
                }
            }
        }*/
    }

        public static void main(String[] args) {
            List<Actividad> actividades = new ArrayList<>();
            actividades.add(new Actividad(1, 4));
            actividades.add(new Actividad(3, 5));
            actividades.add(new Actividad(0, 6));
            actividades.add(new Actividad(5, 7));
            actividades.add(new Actividad(8, 9));
            actividades.add(new Actividad(5, 9));

            Ejercicio3 ej = new Ejercicio3();
            List<Actividad> seleccionadas = ej.greedy(actividades);

            System.out.println("Actividades seleccionadas:");
            for (Actividad a : seleccionadas) {
                System.out.println(a);
            }
        }

}
