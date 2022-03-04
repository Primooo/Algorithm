package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_11404 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine()); // ���� ����
		int m = Integer.parseInt(br.readLine()); // ���� ����
		int max = 100001;
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i!=j) map[i][j] = max;
			}
		}
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			if(map[s][e] > weight) {
				map[s][e] = weight;
			}
		}
		
		for(int k=0; k<n; k++) { // ���İ��� ���
			for(int i=0; i<n; i++) { // ��� ���
				for(int j=0; j<n; j++) { //���� ���
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
				
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == max) map[i][j] = 0;
				if(j!=n-1) 
					System.out.print(map[i][j] + " ");
				else 
					System.out.println(map[i][j]);
			}
		}
	}
}
