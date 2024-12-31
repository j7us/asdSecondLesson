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

        if (delete.LeftChild == replacement) {
            return;
        }

        replacement.LeftChild = delete.LeftChild;
        replacement.RightChild = delete.RightChild;

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
        BSTNode<T> nodeParent = node.Parent;

        if (nodeChild != null) {
            nodeChild.Parent = nodeParent;
        }

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

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> result = new ArrayList<>();
        Queue<BSTNode> nodes = new ArrayDeque<>();
        if (Root == null) {
            return result;
        }

        nodes.add(Root);

        for (BSTNode node = nodes.poll(); node != null; node = nodes.poll()) {
            if (node.LeftChild != null) {
                nodes.add(node.LeftChild);
            }

            if (node.RightChild != null) {
                nodes.add(node.RightChild);
            }

            result.add(node);
        }

        return result;
    }

    public ArrayList<BSTNode> DeepAllNodes(int type) {
        ArrayList<BSTNode> result = new ArrayList<>();

        if (Root == null)
            type = -1;

        return switch (type) {
            case 0 -> deepAllNodesInOrder(result, Root);
            case 1 -> deepAllNodesPostOrder(result, Root);
            case 2 -> deepAllNodesPreOrder(result, Root);
            default -> result;
        };
    }

    private ArrayList<BSTNode> deepAllNodesInOrder(ArrayList<BSTNode> result, BSTNode node) {
        if (node == null) {
            return result;
        }

        deepAllNodesInOrder(result, node.LeftChild);
        result.add(node);
        deepAllNodesInOrder(result, node.RightChild);

        return result;
    }

    private ArrayList<BSTNode> deepAllNodesPostOrder(ArrayList<BSTNode> result, BSTNode node) {
        if (node == null) {
            return result;
        }

        deepAllNodesPostOrder(result, node.LeftChild);
        deepAllNodesPostOrder(result, node.RightChild);
        result.add(node);

        return result;
    }

    private ArrayList<BSTNode> deepAllNodesPreOrder(ArrayList<BSTNode> result, BSTNode node) {
        if (node == null) {
            return result;
        }

        result.add(node);
        deepAllNodesPreOrder(result, node.LeftChild);
        deepAllNodesPreOrder(result, node.RightChild);

        return result;
    }

    public boolean isTreesEquals(BST<T> comparedTree) {
        if (Root == null && comparedTree.Root == null) {
            return  true;
        }

        ArrayDeque<BSTNode<T>> firstTreeNodes = new ArrayDeque<>();
        firstTreeNodes.add(Root);

        ArrayDeque<BSTNode<T>> secondTreeNodes = new ArrayDeque<>();
        secondTreeNodes.add(comparedTree.Root);

        return isTreesEqualsRecursive(firstTreeNodes, secondTreeNodes);
    }

    private boolean isTreesEqualsRecursive(ArrayDeque<BSTNode<T>> firstTreeNodes, ArrayDeque<BSTNode<T>> secondTreeNodes) {
        BSTNode<T> first = firstTreeNodes.poll();
        BSTNode<T> second = secondTreeNodes.poll();

        if (first == null && second == null) {
            return true;
        }

        if (!isNodesEquals(first, second)) {
            return false;
        }

        addedChildToQueue(firstTreeNodes, first);
        addedChildToQueue(secondTreeNodes, second);

        return isTreesEqualsRecursive(firstTreeNodes, secondTreeNodes);
    }

    private void addedChildToQueue(ArrayDeque<BSTNode<T>> nodesQueue, BSTNode<T> node) {
        if (node.LeftChild != null) {
            nodesQueue.add(node.LeftChild);
        }

        if (node.RightChild != null) {
            nodesQueue.add(node.RightChild);
        }
    }

    private boolean isNodesEquals(BSTNode<T> first, BSTNode<T> second) {
        if (first == null || second == null) {
            return false;
        }

        return first.NodeKey == second.NodeKey && Objects.equals(first.NodeValue, second.NodeValue);
    }

    public List<List<BSTNode<T>>> findAllPathWithLength(int length) {

        List<List<BSTNode<T>>> paths = new ArrayList<>();

        if (Root != null) {
            findAllPathWithLengthRecursive(Root, new LinkedList<>(), paths, length);
        }

        return paths;
    }

    private void findAllPathWithLengthRecursive(BSTNode<T> node,
                                                List<BSTNode<T>> path,
                                                List<List<BSTNode<T>>> paths,
                                                int length) {
         if (length == 0) {
             return;
         }

         path.add(node);

         if (node.LeftChild == null && node.RightChild == null) {
             if (length == 1) {
                 paths.add(path);
             }
             return;
         }

         if (node.LeftChild != null) {
             findAllPathWithLengthRecursive(node.LeftChild, new LinkedList<>(path), paths, length - 1);
         }

        if (node.RightChild != null) {
            findAllPathWithLengthRecursive(node.RightChild, new LinkedList<>(path), paths, length - 1);
        }
    }

    public List<List<BSTNode<T>>> findAllPathWithMaxValue() {
        TreeMap<Integer, List<List<BSTNode<T>>>> pathsWithMaxValue = new TreeMap<>();

        if (Root != null) {
            findAllPathWithMaxValueRecursive(Root, new LinkedList<>(), 0, pathsWithMaxValue);
        }

        Map.Entry<Integer, List<List<BSTNode<T>>>> result = pathsWithMaxValue.lastEntry();

        return result == null ? new ArrayList<>() : result.getValue();
    }

    private void findAllPathWithMaxValueRecursive(BSTNode<T> node,
                                                  List<BSTNode<T>> path,
                                                  int sumValue,
                                                  TreeMap<Integer, List<List<BSTNode<T>>>> pathsWithMaxValue) {
        path.add(node);

        if (node.LeftChild == null && node.RightChild == null) {
            int key = sumValue + (Integer)node.NodeValue;
            List<List<BSTNode<T>>> pathsWithValue = pathsWithMaxValue.get(key);

            if (pathsWithValue == null) {
                List<List<BSTNode<T>>> paths = new ArrayList<>();
                paths.add(path);
                pathsWithMaxValue.put(key, paths);
            } else {
                pathsWithValue.add(path);
                pathsWithMaxValue.put(key, pathsWithValue);
            }

            return;
        }

        if (node.LeftChild != null) {
            findAllPathWithMaxValueRecursive(
                    node.LeftChild,
                    new ArrayList<>(path),
                    sumValue + (Integer)node.NodeValue,
                    pathsWithMaxValue);
        }

        if (node.RightChild != null) {
            findAllPathWithMaxValueRecursive(
                    node.RightChild,
                    new ArrayList<>(path),
                    sumValue + (Integer)node.NodeValue,
                    pathsWithMaxValue);
        }
    }
}
