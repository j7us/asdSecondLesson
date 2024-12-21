package org.example.lesson1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    public SimpleTreeNode<Integer> defaultRootNode =  new SimpleTreeNode<>(1, null);
    public SimpleTree<Integer> tree = new SimpleTree<>(defaultRootNode);;
    public SimpleTree<Integer> emptyRootTree = new SimpleTree<>(null);;

    @BeforeEach
    void setTree() {
        defaultRootNode.Children = null;
    }

    @Test
    void addChildTest() {
        tree.AddChild(defaultRootNode, new SimpleTreeNode<>(2, null));

        List<SimpleTreeNode<Integer>> simpleTreeNodes = tree.FindNodesByValue(2);

        assertThat(simpleTreeNodes.size()).isEqualTo(1);
    }

    @Test
    void deleteNodeTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(node, leafNode);

        tree.DeleteNode(node);

        assertThat(tree.Count()).isEqualTo(1);
    }

    @Test
    void GetAllNodesTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, secondNode);
        tree.AddChild(node, leafNode);

        List<SimpleTreeNode<Integer>> resultNodesList = tree.GetAllNodes();

        assertThat(resultNodesList.size()).isEqualTo(4);
    }

    @Test
    void FindNodesByValueTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(2, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, secondNode);
        tree.AddChild(node, leafNode);

        List<SimpleTreeNode<Integer>> resultList = tree.FindNodesByValue(2);

        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    void MoveNodeTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(node, secondNode);
        tree.AddChild(secondNode, leafNode);

        tree.MoveNode(secondNode, defaultRootNode);

        assertThat(defaultRootNode.Children.contains(secondNode)).isTrue();
    }

    @Test
    void CountTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(node, secondNode);
        tree.AddChild(secondNode, leafNode);
        tree.MoveNode(secondNode, defaultRootNode);

        int resultCount = tree.Count();

        assertThat(resultCount).isEqualTo(4);
    }

    @Test
    void leafCountTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(node, secondNode);
        tree.AddChild(secondNode, leafNode);
        tree.MoveNode(secondNode, defaultRootNode);

        int result = tree.LeafCount();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void addedLevelToAllNodeTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(node, secondNode);
        tree.AddChild(secondNode, leafNode);
        tree.MoveNode(secondNode, defaultRootNode);

        tree.addedLevelToAllNode();

        assertThat(leafNode.level).isEqualTo(3);
    }

    @Test
    void isTreeSymmetricalTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(9, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, node2);
        tree.AddChild(node, node3);
        tree.AddChild(node, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node2, node6);
        tree.AddChild(node5, node8);
        tree.AddChild(node4, node7);

        boolean result = tree.isTreeSymmetrical();

        assertThat(result).isTrue();
    }
}
