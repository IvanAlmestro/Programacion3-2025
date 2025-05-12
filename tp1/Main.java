import java.util.Iterator;

public class Main {
    public static void main (String[]args){
        MySimpleLinkedList<Integer> myList = new MySimpleLinkedList<Integer>();

        myList.insertFront(22);
        myList.insertFront(12);
        myList.insertFront(1);
        myList.insertFront(8);
        myList.insertFront(7);

        MySimpleLinkedList<Integer> myList2 = new MySimpleLinkedList<Integer>();

        myList.insertFront(3);
        myList.insertFront(12);
        myList.insertFront(7);
        myList.insertFront(9);
        myList.insertFront(15);


        // Para listas desordenadas
        MySimpleLinkedList<Integer> resultadoUnsorted = MySimpleLinkedListUtils.intersectionUnsorted(myList1, myList2);
        System.out.println("Intersección de listas desordenadas (ordenada): " + resultadoUnsorted);

        // Para listas ordenadas
        MySimpleLinkedList<Integer> resultadoSorted = MySimpleLinkedListUtils.intersectionSorted(myList1, myList2);
        System.out.println("Intersección de listas ordenadas: " + resultadoSorted);

        Iterator<Integer> it = myList.iterator();
        /*while(it.hasNext()){
            Integer i =it.next();
            System.out.println(i);
        }*/
        // aca dos formas de hacerlo, el foreach hace el mismo trabajo por atras. Solo si la lista implementa iterable.
        for(int i : myList){
            System.out.println(i);
        }

        int aux = myList.indexOf(23);
        if(aux!=-1){
            System.out.println("la pos del numero es: " + aux);
        }else{
            System.out.println("No se encontro el numero ingresado");
        }

        String text = myList.toString();
        System.out.println(text);
    }
}
