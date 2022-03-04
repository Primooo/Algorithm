package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
	int s, e, cost;
	Edge(int s, int e, int cost) {
		this.s = s;
		this.e = e;
		this.cost = cost;
	}
}

public class BOJ_1197 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] parent;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<Edge> edges = new ArrayList<Edge>();
		int answer = 0;
		parent = new int[V+1];
		
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(n1, n2, w));
		}
		
		edges.sort(new Comparator<Edge> () {
			@Override
			public int compare(Edge e1, Edge e2) {
				return Integer.compare(e1.cost, e2.cost);
			}
		});
		
		for(int i=0; i<E; i++) {
			Edge edge = edges.get(i);
			if(!isSameParent(edge.s, edge.e)) {
				union(edge.s, edge.e);
				answer += edge.cost;
			}
		}
		
		System.out.println(answer);
	}

	public static int findParent(int num) {
		if(parent[num] == num) return num;
		else return parent[num] = findParent(parent[num]);
	}
	
	public static boolean isSameParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		if(x == y) return true;
		else return false;
	}
	
	public static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		if(x != y) {
			parent[y] = x;
		}
	}
}
