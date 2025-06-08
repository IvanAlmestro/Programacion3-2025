import java.util.ArrayList;

public class Procesador {
    private ArrayList<Tarea> tareasAsignadas;


    public Procesador() {
        tareasAsignadas = new ArrayList<>();
    }

    public void asignarTarea(Tarea tt){
        if (!tareasAsignadas.contains(tt)){ // esto hace falta o es redundate?
            tareasAsignadas.add(tt);
        }
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "tareasAsignadas=" + tareasAsignadas +
                '}';
    }

    public void quitarTarea(Tarea tt){
        tareasAsignadas.remove(tt);
    }

    public ArrayList<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }


    public int getTiempoTareas(){
        int tiempo = 0;
        for (Tarea tt : tareasAsignadas){
            tiempo += tt.getTiempoTarea();
        }
        return tiempo;
    }
}
