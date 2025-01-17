package org.example.lesson6ArrayTiBiTreeObject;

import java.util.*;

class BSTNode
{
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent)
    {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST
{
    public BSTNode Root; // корень дерева

    public BalancedBST()
    {
        Root = null;
    }

    public void GenerateTree(int[] a)
    {
        Arrays.sort(a);

        Root = generateTreeRecursive(null, a, 0, a.length - 1, 0);
    }

    private BSTNode generateTreeRecursive(BSTNode parent, int[] a, int startIndex, int endIndex, int level) {
        if (startIndex > endIndex) {
            return null;
        }

        int mid = endIndex - startIndex == 1
                ? getIndexFromSide(startIndex, endIndex)
                : startIndex + (endIndex - startIndex) / 2;

        BSTNode node = new BSTNode(a[mid], parent);
        node.Level = level;

        node.LeftChild = generateTreeRecursive(node, a, startIndex, mid - 1, level + 1);
        node.RightChild = generateTreeRecursive(node, a, mid + 1, endIndex, level + 1);

        return node;
    }

    private static int getIndexFromSide(int startIndex, int endIndex) {
        return startIndex == 0 ? endIndex : startIndex;
    }

    public boolean validateTree() {
        return validateTreeRecursive(Root);
    }

    public boolean validateTreeRecursive(BSTNode node) {
        if (node == null || (node.LeftChild == null && node.RightChild == null)) {
            return true;
        }

        if (!isRightRelationWithChild(node)) {
            return false;
        }

        return validateTreeRecursive(node.LeftChild) && validateTreeRecursive(node.RightChild);
    }

    private boolean isRightRelationWithChild(BSTNode node) {
        if (node.LeftChild != null && node.NodeKey <= node.LeftChild.NodeKey) {
            return false;
        }

        if (node.RightChild != null && node.NodeKey > node.RightChild.NodeKey) {
            return false;
        }

        return true;
    }

    public boolean IsBalanced(BSTNode root_node)
    {
        if (Root == null) {
            return true;
        }

        int leftLevelMax = isBalancedRecursive(root_node.LeftChild, root_node.Level);
        int rightLevelMax = isBalancedRecursive(root_node.RightChild, root_node.Level);

        return leftLevelMax >= 0 && rightLevelMax >= 0 && Math.abs(rightLevelMax - leftLevelMax) <= 1;
    }

    private int isBalancedRecursive(BSTNode root_node, int level) {
        if (root_node == null) {
            return level;
        }

        int leftLevelMax = isBalancedRecursive(root_node.LeftChild, root_node.Level);
        int rightLevelMax = isBalancedRecursive(root_node.RightChild, root_node.Level);

        if (leftLevelMax >= 0 && rightLevelMax >= 0 && Math.abs(rightLevelMax - leftLevelMax) <= 1) {
            return Math.max(rightLevelMax, leftLevelMax);
        }

        return -1;
    }
}
