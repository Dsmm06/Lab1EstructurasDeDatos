package estructuras.app;

import estructuras.colas.ArrayQueue;
import estructuras.colas.LinkedQueue;
import estructuras.colas.MyQueue;
import estructuras.listas.SinglyLinkedListWithTail;
import estructuras.pilas.ArrayStack;
import estructuras.pilas.LinkedStack;
import estructuras.pilas.MyStack;

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

