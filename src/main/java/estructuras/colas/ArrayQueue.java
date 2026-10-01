package estructuras.colas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayQueue<T> implements MyQueue<T> {
    private Object[] data;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue() {
        data = new Object[8];
    }

    @Override
    public void enqueue(T x) {
        if (size == data.length) {
            Object[] next = new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                next[i] = data[(head + i) % data.length];
            }
            data = next;
            head = 0;
            tail = size;
        }
        data[tail] = x;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía.");
        }
        T value = (T) data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía.");
        }
        return (T) data[head];
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
        for (int i = 0; i < size; i++) {
            T current = (T) data[(head + i) % data.length];
            if (Objects.equals(current, value)) {
                for (int j = i; j < size - 1; j++) {
                    data[(head + j) % data.length] = data[(head + j + 1) % data.length];
                }
                data[(head + size - 1) % data.length] = null;
                size--;
                tail = (tail - 1 + data.length) % data.length;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(head + i) % data.length]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

