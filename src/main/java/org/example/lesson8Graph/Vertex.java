package org.example.lesson8Graph;

import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
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
            m_adjacency[i][v] = 0;
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
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
        if (vertex[VFrom] == null || vertex[VTo] == null) {
            return new ArrayList<>();
        }

        cleanAllHit();
        ArrayList<Vertex> resultPath = new ArrayList<>();

        vertex[VFrom].Hit = true;
        resultPath.add(vertex[VFrom]);

        return DepthFirstSearchRecursive(VFrom, VTo, resultPath);
    }

    private ArrayList<Vertex> DepthFirstSearchRecursive(int VFrom, int VTo, ArrayList<Vertex> resultPath) {
        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[VFrom][i] == 1 && !vertex[i].Hit) {
                vertex[i].Hit = true;
                resultPath.add(vertex[i]);

                if (i == VTo) {
                    return resultPath;
                }

                ArrayList<Vertex> stepResult = DepthFirstSearchRecursive(i, VTo, resultPath);

                if (stepResult.get(stepResult.size() - 1) == vertex[VTo]) {
                    return resultPath;
                }
            }
        }

        resultPath.remove(resultPath.size() - 1);
        return resultPath;
    }

    private void cleanAllHit() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null) {
                vertex[i].Hit = false;
            }
        }
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
    {
        if (vertex[VFrom] == null || vertex[VTo] == null) {
            return new ArrayList<>();
        }

        if (VFrom == VTo) {
            return new ArrayList<>(Arrays.asList(vertex[VFrom]));
        }

        cleanAllHit();

        Queue<Integer> verQueue = new ArrayDeque<>();
        verQueue.add(VFrom);
        vertex[VFrom].Hit = true;

        HashMap<Integer, Integer> childParent = new HashMap<>();
        childParent.put(VFrom, null);

        BreadthFirstSearchRecursive(verQueue, VTo, childParent);

        return childParent.get(VTo) == null ? new ArrayList<>() : buildPath(childParent, VTo, new ArrayList<>());
    }

    private ArrayList<Vertex> buildPath(Map<Integer, Integer> childParent, int VTo, ArrayList<Vertex> path) {
        for (Integer i = VTo; i != null; i = childParent.get(i)) {
            path.add(vertex[i]);
        }

        return path;
    }

    private void BreadthFirstSearchRecursive(Queue<Integer> verQueue, int VTo, Map<Integer, Integer> childParent) {
        if (verQueue.isEmpty()) {
            return;
        }

        Integer currentVertex = verQueue.poll();

        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[currentVertex][i] != 1 || (m_adjacency[currentVertex][i] == 1 && vertex[i].Hit)) {
                continue;
            }

            vertex[i].Hit = true;
            verQueue.add(i);
            childParent.put(i, currentVertex);

            if (i == VTo) {
                return;
            }
        }

        BreadthFirstSearchRecursive(verQueue, VTo, childParent);
    }
}
