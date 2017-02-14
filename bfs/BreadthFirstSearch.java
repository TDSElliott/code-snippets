package bfs;

/**
 * Created by Tyler on 13/02/2017.
 */
public class BreadthFirstSearch {
	static int MAXV; static int MAXDEGREE;
	public static void main(String[] args) {
		MAXV = 1000; MAXDEGREE = 500;
	}

	boolean[] processed = new boolean[MAXV];  // vertices that have been processed
	boolean[] discovred = new boolean [MAXV]; // which vertices have been found
	int[] parent = new int[MAXV];             // discovery relation

	void readGraph(graph g, boolean directed) {
		int i;
		int m;
		int x, y;

		initializeGraph(g);
	}

	void initializeGraph(graph g) {
		int i;
		g.nvertics = 0;
		g.nedges = 0;

		for(i = 1; i <= MAXV; i++) {
			g.degree[i] = 0;
		}
	}
}

class graph {
		int[][] edges = new int[BreadthFirstSearch.MAXV+1][BreadthFirstSearch.MAXDEGREE];
		int[] degree = new int[BreadthFirstSearch.MAXV+1];
		int nvertics;
		int nedges;
		}
