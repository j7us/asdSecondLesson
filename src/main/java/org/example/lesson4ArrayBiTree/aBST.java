package org.example.lesson4ArrayBiTree;

import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = (int)Math.pow(2, (depth + 1)) - 1;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key)
    {
        return findKeyIndexRecursive(key, 0);
    }

    private Integer findKeyIndexRecursive(int key, int index) {
        if (index > (Tree.length - 1)) {
            return null;
        }

        Integer keyNode = Tree[index];

        if (keyNode == null) {
            return index * (-1);
        }

        if (keyNode == key) {
            return index;
        }

        int nextIndex = keyNode > key
                ? 2 * index + 1
                : 2 * index + 2;
        return findKeyIndexRecursive(key, nextIndex);
    }

    public int AddKey(int key)
    {
        Integer insertIndex = FindKeyIndex(key);

        if (insertIndex == null) {
            return -1;
        }

        if (!(insertIndex > 0)) {
            insertIndex = insertIndex * (-1);
            Tree[insertIndex] = key;
        }

        return insertIndex;
    }

    public int findLCA(int firstKey, int secondKey) {
        Integer firstInd = FindKeyIndex(firstKey);
        Integer secondInd = FindKeyIndex(secondKey);

        return findLcaRecursive(firstInd, secondInd);
    }

    public int findLcaRecursive(int firstInd, int secondInd) {
        if (firstInd == secondInd) {
            return firstInd;
        }

        return findLcaRecursive((firstInd - 1) / 2, (secondInd - 1) / 2);
    }

    private int findNextIndex(int key, int index, int find) {
        if (key == find) {
            return index;
        }

        int nextIndPlus = key > find ? 1 : 2;

        return 2 * index + nextIndPlus;
    }

    public ArrayList<Integer> WideAllNodes() {
        ArrayList<Integer> result = new ArrayList<>();

        for (int treeIndex = 0; treeIndex < Tree.length; treeIndex++) {
            if (Tree[treeIndex] != null) {
                result.add(Tree[treeIndex]);
            }
        }

        return result;
    }
}
