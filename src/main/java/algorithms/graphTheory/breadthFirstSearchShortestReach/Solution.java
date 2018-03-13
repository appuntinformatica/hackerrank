package algorithms.graphTheory.breadthFirstSearchShortestReach;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/bfsshortreach */
public class Solution {

    private static final int EDGE_LENGTH = 6;
    
    public static class Graph<T> {
        private TreeMap<T, TreeSet<T>> neighboringVertices;
        private int E;

        public Graph() {
            E = 0;
            neighboringVertices = new TreeMap<>();
        }
        public boolean hasEdge(T v, T w) {
            if ( hasVertex(v) && hasVertex(w) ) 
                return neighboringVertices.get(v).contains(w);
            else
                return false;
        }

        public boolean hasVertex(T v) {
            return neighboringVertices.containsKey(v);
        }

        public void addEdge(T v, T w) {
            if (!hasVertex(v)) addVertex(v);
            if (!hasVertex(w)) addVertex(w);
            if (!hasEdge(v, w)) E++;
            neighboringVertices.get(v).add(w);
            neighboringVertices.get(w).add(v);
        }
        public void addVertex(T v) {
            if (!hasVertex(v)) neighboringVertices.put(v, new TreeSet<T>());
        }

        public int V() {
            return neighboringVertices.size();
        }

        public int E() {
            return E;
        }

        public Iterable<T> adjacentTo(T v) {
            if (hasVertex(v))
                return neighboringVertices.get(v);
            else 
                return null;
        }
    }
    
    public static class PathFinder<T> {

        private TreeMap<T, T> prev;
        private TreeMap<T, Integer> dist;

        private Graph<T> G;
        public PathFinder(Graph<T> G, T s) {
            this.G = G;
            prev = new TreeMap<>();
            dist = new TreeMap<>();

            Queue<T> q = new ArrayDeque<>();
            q.add(s);
            dist.put(s, 0);

            while (!q.isEmpty()) {
                T v = q.remove();
                Iterable<T> iterator = G.adjacentTo(v);
                if (iterator != null) {
                    for (T w : iterator) {
                        if (!dist.containsKey(w)) {
                            q.add(w);
                            dist.put(w, 1 + dist.get(v));
                            prev.put(w, v);
                        }
                    }
                }
            }
        }

        public boolean hasPathTo(T v) {
            return dist.containsKey(v);
        }

        public int distanceTo(T v) {
            if (!hasPathTo(v)) {
                return -1;
            }
            return dist.get(v);
        }

        public Iterable<T> pathTo(T v) {
            Stack<T> path = new Stack<>();
            while (v != null && dist.containsKey(v)) {
                path.push(v);
                v = prev.get(v);
            }
            return path;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tMax = scanner.nextInt();
        for (int t = 0; t < tMax; t++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Graph<Integer> graph = new Graph<>();
            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                graph.addEdge(x, y);
            }
            int s = scanner.nextInt();
            PathFinder<Integer> pathFinder = new PathFinder<>(graph, s);
            StringBuilder sb = new StringBuilder();
            for (int node = 1; node <= n; node++) {
                if (node != s) {
                    int d = pathFinder.distanceTo(node);
                    if (d != -1) {
                        d = d * EDGE_LENGTH;
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
}
