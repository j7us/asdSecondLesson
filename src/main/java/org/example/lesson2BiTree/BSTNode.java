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
        return Root == null
                ? buildBSTFind(null, false, false)
                : findNodeByKeyRecursive(Root, key);
    }

    private BSTFind<T> findNodeByKeyRecursive(BSTNode<T> node, int key) {
        BSTNode<T> nextNode;
        boolean toLeft = false;
        boolean hasKey = false;

        if (node.NodeKey > key) {
            nextNode = checkNodeChildAndReturnNext(node, true);
            toLeft = true;
        } else if (node.NodeKey < key) {
            nextNode = checkNodeChildAndReturnNext(node, false);
        } else {
            nextNode = node;
            hasKey = true;
        }

        return nextNode == node
                ? buildBSTFind(node, hasKey, toLeft)
                : findNodeByKeyRecursive(nextNode, key);
    }

    private BSTNode<T> checkNodeChildAndReturnNext(BSTNode<T> node, boolean findLeft) {
        BSTNode<T> nextNode = findLeft
                ? node.LeftChild
                : node.RightChild;

        return nextNode == null ? node : nextNode;
    }

    private BSTFind<T> buildBSTFind(BSTNode<T> node, boolean nodeHasKey, boolean toLeft) {
        BSTFind<T> find = new BSTFind<>();
        find.Node = node;
        find.NodeHasKey = nodeHasKey;
        find.ToLeft = toLeft;

        return find;
    }

    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> treeNodeWithCurrentKey = FindNodeByKey(key);

        if (treeNodeWithCurrentKey.NodeHasKey) {
            return false;
        }

        BSTNode<T> nodeToInsert = new BSTNode<>(key, val, treeNodeWithCurrentKey.Node);

        if (nodeToInsert.Parent == null) {
            Root = nodeToInsert;
        } else if (treeNodeWithCurrentKey.ToLeft) {
            treeNodeWithCurrentKey.Node.LeftChild = nodeToInsert;
        } else {
            treeNodeWithCurrentKey.Node.RightChild = nodeToInsert;
        }

        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if (Root == null) {
            return null;
        } else if (Root.LeftChild == null && Root.RightChild == null) {
            return Root;
        }

        return findMinMaxRecursive(FromNode, FindMax);
    }

    private BSTNode<T> findMinMaxRecursive(BSTNode<T> fromNode, boolean findMax) {
        BSTNode<T> nextNode = findMax ? fromNode.RightChild : fromNode.LeftChild;

        return nextNode == null ? fromNode : findMinMaxRecursive(nextNode, findMax);
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> nodeInTreeWithKey = FindNodeByKey(key);

        if (!nodeInTreeWithKey.NodeHasKey) {
            return false;
        }

        BSTNode<T> nodeToDelete = nodeInTreeWithKey.Node;

        BSTNode<T> replacementNode = nodeToDelete.RightChild == null
                ? nodeToDelete.LeftChild
                : findReplacementNodeFromSubTree(nodeToDelete.RightChild);

        BSTNode<T> deletedNodeParent = nodeToDelete.Parent;

        if (deletedNodeParent.LeftChild == nodeToDelete) {
            deletedNodeParent.LeftChild = replacementNode;
        } else {
            deletedNodeParent.RightChild = replacementNode;
        }

        replacementNode.Parent = deletedNodeParent;

        if (replacementNode == nodeToDelete.LeftChild) {
            replacementNode.LeftChild = null;
            replacementNode.RightChild = nodeToDelete.RightChild;
        } else if (replacementNode == nodeToDelete.RightChild) {
            replacementNode.RightChild = null;
            replacementNode.LeftChild = nodeToDelete.LeftChild;
        } else {
            replacementNode.LeftChild = nodeToDelete.LeftChild;
            replacementNode.RightChild = nodeToDelete.RightChild;
        }

        return true;
    }

    private BSTNode<T> findReplacementNodeFromSubTree(BSTNode<T> node) {
        if (node.LeftChild == null) {
            replaceLeftNodeByRightChildIfExists(node);
            return node;
        }

        return findReplacementNodeFromSubTree(node.LeftChild);
    }

    private void replaceLeftNodeByRightChildIfExists(BSTNode<T> node) {
        if (node.RightChild == null) {
            return;
        }

        BSTNode<T> replacementNode = node.RightChild;
        node.Parent.LeftChild = replacementNode;
        replacementNode.Parent = node.Parent;
    }

    public int Count()
    {
        if (Root == null) {
            return 0;
        }

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
