package estructuras.listas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T> implements CustomList<T> {
    protected ListNode<T> head;
    protected ListNode<T> tail;
    protected int size;

    @Override
    public void pushFront(T value) {
        ListNode<T> node = new ListNode<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
        size++;
    }

    @Override
    public void pushBack(T value) {
        ListNode<T> node = new ListNode<>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    @Override
    public T popFront() {
        if (head == null) throw new NoSuchElementException("La lista está vacía.");
        T value = head.getValue();
        head = head.getNext();
        if (head != null) {
            head.setPrev(null);
        } else {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public T popBack() {
        if (tail == null) throw new NoSuchElementException("La lista está vacía.");
        T value = tail.getValue();
        tail = tail.getPrev();
        if (tail != null) {
            tail.setNext(null);
        } else {
            head = null;
        }
        size--;
        return value;
    }

    @Override
    public ListNode<T> find(T value) {
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.getValue(), value)) return current;
            current = current.getNext();
        }
        return null;
    }

    @Override
    public boolean erase(T value) {
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.getValue(), value)) {
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void addBefore(T target, T value) {
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.getValue(), target)) {
                ListNode<T> node = new ListNode<>(value);
                if (current.getPrev() != null) {
                    node.setPrev(current.getPrev());
                    current.getPrev().setNext(node);
                } else {
                    head = node;
                }
                node.setNext(current);
                current.setPrev(node);
                size++;
                return;
            }
            current = current.getNext();
        }
        throw new NoSuchElementException("El elemento objetivo no existe.");
    }

    @Override
    public void addAfter(T target, T value) {
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.getValue(), target)) {
                ListNode<T> node = new ListNode<>(value);
                node.setPrev(current);
                node.setNext(current.getNext());
                if (current.getNext() != null) {
                    current.getNext().setPrev(node);
                } else {
                    tail = node;
                }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListNode<T> current = head;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}

