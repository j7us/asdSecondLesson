package org.example.lesson7Heap;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeapTest {

    @Test
    void MakeHeapTest() {
        Heap heap = new Heap();

        heap.MakeHeap(new int[]{1,2,3,4,5}, 2);

        assertThat(heap.GetMax()).isEqualTo(5);
    }

    @Test
    void GetMaxEmptyTest() {
        Heap heap = new Heap();

        heap.MakeHeap(new int[2], 2);

        heap.GetMax();
        heap.GetMax();
        int res = heap.GetMax();

        assertThat(res).isEqualTo(-1);
    }

    @Test
    void AddEmptyTest() {
        Heap heap = new Heap();

        heap.MakeHeap(new int[5], 2);

        boolean res = heap.Add(15);

        assertThat(res).isTrue();
    }

    @Test
    void AddTest() {
        Heap heap = new Heap();

        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        boolean res = heap.Add(15);

        assertThat(res).isFalse();
    }

    @Test
    void GetAllTest() {
        Heap heap = new Heap();

        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        heap.GetMax();
        heap.GetMax();
        heap.GetMax();
        heap.GetMax();
        heap.GetMax();
        heap.GetMax();
        int last = heap.GetMax();

        int res = heap.GetMax();

        assertThat(res).isEqualTo(-1);
        assertThat(last).isEqualTo(1);
    }

    @Test
    void isHeapCorrectRootTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1}, 0);

        boolean res = heap.isHeapCorrect();

        assertThat(res).isTrue();
    }

    @Test
    void isHeapCorrectEmptyTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        boolean res = heap.isHeapCorrect();

        assertThat(res).isTrue();
    }

    @Test
    void isHeapCorrectFalseTest() {
        Heap heap = new Heap();
        heap.HeapArray = new int[]{7,4,6,5,3,1,2};

        boolean res = heap.isHeapCorrect();

        assertThat(res).isFalse();
    }

    @Test
    void findEmptyTest() {
        Heap heap = new Heap();
        heap.HeapArray = new int[10];

        int res = heap.find(10);

        assertThat(res).isEqualTo(-1);
    }

    @Test
    void findRootTest() {
        Heap heap = new Heap();
        heap.HeapArray = new int[] {10};

        int res = heap.find(10);

        assertThat(res).isEqualTo(0);
    }

    @Test
    void findTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        int res = heap.find(4);

        assertThat(res).isEqualTo(1);
    }

    @Test
    void findLeafTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        int res = heap.find(1);

        assertThat(res).isEqualTo(3);
    }

    @Test
    void findMaxTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        int res = heap.find(15);

        assertThat(res).isEqualTo(-1);
    }

    @Test
    void findMinTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        int res = heap.find(-10);

        assertThat(res).isEqualTo(-1);
    }

    @Test
    void combineTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3}, 1);

        Heap heap2 = new Heap();
        heap2.MakeHeap(new int[]{4,5,6,7}, 2);

        Heap res = heap.combine(heap2);

        assertThat(res.isHeapCorrect()).isTrue();
    }

    @Test
    void combineSecondTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3,10}, 2);

        Heap heap2 = new Heap();
        heap2.MakeHeap(new int[]{4,5,6,7}, 2);

        Heap res = heap.combine(heap2);

        assertThat(res.isHeapCorrect()).isTrue();
    }

    @Test
    void combineFirstEmptyTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[1], 1);

        Heap heap2 = new Heap();
        heap2.MakeHeap(new int[]{4,5,6,7}, 2);

        Heap res = heap.combine(heap2);

        assertThat(res.isHeapCorrect()).isTrue();
    }

    @Test
    void combineSecondEmptyTest() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1,2,3}, 1);

        Heap heap2 = new Heap();
        heap2.MakeHeap(new int[1], 1);

        Heap res = heap.combine(heap2);

        assertThat(res.isHeapCorrect()).isTrue();
    }
}
