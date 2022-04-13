package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_HomeSecurity {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int answer;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int houseCounter = 0;
			answer = 1;
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						houseCounter++;
					}
				}
			}
			
			int maxK = getMaxK(M, houseCounter);
			for(int i=2; i<=maxK; i++) {
				calculate(M, i);
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static int getMaxK(int M, int houseCounter) {
		int maxProfit = M * houseCounter;
		int K = 1;
		while(K*K + (K-1)*(K-1) < maxProfit) {
			K++;
		}
		return K-1;
	}
	
	private static void calculate(int M, int K) {
		int opCost = K*K + (K-1)*(K-1);
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				int center_x = i;
				int center_y = j;
				int dist = 0;
				int houseCount = 0;
				for(int l=center_x-(K-1); l<center_x+K; l++) {
					for(int k=center_y-dist; k<=center_y+dist; k++) {
						if(l<0 || l >= map.length || k<0 || k>= map.length) continue; 
						if(map[l][k] == 1) {
							houseCount++;
						}
					}
					
					if(l < center_x) {
						dist++;
					}
					else {
						dist--;
					}
				}
				
				if(houseCount * M >= opCost && answer < houseCount) {
					answer = houseCount;
				}
			}
		}
	}

}
