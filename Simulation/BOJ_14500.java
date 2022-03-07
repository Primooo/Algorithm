package Simulation;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14500 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int answer = 0;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	// ╩С - 0, го - 1, аб - 2, ©Л - 3
	private static final int tetromino[][] = {
			{3, 3, 3}, {1, 1, 1},
			{3, 1, 2},
			{1, 1, 3}, {1, 1, 2}, {0, 0, 3}, {0, 0, 2}, {3, 3, 0}, {3, 3, 1}, {2, 2, 0}, {2, 2, 1},
			{1, 3, 1}, {1, 2, 1}, {0, 3, 0}, {0, 2, 0}, {3, 0, 3}, {3, 1, 3}, {2, 0, 2}, {2, 1, 2},
			{0, 3, 2, 0}, {0, 2, 3, 0}, {3, 0, 1, 3}, {3, 1, 0, 3}, {1, 3, 2, 1}, {1, 2, 3, 1}, {2, 0, 1, 2}, {2, 1, 0, 2}
	};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				calculate(i, j, N, M);
			}
		}
		System.out.println(answer);
	}

	private static void calculate(int x, int y, int N, int M) {
		for(int i=0; i<tetromino.length; i++) {
			int cur_x = x;
			int cur_y = y;
			int count = 0;
			int sum = map[x][y];
			for(int j=0; j<tetromino[i].length; j++) {
				cur_x += dx[tetromino[i][j]];
				cur_y += dy[tetromino[i][j]];
				if(cur_x < 0 || cur_x >= N || cur_y < 0 || cur_y >= M) continue;
				if(tetromino[i].length == 4 && j == 2) continue;
				count++;
				sum += map[cur_x][cur_y];
			}
			
			if(count >= 3 && sum > answer) {
				answer = sum;
			}
		}
	}
}
