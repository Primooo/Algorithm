package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2644 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static boolean[][] visited;
	private static int answer = 0;
	
	private static void dfs(int cur, int target, int step, int n) {
		if(cur == target) {
			answer = step;
			return;
		}
		
		for(int i=1; i<=n; i++) {
			if(visited[cur][i] == false && map[cur][i] != 0) {
				visited[cur][i] = true;
				visited[i][cur] = true;
				dfs(map[cur][i], target, step+1, n);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			map[node1][node2] = node2;
			map[node2][node1] = node1;
		}
		
		dfs(n1, n2, 0, n);
		if(answer == 0) System.out.println(-1);
		else System.out.println(answer);
	}

}
