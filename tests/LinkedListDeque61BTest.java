import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /* In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /* In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /* This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     // writing my own tests below for the first time!

    @Test
    /* This test calls isEmpty on an empty, then subsequently non-empty list.**/
    public void isEmptyTest() {
        Deque61B<String> list = new LinkedListDeque61B<>();
        assertThat(list.isEmpty()).isTrue();
        list.addFirst("Michelle");
        list.addLast("Ma");
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    /* This test calls isEmpty on a list with one item. **/
    public void isEmptyTest2() {
        Deque61B<Boolean> list = new LinkedListDeque61B<>();
        list.addLast(true);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    /* Tests size() when list is empty. **/
    public void testSizeZero() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    /* Tests size() when items are added to the list. **/
    public void testChangingSize() {
        Deque61B<String> list = new LinkedListDeque61B<>();
        list.addFirst("hello");
        assertThat(list.size()).isEqualTo(1);
        list.addLast("world");
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    /* Tests getting index 0. **/
    public void testGetIndexZero() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(3);
        assertThat(list.get(0)).isEqualTo(3);
    }

    @Test
    /* Tests getting the last index in a list. **/
    public void testGetLastIndex() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        assertThat(list.get(4)).isEqualTo(1);
    }

    @Test
    /* Tests when get is given a negative index. Should return null. **/
    public void testGetNegativeIndex() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(3);
        assertThat(list.get(-328)).isEqualTo(null);
    }

    @Test
    /* Tests when get is given a very large, out of bounds index. Should return null. **/
    public void testGetLargeIndex() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(3);
        assertThat(list.get(1028)).isEqualTo(null);
    }


    @Test
    /* Tests getting index 0 recursively. **/
    public void testRecursiveGetIndexZero() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(3);
        assertThat(list.getRecursive(0)).isEqualTo(3);
    }

    @Test
    /* Tests getting the last index in a list recursively. **/
    public void testRecursiveGetLastIndex() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        assertThat(list.getRecursive(4)).isEqualTo(1);
    }

    @Test
    /* Tests when get is given a negative index recursively. Should return null. **/
    public void testRecursiveGetNegativeIndex() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(3);
        assertThat(list.getRecursive(-328)).isEqualTo(null);
    }

    @Test
    /* Tests when get is given a very large, out of bounds index recursively. Should return null. **/
    public void testRecursiveGetLargeIndex() {
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addFirst(3);
        assertThat(list.getRecursive(1028)).isEqualTo(null);
    }

    @Test
    /* Tests removeFirst on a list with one element. **/
    public void testRemoveFirstOneElement(){
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addLast(3);
        Integer removedItem = list.removeFirst();
        assertThat(removedItem).isEqualTo(3);
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    /* Tests removeFirst on an empty list, should return null. **/
    public void testRemoveFirstEmptyList(){
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        Integer removedItem = list.removeFirst();
        assertThat(removedItem).isEqualTo(null);
    }

    @Test
    /* Tests removeFirst on a list with three elements. **/
    public void testRemoveFirst(){
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addLast(13);
        list.addLast(14);
        list.addLast(15);
        Integer removedItem = list.removeFirst();
        assertThat(removedItem).isEqualTo(13);
        assertThat(list.toList()).containsExactly(14, 15).inOrder();
    }

    @Test
    /* Tests removeLast on a list with one element. **/
    public void testRemoveLastOneElement(){
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addLast(3);
        Integer removedItem = list.removeLast();
        assertThat(removedItem).isEqualTo(3);
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    /* Tests removeLast on an empty list, should return null. **/
    public void testRemoveLastEmptyList(){
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        Integer removedItem = list.removeLast();
        assertThat(removedItem).isEqualTo(null);
    }

    @Test
    /* Tests removeLast on a list with three elements. **/
    public void testRemoveLast(){
        Deque61B<Integer> list = new LinkedListDeque61B<>();
        list.addLast(13);
        list.addLast(14);
        list.addLast(15);
        Integer removedItem = list.removeLast();
        assertThat(removedItem).isEqualTo(15);
        assertThat(list.toList()).containsExactly(13, 14).inOrder();
    }

    @Test
    /* Tests iterable functionality. **/
    public void testIterable() {
        Deque61B<String> list = new LinkedListDeque61B<>();
        list.addLast("hello");
        list.addLast("cs61b");
        list.addLast("class");
        list.addLast("and");
        list.addLast("world");
        assertThat(list).containsExactly("hello", "cs61b", "class", "and", "world");
    }
}