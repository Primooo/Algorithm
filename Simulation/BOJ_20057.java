package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20057 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {-1, 0, 1, 0};
	private static int[] moveCount = {1, 1, 2, 2};
	private static int[][] dxx = {
			{-1, 1, -2, 2, -1, 1, -1, 1, 0}, 
			{-1, -1, 0, 0, 0, 0, 1, 1, 2},
			{-1, 1, -2, 2, -1, 1, -1, 1, 0}, 
			{1, 1, 0, 0, 0, 0, -1, -1, -2}
	};
	private static int[][] dyy = {
			{1, 1, 0, 0, 0, 0, -1, -1, -2}, 
			{-1, 1, -2, 2, -1, 1, -1, 1, 0},
			{-1, -1, 0, 0, 0, 0, 1, 1, 2}, 
			{-1, 1, -2, 2, -1, 1, -1, 1, 0}
	};
	private static int[] ratio = {1, 1, 2, 2, 7, 7, 10, 10, 5};
	private static int[][] map;
	private static int N;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(calculate(N/2, N/2));
	}
	
	private static int calculate(int x, int y) {
		int result = 0;
		int cur_x = x;
		int cur_y = y;
		
		while(true) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<moveCount[i]; j++) {
					int next_x = cur_x + dx[i];
					int next_y = cur_y + dy[i];
					
					if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= N) {
						return result;
					}
					
					int sand = map[next_x][next_y];
					map[next_x][next_y] = 0;
					int spreadTotal = 0;
					
					for(int spot = 0; spot < 9; spot++) {
						int sand_x = next_x + dxx[i][spot];
						int sand_y = next_y + dyy[i][spot];
						int amount = (sand*ratio[spot])/100;
						if(sand_x < 0 || sand_x >= N || sand_y < 0 || sand_y >= N) {
							result += amount;
						}
						else {
							map[sand_x][sand_y] += amount;
						}
						spreadTotal += amount;
					}
					
					int alpha_x = next_x + dx[i];
					int alpha_y = next_y + dy[i];
					int alphaAmount = sand - spreadTotal;
					if(alpha_x < 0 || alpha_x >= N || alpha_y < 0 || alpha_y >= N) {
						result += alphaAmount;
					}
					else {
						map[alpha_x][alpha_y] += alphaAmount;
					}
					
					cur_x = next_x;
					cur_y = next_y;
				}
			}
			
			for(int j=0; j<4; j++) {
				moveCount[j] += 2;
			}
		}
	}
}
