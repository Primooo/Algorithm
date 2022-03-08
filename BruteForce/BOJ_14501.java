package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14501 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] table;
	private static int answer = 0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		table = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<2; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		
		DFS(0, 0, N);
		System.out.println(answer);
	}

	private static void DFS(int day, int cost, int N) {
		if(day >= N) {
			if(answer < cost) answer = cost;
			return;
		}
		
		if(day + table[day][0] <= N) {
			DFS(day + table[day][0], cost + table[day][1], N);
		}
		
		if(day + 1 <= N) {
			DFS(day+1, cost, N);
		}
	}
}
