package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Tyler D.S. Elliott on 13-Feb-17.
 */
public class Graphing {
	static int MAXV; static int MAXDEGREE; // If you want a limit on vertices or edges entering/leaving a vertice
	static BufferedReader br;
	static StringTokenizer st; // Although ST is a legacy class and its use is not recommended, it is 4x faster than String.split

	public static void main(String[] args) throws IOException {
		MAXV = 1000; MAXDEGREE = 500; // Arbitrary

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		graph g = new graph();
		readGraph(g, false); // Directed or not?

		printGraph(g); // Everything is good to go and sitting in memory, just have to choose to print it or work with it
	}

	boolean[] processed = new boolean[MAXV];  // vertices that have been processed
	boolean[] discovred = new boolean [MAXV]; // which vertices have been found
	int[] parent = new int[MAXV];             // discovery relation

	static void readGraph(graph g, boolean directed) throws IOException {
		int i;	// counter
		int m;	// number of edges
		int x, y; // vertices in edge (x, y)

		initializeGraph(g);	// Initialize all of the graph variables

		int restaurants = Integer.parseInt(st.nextToken());
		int phoRestaurants = Integer.parseInt(st.nextToken());
		g.nvertices = restaurants; // Set vertices from input
		m = restaurants -1; 	   // In this case edges were always 1 less than the number of vertices

		st = new StringTokenizer(br.readLine());
		int[] phoList = new int[phoRestaurants];
		for(int a = 0; a < phoRestaurants; a++) {
			phoList[a] = Integer.parseInt(st.nextToken());
		}


		for (i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			insertEdge(g, x, y, directed);	// directed is for the later decision making
		}

	}

	// Just initializes all values to 0
	static void initializeGraph(graph g) {
		int i;
		g.nvertices = 0;
		g.nedges = 0;

		for(i = 1; i <= MAXV; i++) {
			g.degree[i] = 0;
		}
	}


	static void insertEdge(graph g, int x, int y, boolean directed) {
		if (g.degree[x] > MAXDEGREE) { // Safety check
			System.out.println("Warning: insertion exceeds max degree");
		}
		g.edges[x][g.degree[x]] = y; // Add the connection from x, to y, in the degree position allotted to it
		g.degree[x] ++; // Increase the degree, as you've just added an edge

		if(directed == false) { // If it is not a directed graph
			// Recursively insert another edge with the vertices flipped
			insertEdge(g, y, x, true); // mark directed as true so there is not an infinite loop (one edge is already there)
		} else {
			g.nedges ++; // Increase the number of edges counter
		}
	}

	// Nested loop to print the array
	static void printGraph(graph g) {
		int i, j;	// counters

		for (i = 0; i < g.nvertices; i++) {
			System.out.print(i + ": ");
			for(j = 0; j < g.degree[i]; j++) {
				System.out.print(" " + g.edges[i][j]);
			}
			System.out.println();
		}
	}
}

class graph {
	int[][] edges = new int[Graphing.MAXV+1][Graphing.MAXDEGREE];
	int[] degree = new int[Graphing.MAXV+1];
	int nvertices;
	int nedges;
}
