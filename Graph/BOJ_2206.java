package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2206 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, 0, 1});
		visited[0][0] = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cur_x = cur[0];
			int cur_y = cur[1];
			int cur_breakCounter = cur[2];
			int cur_length = cur[3];
			
			if(cur_x == N-1 && cur_y == M-1) {
				System.out.println(cur_length);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = cur_x + dx[i];
				int ny = cur_y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visited[nx][ny] <= cur_breakCounter) continue;
				
				if(map[nx][ny] == 0) {
					visited[nx][ny] = cur_breakCounter;
					q.add(new int[] {nx, ny, cur_breakCounter, cur_length+1});
				} else {
					if(cur_breakCounter == 0) {
						visited[nx][ny] = cur_breakCounter+1;
						q.add(new int[] {nx, ny, cur_breakCounter+1, cur_length+1});
					}
				}
//				if(cur_breakCounter == 1) {
//					if(map[nx][ny] == 0 && visited[nx][ny] == 0) {
//						visited[nx][ny] = 1;
//						q.add(new int[] {nx, ny, cur_breakCounter, cur_length+1});
//					}
//				} else {
//					if(visited[nx][ny] == 0) {
//						visited[nx][ny] = 1;
//						q.add(new int[] {nx, ny, cur_breakCounter+1, cur_length+1});
//					}
//				}
				
			}
		}
		System.out.println(-1);
	}
}
