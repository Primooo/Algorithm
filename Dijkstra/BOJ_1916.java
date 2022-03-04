package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
	int cost;
	int node;
	
	Node(int cost, int node) {
		this.cost = cost;
		this.node = node;
	}

	@Override
	public int compareTo(Node o) {
		return cost - o.cost;
	}
}

public class BOJ_1916 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int[] distArr;
	
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		distArr = new int[N+1];
		for(int i=1; i<N+1; i++) {
			distArr[i] = Integer.MAX_VALUE;
			for(int j=1; j<N+1; j++) {
				if(i != j) map[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			map[node1][node2] = dist;
		}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		distArr[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, start));
		boolean[] check = new boolean[N+1];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int cur_cost = cur.cost;
			int cur_node = cur.node;
			
			if(!check[cur_node]) {
				check[cur_node] = true;
				for(int i=1; i<=N; i++) {
					if(cur_node == i) continue;
					if(map[cur_node][i] == Integer.MAX_VALUE) continue;
					int next_node = i;
					int next_cost = map[cur_node][i];
					
					if(!check[next_node] && distArr[next_node] > cur_cost + next_cost) {
						distArr[next_node] = cur_cost + next_cost;
						pq.add(new Node (distArr[next_node], next_node));
					}
				}
			}
		}
		
		System.out.println(distArr[end]);
	}
}
