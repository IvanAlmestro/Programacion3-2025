import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public void add(Integer value) {
        if (this.root == null)
            this.root = new TreeNode(value);
        else
            this.add(this.root, value);
    }

    private void add(TreeNode actual, Integer value) {
        if (actual.getValue() > value) {
            if (actual.getLeft() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setLeft(temp);
            } else {
                add(actual.getLeft(), value);
            }
        } else if (actual.getValue() < value) {
            if (actual.getRight() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setRight(temp);
            } else {
                add(actual.getRight(), value);
            }
        }
    }

    private TreeNode getRoot() {
        return this.root;
    }


    public boolean hasElem(Integer elem) {
        boolean seEncontro = false;
        if (elem > this.getRoot().getValue()) {
            Integer valor = getRoot().getLeft().getValue();
            if (elem > valor) {
                hasElem(valor);
            } else if (elem == valor) {
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int getHeigth(TreeNode node) {
        int alturaDer = 0;
        int alturaIzq = 0;
        if (node.getRight() == null && node.getLeft() == null) {
            return 0;
        } else {
            if (node.getRight() != null) {
                alturaDer = getHeigth(node.getRight());
            } else if (node.getLeft() != null) {
                alturaIzq = getHeigth(node.getLeft());
            }
            return max(alturaIzq, alturaDer);
        }

    }
    public int getMaxElem(){
        int elemMax=0;
        TreeNode temp = this.root;
        if(this.root!=null){
            if(temp.getRight()!= null){
                elemMax = temp.getRight().getValue();
            }else{
                return temp.getValue();
            }

        }
        return elemMax;
    }
    public void printPreOrder(TreeNode nodo){
        if(nodo ==null){
            return;
        }
        System.out.println(nodo.getValue() + " ");
        printPreOrder(nodo.getLeft());
        printPreOrder(nodo.getRight());
    }
    public void printInOrder(TreeNode nodo){
        if(nodo ==null){
            return;
        }
        printInOrder(nodo.getLeft());
        System.out.println(nodo.getValue() + " ");
        printInOrder(nodo.getRight());
    }
    public void printPosOrder(TreeNode nodo){
        if(nodo ==null){
            return;
        }

        printPosOrder(nodo.getLeft());
        printPosOrder(nodo.getRight());
        System.out.println(nodo.getValue() + " ");
    }

    // es importante pasar la lista en vez de crearla en la mismo metodo recursivo, sino cada vez que se llama se crea un nuevo arreglo vacio.
    private ArrayList<TreeNode> getFrontera(TreeNode nodo, ArrayList<TreeNode> resultado) {
        if (nodo != null) {
            // Si es una hoja (no tiene hijos), la agregamos a la lista
            if (nodo.getLeft() == null && nodo.getRight() == null) {
                resultado.add(nodo);
            } else {
                // Si tiene hijos, exploramos ambos lados
                getFrontera(nodo.getLeft(), resultado);
                getFrontera(nodo.getRight(), resultado);
            }
        }
        return resultado;
    }

    private ArrayList<TreeNode> getLongestBranch(TreeNode nodo){
        if(this.root !=null){
            ArrayList<TreeNode> leftBranch = getLongestBranch(nodo.getLeft());
            ArrayList<TreeNode> rightBranch = getLongestBranch(nodo.getRight());
            // Elegir la más larga
            if (rightBranch.size() > leftBranch.size()) {
                rightBranch.add(0, nodo.getValue()); // Agregar nodo actual al inicio
                return rightBranch;
            } else {
                leftBranch.add(0, nodo.getValue()); // Agregar nodo actual al inicio
                return leftBranch;
            }
        }else{
            return new ArrayList<TreeNode>();
        }
    }
    private ArrayList<TreeNode> getElemAtLevel(TreeNode nodo, Integer current, Integer target, ArrayList<TreeNode> resultado){
        if(this.root ==null){
            return new ArrayList<>();
        }
        if(current == target) {
            resultado.add(nodo);

        }else{
            getElemAtLevel(nodo.getLeft(), current + 1, target, resultado);
            getElemAtLevel(nodo.getRight(), current + 1, target, resultado);
        }
        return  resultado;
    }
    /*
    private boolean delete(TreeNode nodo, Integer num){
        if(this.root==null){
            return false;
        }
        if(nodo.getValue() < num){
            return delete(nodo.getLeft(), num);
        }else-if(){

        }
    }
*/
    /*Ejercicio 2
Dado un árbol binario de búsquedas que almacena números enteros, implementar un algoritmo
que retorne la suma de todos los nodos internos del árbol.
*/
    public  Integer sumaNodosInternos(TreeNode nodo){
        if(nodo==null){
            return 0;
        }
        if(nodo.getLeft() ==null && nodo.getRight() == null){
            return 0;
        }
        return nodo.getValue() + sumaNodosInternos(nodo.getLeft()) + sumaNodosInternos(nodo.getRight());
    }
/*Ejercicio 3
Dado un árbol binario de búsqueda que almacena números enteros y un valor de entrada K, implementar un algoritmo
que permita obtener un listado con los valores de todas las hojas cuyo valor supere K. Por ejemplo, para el árbol de la
derecha, con un valor K = 8, el resultado debería ser [9, 11].*/
public  ArrayList<Integer> obtenerMayoresQueK(TreeNode nodo, int K, ArrayList<Integer> arr){

        if(this.root==null){
            return new ArrayList<>();
        }
        // compruebo si es hoja y si es mayor a K
        if(nodo.getLeft() == null && nodo.getRight()== null){
            if(nodo.getValue() >K){
                arr.add(nodo.getValue());
            }
        }
        // recorro los hijos.
        obtenerMayoresQueK(nodo.getRight(), K, arr);
        obtenerMayoresQueK(nodo.getLeft(), K, arr);

        return arr;
    }


/*
* Ejercicio 4
Se posee un árbol binario (no de búsqueda), donde los nodos internos están vacíos, mientras
que las hojas tienen valores enteros. Se debe implementar un metodo que recorra el árbol y
coloque valores en los nodos vacíos (los nodos internos). El valor de cada nodo interno debe ser
igual al valor de su hijo derecho, menos el valor de su hijo izquierdo. En caso de que el nodo
tenga un solo hijo, el valor del hijo faltante se reemplaza por un 0. Por ejemplo, tomando como
entrada el árbol de la izquierda, el árbol resultante debería quedar con los mismos valores que el
de la derecha.
    */
    public int completarValores(TreeNode nodo) {
        if (nodo == null)
            return 0;

        if (nodo.getLeft() == null && nodo.getRight() == null)
            return nodo.getValue();

        int leftValue = completarValores(nodo.getLeft());
        int rightValue = completarValores(nodo.getRight());

        int valorNodo = rightValue - leftValue;
        nodo.setValue(valorNodo);
        return valorNodo;
    }
    /*Ejercicio 5
Dado un árbol binario donde todos los nodos poseen un carácter, de manera que cada rama del
árbol contiene una palabra, implementar un algoritmo que busque y retorne todas las palabras
que posea exactamente N vocales (ni más ni menos). Por ejemplo, para el siguiente árbol, con
una entrada de N = 1, el algoritmo debería retornar [“MAL”]. En cambio, para un N = 2, debería
retornar [“MANA”, “MANO”, “MISA”, “MIO”].*/
    private void construirPalabras(TreeNode nodo, String palabraActual, int cantidadVocales, int N, List<String> resultado) {
        if (nodo == null) return;

        char letra = nodo.getValue(); // Asumimos que TreeNode guarda un 'char'
        String nuevaPalabra = palabraActual + letra;

        if (esVocal(letra)) {
            cantidadVocales++;
        }

        // Si es hoja
        if (nodo.getLeft() == null && nodo.getRight() == null) {
            if (cantidadVocales == N) {
                resultado.add(nuevaPalabra);
            }
        } else {
            construirPalabras(nodo.getLeft(), nuevaPalabra, cantidadVocales, N, resultado);
            construirPalabras(nodo.getRight(), nuevaPalabra, cantidadVocales, N, resultado);
        }
    }
    public boolean esVocal(char letra) {
        return letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U';
    }
}
