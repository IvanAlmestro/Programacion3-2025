public class MySimpleLinkedListUtils {

    // Caso A: Si las listas están desordenadas, la lista resultante debe quedar ordenada.
    public static <T extends Comparable<T>> MySimpleLinkedList<T> intersectionUnsorted(MySimpleLinkedList<T> list1, MySimpleLinkedList<T> list2) {
        MySimpleLinkedList<T> result = new MySimpleLinkedList<>();

        Node<T> temp1 = list1.getFirst();
        while (temp1 != null) {
            Node<T> temp2 = list2.getFirst();
            while (temp2 != null) {
                if (temp1.getInfo().equals(temp2.getInfo()) && !contains(result, temp1.getInfo())) {
                    result.insertFront(temp1.getInfo()); // Insertamos en la lista resultante
                }
                temp2 = temp2.getNext();
            }
            temp1 = temp1.getNext();
        }

        return sortList(result); // Ordenamos la lista antes de devolverla
    }

    // Método auxiliar para ordenar la lista usando Bubble Sort
    private static <T extends Comparable<T>> MySimpleLinkedList<T> sortList(MySimpleLinkedList<T> list) {
        if (list.isEmpty()) return list;

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = list.getFirst();
            while (current != null && current.getNext() != null) {
                if (current.getInfo().compareTo(current.getNext().getInfo()) > 0) {
                    // Intercambiar valores
                    T temp = current.getInfo();
                    current.setInfo(current.getNext().getInfo());
                    current.getNext().setInfo(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
        } while (swapped);

        return list;
    }

    // Caso B: Si las listas están ordenadas, la resultante debe mantenerse ordenada.
    public static <T extends Comparable<T>> MySimpleLinkedList<T> intersectionSorted(MySimpleLinkedList<T> list1, MySimpleLinkedList<T> list2) {
        MySimpleLinkedList<T> result = new MySimpleLinkedList<>();
        Node<T> temp1 = list1.getFirst();
        Node<T> temp2 = list2.getFirst();

        while (temp1 != null && temp2 != null) {
            int compare = temp1.getInfo().compareTo(temp2.getInfo());

            if (compare == 0) { // Elementos iguales
                if (result.isEmpty() || !result.get(result.size() - 1).equals(temp1.getInfo())) {
                    result.insertFront(temp1.getInfo()); // Evita duplicados
                }
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            } else if (compare < 0) {
                temp1 = temp1.getNext();
            } else {
                temp2 = temp2.getNext();
            }
        }

        return result;
    }

    // Método auxiliar para verificar si un elemento ya está en la lista
    private static <T> boolean contains(MySimpleLinkedList<T> list, T value) {
        Node<T> temp = list.getFirst();
        while (temp != null) {
            if (temp.getInfo().equals(value)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }
    public static <T extends Comparable<T>> MySimpleLinkedList<T> crearListaConDuplicados(MySimpleLinkedList<T> list1, MySimpleLinkedList<T> list2) {
        MySimpleLinkedList<T> result = new MySimpleLinkedList<>();
        Node<T> nodo1= list1.getFirst();
        Node<T> nodo2= list2.getFirst();
        int compare = nodo1.getInfo().compareTo(nodo2.getInfo());
        while (nodo1 != null && nodo2 !=null){
            if(compare==0){
                result.insertFront(nodo1.getInfo());
                nodo1 = nodo1.getNext();
                nodo2 = nodo2.getNext();
            }else{
                nodo2 = nodo2.getNext();
            }
        }
        return result;
    }
}
