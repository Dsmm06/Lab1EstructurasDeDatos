package estructuras.pilas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayStack<T> implements MyStack<T> {
    private Object[] data;
    private int size;

    public ArrayStack() {
        data = new Object[8];
    }

    @Override
    public void push(T x) {
        if (size == data.length) {
            Object[] next = new Object[data.length * 2];
            System.arraycopy(data, 0, next, 0, data.length);
            data = next;
        }
        data[size++] = x;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        T value = (T) data[--size];
        data[size] = null;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        return (T) data[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean delete(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(data[i], value)) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

