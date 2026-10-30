import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {

    private static final int SIZE = 10;
    private int size;
    private Object[] myArray = new Object[SIZE];


    @Override
    public E top() {

        if (isEmpty()) {
            throw new NoSuchElementException("La pila está buida");
        }

        return (E) myArray[size - 1];
    }

    @Override
    public void pop() {

        if (isEmpty()) {
            throw new NoSuchElementException("La pila está buida");
        }

        myArray[size - 1] = null;
        size--;

    }

    @Override
    public void push(E elem) {

        //si no hay espacio llamar a resize()
        if (size == myArray.length) {
            resize();
        }

        myArray[size] = elem;
        size++;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    //redimensionar array
    private void resize() {

        //crear nuevo array con *2 de espacio
        Object[] newArray = new Object[myArray.length * 2];

        //añadir los datos del array original en el nuevo array
        for (int i = 0; i < myArray.length; i++) {
            newArray[i] = myArray[i];
        }

        myArray = newArray;
    }
}