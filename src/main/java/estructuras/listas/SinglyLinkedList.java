package estructuras.listas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> implements CustomList<T> {
    protected ListNode<T> head;
    protected int size;

    @Override
    public void pushFront(T value) {
        ListNode<T> node = new ListNode<>(value);
        node.setNext(head);
        head = node;
        size++;
    }

    @Override
    public void pushBack(T value) {
        ListNode<T> node = new ListNode<>(value);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        ListNode<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(node);
        size++;
    }

    @Override
    public T popFront() {
        if (head == null) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        T value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    @Override
    public T popBack() {
        if (head == null) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        if (head.getNext() == null) {
            T value = head.getValue();
            head = null;
            size--;
            return value;
        }
        ListNode<T> previous = head;
        ListNode<T> current = head.getNext();
        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        previous.setNext(null);
        size--;
        return current.getValue();
    }

    @Override
    public ListNode<T> find(T value) {
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.getValue(), value)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public boolean erase(T value) {
        if (head == null) {
            return false;
        }
        if (Objects.equals(head.getValue(), value)) {
            head = head.getNext();
            size--;
            return true;
        }
        ListNode<T> previous = head;
        ListNode<T> current = head.getNext();
        while (current != null) {
            if (Objects.equals(current.getValue(), value)) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void addBefore(T target, T value) {
        if (head == null) {
            throw new NoSuchElementException("El elemento objetivo no existe.");
        }
        if (Objects.equals(head.getValue(), target)) {
            pushFront(value);
            return;
        }
        ListNode<T> previous = head;
        ListNode<T> current = head.getNext();
        while (current != null) {
            if (Objects.equals(current.getValue(), target)) {
                ListNode<T> node = new ListNode<>(value);
                node.setNext(current);
                previous.setNext(node);
                size++;
                return;
            }
            previous = current;
            current = current.getNext();
        }
        throw new NoSuchElementException("El elemento objetivo no existe.");
    }

    @Override
    public void addAfter(T target, T value) {
        if (head == null) {
            throw new NoSuchElementException("El elemento objetivo no existe.");
        }
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.getValue(), target)) {
                ListNode<T> node = new ListNode<>(value);
                node.setNext(current.getNext());
                current.setNext(node);
                size++;
                return;
            }
            current = current.getNext();
        }
        throw new NoSuchElementException("El elemento objetivo no existe.");
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    public T topBack() {
        if (head == null) throw new NoSuchElementException("La lista está vacía.");
        ListNode<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getValue();
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

