package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {
    private Map<V, Double> distTo = new HashMap<>();

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        super(graph, start);
        dijkstra(start);
    }

    private void dijkstra(V start) {
        distTo.put(start, 0.0);
        PriorityQueue<VertexDistance<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(VertexDistance::getDistance));
        pq.add(new VertexDistance<>(start, 0.0));

        while (!pq.isEmpty()) {
            VertexDistance<V> current = pq.poll();
            V currentData = current.getVertexData();
            if (current.getDistance() > distTo.getOrDefault(currentData, Double.POSITIVE_INFINITY)) {
                continue;
            }

            Map<V, Double> adjacents = graph.getAdjacentData(currentData);
            for (Map.Entry<V, Double> entry : adjacents.entrySet()) {
                V neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distTo.get(currentData) + weight;

                if (newDist < distTo.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, currentData);
                    pq.add(new VertexDistance<>(neighbor, newDist));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V target) {
        return distTo.containsKey(target) && distTo.get(target) < Double.POSITIVE_INFINITY;
    }

    public double distTo(V target) {
        return distTo.getOrDefault(target, Double.POSITIVE_INFINITY);
    }

    private static class VertexDistance<V> {
        private V vertexData;
        private double distance;

        public VertexDistance(V vertexData, double distance) {
            this.vertexData = vertexData;
            this.distance = distance;
        }

        public V getVertexData() {
            return vertexData;
        }

        public double getDistance() {
            return distance;
        }
    }
}