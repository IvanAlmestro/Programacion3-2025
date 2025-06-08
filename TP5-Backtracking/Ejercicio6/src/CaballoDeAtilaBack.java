import java.util.ArrayList;
    /*
    Ejercicio 6
    Caballo de Atila. Por donde pisa el caballo de Atila jamás vuelve a crecer el pasto. El caballo fue
    directamente hacia el jardín de n x n casillas. Empezó su paseo por una casilla cualquiera y volvió a
    ella, es decir hizo un recorrido cerrado. No visitó dos veces una misma casilla, se movió de una
    casilla a otra vecina en forma horizontal o vertical, pero nunca en diagonal. Por donde pisó el
    caballo, el pasto jamás volvió a crecer. Luego de terminado el recorrido en algunas casillas todavía
    había pasto (señal de que en ellas no había estado el caballo). Escriba un algoritmo que deduzca el
    recorrido completo que hizo el caballo.
     */

public class CaballoDeAtilaBack {


    ArrayList<Casillero> recorridoActual = new ArrayList<>();
    ArrayList<Casillero> solucion = new ArrayList<>();

    public ArrayList<Casillero> backRecorridoValido(ArrayList<Casillero> casilleros, Casillero origen) {
        backRecorridoValidoRec(casilleros, origen, origen);
        return solucion;
    }

    private void backRecorridoValidoRec(ArrayList<Casillero> recorrido, Casillero actual, Casillero destino) {
        if (!recorridoActual.isEmpty() && actual.equals(destino)) {
            solucion = new ArrayList<>(recorridoActual);
            solucion.add(actual);
            return;
        }

        if (recorridoActual.contains(actual)) { // si ya lo recorri, no volver a pisar
            return;
        }

        recorridoActual.add(actual);
        for (Casillero vecino : obtenerVecinosValidos(actual, recorrido)) {
            backRecorridoValidoRec(recorrido, vecino, destino);
            if (!solucion.isEmpty()) {//poda
                return;
            }
        }

        recorridoActual.remove(recorridoActual.size() - 1);
    }

    private ArrayList<Casillero> obtenerVecinosValidos(Casillero actual, ArrayList<Casillero> casilleros) {
        ArrayList<Casillero> vecinos = new ArrayList<>();
        for (Casillero c : casilleros) {
            if (esVecino(actual, c) && !recorridoActual.contains(c)) {
                vecinos.add(c);
            }
        }
        return vecinos;
    }

    private boolean esVecino(Casillero a, Casillero b) {
        int filaA = a.getFila();
        int colA = a.getColumna();
        int filaB = b.getFila();
        int colB = b.getColumna();

        // Vecinos horizontalmente
        if (filaA == filaB && Math.abs(colA - colB) == 1) {
            return true;
        }
        // Vecinos verticalmente
        if (colA == colB && Math.abs(filaA - filaB) == 1) {
            return true;
        }
        return false;
    }

}
