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

    public boolean isHeapCorrect() {
        return isHeapCorrectRecursive(0);
    }

    private boolean isHeapCorrectRecursive(int index) {
        if (2 * index + 1 >= HeapArray.length) {
            return true;
        }

        if (HeapArray[index] < HeapArray[2 * index + 1] || HeapArray[index] < HeapArray[2 * index + 2]) {
            return false;
        }

        return isHeapCorrectRecursive(2 * index + 1) && isHeapCorrectRecursive(2 * index + 2);
    }

    public int find(int key) {
        return findRecursive(0, key);
    }

    private int findRecursive(int index, int key) {
        if (index >= HeapArray.length || HeapArray[index] < key) {
            return -1;
        }

        if (HeapArray[index] == key) {
            return index;
        }

        return Math.max(findRecursive(2 * index + 1, key), findRecursive(2 * index + 2, key));
    }

    public Heap combine(Heap second) {
        Heap res = new Heap();

        res.MakeHeap(new int[1], (int)Math.floor(Math.log(HeapArray.length + second.HeapArray.length)/Math.log(2)));

        return fillHeapRecursive(res, second, -1, -1);
    }

    private Heap fillHeapRecursive(Heap result, Heap second, int savedFirst, int savedSecond) {
        int maxFirst = savedFirst < 0 ? this.GetMax() : savedFirst;
        int maxSecond = savedSecond < 0 ? second.GetMax() : savedSecond;

        if (maxFirst < 0 && maxSecond < 0) {
            return result;
        }

        boolean insertFirst;

        if (maxFirst < 0 && maxSecond >= 0) {
            result.Add(maxSecond);
            insertFirst = false;
        } else if (maxFirst >= 0 && maxSecond < 0) {
            result.Add(maxFirst);
            insertFirst = true;
        } else {
            insertFirst = maxFirst >= maxSecond;
            result.Add(insertFirst ? maxFirst : maxSecond);
        }

        return fillHeapRecursive(result, second, insertFirst ? -1 : maxFirst, insertFirst ? maxSecond : -1);
    }
}
