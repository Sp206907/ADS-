package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Search<V> {
    protected WeightedGraph<V> graph;
    protected Map<V, V> edgeTo;
    protected V start;

    public Search(WeightedGraph<V> graph, V start) {
        this.graph = graph;
        this.start = start;
        this.edgeTo = new HashMap<>();
    }

    public abstract boolean hasPathTo(V target);

    public Iterable<V> pathTo(V target) {
        if (!hasPathTo(target)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V v = target; v != null; v = edgeTo.get(v)) {
            path.addFirst(v);
        }
        return path;
    }
}