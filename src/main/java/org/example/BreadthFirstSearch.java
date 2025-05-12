package org.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<V> extends Search<V> {
    private Set<V> visited = new HashSet<>();

    public BreadthFirstSearch(WeightedGraph<V> graph, V start) {
        super(graph, start);
        bfs(start);
    }

    private void bfs(V start) {
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        edgeTo.put(start, null);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            Map<V, Double> adjacents = graph.getAdjacentData(current);
            for (V neighbor : adjacents.keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V target) {
        return visited.contains(target);
    }
}
