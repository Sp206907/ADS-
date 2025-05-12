package org.example;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 3.0);
        graph.addEdge("A", "C", 5.0);
        graph.addEdge("B", "C", 2.0);

        System.out.println("BFS Path from A to C:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, "A");
        Iterable<String> path = bfs.pathTo("C");
        if (path != null) {
            for (String v : path) {
                System.out.print(v + " ");
            }
        }
        System.out.println();

        System.out.println("Dijkstra Path from A to C:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, "A");
        if (dijkstra.hasPathTo("C")) {
            System.out.println("Distance: " + dijkstra.distTo("C"));
            for (String v : dijkstra.pathTo("C")) {
                System.out.print(v + " ");
            }
        }
    }
}