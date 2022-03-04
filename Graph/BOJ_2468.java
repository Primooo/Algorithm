package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2468 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	private static boolean[][] visited;
	private static int[][] map;
	private static int N;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int highest = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(highest <= map[i][j]) highest = map[i][j];
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=highest; i++) {
			visited = new boolean[N][N];
			int safePlaces = 0;
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(map[x][y] > i && visited[x][y] == false) {
						safePlaces++;
						visited[x][y] = true;
						DFS(x, y, i);
					}
				}
			}
			if(max <= safePlaces) max = safePlaces;
		}
		System.out.println(max);		
	}
	
	private static void DFS(int x, int y, int standard) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(map[nx][ny] > standard && visited[nx][ny] == false) {
				visited[nx][ny] = true;
				DFS(nx, ny, standard);
			}
		}
	}
}
