import java.util.Iterator;

public class MySimpleLinkedList<T extends Comparable<T>> implements Iterable<T> {
	
	private Node<T> first;
    private int size;

	public MySimpleLinkedList() {
        size=0;
		this.first = null;
	}
	
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
        this.size = size+1;
	}

    public Node<T> getFirst() {
        return first;
    }



    public T extractFront() {
        if (!isEmpty()) {
            Node<T> temp = this.first; // Guardamos la referencia al primer nodo
            this.first = this.first.getNext(); // Actualizamos first al siguiente nodo
            this.size = size-1; // Reducimos el tamaño de la lista
            return temp.getInfo(); // Devolvemos el valor del nodo extraído
        }
        return null; // Si la lista está vacía, devuelve null
    }

	public boolean isEmpty() {
		return this.first ==null;
	}
	
   public T get(int index) {
        Node<T> temp = this.first;
        for (int i = 0; i < this.size; i++) {
            if (index == i) {
                return temp.getInfo();
            }
            temp = temp.getNext();
        }
        return null;
    }
	
	public int size() {
		return this.size;
	}
    @Override
    public String toString() {
        Node<T> temp = this.first;
        String txt = ""; // Inicializamos en cadena vacía
        if (temp != null) {
            for (int i = 0; i < this.size; i++) {
                txt += temp.getInfo(); // Concatenamos el valor del nodo

                if (temp.getNext() != null) { // Si hay un siguiente nodo, agregamos la coma
                    txt += ", ";
                }
                temp = temp.getNext(); // Movemos al siguiente nodo
            }
        }

        return txt;
    }
    public int indexOf(T elem){
        int indice = -1;
        Node<T> aux = this.first;
        for(int i =0; i<this.size(); i++){
            if(elem == aux.getInfo()){
                indice = i;
            }
            aux = aux.getNext();

        }
        return indice;
    }
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this.first);
    }

    public MySimpleLinkedList<T> getComunesListOrdenadas(MySimpleLinkedList<T> list1, MySimpleLinkedList<T> list2){
        MySimpleLinkedList<T> resultado = new MySimpleLinkedList<>();
        MyIterator<T> it1 = new MyIterator<>(list1.getFirst());
        while(it1.hasNext()){
            T info1 = it1.next();
            MyIterator<T> it2= new MyIterator<>(list2.getFirst());

            while(it2.hasNext()){
                T info2 = it2.next();
                int comparacion = info1.compareTo(info2);
                if(comparacion < 0){
                    it1.next();
                }
                else if(comparacion >0) {
                    it2.next();
                } else{
                    resultado.insertarOrdenado(info1);
                    it1.next();
                    it2.next();
                    break;
                }


            }

        }
        return resultado;

    }
    public MySimpleLinkedList<T> getComunesListDesordenada(MySimpleLinkedList<T> list1, MySimpleLinkedList<T> list2) {

        MySimpleLinkedList<T> retorno = new MySimpleLinkedList<>();

        MyIterator<T> it1 = new MyIterator<>(list1.getFirst());

        while(it1.hasNext()) {
            T info1 = it1.next();
            MyIterator<T> it2 = new MyIterator<>(list2.getFirst());
            while ( it2.hasNext()) {
                T info2 = it2.next();
                if (info1.equals(info2)) {
                    retorno.insertarOrdenado(info1);
                    break;
                }
            }
        }
        return retorno;
    }
    public void insertarOrdenado(T info) {
        Node<T> newNode = new Node<>(info, null);

        // Caso 1: Lista vacia o el nuevo nodo es menor que el primero
        if (first == null || info.compareTo(first.getInfo()) == 0) { //Si compareTo devuelve 0, significa que ambos valores son iguales.
            this.insertFront(newNode.getInfo());
        }
        else {
            Node<T> aux = this.first;
            // si no se quedo sin numeros y el siguiente es < a info
            //mientras aux no sea el último nodo de la lista.
            //Compara info con el valor del siguiente nodo (aux.getNext().getInfo()).
            // Si compareTo devuelve un número negativo (< 0), significa que info es menor que el valor del siguiente nodo,
            while (aux.getNext() != null && info.compareTo(aux.getInfo())< 0) {
                aux = aux.getNext();
            }
            if (aux.getNext() == null) { // si no hay proximo inserto ultimo al next aux
                aux.setNext(newNode);
            } else { // aux es < info
                // al 4 le seteo el 5 como next, al 3 le seteo como proximo el 4
                newNode.setNext(aux.getNext());
                aux.setNext(newNode);
            }
        }
    }

}