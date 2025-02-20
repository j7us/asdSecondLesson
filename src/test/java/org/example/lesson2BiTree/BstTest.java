package org.example.lesson2BiTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BstTest {

    BSTNode<String> testRootNode = new BSTNode<>(15, "A", null);

    @BeforeEach
    void cleanRootChildren() {
        testRootNode.LeftChild = null;
        testRootNode.RightChild = null;
    }

    @Test
    void FindNodeByKeyRootTest() {
        BST<String> bst = new BST<>(testRootNode);

        BSTFind<String> res = bst.FindNodeByKey(15);

        assertThat(res.Node.NodeKey).isEqualTo(15);
        assertThat(res.NodeHasKey).isTrue();
    }

    @Test
    void FindNodeByKeyRootFalseTest() {
        BST<String> bst = new BST<>(testRootNode);

        BSTFind<String> res = bst.FindNodeByKey(17);

        assertThat(res.Node.NodeKey).isEqualTo(15);
        assertThat(res.NodeHasKey).isFalse();
        assertThat(res.ToLeft).isFalse();
    }

    @Test
    void FindNodeByKeyRootFalseToLeftTest() {
        BST<String> bst = new BST<>(testRootNode);

        BSTFind<String> res = bst.FindNodeByKey(14);

        assertThat(res.Node.NodeKey).isEqualTo(15);
        assertThat(res.NodeHasKey).isFalse();
        assertThat(res.ToLeft).isTrue();
    }

    @Test
    void FindNodeByKeyEmptyTest() {
        BST<String> bst = new BST<>(null);

        BSTFind<String> res = bst.FindNodeByKey(15);

        assertThat(res.Node).isNull();
        assertThat(res.NodeHasKey).isFalse();
    }


    @Test
    void AddKeyValueTest() {
        BST<String> bst = new BST<>(testRootNode);

        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(20, "C");

        BSTFind<String> res = bst.FindNodeByKey(20);
        BSTFind<String> res2 = bst.FindNodeByKey(10);


        assertThat(res.NodeHasKey).isTrue();
        assertThat(res.Node.NodeKey).isEqualTo(20);
        assertThat(res2.NodeHasKey).isTrue();
        assertThat(res2.Node.NodeKey).isEqualTo(10);
    }

    @Test
    void AddKeyValueBigTreeTest() {
        BST<String> bst = new BST<>(testRootNode);

        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(20, "C");
        bst.AddKeyValue(5, "B");
        bst.AddKeyValue(12, "C");
        bst.AddKeyValue(17, "B");
        bst.AddKeyValue(25, "C");

        BSTFind<String> res = bst.FindNodeByKey(25);
        BSTFind<String> res2 = bst.FindNodeByKey(5);


        assertThat(res.NodeHasKey).isTrue();
        assertThat(res.Node.NodeKey).isEqualTo(25);
        assertThat(res2.NodeHasKey).isTrue();
        assertThat(res2.Node.NodeKey).isEqualTo(5);
    }

    @Test
    void AddKeyValueEmptyTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(10, "B");

        assertThat(bst.Root.NodeKey).isEqualTo(10);
    }

    @Test
    void DeleteNodeByKeyEmptyTest() {
        BST<String> bst = new BST<>(null);

        boolean res = bst.DeleteNodeByKey(10);

        assertThat(res).isFalse();
    }

    @Test
    void DeleteNodeByKeyRootTest() {
        BST<String> bst = new BST<>(testRootNode);

        boolean res = bst.DeleteNodeByKey(15);

        assertThat(res).isTrue();
        assertThat(bst.Root).isNull();
    }

    @Test
    void DeleteNodeByKeyLeftTest() {
        BST<String> bst = new BST<>(testRootNode);

        testRootNode.LeftChild = new BSTNode<>(10, "B", testRootNode);

        boolean res = bst.DeleteNodeByKey(15);

        assertThat(res).isTrue();
        assertThat(bst.Root.LeftChild).isNull();
    }

    @Test
    void DeleteNodeByKeyRightTest() {
        BST<String> bst = new BST<>(testRootNode);

        testRootNode.LeftChild = new BSTNode<>(10, "B", testRootNode);
        testRootNode.RightChild = new BSTNode<>(20, "C", testRootNode);

        boolean res = bst.DeleteNodeByKey(15);

        assertThat(res).isTrue();
        assertThat(bst.Root.NodeKey).isEqualTo(20);
        assertThat(bst.Root.LeftChild.NodeKey).isEqualTo(10);
    }

    @Test
    void DeleteNodeByKeyWithChildTest() {
        BST<String> bst = new BST<>(testRootNode);

        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(20, "c");
        bst.AddKeyValue(30, "D");

        boolean res = bst.DeleteNodeByKey(15);

        assertThat(res).isTrue();
        assertThat(bst.Root.NodeKey).isEqualTo(20);
        assertThat(bst.Root.LeftChild.NodeKey).isEqualTo(10);
        assertThat(bst.Root.RightChild.NodeKey).isEqualTo(30);
    }

    @Test
    void DeleteNodeByKeyWithSecondLevelTest() {
        BST<String> bst = new BST<>(testRootNode);

        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(20, "c");
        bst.AddKeyValue(30, "D");
        bst.AddKeyValue(21, "D");
        bst.AddKeyValue(22, "D");
        bst.AddKeyValue(17, "D");

        boolean res = bst.DeleteNodeByKey(20);

        BSTFind<String> found = bst.FindNodeByKey(20);

        assertThat(res).isTrue();
        assertThat(found.NodeHasKey).isFalse();
        assertThat(testRootNode.RightChild.NodeKey).isEqualTo(21);
    }

    @Test
    void DeleteNodeByKeyWithThirdLevelTest() {
        BST<String> bst = new BST<>(testRootNode);

        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(20, "c");
        bst.AddKeyValue(30, "D");
        bst.AddKeyValue(25, "D");
        bst.AddKeyValue(22, "D");
        bst.AddKeyValue(23, "D");
        bst.AddKeyValue(21, "D");

        boolean res = bst.DeleteNodeByKey(20);

        BSTFind<String> found = bst.FindNodeByKey(20);

        assertThat(res).isTrue();
        assertThat(found.NodeHasKey).isFalse();
        assertThat(testRootNode.RightChild.NodeKey).isEqualTo(21);
    }

    @Test
    void DeleteNodeByKeyWithLeafTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(15, "A");
        bst.AddKeyValue(12, "A");
        bst.AddKeyValue(17, "A");
        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(14, "A");
        bst.AddKeyValue(16, "A");
        bst.AddKeyValue(23, "A");
        bst.AddKeyValue(21, "A");
        bst.AddKeyValue(27, "A");
        bst.AddKeyValue(22, "A");
        bst.AddKeyValue(19, "A");

        boolean res = bst.DeleteNodeByKey(17);

        BSTFind<String> found = bst.FindNodeByKey(17);

        assertThat(res).isTrue();
        assertThat(found.NodeHasKey).isFalse();
        assertThat(bst.Root.RightChild.NodeKey).isEqualTo(19);
        assertThat(bst.Count()).isEqualTo(10);
    }

    @Test
    void WideAllNodesTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(15, "A");
        bst.AddKeyValue(12, "A");
        bst.AddKeyValue(17, "A");
        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(13, "A");
        bst.AddKeyValue(16, "A");
        bst.AddKeyValue(14, "A");

        ArrayList<BSTNode> bstNodes = bst.WideAllNodes();

        assertThat(bstNodes.size()).isEqualTo(7);
    }

    @Test
    void DeepAllNodesRootTest() {
        BST<String> bst = new BST<>(testRootNode);

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(0);

        assertThat(bstNodes.size()).isEqualTo(1);
    }

    @Test
    void DeepAllNodesEmptyTest() {
        BST<String> bst = new BST<>(null);

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(0);

        assertThat(bstNodes.isEmpty()).isTrue();
    }

    @Test
    void DeepAllNodesTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(15, "A");
        bst.AddKeyValue(12, "A");
        bst.AddKeyValue(17, "A");
        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(13, "A");
        bst.AddKeyValue(16, "A");
        bst.AddKeyValue(14, "A");

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(0);

        assertThat(bstNodes.size()).isEqualTo(7);
        assertThat(bstNodes.get(0).NodeKey).isEqualTo(7);
        assertThat(bstNodes.get(bstNodes.size() - 1).NodeKey).isEqualTo(17);
    }

    @Test
    void DeepAllNodesPostOrderTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(15, "A");
        bst.AddKeyValue(12, "A");
        bst.AddKeyValue(17, "A");
        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(13, "A");
        bst.AddKeyValue(16, "A");
        bst.AddKeyValue(14, "A");

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(1);

        assertThat(bstNodes.size()).isEqualTo(7);
        assertThat(bstNodes.get(0).NodeKey).isEqualTo(7);
        assertThat(bstNodes.get(bstNodes.size() - 1).NodeKey).isEqualTo(15);
    }

    @Test
    void DeepAllNodesPreOrderTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(15, "A");
        bst.AddKeyValue(12, "A");
        bst.AddKeyValue(17, "A");
        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(13, "A");
        bst.AddKeyValue(16, "A");
        bst.AddKeyValue(14, "A");

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(2);

        assertThat(bstNodes.size()).isEqualTo(7);
        assertThat(bstNodes.get(0).NodeKey).isEqualTo(15);
        assertThat(bstNodes.get(bstNodes.size() - 1).NodeKey).isEqualTo(16);
    }

    @Test
    void isTreesEqualsEmptyTest() {
        BST<String> bst1 = new BST<>(null);
        BST<String> bst2 = new BST<>(null);

        boolean res = bst1.isTreesEquals(bst2);

        assertThat(res).isTrue();
    }

    @Test
    void isTreesEqualsTrueTest() {
        BST<String> bst1 = new BST<>(null);

        bst1.AddKeyValue(7, "A");
        bst1.AddKeyValue(1, "A");
        bst1.AddKeyValue(11, "A");
        bst1.AddKeyValue(5, "A");
        bst1.AddKeyValue(6, "A");
        bst1.AddKeyValue(12, "A");

        BST<String> bst2 = new BST<>(null);

        bst2.AddKeyValue(7, "A");
        bst2.AddKeyValue(1, "A");
        bst2.AddKeyValue(11, "A");
        bst2.AddKeyValue(5, "A");
        bst2.AddKeyValue(6, "A");
        bst2.AddKeyValue(12, "A");

        boolean res = bst1.isTreesEquals(bst2);

        assertThat(res).isTrue();
    }

    @Test
    void isTreesEqualsFalseTest() {
        BST<String> bst1 = new BST<>(null);

        bst1.AddKeyValue(7, "A");
        bst1.AddKeyValue(1, "A");
        bst1.AddKeyValue(11, "A");
        bst1.AddKeyValue(5, "A");
        bst1.AddKeyValue(6, "A");
        bst1.AddKeyValue(12, "A");

        BST<String> bst2 = new BST<>(null);

        bst2.AddKeyValue(7, "A");
        bst2.AddKeyValue(1, "A");
        bst2.AddKeyValue(14, "A");
        bst2.AddKeyValue(5, "A");
        bst2.AddKeyValue(6, "A");
        bst2.AddKeyValue(12, "A");

        boolean res = bst1.isTreesEquals(bst2);

        assertThat(res).isFalse();
    }

    @Test
    void findAllPathWithLengthEmptyTest() {
        BST<String> bst = new BST<>(null);

        List<List<BSTNode<String>>> res = bst.findAllPathWithLength(3);

        assertThat(res.isEmpty()).isTrue();
    }

    @Test
    void findAllPathWithLengthRootTest() {
        BST<String> bst = new BST<>(testRootNode);

        List<List<BSTNode<String>>> res = bst.findAllPathWithLength(3);

        assertThat(res.isEmpty()).isTrue();
    }

    @Test
    void findAllPathWithLengthTest() {
        BST<String> bst = new BST<>(null);

        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(1, "A");
        bst.AddKeyValue(-5, "A");
        bst.AddKeyValue(11, "A");
        bst.AddKeyValue(5, "A");
        bst.AddKeyValue(6, "A");
        bst.AddKeyValue(12, "A");

        List<List<BSTNode<String>>> res = bst.findAllPathWithLength(3);

        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    void findAllPathWithMaxValueEmptyTest() {
        BST<Integer> bst = new BST<>(null);

        List<List<BSTNode<Integer>>> res = bst.findAllPathWithMaxValue();

        assertThat(res.isEmpty()).isTrue();
    }

    @Test
    void findAllPathWithMaxValueRootTest() {
        BST<Integer> bst = new BST<>(new BSTNode<>(15,15, null));

        List<List<BSTNode<Integer>>> res = bst.findAllPathWithMaxValue();

        assertThat(res.size()).isEqualTo(1);
    }

    @Test
    void findAllPathWithMaxValueTest() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(7, 1);
        bst.AddKeyValue(1, 1);
        bst.AddKeyValue(-5, 2);
        bst.AddKeyValue(11, 15);
        bst.AddKeyValue(5, 1);
        bst.AddKeyValue(6, 17);
        bst.AddKeyValue(12, 4);

        List<List<BSTNode<Integer>>> res = bst.findAllPathWithMaxValue();

        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    void invertTreeEmptyTest() {
        BST<Integer> bst = new BST<>(null);

        bst.invertTree();
    }

    @Test
    void invertTreeRootTest() {
        BST<String> bst = new BST<>(testRootNode);

        bst.invertTree();
    }

    @Test
    void invertTreeTest() {
        BST<String> bst = new BST<>(testRootNode);
        bst.AddKeyValue(10, "A");
        bst.AddKeyValue(5, "A");
        bst.AddKeyValue(12, "A");
        bst.AddKeyValue(1, "A");
        bst.AddKeyValue(7, "A");
        bst.AddKeyValue(11, "A");
        bst.AddKeyValue(14, "A");
        bst.AddKeyValue(20, "A");
        bst.AddKeyValue(17, "A");
        bst.AddKeyValue(25, "A");
        bst.AddKeyValue(16, "A");
        bst.AddKeyValue(19, "A");

        bst.invertTree();

        assertThat(bst.Root.RightChild.NodeKey).isEqualTo(10);
    }

    @Test
    void findLevelWithMaxSumValueEmptyTest() {
        BST<String> bst = new BST<>(null);

        int res = bst.findLevelWithMaxSumValue();

        assertThat(res).isEqualTo(-1);
    }

    @Test
    void findLevelWithMaxSumValueRootTest() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(15, 10);

        int res = bst.findLevelWithMaxSumValue();

        assertThat(res).isEqualTo(0);
    }

    @Test
    void findLevelWithMaxSumValueTest() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(15, 10);
        bst.AddKeyValue(10, 100);
        bst.AddKeyValue(20, 10);
        bst.AddKeyValue(5, 10);
        bst.AddKeyValue(12, 10);
        bst.AddKeyValue(25, 10);

        int res = bst.findLevelWithMaxSumValue();

        assertThat(res).isEqualTo(1);
    }

    @Test
    void buildTreeFromOrderTest() {
        BSTNode<String> n1 = new BSTNode<>(1, "A", null);
        BSTNode<String> n2 = new BSTNode<>(2, "A", null);
        BSTNode<String> n3 = new BSTNode<>(3, "A", null);
        BSTNode<String> n4 = new BSTNode<>(4, "A", null);
        BSTNode<String> n5 = new BSTNode<>(5, "A", null);
        BSTNode<String> n6 = new BSTNode<>(6, "A", null);
        BSTNode<String> n7 = new BSTNode<>(7, "A", null);

        ArrayList pre = new ArrayList<>(Arrays.asList(n1, n2, n4, n5, n3, n6, n7));

        ArrayList inf = new ArrayList<>(Arrays.asList(n4, n2, n5, n1, n6, n3, n7));

        BST<String> bst = BST.buildTreeFromOrder(pre, inf);

        assertThat(bst.Root.NodeKey).isEqualTo(1);
    }
}
