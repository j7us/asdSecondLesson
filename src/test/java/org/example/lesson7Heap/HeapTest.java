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
    void AddTest() {
        Heap heap = new Heap();

        heap.MakeHeap(new int[]{1,2,3,4,5,6,7}, 2);

        boolean res = heap.Add(15);

        assertThat(res).isFalse();
    }
}
