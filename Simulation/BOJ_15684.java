package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Coor {
	int x;
	int y;
	
	Coor(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15684 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, M, H;
	private static int[][] ladder;
	private static int answer = 4;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new int[H+2][N+1];
		int bridgeNum = 1;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ladder[x][y] = bridgeNum;
			ladder[x][y+1] = bridgeNum++;
		}
		
		for(int i=0; i<=3; i++) {
			dfs(0, 1, i, bridgeNum);
			if(answer == i) break;
		}
				
		if(answer > 3) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}
	
	private static void dfs(int depth, int idx, int n, int bridgeNum) {
		if(depth == n) {
			if(check()) {
				if(answer > n) answer = n;
			}
			return;
		}
		
		for(int i=idx; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(ladder[i][j] == 0 && ladder[i][j+1] == 0) {
					ladder[i][j] = bridgeNum;
					ladder[i][j+1] = bridgeNum++;
					dfs(depth+1, i, n, bridgeNum);
					ladder[i][j] = 0;
					ladder[i][j+1] = 0;
				}
			}
		}
	}
	
	private static boolean check() {
		int heightMax = ladder.length;
		int widthMax = ladder[0].length;
		int[] resultArr = new int[widthMax];
		
		for(int i=1; i<widthMax; i++) {
			int cur_x = 0;
			int cur_y = i;
			boolean flag = false;
			while(cur_x < heightMax) {
				if(ladder[cur_x][cur_y] == 0) {
					cur_x++;
				}
				else {
					if(flag == false) {
						if(cur_y+1 < widthMax) {
							if(ladder[cur_x][cur_y+1] == ladder[cur_x][cur_y]) {
								cur_y++;
								flag = true;
								continue;
							}
						}
						
						if(cur_y-1 >= 1) {
							if(ladder[cur_x][cur_y-1] == ladder[cur_x][cur_y]) {
								cur_y--;
								flag = true;
							}
						}
					}
					else {
						cur_x++;
						flag = false;
					}
				}
			}
			resultArr[i] = cur_y;
			if(resultArr[i] != i) return false;
		}
		return true;
	}

}
