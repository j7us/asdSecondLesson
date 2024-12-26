package org.example.lesson2BiTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
