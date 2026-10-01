import java.util.NoSuchElementException;
import java.util.Objects;

public class Main {
    public static final int[] SAMPLE_SIZES = {10, 100, 1_000, 10_000};

    public static void main(String[] args) {
        demoList();
        demoStack();
        demoQueue();
        benchmarkStructures();
    }

    private static void demoList() {
        System.out.println("== Demo: listas enlazadas ==");
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.pushBack(10);
        list.pushBack(20);
        list.pushFront(5);
        list.addAfter(10, 15);
        list.addBefore(20, 18);
        System.out.println("Lista: " + list);
        System.out.println("find(15) -> " + list.find(15));
        System.out.println("popFront -> " + list.popFront());
        System.out.println("popBack -> " + list.popBack());
        System.out.println("erase(18) -> " + list.erase(18));
        System.out.println("Lista final: " + list);
        System.out.println();
    }

    private static void demoStack() {
        System.out.println("== Demo: MyStack ==");
        MyStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("peek -> " + stack.peek());
        System.out.println("pop -> " + stack.pop());
        System.out.println("size -> " + stack.size());
        System.out.println("delete(2) -> " + stack.delete(2));
        System.out.println("stack -> " + stack);
        System.out.println();
    }

    private static void demoQueue() {
        System.out.println("== Demo: MyQueue ==");
        MyQueue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("front -> " + queue.front());
        System.out.println("dequeue -> " + queue.dequeue());
        System.out.println("size -> " + queue.size());
        System.out.println("delete(30) -> " + queue.delete(30));
        System.out.println("queue -> " + queue);
        System.out.println();
    }

    private static void benchmarkStructures() {
        System.out.println("== Benchmark ==");
        benchmarkArrayStack();
        benchmarkLinkedStack();
        benchmarkArrayQueue();
        benchmarkLinkedQueue();
        benchmarkLinkedList();
    }

    private static void benchmarkArrayStack() {
        System.out.println("ArrayStack benchmark (milisegundos):");
        for (int size : SAMPLE_SIZES) {
            MyStack<Integer> stack = new ArrayStack<>();
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                stack.push(i);
            }
            for (int i = 0; i < size; i++) {
                stack.pop();
            }
            long elapsed = System.nanoTime() - start;
            System.out.printf("  size=%d -> %.3f ms%n", size, elapsed / 1_000_000.0);
        }
        System.out.println();
    }

    private static void benchmarkLinkedStack() {
        System.out.println("LinkedStack benchmark (milisegundos):");
        for (int size : SAMPLE_SIZES) {
            MyStack<Integer> stack = new LinkedStack<>();
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                stack.push(i);
            }
            for (int i = 0; i < size; i++) {
                stack.pop();
            }
            long elapsed = System.nanoTime() - start;
            System.out.printf("  size=%d -> %.3f ms%n", size, elapsed / 1_000_000.0);
        }
        System.out.println();
    }

    private static void benchmarkArrayQueue() {
        System.out.println("ArrayQueue benchmark (milisegundos):");
        for (int size : SAMPLE_SIZES) {
            MyQueue<Integer> queue = new ArrayQueue<>();
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                queue.enqueue(i);
            }
            for (int i = 0; i < size; i++) {
                queue.dequeue();
            }
            long elapsed = System.nanoTime() - start;
            System.out.printf("  size=%d -> %.3f ms%n", size, elapsed / 1_000_000.0);
        }
        System.out.println();
    }

    private static void benchmarkLinkedQueue() {
        System.out.println("LinkedQueue benchmark (milisegundos):");
        for (int size : SAMPLE_SIZES) {
            MyQueue<Integer> queue = new LinkedQueue<>();
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                queue.enqueue(i);
            }
            for (int i = 0; i < size; i++) {
                queue.dequeue();
            }
            long elapsed = System.nanoTime() - start;
            System.out.printf("  size=%d -> %.3f ms%n", size, elapsed / 1_000_000.0);
        }
        System.out.println();
    }

    private static void benchmarkLinkedList() {
        System.out.println("SinglyLinkedListWithTail benchmark (milisegundos):");
        for (int size : SAMPLE_SIZES) {
            SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                list.pushBack(i);
            }
            for (int i = 0; i < size; i++) {
                list.popFront();
            }
            long elapsed = System.nanoTime() - start;
            System.out.printf("  size=%d -> %.3f ms%n", size, elapsed / 1_000_000.0);
        }
        System.out.println();
    }
}

interface MyStack<T> {
    void push(T x);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
    boolean delete(T value);
}

interface MyQueue<T> {
    void enqueue(T x);
    T dequeue();
    T front();
    boolean isEmpty();
    int size();
    boolean delete(T value);
}

interface CustomList<T> {
    void pushFront(T value);
    void pushBack(T value);
    T popFront();
    T popBack();
    ListNode<T> find(T value);
    boolean erase(T value);
    void addBefore(T target, T value);
    void addAfter(T target, T value);
    boolean isEmpty();
    int size();
}

class ListNode<T> {
    private T value;
    private ListNode<T> next;
    private ListNode<T> prev;

    public ListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

class SinglyLinkedList<T> implements CustomList<T> {
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

class SinglyLinkedListWithTail<T> extends SinglyLinkedList<T> {
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

class DoublyLinkedList<T> implements CustomList<T> {
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

class DoublyLinkedListWithTail<T> extends DoublyLinkedList<T> {
    @Override
    public int size() {
        return super.size();
    }
}

class ArrayStack<T> implements MyStack<T> {
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

class LinkedStack<T> implements MyStack<T> {
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

class ArrayQueue<T> implements MyQueue<T> {
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

class LinkedQueue<T> implements MyQueue<T> {
    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void enqueue(T x) {
        Node<T> node = new Node<>(x);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (head == null) {
            throw new NoSuchElementException("La cola está vacía.");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public T front() {
        if (head == null) {
            throw new NoSuchElementException("La cola está vacía.");
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
                if (current == tail) {
                    tail = previous;
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

interface Operation {
    void apply(int value);
}