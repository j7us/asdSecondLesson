package org.example.lesson8Graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class VertexTest {

    @Test
    void AddVertexTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(10);

        assertThat(graph.vertex[0].Value).isEqualTo(10);
    }

    @Test
    void AddVertexFalseTest() {
        SimpleGraph graph = new SimpleGraph(5);

        Vertex[] resultArray = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5)
        };

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);

        assertThat(Arrays.equals(graph.vertex, resultArray, Comparator.comparingInt((Vertex a) -> a.Value))).isTrue();
    }

    @Test
    void AddVertexWithRemoveFullTest() {
        SimpleGraph graph = new SimpleGraph(5);

        Vertex[] resultArray = new Vertex[]{
                new Vertex(1),
                new Vertex(6),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5)
        };

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.RemoveVertex(1);

        graph.AddVertex(6);
        graph.AddVertex(7);

        assertThat(Arrays.equals(graph.vertex, resultArray, Comparator.comparingInt((Vertex a) -> a.Value))).isTrue();
    }

    @Test
    void AddVertexWithNewFreeIndexTest() {
        SimpleGraph graph = new SimpleGraph(5);

        Vertex[] resultArray = new Vertex[]{
                new Vertex(4),
                new Vertex(5),
                new Vertex(3),
                new Vertex(6),
                new Vertex(7)
        };

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.RemoveVertex(0);
        graph.RemoveVertex(1);

        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);

        assertThat(Arrays.equals(graph.vertex, resultArray, Comparator.comparingInt((Vertex a) -> a.Value))).isTrue();
    }

    @Test
    void RemoveVertexEmptyTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.RemoveVertex(0);
    }

    @Test
    void RemoveVertexOneElementTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);

        graph.RemoveVertex(0);

        assertThat(graph.vertex[0]).isNull();
    }

    @Test
    void RemoveVertexTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);

        graph.RemoveVertex(0);

        assertThat(graph.vertex[1].Value).isEqualTo(2);
    }

    @Test
    void RemoveVertexWithEdgeTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddEdge(0, 1);
        graph.AddEdge(2, 1);

        graph.RemoveVertex(0);

        assertThat(graph.vertex[0]).isNull();
        assertThat(graph.m_adjacency[0][1]).isEqualTo(0);
        assertThat(graph.m_adjacency[1][0]).isEqualTo(0);
        assertThat(graph.m_adjacency[1][2]).isEqualTo(1);
        assertThat(graph.m_adjacency[2][1]).isEqualTo(1);
    }

    @Test
    void IsEdgeTrueTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddEdge(0, 1);
        graph.AddEdge(2, 1);

        boolean res = graph.IsEdge(0, 1);

        assertThat(res).isTrue();
    }

    @Test
    void IsEdgeFalseTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddEdge(0, 1);
        graph.AddEdge(2, 1);

        boolean res = graph.IsEdge(0, 2);

        assertThat(res).isFalse();
    }

    @Test
    void RemoveEdgeTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddEdge(0, 1);
        graph.AddEdge(2, 1);

        graph.RemoveEdge(0,1);

        boolean res = graph.IsEdge(0, 1);

        assertThat(res).isFalse();
    }

    @Test
    void DepthFirstSearchEmptyTest() {
        SimpleGraph graph = new SimpleGraph(5);

        ArrayList<Vertex> res = graph.DepthFirstSearch(1, 2);

        assertThat(res).isEmpty();
    }

    @Test
    void DepthFirstSearchSingleTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddEdge(0, 1);

        ArrayList<Vertex> res = graph.DepthFirstSearch(0, 1);

        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    void DepthFirstSearchFalseTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddEdge(0, 1);

        ArrayList<Vertex> res = graph.DepthFirstSearch(0, 2);

        assertThat(res).isEmpty();
    }

    @Test
    void DepthFirstSearchTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);

        ArrayList<Vertex> res = graph.DepthFirstSearch(0, 2);

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    void isConnectivityGraphFalseTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);

        boolean res = graph.isConnectivityGraph();

        assertThat(res).isFalse();
    }

    @Test
    void isConnectivityGraphTrueTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);

        boolean res = graph.isConnectivityGraph();

        assertThat(res).isTrue();
    }

    @Test
    void BreadthFirstSearchEmptyTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);

        assertThat(res).isEmpty();
    }

    @Test
    void BreadthFirstSearchTrueTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);

        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    void BreadthFirstSearchFalseTest() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 2);

        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    void cycl() {
        SimpleGraph graph = new SimpleGraph(4);
        graph.AddVertex(10);
        graph.AddVertex(20);
        graph.AddVertex(30);
        graph.AddVertex(40);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 0);
        ArrayList<Vertex> path = graph.BreadthFirstSearch(0, 3);

        assertThat(path.size()).isEqualTo(2);
    }

    @Test
    void emp() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddEdge(0, 1);

        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 0);

        assertThat(res).isEmpty();
    }

    @Test
    void one() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 0);

        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);

        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    void two() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddEdge(0, 1);
        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);

        assertThat(res).isEmpty();
    }

    @Test
    void cy() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);

        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);

        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    void WeakVerticesEmptyTest() {
        SimpleGraph graph = new SimpleGraph(3);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res).isEmpty();
    }

    @Test
    void WeakVerticesWithoutEdgeTest() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    void WeakVerticesWithoutTriangleTest() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    void WeakVerticesWithoutTriangleWithLoopTest() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 0);
        graph.AddEdge(1, 1);
        graph.AddEdge(2, 2);
        graph.AddEdge(0, 2);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res.size()).isEqualTo(3);
    }

    @Test
    void WeakVerticesWithTriangleTest1() {
        SimpleGraph graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 0);
        graph.AddEdge(1, 1);
        graph.AddEdge(2, 2);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res).isEmpty();
    }

    @Test
    void WeakVerticesWithTriangleTest2() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(0, 0);
        graph.AddEdge(2, 2);
        graph.AddEdge(1, 1);
        graph.AddEdge(4, 4);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    void WeakVerticesWithTriangleTest3() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(0, 0);
        graph.AddEdge(2, 2);
        graph.AddEdge(1, 1);
        graph.AddEdge(4, 4);
        graph.AddEdge(0, 2);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res).isEmpty();
    }

    @Test
    void WeakVerticesWithTriangleTest4() {
        SimpleGraph graph = new SimpleGraph(7);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(0, 4);
        graph.AddEdge(0, 5);
        graph.AddEdge(0, 6);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(5, 6);
        graph.AddEdge(1, 6);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res).isEmpty();
    }

    @Test
    void WeakVerticesWithSquareTest() {
        SimpleGraph graph = new SimpleGraph(7);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 0);

        ArrayList<Vertex> res = graph.WeakVertices();

        assertThat(res.size()).isEqualTo(4);
    }
}
