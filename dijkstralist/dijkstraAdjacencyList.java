package dijkstralist;

import java.util.*;

/**
 * Created by Tyler D.S. Elliott on 07-Feb-17.
 */
public class dijkstraAdjacencyList {
	public static void main(String[] args) {
		Map<Integer, List<Edge>>  adjacencyList = new HashMap<>();

		// A --> B + C + F
		List<Edge> Node0 = new ArrayList<Edge>(); // A
		Node0.add(new Edge(0, 1, 1)); // b
		Node0.add(new Edge(0, 2, 3)); // c
		Node0.add(new Edge(0, 5, 20)); // f

		// B --> D
		List<Edge> Node1 = new ArrayList<Edge>(); // B
		Node1.add(new Edge(1, 3, 2)); // d

		// C --> E
		List<Edge> Node2 = new ArrayList<Edge>(); // C
		Node2.add(new Edge(2, 4, 4)); // e

		// D --> F
		List<Edge> Node3 = new ArrayList<Edge>(); // D
		Node3.add(new Edge(3, 5, 1)); // f

		// E --> F
		List<Edge> Node4 = new ArrayList<Edge>(); // E
		Node4.add(new Edge(4, 5, 5)); // f

		// F --> A + D + E
		List<Edge> Node5 = new ArrayList<Edge>(); // F
		Node5.add(new Edge(5, 0, 20)); // a
		Node5.add(new Edge(5, 3, 1)); // d
		Node5.add(new Edge(5, 4, 5)); // e


		adjacencyList.put(0, Node0); // A connections
		adjacencyList.put(1, Node1); // B connections
		adjacencyList.put(2, Node2); // C connections
		adjacencyList.put(3, Node3); // D connections
		adjacencyList.put(4, Node4); // E connections
		adjacencyList.put(5, Node5); // F connections (duplicated from A, D, and E)



		Integer[] results = dijkstra(adjacencyList, 6, 0);
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int x = 0; x < results.length; x++) {
			System.out.println("A --> " + alphabet[x] + " = " + results[x]);
		}

	}

	/**
	 * adjList - The adjacency list mapping a node to a list of edges
	 * N - the number of nodes
	 * S - the source node
	 **/
	static Integer[] dijkstra(Map< Integer, List<Edge>> adjList, int N, int S) {

		Integer[] bestDist = new Integer[N];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, 0));
		bestDist[S] = 0;

		while (!pq.isEmpty()) {

			Node node = pq.poll();
			List<Edge> edges = adjList.get(node.index);

			// This node has edges leading out from it
			if (edges != null) {

				// Node is stale. There is already another better path.
				if (bestDist[node.index] < node.dist) continue;
				for (Edge edge: edges) {
					int newDist = node.dist + edge.weight;
					if (bestDist[edge.to] == null || bestDist[edge.to] > newDist) {
						bestDist[edge.to] = newDist;
						pq.offer(new Node(edge.to, newDist));
					}
				}

			}
		}
		return bestDist;
	}

}

	class Edge {
		int from, to, weight;
		public Edge(int f, int t, int w) {
			from = f; to = t; weight = w;
		}
	}

	class Node implements Comparable<Node> {
		int index, dist;
		public Node(int i, int d) {
			index = i; dist = d;
		}
		@Override public int compareTo(Node other) {
			return Integer.compare(dist, other.dist);
		}
	}



