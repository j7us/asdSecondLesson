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
        Arrays.sort(a);

        for (int i = a.length - 1, x = 0; i >= 0 && x < tree_size; i--, x++) {
            HeapArray[x] = a[i];
        }

        lastInd = a.length;
    }

    public int GetMax()
    {
        if (lastInd == 0) {
            return -1;
        }

        int root = HeapArray[0];
        int lastExistInd = lastInd - 1;

        if (lastExistInd == 0) {
            return root;
        }

        HeapArray[0] = HeapArray[lastExistInd];
        HeapArray[lastExistInd] = 0;

        findNewRoot(0);

        return root;
    }

    private void findNewRoot(int index) {
       if (index >= lastInd || isBiggerThanChild(index) || 2 * index + 1 >= HeapArray.length) {
           return;
       }

       final int newIndex;

       if (HeapArray[2 * index + 1] > HeapArray[index]) {
           int child = HeapArray[2 * index + 1];
           HeapArray[2 * index + 1] = HeapArray[index];
           HeapArray[index] = child;
           newIndex = 2 * index + 1;
       } else {
           int child = HeapArray[2 * index + 2];
           HeapArray[2 * index + 2] = HeapArray[index];
           HeapArray[index] = child;
           newIndex = 2 * index + 2;
       }

        findNewRoot(newIndex);
    }

    private boolean isBiggerThanChild(int index) {
        return 2 * index + 1 < HeapArray.length
                && HeapArray[2 * index + 1] < HeapArray[index]
                && HeapArray[2 * index + 2] < HeapArray[index];
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
        if (parentInd < 0 || HeapArray[parentInd] >= HeapArray[keyInd]) {
            return;
        }

        int parent = HeapArray[parentInd];
        HeapArray[parentInd] = HeapArray[keyInd];
        HeapArray[keyInd] = parent;

        replaceNewKeyToRootIfNeeded(parentInd, (parentInd - 1) / 2);
    }

}
