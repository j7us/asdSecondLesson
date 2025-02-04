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

    public int findLongestPath() {
        int longestPath = 0;
        HashMap<Integer, Integer> maxLengthForEachVertex = new HashMap<>();

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && vertex[i].colour != VertexColour.BLACK.getValue()) {
                int currentLength = findLongestPathRec(i, maxLengthForEachVertex);

                longestPath = Math.max(currentLength, longestPath);
            }
        }

        return longestPath;
    }

    private int findLongestPathRec(int index, Map<Integer, Integer> maxLengthForEachVertex) {
        int maxLengthForCurrent = 0;

        vertex[index].colour = VertexColour.GREY.getValue();

        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[index][i] != 1
                    || (m_adjacency[index][i] == 1 && vertex[i].colour == VertexColour.GREY.getValue())) {
                continue;
            }

            int res;

            if (vertex[i].colour == VertexColour.BLACK.getValue()) {
                res = maxLengthForEachVertex.get(i) + 1;
            } else {
                res = findLongestPathRec(i, maxLengthForEachVertex);
            }

            maxLengthForCurrent = Math.max(maxLengthForCurrent, res);
        }

        vertex[index].colour = VertexColour.BLACK.getValue();

        maxLengthForEachVertex.put(index, maxLengthForCurrent);

        return maxLengthForCurrent + 1;
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
