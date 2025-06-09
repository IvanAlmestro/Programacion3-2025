/*
Ejercicio 1
Cambio de monedas: Dado un conjunto C de N tipos de monedas con un número ilimitado de
ejemplares de cada tipo, se requiere formar, si se puede, una cantidad M empleando el mínimo
número de ellas.

Por ejemplo, un cajero automático dispone de billetes de distintos valores: 100$, 25$, 10$, 5$ y 1$,
si se tiene que pagar 289$, la mejor solución consiste en dar 10 billetes: 2 de 100$, 3 de 25$, 1 de
10$ y 4 de 1$.
*/

import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio1 {
    ArrayList<Integer> conjunto = new ArrayList<>(); //100,25,10,5,1
    ArrayList<Integer> S = new ArrayList<>();
    private final int valor = 289;

    public void greedy(){
        Collections.sort(conjunto, Collections.reverseOrder()); // Ordenar de mayor a menor
        ArrayList<Integer> resultado = greedyBilletes(new ArrayList<>(conjunto));


        if (resultado != null) {
            System.out.println("Se puede formar " + valor + "$ con " + resultado.size() + " billetes:");
            for (Integer billete : resultado) {
                System.out.println(billete + "$");
            }
        } else {
            System.out.println("No se puede formar el monto exacto con los billetes disponibles.");
        }

    }
    private ArrayList<Integer> greedyBilletes(ArrayList<Integer> conjunto){

        int i =0;
        while(i< conjunto.size() && !solucion(S)){
            int elegido = seleccionar(conjunto,i);

            if(esFactible(S, elegido)){
                S.add(elegido);
            }else{
                i++;
            }
        }
        if(solucion(S)){
            return S;
        }else{
            return null;
        }
    }
    public int seleccionar(ArrayList<Integer> conjunto,int i){
        return conjunto.get(i);
    }
    public boolean esFactible(ArrayList<Integer>S, int elegido){
        int suma =0;
        for(int i = 0; i<S.size(); i++){
            suma+= S.get(i);
        }
        return suma+elegido <= valor;

    }
    public boolean solucion(ArrayList<Integer> S){
        int suma =0;
        for (int i = 0; i<S.size(); i++){
            suma += S.get(i);

        }
        return suma == valor;
    }
    public static void main(String[] args) {
        Ejercicio1 ej = new Ejercicio1();
        ej.conjunto.add(100);
        ej.conjunto.add(25);
        ej.conjunto.add(10);
        ej.conjunto.add(5);
        ej.conjunto.add(1);

        ej.greedy();
    }
}


/* Estructura general de los Algoritmos Greedy
    public List<Integer> greedy(List<Integer> C) { // Inicialmente el conjunto C contiene todos los candidatos
        Collections.sort(C, Collections.reverseOrder()); // Ordenar de mayor a menor
        S = new ArrayList<>(); // Conjunto solución, inicialmente Vacío

        while (!C.isEmpty() && !solucion(S)) { // Mientras haya candidatos y no se llegó a una solución
            int x = C.get(0); // Determina el mejor candidato del conjunto a seleccionar
            C.remove(0);

            if (factible(S, x)) {
                S.add(x);
            }
        }

        if (solucion(S)) {
            return S;
        }

        return null;
    }

    Elementos:

    • Conjunto de candidatos a seleccionar C.

    • Conjunto de candidatos seleccionados S.

    • Función Solución: determina si los candidatos seleccionados han alcanzado una solución.

    • Función Seleccionar: determina el mejor candidato del conjunto a seleccionar en ese momento (criterio greedy).

    • Función de Factible: determina si válido para nuestro problema agregar el candidato seleccionado a la solución.

    • Siempre está presente una Función Objetivo: da el valor de la solución
    alcanzada que queremos sea óptima (maximice o minimice algo). */