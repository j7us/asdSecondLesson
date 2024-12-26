package org.example.lesson2BiTree;

import java.io.*;
import java.util.*;


class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() { Node = null; }
}

class BST<T>
{
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        if (Root == null) {
           return new BSTFind<>();
        }

       return findNodeByKeyRecursive(Root, key);
    }

    private BSTFind<T> findNodeByKeyRecursive(BSTNode<T> node, int key) {
        if (node.NodeKey == key || isLastNodeInRecursive(node, key)) {
            return buildBstFind(node, node.NodeKey == key, node.NodeKey > key);
        }

        return node.NodeKey > key
                ? findNodeByKeyRecursive(node.LeftChild, key)
                : findNodeByKeyRecursive(node.RightChild, key);
    }

    private boolean isLastNodeInRecursive(BSTNode<T> node, int key) {
        return (node.NodeKey > key && node.LeftChild == null) || (node.NodeKey < key && node.RightChild == null);
    }

    private BSTFind<T> buildBstFind(BSTNode<T> node, boolean NodeHasKey, boolean ToLeft) {
        BSTFind<T> res = new BSTFind<>();
        res.Node = node;
        res.NodeHasKey = NodeHasKey;
        res.ToLeft = ToLeft;
        return res;
    }

    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> foundNodeByKey = FindNodeByKey(key);

        if (foundNodeByKey.NodeHasKey) {
            return false;
        }

        BSTNode<T> futureParent = foundNodeByKey.Node;
        BSTNode<T> insertNode = new BSTNode<>(key, val, futureParent);

        if (futureParent == null) {
            Root = insertNode;
            return true;
        }

        if (foundNodeByKey.ToLeft) {
            futureParent.LeftChild = insertNode;
        } else {
            futureParent.RightChild = insertNode;
        }

        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if (Root == null) {
            return null;
        }

        return finMinMaxRecursive(FromNode, FindMax);
    }

    private BSTNode<T> finMinMaxRecursive(BSTNode<T> fromNode, boolean findMax) {
        if ((findMax && fromNode.RightChild == null)
                || (!findMax && fromNode.LeftChild == null)) {
            return fromNode;
        }

        return finMinMaxRecursive(findMax ? fromNode.RightChild : fromNode.LeftChild, findMax);
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> foundWithKey = FindNodeByKey(key);

        if (!foundWithKey.NodeHasKey) {
            return false;
        }

        BSTNode<T> delete = foundWithKey.Node;

        BSTNode<T> replacement = delete.RightChild == null
                ? delete.LeftChild
                : findDeleteReplacement(delete.RightChild);

        replaceDeletedNode(delete, replacement);

        if (Root == delete) {
            Root = replacement;
        }

        return true;
    }

    private void replaceDeletedNode(BSTNode<T> delete, BSTNode<T> replacement) {
        BSTNode<T> parent = delete.Parent;

        if (parent != null) {
            replaceParentChild(parent, delete, replacement);
        }

        if (replacement == null)
            return;

        replacement.Parent = parent;

        replacement.LeftChild = delete.LeftChild == replacement ? null : delete.LeftChild;
        replacement.RightChild = delete.RightChild == replacement ? null : delete.RightChild;

        if (replacement.LeftChild != null) {
            replacement.LeftChild.Parent = replacement;
        }

        if (replacement.RightChild != null) {
            replacement.RightChild.Parent = replacement;
        }
    }

    private BSTNode<T> findDeleteReplacement(BSTNode<T> node) {
        if (node.LeftChild == null) {
            swapNodeWithChildIfExists(node, node.RightChild);
            return node;
        }

        return findDeleteReplacement(node.LeftChild);
    }

    private void swapNodeWithChildIfExists(BSTNode<T> node, BSTNode<T> nodeChild) {
        if (nodeChild == null) {
            return;
        }

        BSTNode<T> nodeParent = node.Parent;

        nodeChild.Parent = nodeParent;

        replaceParentChild(nodeParent, node, nodeChild);
    }

    private void replaceParentChild(BSTNode<T> parent, BSTNode<T> replaceable, BSTNode<T> replacement) {
        if (parent.RightChild == replaceable) {
            parent.RightChild = replacement;
        } else {
            parent.LeftChild = replacement;
        }
    }

    public int Count()
    {
        return countRecursive(Root);
    }

    private int countRecursive(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }

        return 1 + countRecursive(node.LeftChild) + countRecursive(node.RightChild);
    }

    public boolean isEqualToAnotherTree(BST<T> anotherTree) {
        return isEqualToAnotherTreeRecursive(Root, anotherTree.Root);
    }

    private boolean isEqualToAnotherTreeRecursive(BSTNode<T> firstNode, BSTNode<T> secondNode) {
        if (firstNode == null && secondNode == null) {
            return true;
        }

        if (!isNodesEquals(firstNode, secondNode)) {
            return false;
        }

        return isEqualToAnotherTreeRecursive(firstNode.LeftChild, secondNode.LeftChild)
                && isEqualToAnotherTreeRecursive(firstNode.RightChild, secondNode.RightChild);
    }

    private boolean isNodesEquals(BSTNode<T> firstNode, BSTNode<T> secondNode) {
        if ((firstNode == null && secondNode != null) || (firstNode != null && secondNode == null)) {
            return false;
        }

        if (firstNode == null && secondNode == null) {
            return true;
        }

        return firstNode.NodeKey == secondNode.NodeKey && Objects.equals(firstNode.NodeValue, secondNode.NodeValue);
    }
}
