package org.example.lesson6ArrayTiBiTreeObject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BalancedBSTTest {

    @Test
    void GenerateBBSTArrayTest() {
        int[] test = new int[]{10,15,25,7,30,12};

        BalancedBST tree = new BalancedBST();

        tree.GenerateTree(test);

        assertThat(tree.Root.NodeKey).isEqualTo(12);
    }

    @Test
    void IsBalancedEmptyTest() {
        BalancedBST tree = new BalancedBST();

        assertThat(tree.IsBalanced(tree.Root)).isTrue();
    }

    @Test
    void IsBalancedRoottest() {
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
}