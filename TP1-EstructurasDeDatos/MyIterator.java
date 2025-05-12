import java.util.Iterator;

public class MyIterator<T> implements Iterator {
    private Node<T> puntero;
    public MyIterator(Node<T> puntero) {
        this.puntero = puntero;
    }

    @Override
    public boolean hasNext() {
        return puntero !=null;
    }

    @Override
    public T next() {
        T info = this.puntero.getInfo();
        this.puntero =this.puntero.getNext();
        return info;
    }
}