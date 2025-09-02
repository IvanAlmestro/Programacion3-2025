public class Cancion {
    private String nombre;
    private String genero;
    private int duracion;
    private int kylobytes;

    public Cancion(String nombre, String genero, int duracion, int kylobytes) {
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.kylobytes = kylobytes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getKylobytes() {
        return kylobytes;
    }

    public void setKylobytes(int kylobytes) {
        this.kylobytes = kylobytes;
    }
}
