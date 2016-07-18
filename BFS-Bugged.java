import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {
    public static final class Edge {
        private final int from;
        private final int to;
        public Edge (int from, int to) {
            this.from = from; this.to = to;
        }
        public Edge (String s) {
            String nodes[] = s.substring(1, s.length()-1).split(",");
            this.from = Integer.parseInt(nodes[0].trim());
            this.to = Integer.parseInt(nodes[1].trim());
        }
        public int getFrom () {
            return this.from;
        }
        public int getTo() {
            return this.to;
        }
        public String toString () {
            return "(" + from + "," + to + ")";
        }
        public Edge reverse() {
            return new Edge(to, from);
        }
    }
    
    public static final class Graph {
        private final int nodes;
        private final Set<Edge> edges;
        
        public Graph(int nodes, Set<Edge> edges) {
            this.nodes = nodes;
            this.edges = edges;
        }
        
        public Set<Integer> neighbours(int node) {
            Set<Integer> result = new HashSet<>();
            for (Edge e: edges) {
                if (e.getFrom() == node) {
                    result.add(e.getTo());
                }
            }
            return result;
        }
        
        public int getNodes() {
            return this.nodes;
        }
    }
    
    public static final class SearchGraph {
        private final Graph graph;
        private final int startNode;
        private final Map<Integer, Integer> parents = new HashMap<>();
        private final Map<Integer, Double> distances = new HashMap<>();
        
        public SearchGraph (Graph g, int startNode) {
            this.graph = g;
            this.startNode = startNode;

            bfs();
        }
        
        private void bfs () {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(startNode);
            Set<Integer> visited = new HashSet<>();

            while (!queue.isEmpty()) {
                int currentNode = queue.remove();
                if (visited.contains(currentNode)) {
                    continue;
                }
                visited.add(currentNode);
                
                Set<Integer> connections = graph.neighbours(currentNode);
                for (int c: connections) {
                    queue.add(c);
                    parents.put(c, currentNode);
                }
            }
        }
        
        public double getDistance (int node) {
            if (distances.get(node) == null) {
                double dist = getDistance(node, 0, new HashSet<>());
                distances.put(node, dist);
                return dist;
            }
            return distances.get(node);
        }
        
        private double getDistance (int node, int currentDistance, Set<Integer> visitedNodes) {
            if (visitedNodes.contains(node)) {
                return Double.POSITIVE_INFINITY;
            }
            visitedNodes.add(node);
            if (parents.get(node) == null) {
                return Double.POSITIVE_INFINITY;
            }
            return getDistance(parents.get(node), currentDistance + 1, visitedNodes);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.nextLine());
        for (int t=0; t<testCases; ++t) {
            String line2[] = sc.nextLine().trim().split(" ");
            int numberOfNodes = Integer.parseInt(line2[0]);
            int numberOfEdges = Integer.parseInt(line2[1]);
            Set<Edge> edges = new HashSet<>();
            for (int i=0; i<numberOfEdges; ++i) {
                String thisLine[] = sc.nextLine().trim().split(" ");
                int startEdge = Integer.parseInt(thisLine[0]);
                int endEdge = Integer.parseInt(thisLine[1]);
                Edge thisEdge = new Edge(startEdge, endEdge);
                edges.add(thisEdge);
                edges.add(thisEdge.reverse());
            }
            Graph g = new Graph(numberOfNodes, edges);
            int startNode = Integer.parseInt(sc.nextLine().trim());
            SearchGraph sg = new SearchGraph(g, startNode);
            StringBuffer output = new StringBuffer();
            for (int i=0; i<numberOfNodes; ++i) {
                if (i == startNode) {
                    continue;
                }
                double distance = sg.getDistance(i);
                if (distance == Double.POSITIVE_INFINITY) {
                    output.append(" -1");
                } else {
                    output.append(" " + distance * 6);
                }
            }
            System.out.println(output.toString().trim());
        }
    }
}
