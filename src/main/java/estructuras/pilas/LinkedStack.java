package estructuras.pilas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedStack<T> implements MyStack<T> {
    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int size;

    @Override
    public void push(T x) {
        Node<T> node = new Node<>(x);
        node.next = head;
        head = node;
        size++;
    }

    @Override
    public T pop() {
        if (head == null) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        return head.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean delete(T value) {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

