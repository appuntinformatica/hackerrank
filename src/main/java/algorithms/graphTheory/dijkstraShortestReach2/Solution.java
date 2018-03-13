package algorithms.graphTheory.dijkstraShortestReach2;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/dijkstrashortreach */
public class Solution {

    private static class Vertex implements Comparable<Vertex> {

        public final int id;
        public List<Edge> adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public Vertex(int id) {
            this.id = id;
            this.adjacencies = new ArrayList<>();
        }

        @Override
        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }
        
        public void add(Vertex vertex, double distance) {
            Edge edge = new Edge(vertex, distance);
            boolean founded = false;
            for (Edge e : this.adjacencies) {
                if (e.compareTo(edge) == 0) {
                    founded = true;
                }
            }
            if (!founded) {
                this.adjacencies.add(edge);
            }
        }

    }

    private static class Edge implements Comparable<Edge> {

        public final Vertex target;
        public final double weight;

        public Edge(Vertex argTarget, double argWeight) {
            target = argTarget;
            weight = argWeight;
        }

        @Override
        public int compareTo(Edge other) {
            if (this.target.id == other.target.id && this.target.compareTo(other.target) == 0 && Double.compare(this.weight, other.weight) == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tMax = scanner.nextInt();
        for (int t = 0; t < tMax; t++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Vertex[] vertexs = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vertexs[i] = new Vertex(i + 1);
            }
            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int r = scanner.nextInt();
/*
                Edge eg1 = new Edge(vertexs[y - 1], r);
                
                boolean founded = false;
                for (Edge edge : vertexs[x - 1].adjacencies) {
                    if (edge.compareTo(eg1) == 0) {
                        founded = true;
                    }
                }
                if (!founded) {
                    vertexs[x - 1].adjacencies.add(eg1);
                }
                */
                vertexs[x - 1].add(vertexs[y - 1], r);
                vertexs[y - 1].add(vertexs[x - 1], r);

                /*
                Edge eg2 = new Edge(vertexs[x - 1], r);
                boolean founded = false;
                for (Edge edge : vertexs[y - 1].adjacencies) {
                    if (edge.compareTo(eg2) == 0) {
                        founded = true;
                    }
                }
                if (!founded) {
                    vertexs[y - 1].adjacencies.add(eg2);
                }
                        */
            }
            int s = scanner.nextInt();

            computePaths(vertexs[s - 1]);

            StringBuilder sb = new StringBuilder();
            for (int node = 1; node <= n; node++) {
                if (node != s) {
                    int d = -1;
                    if (vertexs[node - 1].minDistance != Double.POSITIVE_INFINITY) {
                        d = (int) vertexs[node - 1].minDistance;
                    }
                    sb.append(d);
                    sb.append(" ");
                }
            }
            String output = sb.toString();
            output = output.substring(0, output.length() - 1);
            System.out.println(output);
        }
    }

    private static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
}
