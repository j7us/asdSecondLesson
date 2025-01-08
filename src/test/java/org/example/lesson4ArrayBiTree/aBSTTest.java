package org.example.lesson4ArrayBiTree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class aBSTTest {

    @Test
    void FindKeyIndexEmptyTest() {
        aBST tree = new aBST(3);

        Integer res = tree.FindKeyIndex(15);

        assertThat(res).isEqualTo(0);
    }

    @Test
    void FindKeyIndexTest() {
        aBST tree = new aBST(2);

        tree.AddKey(15);
        tree.AddKey(11);
        tree.AddKey(19);
        tree.AddKey(14);
        tree.AddKey(16);
        tree.AddKey(25);

        Integer res = tree.FindKeyIndex(7);

        assertThat(res).isEqualTo(-3);
    }

    @Test
    void FindKeyIndexFullTest() {
        aBST tree = new aBST(2);

        tree.AddKey(15);
        tree.AddKey(11);
        tree.AddKey(19);
        tree.AddKey(14);
        tree.AddKey(16);
        tree.AddKey(25);


        Integer res = tree.AddKey(47);

        assertThat(res).isEqualTo(-1);
    }

    @Test
    void FindKeyIndexRootTest() {
        aBST tree = new aBST(2);

        tree.AddKey(15);

        Integer res = tree.AddKey(15);

        assertThat(res).isEqualTo(0);
    }

    @Test
    void findLCAEmptyTest() {
        aBST tree = new aBST(2);

        tree.AddKey(15);
        tree.AddKey(11);
        tree.AddKey(19);
        tree.AddKey(14);
        tree.AddKey(16);
        tree.AddKey(25);

        int res = tree.findLCA(14, 25);

        assertThat(res).isEqualTo(0);
    }

    @Test
    void WideAllNodesTest() {
        aBST tree = new aBST(2);

        tree.AddKey(15);
        tree.AddKey(11);
        tree.AddKey(19);
        tree.AddKey(12);
        tree.AddKey(16);
        tree.AddKey(25);

        ArrayList<Integer> res = tree.WideAllNodes();

        assertThat(res.size()).isEqualTo(6);
    }
}
