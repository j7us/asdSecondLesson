package org.example.lesson5SortArrayToBiTree;

import java.util.*;

public class AlgorithmsDataStructures2
{
    public static int[] GenerateBBSTArray(int[] a)
    {
        int[] biTreeMassive = new int[(int)Math.pow(2,Math.floor(Math.log(a.length)/Math.log(2)) + 1) - 1];

        Arrays.sort(a);

        fillBiTreeMassiveRecursive(biTreeMassive, a, 0, a.length - 1, 0);

        return biTreeMassive;
    }

    private static void fillBiTreeMassiveRecursive(int[] biTreeMassive,
                                                   int[] from,
                                                   int startIndex,
                                                   int endIndex,
                                                   int indexInTree) {
        if (startIndex == endIndex) {
            biTreeMassive[indexInTree] = from[startIndex];
            return;
        }

        int mid = endIndex - startIndex == 1
                ? getIndexFromSide(startIndex, endIndex)
                : startIndex + (endIndex - startIndex) / 2;

        biTreeMassive[indexInTree] = from[mid];

        if (mid > startIndex) {
            fillBiTreeMassiveRecursive(biTreeMassive, from, startIndex, mid - 1, 2 * indexInTree + 1);
        }

        if (mid < endIndex) {
            fillBiTreeMassiveRecursive(biTreeMassive, from, mid + 1, endIndex, 2 * indexInTree + 2);
        }
    }

    private static int getIndexFromSide(int startIndex, int endIndex) {
        return startIndex == 0 ? endIndex : startIndex;
    }
}
