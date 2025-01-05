package org.example.lesson5SortArrayToBiTree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.lesson5SortArrayToBiTree.AlgorithmsDataStructures2.GenerateBBSTArray;

public class AlgorithmsDataStructures2Test {

    @Test
    void GenerateBBSTArrayEmptyTest() {
        int[] test = new int[5];

        int[] res = GenerateBBSTArray(test);

        assertThat(res[0]).isEqualTo(0);
    }

    @Test
    void GenerateBBSTArrayRootTest() {
        int[] test = new int[5];
        test[2] = 1;

        int[] res = GenerateBBSTArray(test);

        assertThat(res[6]).isEqualTo(1);
    }

    @Test
    void GenerateBBSTArrayTest() {
        int[] test = new int[]{10,15,25,7,30,12};

        int[] res = GenerateBBSTArray(test);

        assertThat(res[0]).isEqualTo(12);
    }
}
