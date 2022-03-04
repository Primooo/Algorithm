package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_1238 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int map[][];
	private static final int max = 987654321;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int endpoint = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i!=j)
					map[i][j] = max;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map[s][e] = w;
		}
		
		for(int k=1; k<N+1; k++) {
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int longest = -1;
		for(int i=1; i<N+1; i++) {
			int dist = map[i][endpoint] + map[endpoint][i];
			if(longest < dist)
				longest = dist;
		}
		System.out.println(longest);
	}
}
