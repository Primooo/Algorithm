package Simulation;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14499 {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int[] orders;
	private static int[] dice = new int[6];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		orders = new int[K];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<K; i++) {
			orders[i] = Integer.parseInt(st.nextToken());
		}
		
		int cur_x = x;
		int cur_y = y;
		// dice ¹è¿­ ±¸Á¶ : {À­¸é, ¾Æ·§¸é, µ¿ÂÊÀ¸·Î ±¼¸± ¶§ À­¸é, ¼­ÂÊÀ¸·Î ±¼¸± ¶§ À­¸é, ºÏÂÊÀ¸·Î ±¼¸± ¶§ À­¸é, ³²ÂÊÀ¸·Î ±¼¸±¶§ À­¸é}
		for(int i=0; i<K; i++) {
			if(orders[i] == 1) {		// µ¿ÂÊÀ¸·Î ±¼¸± ¶§
				if(cur_y + 1 < M) {
					cur_y++;
					swap(0, 2);
					swap(1, 3);
					swap(2, 3);
				}
				else {
					continue;
				}
				
			}
			
			else if(orders[i] == 2) {	// ¼­ÂÊÀ¸·Î ±¼¸± ¶§
				if(cur_y - 1 >= 0) {
					cur_y--;
					swap(0, 3);
					swap(1, 2);
					swap(2, 3);
				}
				else {
					continue;
				}
			}
			
			else if(orders[i] == 3) {	// ºÏÂÊÀ¸·Î ±¼¸± ¶§
				if(cur_x - 1 >= 0) {
					cur_x--;
					swap(0, 4);
					swap(1, 5);
					swap(4, 5);
				}
				else {
					continue;
				}
				
			}
			
			else {
				if(cur_x + 1 < N) {		// ³²ÂÊÀ¸·Î ±¼¸± ¶§
					cur_x++;
					swap(0, 5);
					swap(1, 4);
					swap(4, 5);
				}
				else {
					continue;
				}
			}
			if(map[cur_x][cur_y] == 0) {
				map[cur_x][cur_y] = dice[1];
			} else {
				dice[1] = map[cur_x][cur_y];
				map[cur_x][cur_y] = 0;
			}
			System.out.println(dice[0]);
		}
	}

	private static void swap(int idx1, int idx2) {
		int temp = dice[idx1];
		dice[idx1] = dice[idx2];
		dice[idx2] = temp;
	}
}
