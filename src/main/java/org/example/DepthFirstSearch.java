package org.example;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DepthFirstSearch<V> extends Search<V> {
    private Set<V> visited = new HashSet<>();

    public DepthFirstSearch(WeightedGraph<V> graph, V start) {
        super(graph, start);
        dfs(start);
    }

    private void dfs(V start) {
        Deque<V> stack = new LinkedList<>();
        stack.push(start);
        visited.add(start);
        edgeTo.put(start, null);

        while (!stack.isEmpty()) {
            V current = stack.pop();
            // Process neighbors in reverse order to mimic recursive DFS traversal
            LinkedList<V> neighbors = new LinkedList<>(graph.getAdjacentData(current).keySet());
            for (V neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V target) {
        return visited.contains(target);
    }
}