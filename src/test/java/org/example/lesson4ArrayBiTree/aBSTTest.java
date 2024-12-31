package org.example.lesson4ArrayBiTree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class aBSTTest {

    @Test
    void FindKeyIndexTest() {
        aBST tree = new aBST(3);

        Integer res = tree.FindKeyIndex(15);

        assertThat(res).isEqualTo(0);
    }

    @Test
    void FindKeyIndexRootTest() {
        aBST tree = new aBST(3);

        tree.AddKey(15);

        Integer res = tree.FindKeyIndex(15);

        assertThat(res).isEqualTo(0);
    }
}
