package org.example.lesson8Graph;

import org.junit.jupiter.api.Test;

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
}
