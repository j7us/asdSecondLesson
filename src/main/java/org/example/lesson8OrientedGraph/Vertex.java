package org.example.lesson8OrientedGraph;

import java.util.*;

class Vertex
{
    public int Value;
    int colour;
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
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].colour != VertexColour.BLACK.getValue() && isСyclicGraphRecursive(i)) {
                return true;
            }
        }

        return false;
    }

    public boolean isСyclicGraphRecursive(int vertexIndex) {
        if (vertex[vertexIndex].colour == VertexColour.GREY.getValue()) {
            return true;
        } else if (vertex[vertexIndex].colour == VertexColour.BLACK.getValue()) {
            return false;
        }

        vertex[vertexIndex].colour = VertexColour.GREY.getValue();

        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[vertexIndex][i] == 1 && isСyclicGraphRecursive(i)) {
                return true;
            }
        }

        vertex[vertexIndex].colour = VertexColour.BLACK.getValue();

        return false;
    }

    private void cleanAllVertexColour() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null) {
                vertex[i].colour = 0;
            }
        }
    }

    public int findLongestPath() {
        cleanAllVertexColour();

        int longestPath = 0;

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].colour != VertexColour.BLACK.getValue()) {
                int currentLength = findLongestPathRec(i);

                longestPath = Math.max(currentLength, longestPath);
            }
        }

        return longestPath;
    }

    private int findLongestPathRec(int currIndex) {
        vertex[currIndex].colour = VertexColour.GREY.getValue();

        int longestPath = 0;

        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[currIndex][i] == 1 && vertex[i].colour != VertexColour.GREY.getValue()) {
                int subTreeLongestPath = findLongestPathRec(i);

                longestPath = Math.max(longestPath, subTreeLongestPath);
            }
        }

        vertex[currIndex].colour = VertexColour.BLACK.getValue();

        return longestPath + 1;
    }



    private enum VertexColour {
        GREY(1),
        BLACK(2);

        private final int value;

        VertexColour(int colorValue) {
            this.value = colorValue;
        }

        public int getValue() {
            return value;
        }
    }
}
