package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class SWEA_Desert {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dx = {1, 1, -1, -1};
	private static int[] dy = {1, -1, -1, 1};
	private static int[][] map;
	private static int answer, N;
	
	private static boolean[][] visited;
	private static boolean[] deserts;
	
	public static void main(String args[]) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			answer = -1;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					visited = new boolean[N][N];
					deserts = new boolean[101];
					visited[i][j] = true;
					deserts[map[i][j]] = true;
					dfs(i, j, i, j, 0, 1);
				}
			}
			
			System.out.println("#"+ test_case + " " + answer);
		}
	}
	
	private static void dfs(int cur_x, int cur_y, int init_x, int init_y, int dir, int count) {
		for(int i=dir; i<4; i++) {
			int next_x = cur_x + dx[i];
			int next_y = cur_y + dy[i];
			
			if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < N) {
				if(next_x == init_x && next_y == init_y && count >= 4) {
					answer = Math.max(answer, count);
					return;
				}
				
				if(visited[next_x][next_y] == false && deserts[map[next_x][next_y]] == false) {
					visited[next_x][next_y] = true;
					deserts[map[next_x][next_y]] = true;
					dfs(next_x, next_y, init_x, init_y, i, count+1);
					visited[next_x][next_y] = false;
					deserts[map[next_x][next_y]] = false;
				}
			}
		}
	}
}
