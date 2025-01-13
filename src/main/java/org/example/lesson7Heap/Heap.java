package org.example.lesson7Heap;

import java.util.*;

class Heap
{
    public int [] HeapArray; // хранит неотрицательные числа-ключи
    private int lastInd = 0;

    public Heap() { HeapArray = null; }

    public void MakeHeap(int[] a, int depth)
    {
        int tree_size = (int)Math.pow(2, (depth + 1)) - 1;
        HeapArray = new int[tree_size];

        for (int key : a) {
            Add(key);
        }
    }

    public int GetMax()
    {
        if (lastInd == 0) {
            return -1;
        }

        int root = HeapArray[0];
        int lastExistInd = lastInd - 1;

        if (lastExistInd == 0) {
            HeapArray[0] = 0;
            lastInd--;
            return root;
        }

        HeapArray[0] = HeapArray[lastExistInd];
        HeapArray[lastExistInd] = 0;

        lastInd--;

        compareAndSwapRootWithChild(0);

        return root;
    }

    private void compareAndSwapRootWithChild(int index) {
       if (index >= lastInd || 2 * index + 1 > lastInd - 1) {
           return;
       }

       int biggerChildIndex = HeapArray[2 * index + 1] >= HeapArray[2 * index + 2] || 2 * index + 2 >= lastInd
               ? 2 * index + 1
               : 2 * index + 2;

       int newIndex;
       if (HeapArray[biggerChildIndex] > HeapArray[index]) {
           int key = HeapArray[index];
           HeapArray[index] = HeapArray[biggerChildIndex];
           HeapArray[biggerChildIndex] = key;
           newIndex = biggerChildIndex;
       } else {
           return;
       }

        compareAndSwapRootWithChild(newIndex);
    }

    public boolean Add(int key)
    {
        if (lastInd > (HeapArray.length - 1)) {
            return false;
        }

        HeapArray[lastInd] = key;

        replaceNewKeyToRootIfNeeded(lastInd, (lastInd - 1) / 2);

        lastInd++;

        return true;
    }

    private void replaceNewKeyToRootIfNeeded(int keyInd, int parentInd) {
        if (parentInd == keyInd || HeapArray[parentInd] >= HeapArray[keyInd]) {
            return;
        }

        int parent = HeapArray[parentInd];
        HeapArray[parentInd] = HeapArray[keyInd];
        HeapArray[keyInd] = parent;

        replaceNewKeyToRootIfNeeded(parentInd, (parentInd - 1) / 2);
    }

}
