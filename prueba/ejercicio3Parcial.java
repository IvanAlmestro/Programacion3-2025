import java.util.ArrayList;

public class ejercicio3Parcial{
    private  ArrayList<String> letras;
    private ArrayList<ArrayList<String>> solucionPalabras;
    private int tamanio;
    Diccionario Diccionario;


    public ArrayList<ArrayList<String>> backPalabras(){
        ArrayList<String> palabraActual = new ArrayList<>();
        solucionPalabras   =new ArrayList<>();
        backPalabrasRec(letras, palabraActual,0);
        return solucionPalabras;
    }
    private void backPalabrasRec( ArrayList<String> letras,ArrayList<String> palabraActual){
        
        if(tamanio == palabraActual.size()){
            if(Diccionario.esPalabraValida(palabraActual)){
                solucionPalabras.add(palabraActual);
            }
        }else{

            for (String letra : letras) {
                if(!palabraActual.contains(letra)){
                    agregarLetra(palabraActual, letra); // agrega la letra E _ _ _

                    backPalabrasRec(palabraActual, letras);

                    quitarLetra(palabraActual); //removelast o algo asi
                }
                
            }
        }
    }
}