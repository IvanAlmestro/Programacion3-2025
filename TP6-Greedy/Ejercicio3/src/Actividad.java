public class Actividad {
    private int tcomienzo;
    private int tfinal;
    private String nombre;

    public Actividad(int tcomienzo, int tfinal ){
        this.tcomienzo = tcomienzo;
        this.tfinal = tfinal;

    }

    public int getTcomienzo() {
        return tcomienzo;
    }

    public void setTcomienzo(int tcomienzo) {
        this.tcomienzo = tcomienzo;
    }

    public int getTfinal() {
        return tfinal;
    }

    public void setTfinal(int tfinal) {
        this.tfinal = tfinal;
    }
    @Override
    public String toString() {
        return "Actividad [" + tcomienzo + ", " + tfinal + ")";
    }
}
