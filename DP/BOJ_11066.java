package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11066 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int K = Integer.parseInt(br.readLine());
			int[] data = new int[K];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<K; j++) {
				data[j] = Integer.parseInt(st.nextToken());
			}
			
			int[][] grid = new int[K][K];
			int step = 1;
			int x = 0;
			int y = step;
			while(step < K) {
				int min = 987654321;
				int totalFiles = 0;
				for(int idx=x; idx<=y; idx++) {
					totalFiles += data[idx];
				}
				for(int idx=x; idx<y; idx++) {
					int value = grid[x][idx] + grid[idx+1][y] + totalFiles;
					if(min > value) {
						min = value;
					}
				}
				grid[x][y] = min;
				x++;
				y++;
				if(y == K) {
					x = 0;
					step++;
					y = step;
				}
			}
			System.out.println(grid[0][K-1]);
		}
	}

}
