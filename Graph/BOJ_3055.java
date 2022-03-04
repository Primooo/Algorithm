package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3055 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[][] map;
	private static int[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		// init
		int s_x = 0;
		int s_y = 0;
		int e_x = 0;
		int e_y = 0;
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					s_x = i;
					s_y = j;
					visited[i][j] = 1;
				} else if(map[i][j] == '*') {
					visited[i][j] = -1;
				} else if(map[i][j] == 'D') {
					e_x = i;
					e_y = j;
				}
			}
		}
		
		int beforeCounter = -1;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {s_x, s_y, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cur_x = cur[0];
			int cur_y = cur[1];
			int cur_counter = cur[2];
			
			if(cur_x == e_x && cur_y == e_y) {
				System.out.println(cur_counter);
				return;
			}
			
			if(cur_counter != beforeCounter) {
				ArrayList<int[]> waterSpots = new ArrayList<>();
				// 물이 차오르게 하는 곳
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(map[i][j] == '*') {
							waterSpots.add(new int[] {i, j});
						}
					}
				}
				
				for(int i=0; i<waterSpots.size(); i++) {
					int x = waterSpots.get(i)[0];
					int y = waterSpots.get(i)[1];
					for(int j=0; j<4; j++) {
						int nx = x+dx[j];
						int ny = y+dy[j];
						if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
						if(map[nx][ny] == '.' || map[nx][ny] == 'S') {
							map[nx][ny] = '*';
							visited[nx][ny] = -1;
						}
					}
				}
			}
			
			for(int i=0; i<4; i++) {
				int next_x = cur_x + dx[i];
				int next_y = cur_y + dy[i];

				if(next_x < 0 || next_x >= R || next_y < 0 || next_y >= C) continue;
				if(map[next_x][next_y] == '.' || map[next_x][next_y] == 'D') {
					if(visited[next_x][next_y] == 0) {
						q.add(new int[] {next_x, next_y, cur_counter+1});
						visited[next_x][next_y] = 1;
					}
				}
			}
			beforeCounter = cur_counter;
		}
		System.out.println("KAKTUS");
	}

}
