package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_Honey {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int N, M, C;
	private static int profitA, profitB;
	private static int answer;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			answer = 0;
			profitA = profitB = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int workerA_x = i;
					int workerA_y = j;
					int workerB_x = i;
					int workerB_y = j+M;
					
					if(workerA_y + M > N) {
						continue;
					}
					
					while(workerB_x < N) {
						if(workerB_y + M > N) {
							workerB_y = 0;
							workerB_x++;
							continue;
						}
						profitA = profitB = 0;
						for(int k=1; k<=M; k++) {
							check(workerA_x, workerA_y, workerA_y, 0, k, 0, 0, true);
							check(workerB_x, workerB_y, workerB_y, 0, k, 0, 0, false);
						}
						if(profitA + profitB > answer) {
							answer = profitA + profitB;
						}
						workerB_y++;
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static void check(int x, int y, int cur, int depth, int n, int value, int sum, boolean flag) {
		if(depth == n) {
			if(sum > C) {
				return;
			}
			if(flag == true && profitA < value) {
				profitA = value;
			}
			else if(flag == false && profitB < value) {
				profitB = value;
			}
			return;
		}
		
		for(int i=cur; i<y+M; i++) {
			int calculated = value + map[x][i] * map[x][i];
			check(x, y, i+1, depth+1, n, calculated, sum + map[x][i], flag);
		}
	}
	
//	private static void check(int a_x, int a_y, int b_x, int b_y, int m, int c) {
//		int maxA = 0;
//		int maxB = 0;
//		for(int i=a_y; i<a_y + m; i++) {
//			int honeySum = 0;
//			int profitSum = 0;
//			for(int j=i; j<a_y + m; j++) {
//				if(honeySum + map[a_x][j] > c) {
//					continue;
//				}
//				profitSum += map[a_x][j] * map[a_x][j];
//				honeySum += map[a_x][j];
//			}
//			if(profitSum > maxA) {
//				maxA = profitSum;
//			}
//		}
//		
//		for(int i=b_y; i<b_y + m; i++) {
//			int honeySum = 0;
//			int profitSum = 0;
//			for(int j=i; j<b_y + m; j++) {
//				if(honeySum + map[b_x][j] > c) {
//					continue;
//				}
//				profitSum += map[b_x][j] * map[b_x][j];
//				honeySum += map[b_x][j];
//			}
//			if(profitSum > maxB) {
//				maxB = profitSum;
//			}
//		}
//		
//		if(answer < maxA + maxB) {
//			answer = maxA + maxB;
//		}
//	}
}
