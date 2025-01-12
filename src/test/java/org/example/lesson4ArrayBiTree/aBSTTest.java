package org.example.lesson4ArrayBiTree;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

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

        int res = tree.findLCA(15, 19);

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

    @FuzzTest(maxDuration = "2m")
    void WideAllNodesFuzz(FuzzedDataProvider data) {
        int size = data.consumeInt(0, 5);

        aBST tree = new aBST(size);

        int[] ints = data.consumeInts(63);

        for (int key : ints) {
            tree.AddKey(key);
        }

        ArrayList<Integer> res = tree.WideAllNodes();

        if (res.size() > 1 && !isWideCorrect(tree.Tree, new ArrayDeque<>(Arrays.asList(0)), res, 0)) {
            throw new RuntimeException("Wide not correct");
        }
    }

    boolean isWideCorrect(Integer[] tree, Queue<Integer> treeWideInd, ArrayList<Integer> wideRes, int wideResInd) {
        if ((treeWideInd.peek() == null || treeWideInd.peek() >= tree.length ) && wideResInd >= wideRes.size()) {
            return true;
        }

        int LevelSize = treeWideInd.size();

        for (int i = 0; i < LevelSize; i++) {
            Integer treeInd = treeWideInd.poll();

            if(tree[treeInd] == null) {
                continue;
            }

            if (tree[treeInd] != wideRes.get(wideResInd)) {
                return false;
            }

            treeWideInd.add(2*treeInd + 1);
            treeWideInd.add(2*treeInd + 2);

            wideResInd++;
        }

        return isWideCorrect(tree, treeWideInd, wideRes, wideResInd);
    }
}
