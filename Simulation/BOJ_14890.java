package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ_14890 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}

}
