package MST;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Graph implements Comparable<Graph>{
	int weight;
	int start;
	int end;

	Graph(int weight, int start, int end) {
		this.weight = weight;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Graph g) {
		return this.weight - g.weight;
	}
}

public class BOJ_17472 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int map[][];
	private static boolean visited[][];
	private static int dx[] = {1, -1, 0, 0};
	private static int dy[] = {0, 0, 1, -1};
	private static int parents[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int areaNum = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] == false && map[i][j] == 1) {
					DFS(i, j, N, M,areaNum);
					areaNum++;
				}
			}
		}

		int dist[][] = new int[areaNum][areaNum];
		for(int i=0; i<areaNum; i++) {
			for(int j=0; j<areaNum; j++) {
				dist[i][j] = 11; // init
			}
		}
		checkDist(dist, N, M);
		ArrayList<Graph> arrList = new ArrayList<Graph>();
		parents = new int[areaNum];
		for(int i=1; i<areaNum; i++) {
			parents[i] = i;
		}
		
		for(int i=1; i<areaNum; i++) {
			for(int j=i+1; j<areaNum; j++) {
				if(dist[i][j] != 11) {
					arrList.add(new Graph(dist[i][j], i, j));
				}
			}
		}
		Collections.sort(arrList);
		int edgeCounter = 0;
		int answer = 0;

		for(int i=0; i<arrList.size(); i++) {
			int weight = arrList.get(i).weight;
			int start = arrList.get(i).start;
			int end = arrList.get(i).end;
			
			if(!isSameParent(start, end)) {
				unionNode(start, end);
				answer += weight;
				edgeCounter++;
			}
		}
		
		if(edgeCounter == areaNum - 2) {
			System.out.println(answer);
		}
		else System.out.println(-1);
		
	}
	
	private static int findParent(int node) {
		if(parents[node] == node) return node;
		return parents[node] = findParent(parents[node]);
	}
	
	private static boolean isSameParent(int n1, int n2) {
		n1 = findParent(n1);
		n2 = findParent(n2);
		if(n1 == n2) return true;
		else return false;
	}
	
	private static void unionNode(int n1, int n2) {
		n1 = findParent(n1);
		n2 = findParent(n2);
		parents[n2] = n1;
	}
	
	private static void DFS(int x, int y, int N, int M, int areaNum) {
		visited[x][y] = true;
		map[x][y] = areaNum;
		
		for(int i=0; i<4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			
			if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M) continue;
			if(map[next_x][next_y] == 1 && visited[next_x][next_y] == false) {
				DFS(next_x, next_y, N, M, areaNum);
			}
		}
	}
	
	private static void checkDist(int dist[][], int N, int M) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					int start = map[i][j];
					for(int k=0; k<4; k++) {
						int distance = 0;
						int next_x = i + dx[k];
						int next_y = j + dy[k];
						
						while(true) {
							if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M) break;
							if(map[next_x][next_y] == start) {
								break;
							}
							else if(map[next_x][next_y] == 0) {
								distance++;
							}
							else {
								int end = map[next_x][next_y];
								
								if(distance != 1 && dist[start][end] > distance) {
									dist[start][end] = distance;
								}
								break;
							}
							next_x += dx[k];
							next_y += dy[k];
						}
					}
				}
			}
		}
	}

}
