package estructuras.listas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedListWithTail<T> extends SinglyLinkedList<T> {
    private ListNode<T> tail;

    @Override
    public void pushFront(T value) {
        ListNode<T> node = new ListNode<>(value);
        node.setNext(head);
        if (head == null) {
            tail = node;
        }
        head = node;
        size++;
    }

    @Override
    public void pushBack(T value) {
        ListNode<T> node = new ListNode<>(value);
        if (head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }
        tail.setNext(node);
        tail = node;
        size++;
    }

    @Override
    public T popFront() {
        if (head == null) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public T popBack() {
        if (head == null) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        if (head == tail) {
            T value = head.getValue();
            head = null;
            tail = null;
            size--;
            return value;
        }
        ListNode<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        T value = tail.getValue();
        current.setNext(null);
        tail = current;
        size--;
        return value;
    }

    @Override
    public boolean erase(T value) {
        if (head == null) {
            return false;
        }
        if (Objects.equals(head.getValue(), value)) {
            popFront();
            return true;
        }
        ListNode<T> previous = head;
        ListNode<T> current = head.getNext();
        while (current != null) {
            if (Objects.equals(current.getValue(), value)) {
                previous.setNext(current.getNext());
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListNode<T> current = head;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}

