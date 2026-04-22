package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 7;
        this.nextLast = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resizeUp();
        }
        items[nextFirst] = x;
        size++;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp();
        }
        items[nextLast] = x;
        size++;
        nextLast = Math.floorMod(nextLast + 1, items.length);
    }

    public void resizeUp() {
        T[] newArr = (T[]) new Object[items.length * 2];
        int current = Math.floorMod(nextFirst + 1, items.length);

        for (int i = 0; i < items.length; i++) {
            newArr[i] = items[current];
            current = Math.floorMod(current + 1, items.length);
        }
        items = newArr;
        nextFirst = newArr.length - 1;
        nextLast = size;
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

    public int backingArrayLength(){
        return items.length;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int firstIdx = Math.floorMod(nextFirst + 1, items.length);
        T first = items[firstIdx];
        items[firstIdx] = null;
        size--;
        nextFirst = firstIdx;
        if (items.length >= 16 && ((double) size / items.length) <= 0.25) {
            resizeDown();
        }
        return first;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int lastIdx = Math.floorMod(nextLast - 1, items.length);
        T last = items[lastIdx];
        items[lastIdx] = null;
        size--;
        nextLast = lastIdx;
        if (items.length >= 16 && ((double) size / items.length) <= 0.25) {
            resizeDown();
        }
        return last;
    }

    public void resizeDown(){
        T[] newArr = (T[]) new Object[items.length / 2];
        int current = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            newArr[i] = items[current];
            current = Math.floorMod(current + 1, items.length);
        }
        items = newArr;
        nextFirst = items.length - 1;
        nextLast = size;
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
