/*Ejercicio 5
    Asignación de tareas a procesadores. Se tienen m procesadores idénticos y n tareas con un tiempo
    de ejecución dado. Se requiere encontrar una asignación de tareas a procesadores de manera de
    minimizar el tiempo de ejecución del total de tareas. */

import java.util.ArrayList;
import java.util.List;

public class Ejercicio5Back {
    private ArrayList<Tarea> tareas;
    private int mejorTiempo;
    private int m; //cant procesadores
    private ArrayList<Procesador> mejorSolucion ;


    public Ejercicio5Back(int m, int mejorTiempo) {
        this.m = m;
        this.mejorTiempo = Integer.MAX_VALUE;
        this.tareas = new ArrayList<>(tareas) ;
    }

    public Ejercicio5Back() {

    }

    public ArrayList<Procesador> asignarTarea(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores){

        asignarTareaRec(procesadores,tareas,0);
        return mejorSolucion;

    }
    private void asignarTareaRec(ArrayList<Procesador> procesadores, ArrayList<Tarea> tareas, int tareaActual){
        if (tareaActual == tareas.size()) {
            int tiempoMax = calcularTiempoMaximo(procesadores);
            if (tiempoMax < mejorTiempo) {
                mejorTiempo = tiempoMax;
                mejorSolucion = copiarProcesadores(procesadores);

                // Mostrar la mejor solución encontrada hasta ahora
                System.out.println("Nueva mejor solución con tiempo: " + mejorTiempo);
                for (int i = 0; i < mejorSolucion.size(); i++) {
                    System.out.println("P" + (i + 1) + ": " + mejorSolucion.get(i));
                }
                System.out.println("--------------------------------");
            }
        } else {
            Tarea actual = tareas.get(tareaActual);
            for (int i = 0; i < procesadores.size(); i++) {
                Procesador pp = procesadores.get(i);

                pp.asignarTarea(actual);
                System.out.println("Asigno tarea (" + actual.getTiempoTarea() + ") a P" + (i + 1));
                imprimirEstado(procesadores);

                asignarTareaRec(procesadores, tareas, tareaActual + 1);

                pp.quitarTarea(actual);
                System.out.println("Quito tarea (" + actual.getTiempoTarea() + ") de P" + (i + 1));
                imprimirEstado(procesadores);
            }
        }
    }
    public int calcularTiempoMaximo(ArrayList<Procesador> procesadores){
        int maxTiempo=0;
        for(Procesador p : procesadores){
            int tiempoActual = p.getTiempoTareas();
            if(tiempoActual> maxTiempo){
                maxTiempo=tiempoActual;
            }

        }
        return maxTiempo;
    }
    private ArrayList<Procesador> copiarProcesadores(ArrayList<Procesador> original) {
        ArrayList<Procesador> copia = new ArrayList<>();
        for (Procesador p : original) {
            Procesador nuevo = new Procesador();
            for (Tarea t : p.getTareasAsignadas()) {
                nuevo.asignarTarea(new Tarea(t.getTiempoTarea())); // copio tarea también
            }
            copia.add(nuevo);
        }
        return copia;
    }
    private void imprimirEstado(ArrayList<Procesador> procesadors) {
        for (int i = 0; i < procesadors.size(); i++) {
            System.out.println("P" + (i + 1) + ": " + procesadors.get(i));
        }
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        Ejercicio5Back ejercicio5 = new Ejercicio5Back();
        ArrayList<Procesador> procesadores = new ArrayList<>();

        Procesador p1 = new Procesador();
        Procesador p2 = new Procesador();
        procesadores.add(p1);
        procesadores.add(p2);

        // procesadores: p1, p2, p3-
        // tareas: t1 -> tiempo 2,
        // t2 -> tiempo 3,
        // t3 tiempo 5,
        // t4 tiempo 7,
        // t5 tiempo 1,
        // t6 tiempo 4

        ArrayList<Tarea>tareas = new ArrayList<>();

        Tarea t1 = new Tarea(2);
        Tarea t2 = new Tarea(3);
        Tarea t3 = new Tarea(5);

        tareas.add(t1);
        tareas.add(t2);
        tareas.add(t3);

        ejercicio5.asignarTarea(tareas, procesadores);

    }

}
