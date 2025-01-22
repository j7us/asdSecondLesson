package org.example.lesson7Heap;

public class MaxValueFinder {

    public static int findMax(int[] values) {
        Heap heap = new Heap();
        heap.MakeHeap(values, (int)Math.floor(Math.log(values.length)/Math.log(2)));

        return heap.GetMax();
    }
}
