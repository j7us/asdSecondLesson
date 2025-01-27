package org.example.lesson8OrientedGraph;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VertexOrientedTest {

    @Test
    void isСyclicGraphEmptyTest() {
        SimpleGraph simpleGraph = new SimpleGraph(5);

        boolean res = simpleGraph.isСyclicGraph();

        assertThat(res).isFalse();
    }

    @Test
    void isСyclicGraphSingleFalseTest() {
        SimpleGraph simpleGraph = new SimpleGraph(5);

        simpleGraph.AddVertex(5);

        boolean res = simpleGraph.isСyclicGraph();

        assertThat(res).isFalse();
    }

    @Test
    void isСyclicGraphSingleTrueTest() {
        SimpleGraph simpleGraph = new SimpleGraph(5);

        simpleGraph.AddVertex(5);

        simpleGraph.AddEdge(0,0);

        boolean res = simpleGraph.isСyclicGraph();

        assertThat(res).isTrue();
    }

    @Test
    void isСyclicGraphFalseTest() {
        SimpleGraph simpleGraph = new SimpleGraph(5);

        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);

        simpleGraph.AddEdge(0,1);
        simpleGraph.AddEdge(1,2);
        simpleGraph.AddEdge(2,3);
        simpleGraph.AddEdge(3,4);

        boolean res = simpleGraph.isСyclicGraph();

        assertThat(res).isFalse();
    }

    @Test
    void isСyclicGraphTrueTest() {
        SimpleGraph simpleGraph = new SimpleGraph(5);

        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);

        simpleGraph.AddEdge(0,1);
        simpleGraph.AddEdge(1,2);
        simpleGraph.AddEdge(2,3);
        simpleGraph.AddEdge(3,4);
        simpleGraph.AddEdge(4,2);

        boolean res = simpleGraph.isСyclicGraph();

        assertThat(res).isTrue();
    }

    @Test
    void isСyclicGraphTest() {
        SimpleGraph simpleGraph = new SimpleGraph(5);

        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);

        simpleGraph.AddEdge(0,1);
        simpleGraph.AddEdge(1,2);
        simpleGraph.AddEdge(3,4);
        simpleGraph.AddEdge(4,3);

        boolean res = simpleGraph.isСyclicGraph();

        assertThat(res).isTrue();
    }
}
