package org.example.lesson1;

import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
    public int level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if (Root == null) {
            return;
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        if (Root == null)
            return;

        SimpleTreeNode<T> parent = NodeToDelete.Parent;

        parent.Children.remove(NodeToDelete);
    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        List<SimpleTreeNode<T>> resultList = new ArrayList<>();

        getAllNodesRecursiveToResultList(new LinkedList<>(List.of(Root)), resultList);

        return resultList;
    }

    private void getAllNodesRecursiveToResultList(List<SimpleTreeNode<T>> nodes,
                                                  List<SimpleTreeNode<T>> resultList) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        for (SimpleTreeNode<T> node : nodes) {
            resultList.add(node);
            getAllNodesRecursiveToResultList(node.Children, resultList);
        }
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        List<SimpleTreeNode<T>> resultList = new ArrayList<>();

        findNodesByValueRecursive(new LinkedList<>(List.of(Root)), resultList, val);

        return resultList;
    }

    private void findNodesByValueRecursive(List<SimpleTreeNode<T>> nodes,
                                           List<SimpleTreeNode<T>> resultList,
                                           T val) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        for (SimpleTreeNode<T> node : nodes) {
            if (node.NodeValue.equals(val)) {
                resultList.add(node);
            }

            findNodesByValueRecursive(node.Children, resultList, val);
        }
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        if (Root == null) {
            return;
        }

        SimpleTreeNode<T> originParent = OriginalNode.Parent;
        originParent.Children.remove(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count()
    {
        return countRecursive(new LinkedList<>(List.of(Root)));
    }

    private int countRecursive(List<SimpleTreeNode<T>> nodes) {
        int count = 0;
        for (SimpleTreeNode<T> node : nodes) {
            if (node.Children == null || node.Children.isEmpty()) {
                count++;
            } else {
                count = count + 1 + countRecursive(node.Children);
            }
        }

        return count;
    }

    public int LeafCount()
    {
        return leafCountRecursive(new LinkedList<>(List.of(Root)));
    }

    private int leafCountRecursive(List<SimpleTreeNode<T>> nodes) {
        int count = 0;
        for (SimpleTreeNode<T> node : nodes) {
            if (node.Children == null || node.Children.isEmpty()) {
                count++;
            } else {
                count += leafCountRecursive(node.Children);
            }
        }

        return count;
    }

    //Задание 1
    public void addedLevelToAllNode() {
        addedLevelToAllNodeRecursive(new LinkedList<>(List.of(Root)), 1);
    }

    private void addedLevelToAllNodeRecursive(List<SimpleTreeNode<T>> nodes, int level) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        for (SimpleTreeNode<T> node : nodes) {
            node.level = level;
            addedLevelToAllNodeRecursive(node.Children, level + 1);
        }
    }

    // Задание 2
    public void addChildWithLevel(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if (Root == null) {
            return;
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
        NewChild.level = ParentNode.level + 1;
    }
}