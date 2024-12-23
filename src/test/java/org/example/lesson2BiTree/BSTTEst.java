package org.example.lesson2BiTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BSTTEst {

    BSTNode<String> testRootNode = new BSTNode<>(15, "A", null);

    @BeforeEach
    void cleanRootChildren() {
        testRootNode.LeftChild = null;
        testRootNode.RightChild = null;
    }

    @Test
    void FindNodeByKeyLeftTest() {
        BSTNode<String> leftTestNode = new BSTNode<>(10, "B", testRootNode);
        BSTNode<String> rightTestNode = new BSTNode<>(20, "C", testRootNode);

        testRootNode.LeftChild = leftTestNode;
        testRootNode.RightChild = rightTestNode;

        BST<String> bst = new BST<>(testRootNode);

        BSTFind<String> resultNode = bst.FindNodeByKey(5);

        assertThat(resultNode.Node).isEqualTo(leftTestNode);
    }

    @Test
    void FindNodeByKeyRightTest() {
        BSTNode<String> leftTestNode = new BSTNode<>(10, "B", testRootNode);
        BSTNode<String> rightTestNode = new BSTNode<>(20, "C", testRootNode);

        testRootNode.LeftChild = leftTestNode;
        testRootNode.RightChild = rightTestNode;

        BST<String> bst = new BST<>(testRootNode);

        BSTFind<String> resultNode = bst.FindNodeByKey(20);

        assertThat(resultNode.Node).isEqualTo(rightTestNode);
    }

    @Test
    void FindNodeByKeyWithoytRootTest() {
        BST<String> bst = new BST<>(null);

        BSTFind<String> resultNode = bst.FindNodeByKey(20);

        assertThat(resultNode.Node).isNull();
    }

    @Test
    void AddKeyValueTest() {
        BSTNode<String> leftTestNode = new BSTNode<>(10, "B", testRootNode);
        BSTNode<String> rightTestNode = new BSTNode<>(20, "C", testRootNode);

        testRootNode.LeftChild = leftTestNode;
        testRootNode.RightChild = rightTestNode;

        BST<String> bst = new BST<>(testRootNode);

        BSTNode<String> insertNode = new BSTNode<>(25, "G", testRootNode);

        bst.AddKeyValue(25, "G");

        BSTFind<String> result = bst.FindNodeByKey(25);

        assertThat(result.Node.NodeKey).isEqualTo(25);
    }

    @Test
    void FinMinMaxWithMaxTest() {
        BST<String> bst = new BST<>(testRootNode);
        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(7, "C");
        bst.AddKeyValue(34, "D");
        bst.AddKeyValue(10, "E");
        bst.AddKeyValue(17, "F");
        bst.AddKeyValue(1, "G");

        BSTNode<String> result = bst.FinMinMax(testRootNode, true);

        assertThat(result.NodeKey).isEqualTo(1);
    }

    @Test
    void FinMinMaxWithMinTest() {
        BST<String> bst = new BST<>(testRootNode);
        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(7, "C");
        bst.AddKeyValue(34, "D");
        bst.AddKeyValue(10, "E");
        bst.AddKeyValue(17, "F");
        bst.AddKeyValue(1, "G");

        BSTNode<String> result = bst.FinMinMax(testRootNode, false);

        assertThat(result.NodeKey).isEqualTo(1);
    }

    @Test
    void DeleteNodeByKeyTest() {
        BST<String> bst = new BST<>(testRootNode);
        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(7, "C");
        bst.AddKeyValue(34, "D");
        bst.AddKeyValue(12, "E");
        bst.AddKeyValue(17, "F");
        bst.AddKeyValue(1, "G");
        bst.AddKeyValue(11, "H");

        boolean res = bst.DeleteNodeByKey(10);

        BSTFind<String> findDeleteKeyResult = bst.FindNodeByKey(10);

        assertThat(findDeleteKeyResult.Node.NodeKey).isEqualTo(7);
    }

    @Test
    void CountTest() {
        BST<String> bst = new BST<>(testRootNode);
        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(7, "C");
        bst.AddKeyValue(34, "D");
        bst.AddKeyValue(12, "E");
        bst.AddKeyValue(17, "F");
        bst.AddKeyValue(1, "G");

        int result = bst.Count();

        assertThat(result).isEqualTo(7);
    }

    @Test
    void isEqualToAnotherTree() {
        BST<String> bst = new BST<>(testRootNode);
        bst.AddKeyValue(10, "B");
        bst.AddKeyValue(34, "D");

        BST<String> bst2 = new BST<>(new BSTNode<>(15, "A", null));
        bst2.AddKeyValue(10, "B");
        bst2.AddKeyValue(34, "D");

        boolean res = bst.isEqualToAnotherTree(bst2);

        assertThat(res).isTrue();
    }
}
