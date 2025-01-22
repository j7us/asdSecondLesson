package org.example.lesson1Tree;

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

    // Задание 3
    public boolean isTreeSymmetrical() {
        if (Root == null || Root.Children.isEmpty()) {
            return true;
        } else if (Root.Children.size() % 2 != 0) {
            return false;
        }

        return isTreeSymmetricalRecursive(Root.Children);
    }

    private boolean isTreeSymmetricalRecursive(List<SimpleTreeNode<T>> nodes) {
        List<SimpleTreeNode<T>> childNodes = new ArrayList<>();

        for (int i = 0, x = nodes.size() - 1; i < nodes.size()/2; i++, x--) {
            if (nodes.get(i).Children == null && nodes.get(i).Children == nodes.get(x).Children) {
                continue;
            }

            if (nodes.get(i).Children.size() != nodes.get(x).Children.size()) {
                return false;
            }
            childNodes.addAll(nodes.get(i).Children);
            childNodes.addAll(nodes.get(x).Children);
        }

        if (childNodes.isEmpty()) {
            return true;
        }

        return isTreeSymmetricalRecursive(childNodes);
    }

    public ArrayList<T> EvenTrees() {
        ArrayList<T> removedEdge = new ArrayList<>();

        if (Root == null) {
            return removedEdge;
        }

        return EvenTreesRecursive(removedEdge, Root) % 2 == 0
                ? removedEdge
                : new ArrayList<>();
    }

    private int EvenTreesRecursive(ArrayList<T> resultList, SimpleTreeNode<T> node) {
        if (node.Children == null) {
            return 1;
        }

        int resultTreeNodesCount = 1;

        for (SimpleTreeNode<T> children : node.Children) {
            int subtreeNodesCount = EvenTreesRecursive(resultList, children);

            if (subtreeNodesCount % 2 == 0) {
                resultList.add(node.NodeValue);
                resultList.add(children.NodeValue);
            } else {
                resultTreeNodesCount += subtreeNodesCount;
            }
        }

        return resultTreeNodesCount;
    }
}
