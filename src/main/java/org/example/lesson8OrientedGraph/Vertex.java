package org.example.lesson8OrientedGraph;

import java.util.*;

class Vertex
{
    public int Value;
    boolean scanned;
    public Vertex(int val)
    {
        Value = val;
    }
}

class SimpleGraph
{
    Vertex[] vertex;
    int lastFreeIndex = 0;
    int [][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value)
    {
        if (lastFreeIndex > vertex.length - 1) {
            return;
        }

        vertex[lastFreeIndex] = new Vertex(value);

        findNextFreeIndex();
    }

    private void findNextFreeIndex() {
        for (int i = lastFreeIndex; i < max_vertex; i++) {
            if (vertex[i] == null) {
                lastFreeIndex = i;
                return;
            }
        }

        lastFreeIndex = max_vertex;
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v)
    {

        if (vertex[v] == null) {
            return;
        }

        vertex[v] = null;

        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
        }

        if (v < lastFreeIndex) {
            lastFreeIndex = v;
        }
    }

    public boolean IsEdge(int v1, int v2)
    {
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 1;
    }

    public void RemoveEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 0;
    }

    public boolean isСyclicGraph() {
        boolean vertexCyclicTEstResult = false;

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && !vertex[i].scanned && isСyclicGraphRecursive(i, new HashSet<>())) {
                vertexCyclicTEstResult = true;
                break;
            }
        }

        return vertexCyclicTEstResult;
    }

    private boolean isСyclicGraphRecursive(int vertexIndex, Set<Vertex> path) {
        if (!path.add(vertex[vertexIndex])) {
            return true;
        }

        vertex[vertexIndex].scanned = true;

        for (int i = 0; i < vertex.length; i++) {
            boolean res = false;

            if (m_adjacency[vertexIndex][i] == 1) {
                 res = isСyclicGraphRecursive(i, path);
            }

            if (res) {
                return res;
            }

            path.remove(vertex[i]);
        }

        return false;
    }
}
