package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2583 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int[][] visited;
	private static int N, M;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s_y = Integer.parseInt(st.nextToken());
			int s_x = Integer.parseInt(st.nextToken());
			int e_y = Integer.parseInt(st.nextToken());
			int e_x = Integer.parseInt(st.nextToken());
			for(int j=s_x; j<e_x; j++) {
				for(int k=s_y; k<e_y; k++) {
					map[j][k] = 1; // 못가는 곳
				}
			}
		}
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 && visited[i][j] == 0) {
					int count = DFS(i, j);
					result.add(count);
				}
			}
		}
		result.sort(null);
		System.out.println(result.size());
		for(int i=0; i<result.size(); i++) {
			if(i == result.size()-1)
				System.out.print(result.get(i));
			else
				System.out.print(result.get(i) + " ");
		}
	}
	
	private static int DFS(int x, int y) {
		int cnt = 1;
		visited[x][y] = 1;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if(map[nx][ny] == 0 && visited[nx][ny] == 0) {
				cnt += DFS(nx, ny);
			}
		}
		return cnt;
	}

}
