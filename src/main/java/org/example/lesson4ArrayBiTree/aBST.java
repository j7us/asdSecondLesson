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

        return findLcaRecursive(
                firstInd,
                (int)Math.floor(Math.log(firstInd + 1) / Math.log(2)),
                secondInd,
                (int)Math.floor(Math.log(secondInd + 1) / Math.log(2)));
    }

    public int findLcaRecursive(int firstInd, int firstLevel, int secondInd, int secondLevel) {
        if (firstInd == secondInd) {
            return firstInd;
        }

        if (firstLevel > secondLevel) {
            firstLevel--;
            firstInd = (firstInd - 1) / 2;
        } else if (secondLevel > firstLevel) {
            secondInd = (secondInd - 1) / 2;
            secondLevel--;
        } else {
            firstInd = (firstInd - 1) / 2;
            secondInd = (secondInd - 1) / 2;
        }

        return findLcaRecursive(firstInd, firstLevel, secondInd, secondLevel);
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

    public boolean DeleteNodeByKey(int key) {
        Integer insertIndex = FindKeyIndex(key);

        if (!isKeyExistInTree(insertIndex)) {
            return false;
        }

        boolean useLeftChild = (2 * insertIndex + 2) >= Tree.length || Tree[2 * insertIndex + 2] == null;

        int replacementIndex = useLeftChild
                ? 2 * insertIndex + 1
                : findReplacementIndex(2 * insertIndex + 2);

        if (replacementIndex >= Tree.length || Tree[replacementIndex] == null) {
            Tree[insertIndex] = null;
            return true;
        }

        if (useLeftChild) {
            moveAllReplacementChildIfExists(replacementIndex, replacementIndex - insertIndex);
        } else {
            Tree[insertIndex] = Tree[replacementIndex];
            Tree[replacementIndex] = null;
            moveAllReplacementChildIfExists(2 * replacementIndex + 2, replacementIndex + 2);
        }

        return true;
    }

    private boolean isKeyExistInTree(Integer insertIndex) {
        return insertIndex != null && (insertIndex > 0 || (insertIndex == 0 && Tree[insertIndex] != null));
    }

    private int findReplacementIndex(int replacementIndex) {
        if (Tree[2 * replacementIndex + 1] == null) {

            return replacementIndex;
        }

        return findReplacementIndex(2 * replacementIndex + 1);
    }

    private void moveAllReplacementChildIfExists(int index, int length) {
        if (index >= Tree.length || Tree[index] == null) {
            return;
        }

        Tree[index - length] = Tree[index];
        Tree[index] = null;

        moveAllReplacementChildIfExists(2 * index + 1, length * 2);
        moveAllReplacementChildIfExists(2 * index + 2, length * 2);
    }
}
