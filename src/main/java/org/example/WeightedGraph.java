package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V sourceData, V destData, double weight) {
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);
        if (source != null && dest != null) {
            source.addAdjacentVertex(dest, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Double> getAdjacentData(V data) {
        Vertex<V> vertex = vertices.get(data);
        if (vertex == null) return Collections.emptyMap();
        Map<V, Double> result = new HashMap<>();
        for (Map.Entry<Vertex<V>, Double> entry : vertex.getAdjacentVertices().entrySet()) {
            result.put(entry.getKey().getData(), entry.getValue());
        }
        return result;
    }
}
