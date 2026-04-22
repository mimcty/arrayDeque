import deque.ArrayDeque61B;
import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    @Test
    /* Uses addFirst() to add one element to an empty list. **/
    public void addFirstToEmptyList() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("new");
        assertThat(deque.toList()).containsExactly("new");
    }

    @Test
    /* Uses addFirst() to add the same amount of elements as the backing array's size. **/
    public void addFirstMaxArraySize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.toList()).containsExactly(7, 6, 5, 4, 3, 2, 1, 0).inOrder();
    }

    @Test
    /* Uses addFirst to add 9 elements to an empty list. Tests resizing; backing array is initialized with size 8. **/
    public void addFirstTestResizeOnce() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 9; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1, 0).inOrder();
    }

    @Test
    /* Uses addFirst() to add a large amount of elements to the deque. Tests if the deque can handle resizing. **/
    public void addFirstResizeMany(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 10000; i >= 0; i--) {
            deque.addFirst(i);
        }
        List<Integer> list = IntStream.rangeClosed(0, 10000).boxed().toList();
        assertThat(deque.toList()).isEqualTo(list);
    }

    @Test
    /* Uses addLast() to add one element to an empty list. **/
    public void addLastToEmptyList() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("new");

        assertThat(deque.toList()).containsExactly("new");
    }

    @Test
    /* Uses addLast() to add the same amount of elements as the backing array's size. **/
    public void addLastMaxArraySize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }
        assertThat(deque.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7).inOrder();
    }

    @Test
    /* Uses addLast to add 9 elements to an empty list. Tests resizing; backing array is initialized with size 8. **/
    public void addLastTestResize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 9; i++) {
            deque.addLast(i);
        }
        assertThat(deque.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }

    @Test
    /* Uses addLast() to add a large amount of elements to the deque. Tests if the deque can handle resizing. **/
    public void addLastResizeMany(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i <= 10000; i++) {
            deque.addLast(i);
        }
        List<Integer> list = IntStream.rangeClosed(0, 10000).boxed().toList();
        assertThat(deque.toList()).isEqualTo(list);
    }

    @Test
    /* Use addFirst() and addLast() to add elements. **/
    public void addFirstAndLast() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("second");
        deque.addLast("third");
        deque.addFirst("first");
        deque.addLast("last");
        assertThat(deque.toList()).containsExactly("first", "second", "third", "last").inOrder();
    }

    @Test
    /* Use addFirst() and addLast() to add more elements than the backing array's initial size. **/
    public void addFirstAndLastMoreElements() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 3; i >= 0; i--) {
            deque.addLast(i);
        }
        for (int i = 4; i < 9; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1, 0).inOrder();
    }

    @Test
    /* Use get() to get a valid index from deque. **/
    public void getValidIndex() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("hello");
        deque.addLast("world");
        assertThat(deque.get(0)).isEqualTo("hello");
        assertThat(deque.get(1)).isEqualTo("world");
    }

    @Test
    /* Use get() to get an index from an empty array. **/
    public void getIndexFromEmptyArray() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.get(0)).isEqualTo(null);
    }

    @Test
    /* Use get() to get a very large, out-of-bounds index from an array. **/
    public void getVeryLargeIndex() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("hello");
        assertThat(deque.get(10000)).isEqualTo(null);
    }

    @Test
    /* Use get() to get a negative, out-of-bounds index from an array. **/
    public void getNegativeIndex() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("hello");
        assertThat(deque.get(-100)).isEqualTo(null);
    }

    @Test
    /* Tests isEmpty() on an empty deque. **/
    public void testIsEmptyNoElements(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    /* Tests size() on an empty deque. **/
    public void testIsEmptyHasElements(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(10);
        assertThat(deque.isEmpty()).isFalse();
        deque.removeFirst();
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    /* Tests size() on an empty deque. **/
    public void testSizeEmptyDeque(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    /* Tests size() on a non-empty deque. **/
    public void testSizeNonEmptyDeque(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(10);
        assertThat(deque.size()).isEqualTo(1);
        deque.addFirst(9);
        deque.addFirst(8);
        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    /* Removes using removeFirst() from an empty deque, then adds elements, then removes again. **/
    public void removeFirstFromEmptyDeque() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.removeFirst();
        deque.addFirst(1);
        deque.addLast(2);
        deque.removeFirst();
        assertThat(deque.toList()).containsExactly(2);
    }

    @Test
    /* Tests removeFirst() on a deque that has two element. Checks that it contains 0 elements after.**/
    public void removeFirstTwoElements() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(7);
        deque.addFirst(6);
        assertThat(deque.size()).isEqualTo(2);
        deque.removeFirst();
        assertThat(deque.toList()).containsExactly(7);
        assertThat(deque.size()).isEqualTo(1);
        deque.removeFirst();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    /* Tests removeFirst() on a filled deque. **/
    public void removeFirstFullDeque() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.size()).isEqualTo(8);
        deque.removeFirst();
        assertThat(deque.toList()).containsExactly(6, 5, 4, 3, 2, 1, 0).inOrder();
        assertThat(deque.size()).isEqualTo(7);
    }

    @Test
    /* Tests removeLast() on a deque that has two element. Checks that it contains 0 elements after.**/
    public void removeLastTwoElements() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(3);
        deque.addFirst(4);
        assertThat(deque.size()).isEqualTo(2);
        deque.removeLast();
        assertThat(deque.toList()).containsExactly(4);
        assertThat(deque.size()).isEqualTo(1);
        deque.removeLast();
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    /* Tests removeLast() on a filled deque. **/
    public void removeLastFullDeque() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.size()).isEqualTo(8);
        deque.removeLast();
        assertThat(deque.toList()).containsExactly(7, 6, 5, 4, 3, 2, 1).inOrder();
        assertThat(deque.size()).isEqualTo(7);
    }

    @Test
    /* Tests resizeDown() in removeFirst() by adding a lot of elements to deque, then removing. **/
    public void testRemoveFirstResizeDown(){
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i <= 1000; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.backingArrayLength()).isEqualTo(1024);
        for (int i = 0; i < 1000; i++) {
            deque.removeFirst();
        }
        assertThat(deque.backingArrayLength()).isEqualTo(8);
    }

    @Test
    /* Tests resizeDown() in removeLast() by adding a lot of elements to deque, then removing. **/
    public void testRemoveLastResizeDown(){
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i <= 1000; i++) {
            deque.addLast(i);
        }
        assertThat(deque.backingArrayLength()).isEqualTo(1024);
        for (int i = 0; i < 1000; i++) {
            deque.removeLast();
        }
        assertThat(deque.backingArrayLength()).isEqualTo(8);
    }

}
