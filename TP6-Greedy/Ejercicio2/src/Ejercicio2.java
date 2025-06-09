import java.util.ArrayList;

/*Ejercicio 2
Problema de la mochila: Se tienen n objetos y una mochila. Para i = 1,2,..n, el objeto i tiene un peso
positivo pi y un valor positivo vi. La mochila puede llevar un peso que no sobrepase P. El objetivo es
llenar la mochila de tal manera que se maximice el valor de los objetos transportados, respetando la
limitación de capacidad impuesta. Los objetos pueden ser fraccionados, si una fracción xi (0 ≤ xi ≤ 1)
del objeto i es ubicada en la mochila contribuye en xipi al peso total de la mochila y en xivi al valor
de la carga.
¿Qué estrategia Greedy seguiría para resolver el problema? Esquematice la resolución mediante un
pseudocódigo en Java.
*/
public class Ejercicio2 {

    private static int maxPeso =50;
    private static ArrayList<Integer> pesos = new ArrayList<>();
    private static ArrayList<Integer> valores = new ArrayList<>();
    private static int n;
    private static ArrayList<Double> mochila = new ArrayList<>();



    public static ArrayList<Double> mochila(){

        int pesoActual = 0;
        while(pesoActual < maxPeso){
            int i = seleccionar();
            if((pesoActual + pesos.get(i))<= maxPeso){
                mochila.set(i, 1.0);
                pesoActual = pesoActual + pesos.get(i);
            }else{
                double fraccion = (double) (maxPeso - pesoActual) / pesos.get(i);
                mochila.set(i, fraccion);
                pesoActual = maxPeso;
            }
        }
        return mochila;

    }

    // ratio se refiere a la division entre valor/peso, para sacar el mayor provecho.
    public static int seleccionar() {
        double mejorRatio = -1;
        int mejorIndice = -1;
        for (int i = 0; i < valores.size(); i++) {
            if (mochila.get(i) == 0.0) {
                double ratio = (double) valores.get(i) / pesos.get(i);
                if (ratio > mejorRatio) {
                    mejorRatio = ratio;
                    mejorIndice = i;
                }
            }
        }
        return mejorIndice;
    }

    public static void main(String[] args) {
        // Cargamos los objetos
        pesos.add(10); valores.add(60);
        pesos.add(20); valores.add(100);
        pesos.add(30); valores.add(120);

        n = pesos.size();
        for (int i = 0; i < n; i++) {
            mochila.add(0.0);

        }

        mochila();

        System.out.println("Resultado (fracción de cada objeto):");
        double valorTotal = 0;
        for (int i = 0; i < n; i++) {
            System.out.printf("Objeto %d: %.2f (peso=%d, valor=%d)\n", i, mochila.get(i), pesos.get(i), valores.get(i));
            valorTotal += mochila.get(i) * valores.get(i);
        }
        System.out.printf("Valor total cargado: %.2f\n", valorTotal);
    }
}
