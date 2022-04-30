package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_Climbling {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int N, K, answer;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static ArrayList<Point> topList;
	
	public static void main(String[] args) throws Exception{
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			answer = 0;
			topList = new ArrayList<Point>();
			int topHeight = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					topHeight = Math.max(map[i][j], topHeight);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == topHeight) {
						topList.add(new Point(i, j));
					}
				}
			}
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=1; k<=K; k++) {
						map[i][j] -= k;
						for(int l=0; l<topList.size(); l++) {
							dfs(topList.get(l).x, topList.get(l).y, 1);
						}
						map[i][j] += k;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static void dfs(int x, int y, int length) {
		for(int i=0; i<4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			
			if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= N) continue;
			if(map[next_x][next_y] < map[x][y]) {
				dfs(next_x, next_y, length+1);
			}
		}
		
		if(answer < length) {
			answer = length;
		}
	}
}
