package Simulation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14503 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, M;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		int cur_x = Integer.parseInt(st.nextToken())-1;
		int cur_y = Integer.parseInt(st.nextToken())-1;
		int cur_dir = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			String mapStr = br.readLine();
			String[] splitedStr = mapStr.split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(splitedStr[j]);
			}
		}
		// 현재 위치 청소
		int clean_counter = 0;
		int counter = 0;
		if(map[cur_x][cur_y] == 0) {
			map[cur_x][cur_y] = 2;
			counter++;
		}
		boolean canMove = true;
		
		while(canMove) {
			int next_x = -1;
			int next_y = -1;
			if(cur_dir == 0) {
				if(clean_counter == 4) {
					next_x = cur_x+1;
					next_y = cur_y;
					if(wallCheck(next_x, next_y)) {
						break;
					} else {
						cur_x = next_x;
					}
					continue;
				}
				
				if(map[cur_x][cur_y-1] == 0) {
					cur_y = cur_y-1;
					map[cur_x][cur_y] = 2;
					counter++;
					clean_counter = 0;
					cur_dir = 3;
				} else {
					clean_counter++;
					cur_dir = 3;
				}
			} else if (cur_dir == 1) {
				if(clean_counter == 4) {
					next_x = cur_x;
					next_y = cur_y-1;
					if(wallCheck(next_x, next_y)) {
						break;
					} else {
						cur_y = next_y;
					}
					continue;
				}
				
				if(map[cur_x-1][cur_y] == 0) {
					cur_x = cur_x-1;
					map[cur_x][cur_y] = 2;
					counter++;
					clean_counter = 0;
					cur_dir = 0;
				} else {
					clean_counter++;
					cur_dir = 0;
				}
				
			} else if (cur_dir == 2) {
				if(clean_counter == 4) {
					next_x = cur_x-1;
					next_y = cur_y;
					if(wallCheck(next_x, next_y)) {
						break;
					} else {
						cur_x = next_x;
					}
					continue;
				}
				
				if(map[cur_x][cur_y+1] == 0) {
					cur_y = cur_y+1;
					map[cur_x][cur_y] = 2;
					counter++;
					clean_counter = 0;
					cur_dir = 1;
				} else {
					clean_counter++;
					cur_dir = 1;
				}
				
			} else {
				if(clean_counter == 4) {
					next_x = cur_x;
					next_y = cur_y+1;
					if(wallCheck(next_x, next_y)) {
						break;
					} else {
						cur_y = next_y;
					}
					continue;
				}
				
				if(map[cur_x+1][cur_y] == 0) {
					cur_x = cur_x+1;
					map[cur_x][cur_y] = 2;
					counter++;
					clean_counter = 0;
					cur_dir = 2;
				} else {
					clean_counter++;
					cur_dir = 2;
				}
				
			}
		}
		System.out.println(counter);
	}
	
	private static boolean wallCheck(int x, int y) {
		if(map[x][y] == 1) {
			return true;
		} else {
			return false;
		}
	}

}
