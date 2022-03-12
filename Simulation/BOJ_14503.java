package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14503 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int answer = 0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine(), " ");
		int robot_x = Integer.parseInt(st.nextToken());
		int robot_y = Integer.parseInt(st.nextToken());
		int robot_dir = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeClean(robot_x, robot_y, robot_dir);
		System.out.println(answer);
	}

	private static void makeClean(int x, int y, int dir) {
		visited[x][y] = true;
		answer++;
		int cur_x, next_x;
		int cur_y, next_y;
		int cur_dir, next_dir;
		
		cur_x = next_x = x;
		cur_y = next_y = y;
		cur_dir = next_dir = dir;
		
		boolean canClean = true;
		int blockedCounter = 0;
		while(canClean) {
			if(blockedCounter == 4) {
				next_dir = cur_dir+2;
				if(next_dir > 3) {
					next_dir = next_dir % 4;
				}
				next_x = cur_x + dx[next_dir];
				next_y = cur_y + dy[next_dir];
				
				if(map[next_x][next_y] == 1) {
					canClean = false;
				}
				else {
					blockedCounter = 0;
					cur_x = next_x;
					cur_y = next_y;
				}
				continue;
			}
			else {
				next_dir = cur_dir-1;
				if(next_dir < 0) {
					next_dir = 3;
				}
				next_x = cur_x + dx[next_dir];
				next_y = cur_y + dy[next_dir];
			}
			
			if(map[next_x][next_y] == 0 && visited[next_x][next_y] == false) {
				answer++;
				visited[next_x][next_y] = true;
				cur_x = next_x;
				cur_y = next_y;
				blockedCounter = 0;
			}
			else if(map[next_x][next_y] == 1 || visited[next_x][next_y] == true) {
				blockedCounter++;
			}
			cur_dir = next_dir;
		}
	}
}
