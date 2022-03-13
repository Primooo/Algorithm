package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14889 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] synergy;
	private static boolean[] visited;
	private static int minValue = 987654321;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0, 0, N);
		System.out.println(minValue);
	}
	
	private static void DFS(int count, int cur, int N) {
		if(count == N/2) {
			int calculated = calculate(N);
			if(minValue > calculated) minValue = calculated;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i] == false && i >= cur) {
				visited[i] = true;
				DFS(count+1, i, N);
				visited[i] = false;
			}
		}
	}

	private static int calculate(int N) {
		int team1Sum = 0;
		int team2Sum = 0;
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(visited[i] == true && visited[j] == true) {
					team1Sum += synergy[i][j] + synergy[j][i];
				}
				if(visited[i] == false && visited[j] == false) {
					team2Sum += synergy[i][j] + synergy[j][i];
				}
			}
		}
		return Math.abs(team1Sum-team2Sum);
	}
	
}
