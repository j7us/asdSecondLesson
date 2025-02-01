package org.example.lesson1Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    public SimpleTreeNode<Integer> defaultRootNode =  new SimpleTreeNode<>(1, null);
    public SimpleTree<Integer> tree = new SimpleTree<>(defaultRootNode);

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

    @Test
    void EvenTreesEmptyTest() {
        SimpleTree<Integer> emptyTree = new SimpleTree<>(null);

        ArrayList<Integer> res = emptyTree.EvenTrees();

        assertThat(res).isEmpty();
    }

    @Test
    void EvenTreesRootTest() {
        ArrayList<Integer> res = tree.EvenTrees();

        assertThat(res).isEmpty();
    }

    @Test
    void EvenTreesSuccessTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, secondNode);
        tree.AddChild(node, leafNode);

        ArrayList<Integer> res = tree.EvenTrees();

        assertThat(res).isEqualTo(new ArrayList<>(Arrays.asList(1, 2)));
    }

    @Test
    void EvenTreesFalseTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> leafNode2 = new SimpleTreeNode<>(5, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, secondNode);
        tree.AddChild(node, leafNode);
        tree.AddChild(leafNode, leafNode2);

        ArrayList<Integer> res = tree.EvenTrees();

        assertThat(res).isEmpty();
    }

    @Test
    void EvenTreesBigTreeTest() {
        SimpleTreeNode<Integer> n2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> n3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> n4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> n5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> n6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> n7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> n8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> n9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> n10 = new SimpleTreeNode<>(10, null);

        tree.AddChild(defaultRootNode, n2);
        tree.AddChild(defaultRootNode, n3);
        tree.AddChild(defaultRootNode, n4);
        tree.AddChild(n2, n5);
        tree.AddChild(n3, n6);
        tree.AddChild(n3, n7);
        tree.AddChild(n4, n8);
        tree.AddChild(n6, n9);
        tree.AddChild(n8, n10);

        ArrayList<Integer> res = tree.EvenTrees();

        assertThat(res).isEqualTo(new ArrayList<>(Arrays.asList(1,2,3,6,1,3,4,8)));
    }

    @Test
    void countEvenTreesEmptyTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> leafNode2 = new SimpleTreeNode<>(5, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, secondNode);
        tree.AddChild(node, leafNode);
        tree.AddChild(leafNode, leafNode2);

        int res = tree.countEvenTrees(tree.Root);

        assertThat(res).isEqualTo(0);
    }

    @Test
    void countEvenTreesTwoTest() {
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> leafNode = new SimpleTreeNode<>(3, null);

        tree.AddChild(defaultRootNode, node);
        tree.AddChild(defaultRootNode, secondNode);
        tree.AddChild(node, leafNode);

        int res = tree.countEvenTrees(tree.Root);

        assertThat(res).isEqualTo(2);
    }

    @Test
    void countEvenTreesTest() {
        SimpleTreeNode<Integer> n2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> n3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> n4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> n5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> n6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> n7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> n8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> n9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> n10 = new SimpleTreeNode<>(10, null);

        tree.AddChild(defaultRootNode, n2);
        tree.AddChild(defaultRootNode, n3);
        tree.AddChild(defaultRootNode, n4);
        tree.AddChild(n2, n5);
        tree.AddChild(n3, n6);
        tree.AddChild(n3, n7);
        tree.AddChild(n4, n8);
        tree.AddChild(n6, n9);
        tree.AddChild(n8, n10);

        int res = tree.countEvenTrees(tree.Root);

        assertThat(res).isEqualTo(5);
    }
}
