package MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edges implements Comparable<Edges>{
	int start;
	int end;
	int weight;
	
	Edges(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edges e) {
		return this.weight - e.weight;
	}
}

public class BOJ_16202 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] graph;
	private static int[] parents;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		parents = new int[N+1];
		ArrayList<Edges> edgeList = new ArrayList<Edges>();
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			graph[n1][n2] = i;
			edgeList.add(new Edges(n1, n2, i));
		}
		Collections.sort(edgeList);
		
		for(int i=0; i<K; i++) {
			initParents(N);
			int edgeCounter = 0;
			int sum = 0;
			
			for(int j=0; j<edgeList.size(); j++) {
				Edges currentEdge = edgeList.get(j);
				
				if(!isSameParent(currentEdge.start, currentEdge.end)) {
					unionNode(currentEdge.start, currentEdge.end);
					sum += currentEdge.weight;
					edgeCounter++;
				}
			}
			
			if(edgeCounter == N-1) {
				if(i == K-1) {
					System.out.print(sum);
				}
				else {
					System.out.print(sum + " ");
				}
			}
			else {
				if(i == K-1) {
					System.out.print(0);
				}
				else {
					System.out.print(0 + " ");
				}
			}
			
			edgeList.remove(0);
		}
	}
	
	private static void initParents(int N) {
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	private static void unionNode(int n1, int n2) {
		n1 = findParent(n1);
		n2 = findParent(n2);
		parents[n2] = n1;
	}
	
	private static boolean isSameParent(int n1, int n2) {
		n1 = findParent(n1);
		n2 = findParent(n2);
		if(n1 == n2) return true;
		else return false;
	}
	
	private static int findParent(int node) {
		if(parents[node] == node) {
			return node;
		}
		else {
			return parents[node] = findParent(parents[node]);
		}
	}

}
