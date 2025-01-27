package org.example.lesson7Heap;

import java.util.*;

class Heap
{
    public int [] HeapArray; // хранит неотрицательные числа-ключи
    private int lastInd = 0;
    private Integer lastMax = null;
    private int lastIndexMax = 0;
    private int maxSelectedFromThisLevel = 0;
    private int viewedLevel = 0;

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

    public Integer GetMaxFromLevel() {
        int levelCount = (int)Math.pow(2, viewedLevel);
        int startIndex = levelCount - 1;
        int endIndexInclude = Math.min(2 * startIndex, lastInd);

        if (startIndex >= HeapArray.length || startIndex >= lastInd) {
            return null;
        }

        int maxValueOnLevel = HeapArray[startIndex];

        if (maxSelectedFromThisLevel != 0) {
            startIndex = findIndexWithValueLessThenMax(startIndex, endIndexInclude);

            if (startIndex < 0) {
                return null;
            }

            maxValueOnLevel = HeapArray[startIndex - 1];
        }

        int maxValueInd = 0;

        for (int i = startIndex; i <= endIndexInclude; i++) {
            if (HeapArray[i] >= maxValueOnLevel) {
                maxValueInd = i;
            }

            if (HeapArray[i] > maxValueOnLevel && (lastMax == null ||  HeapArray[i] < lastMax)) {
                maxValueOnLevel = HeapArray[i];
            }
        }

        maxSelectedFromThisLevel++;

        checkMaxInfoUpdate(levelCount, maxValueOnLevel, maxValueInd);

        return maxValueOnLevel;
    }

    private void checkMaxInfoUpdate(int nodeLevelCount, int newMaxValue, int maxValueInd) {
        if (maxSelectedFromThisLevel == nodeLevelCount) {
            lastMax = null;
            lastIndexMax = 0;
            maxSelectedFromThisLevel = 0;
            viewedLevel++;
        } else {
            lastMax = newMaxValue;
            lastIndexMax = maxValueInd;
        }
    }

    private int findIndexWithValueLessThenMax(int startIndex, int endIndexInclude) {
        for (int i = startIndex; i <= endIndexInclude; i++) {
            if (HeapArray[i] <= lastMax && i != lastIndexMax) {
                return i + 1;
            }
        }

        return -1;
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

        return fillHeapRecursive(res, second, null, null);
    }

    private Heap fillHeapRecursive(Heap result, Heap second, Integer savedFirst, Integer savedSecond) {
        Integer maxFirst = savedFirst == null ? this.GetMaxFromLevel() : savedFirst;
        Integer maxSecond = savedSecond == null ? second.GetMaxFromLevel() : savedSecond;

        if (maxFirst == null && maxSecond == null) {
            return result;
        }

        boolean insertFirst;

        if (maxFirst == null && maxSecond != null) {
            result.Add(maxSecond);
            insertFirst = false;
        } else if (maxFirst != null && maxSecond == null) {
            result.Add(maxFirst);
            insertFirst = true;
        } else {
            insertFirst = maxFirst >= maxSecond;
            result.Add(insertFirst ? maxFirst : maxSecond);
        }

        return fillHeapRecursive(result, second, insertFirst ? null : maxFirst, insertFirst ? maxSecond : null);
    }
}
