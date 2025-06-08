public class Tarea {
    int tiempoTarea;

    public Tarea(int tiempoTarea) {
        this.tiempoTarea = tiempoTarea;
    }

    public int getTiempoTarea() {
        return tiempoTarea;
    }

    public void setTiempoTarea(int tiempoTarea) {
        this.tiempoTarea = tiempoTarea;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "tiempoTarea=" + tiempoTarea +
                '}';
    }
}