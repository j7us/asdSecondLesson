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
}
