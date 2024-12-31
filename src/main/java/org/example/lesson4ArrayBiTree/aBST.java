package org.example.lesson4ArrayBiTree;

import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = depth * 2 + 1;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key)
    {
        if (Tree[0] == null) {
            return 0;
        }

        return findKeyIndexRecursive(key, 0);
    }

    private Integer findKeyIndexRecursive(int key, int index) {
        if (index > (Tree.length - 1)) {
            return null;
        }

        Integer node = Tree[index];

        if (node == null) {
            return index * (-1);
        }

        if (node == key) {
            return index;
        } else {
            int nextIndex = node > key
                    ? 2 * index + 1
                    : 2 * index + 2;
            return findKeyIndexRecursive(key, nextIndex);
        }
    }

    public int AddKey(int key)
    {
        Integer insertIndex = FindKeyIndex(key);

        if (insertIndex == null) {
            return -1;
        }

        if (!(insertIndex > 0)) {
            Tree[insertIndex * (-1)] = key;
        }

        return insertIndex;
    }
}
