public class Node<T> {
    T dato;
    int indice;
    Node<T> prev;
    Node<T> next;
    public Node (T dato){
        this.dato = dato;
        this.prev=null;
        this.next=null;
        this.indice=0;
    }
}
