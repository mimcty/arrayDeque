package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;

    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 7;
        this.nextLast = 0;
    }

    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        size++;
        nextFirst--;
    }

    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        size++;
        nextLast++;
    }

    @Override
    public List<T> toList() {
        List<T> retList = new ArrayList<>();
        int i = nextFirst + 1;
        for (int j = 0; j < size; j++) {
            retList.add(items[Math.floorMod(i, items.length)]);
            i++;
        }
        return retList;
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
    public T removeFirst() {
        int firstIdx = Math.floorMod(nextFirst + 1, items.length);
        T first = items[firstIdx];
        items[firstIdx] = null;
        size--;
        nextFirst = firstIdx;
        return first;
    }

    @Override
    public T removeLast() {
        int lastIdx = Math.floorMod(nextLast - 1, items.length);
        T last = items[lastIdx];
        items[lastIdx] = null;
        size--;
        nextLast = lastIdx;
        return last;
    }

    @Override
    public T get(int index) {
        // return null if index out of bounds
        if (index < 0 || index >= size) {
            return null;
        } else {
            int first = nextFirst + 1;
            return items[Math.floorMod(first + index, items.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
