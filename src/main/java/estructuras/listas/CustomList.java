package estructuras.listas;

public interface CustomList<T> {
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

