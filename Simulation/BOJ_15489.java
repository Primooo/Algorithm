package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15489 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int[][] map = new int[31][31];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		map[1][1] = 1;
		for(int i=2; i<31; i++) {
			for(int j=1; j<=i; j++) {
				if(j == 1 || j == i) map[i][j] = 1;
				else {
					map[i][j] = map[i-1][j-1] + map[i-1][j];
				}
			}
		}
		int result = 0;
		for(int i=0; i<W; i++) {
			for(int j=0; j<=i; j++) {
				result += map[R+i][C+j];
			}
		}
		System.out.println(result);
	}

}
