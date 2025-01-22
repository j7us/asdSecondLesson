package org.example.lesson7Heap;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.lesson7Heap.MaxValueFinder.findMax;

public class MaxValueFinderTest {

    @Test
    void findMaxOnlyOneTest() {
        int res = findMax(new int[]{1});

        assertThat(res).isEqualTo(1);
    }

    @Test
    void findMaxTest() {
        int res = findMax(new int[]{1,15,2,-100,75});

        assertThat(res).isEqualTo(75);
    }
}
