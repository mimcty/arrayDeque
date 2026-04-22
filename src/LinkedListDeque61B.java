import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private class Node {
        private Node prev;
        private Node next;
        private T data;

        private Node(T data){
            this.prev = this;
            this.next = this;
            this.data = data;
        }
    }

    Node sentinel;
    int size;

    public LinkedListDeque61B(){
        this.sentinel = new Node(null);
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x);
        sentinel.next.prev = newNode;
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x);
        sentinel.prev.next = newNode;
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node current = sentinel.next;
        while (current != sentinel) {
            returnList.add(current.data);
            current = current.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
     }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node first = sentinel.next;
        Node second = first.next;
        T data = first.data;

        second.prev = sentinel;
        sentinel.next = second;

        return data;
    }

    @Override
    public T removeLast() {
        Node last = sentinel.prev;
        Node secondLast = last.prev;
        T data = last.data;

        secondLast.next = sentinel;
        sentinel.prev = secondLast;

        return data;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        Node current;
        if (index < (Math.floorDiv(size, 2))){
            current = sentinel.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = sentinel.prev;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.data;
        }
        return getRecursiveHelper(current.next, index - 1);
    }
}
