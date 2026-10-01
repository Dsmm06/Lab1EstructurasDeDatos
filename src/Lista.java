public class Lista<T> {
    Node<T> head= new Node<>(null);
    Node<T> tail= new Node<>(null);
    int size=0;
    public Lista(){
        head.next=tail;
        tail.prev=head;
        size=0;
    }
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        // revisamos si la lista esta vacia:
        if (head == null){
            tail = newNode;
            head = newNode;
        }
        newNode.next=tail;

    }
}
