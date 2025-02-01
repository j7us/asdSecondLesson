package org.example.lesson6ArrayTiBiTreeObject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.lesson6ArrayTiBiTreeObject.BalancedBST.balanceBST;

public class BalancedBSTTest {

    @Test
    void GenerateBBSTArrayTest() {
        int[] test = new int[]{10,15,25,7,30,12};

        BalancedBST tree = new BalancedBST();

        tree.GenerateTree(test);

        assertThat(tree.Root.NodeKey).isEqualTo(12);
    }

    @Test
    void levelTest() {
        int[] test = new int[]{10,15,25,7,30,12};

        BalancedBST tree = new BalancedBST();

        tree.GenerateTree(test);

        assertThat(allChildrenHasIncreaseLevel(tree.Root, 0)).isTrue();
    }

    boolean allChildrenHasIncreaseLevel(BSTNode node, int level) {
        if (node == null || (node.LeftChild == null && node.RightChild == null)) {
            return true;
        }

        if (node.Level != level) {
            return false;
        }

        return allChildrenHasIncreaseLevel(node.LeftChild, level + 1)
                && allChildrenHasIncreaseLevel(node.RightChild, level + 1);
    }

    @Test
    void validateTreeEmptyTest() {
        BalancedBST tree = new BalancedBST();
        boolean res = tree.validateTree();
        assertThat(res).isTrue();
    }

    @Test
    void validateTreeRootTest() {
        BalancedBST tree = new BalancedBST();
        tree.Root = new BSTNode(15, null);

        boolean res = tree.validateTree();

        assertThat(res).isTrue();
    }

    @Test
    void validateTreeTrueTest() {
        BalancedBST tree = new BalancedBST();
        int[] test = new int[]{10,15,25};
        tree.GenerateTree(test);

        boolean res = tree.validateTree();

        assertThat(res).isTrue();
    }

    @Test
    void validateTreeFalseTest() {
        BalancedBST tree = new BalancedBST();
        int[] test = new int[]{10,15,25};
        tree.GenerateTree(test);

        tree.Root.LeftChild.LeftChild = new BSTNode(750, tree.Root.LeftChild);

        boolean res = tree.validateTree();

        assertThat(res).isFalse();
    }

    @Test
    void IsBalancedEmptyTest() {
        BalancedBST tree = new BalancedBST();

        assertThat(tree.IsBalanced(tree.Root)).isTrue();
    }

    @Test
    void IsBalancedRootTest() {
        BalancedBST tree = new BalancedBST();

        int[] test = new int[]{10};
        tree.GenerateTree(test);

        assertThat(tree.IsBalanced(tree.Root)).isTrue();
    }

    @Test
    void IsBalancedTest() {
        int[] test = new int[]{10,15,25,7,30,12};

        BalancedBST tree = new BalancedBST();

        tree.GenerateTree(test);

        assertThat(tree.IsBalanced(tree.Root)).isTrue();
    }

    @Test
    void IsBalancedFalseTest() {
        int[] test = new int[]{10};

        BalancedBST tree = new BalancedBST();

        tree.GenerateTree(test);

        BSTNode root = tree.Root;

        BSTNode left1 = new BSTNode(7, root);
        left1.Level = 1;
        BSTNode left2 = new BSTNode(1, left1);
        left2.Level = 2;
        root.LeftChild = left1;
        left1.LeftChild = left2;

        assertThat(tree.IsBalanced(tree.Root)).isFalse();
    }

    @Test
    void IsBalancedSubTreeFalseTest() {
        int[] test = new int[]{10,15,25,7,30,12};

        BalancedBST tree = new BalancedBST();

        tree.GenerateTree(test);

        BSTNode toInsert = tree.Root.LeftChild.LeftChild;
        BSTNode node = new BSTNode(1, toInsert);
        node.Level = 3;
        toInsert.LeftChild = node;

        assertThat(tree.IsBalanced(tree.Root)).isFalse();
    }

    @Test
    void balanceBSTTest() {
        BalancedBST tree = new BalancedBST();

        BSTNode node = new BSTNode(1, null);
        BSTNode node1 = new BSTNode(2, node);
        node.RightChild = node1;
        BSTNode node2 = new BSTNode(3, node1);
        node1.RightChild =  node2;
        tree.Root = node;

        BalancedBST resultTree = balanceBST(tree);


        assertThat(resultTree.validateTree()).isTrue();
        assertThat(resultTree.IsBalanced(resultTree.Root)).isTrue();
    }
}
