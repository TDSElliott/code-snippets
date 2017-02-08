package dijkstramatrix;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Tyler D.S. Elliott on 07-Feb-17.
 */
public class DijkstraAdjacencyMatrix {
	public static void main(String[] args) {
		Double[][] weights =
				{
				{0.0, 1.0, 3.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 20.0},
				{1.0, 0.0, Double.POSITIVE_INFINITY, 2.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{3.0, Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY, 4.0, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, 2.0, Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY, 1.0},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 4.0, Double.POSITIVE_INFINITY, 0.0, 5.0},
				{20.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0, 5.0, 0.0}
				};

		System.out.println("A --> F = " + dijkstra(weights, 0, 5).intValue());
	}



	static Double dijkstra(Double[][] weights, int start, int end) {

		// Set up array used to maintain minimum distances from start
		int n = weights.length;
		double[] dist = new double[n];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[start] = 0.0;

		// Priority queue to keep track of the node
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.offer(new Node(start, 0.0));

		// Process each node
		while (!q.isEmpty()) {

			Node node = q.poll();

			// Check to see if it is stale
			if (dist[node.index] < node.dist)
				continue;

			// Reached destination
			if (node.index == end)
				return node.dist;

			// Check each neighbour
			for (int i = 0; i < n; i++) {
				if (weights[node.index][i] != null) {
					double newDist = dist[node.index] + weights[node.index][i];
					if (newDist < dist[i]) {
						dist[i] = newDist;
						q.offer(new Node(i, newDist));
					}
				}
			}

		}

		// Does not connect
		return null;

	}
}




class Node implements Comparable<Node> {

	int index;
	double dist;

	public Node(int index, double dist) {
		this.index = index;
		this.dist = dist;
	}

	@Override public int compareTo(Node other) {
		return Double.compare(dist, other.dist);
	}

}