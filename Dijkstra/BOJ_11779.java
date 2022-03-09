package Dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Nodes implements Comparable<Nodes>{
	int node;
	int cost;
	
	Nodes(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Nodes n) {
		return cost - n.cost;
	}
}

public class BOJ_11779 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int[] dist;
	private static int[] route;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		dist = new int[N+1];
		route = new int[N+1];
		for(int i=1; i<=N; i++) {
			dist[i] = 987654321;
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(map[n1][n2] == 0) map[n1][n2] = weight;
			else {
				if(map[n1][n2] > weight) map[n1][n2] = weight;
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Nodes> pq = new PriorityQueue<Nodes>();
		dist[start] = 0;
		pq.add(new Nodes(start, 0));
		
		while(!pq.isEmpty()) {
			Nodes curNode = pq.poll();
			
			for(int i=1; i<=N; i++) {
				if(curNode.node == i) continue;
				int next_cost = map[curNode.node][i];
				
				if(next_cost != 0 && dist[i] > curNode.cost + next_cost) {
					dist[i] = curNode.cost + next_cost;
					route[i] = curNode.node;
					pq.add(new Nodes(i, dist[i]));
				}
			}
		}
		
		System.out.println(dist[end]);
		ArrayList<Integer> arrayList = new ArrayList<>();
		int tmp = end;
		while(tmp != 0) {
			arrayList.add(tmp);
			tmp = route[tmp];
		}
		System.out.println(arrayList.size());
		for(int i=arrayList.size()-1; i>=0; i--) {
			System.out.print(arrayList.get(i) + " ");
		}
	}

}
