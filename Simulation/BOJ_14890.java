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
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			answer += solution(true, i, N, L);
			answer += solution(false, i, N, L);
		}
		System.out.println(answer);
	}
	
	private static int solution(boolean isRow, int num, int N, int L) {
		int idx = 1;
		int[] cp_arr = new int[N];
		boolean[] visited = new boolean[N];
		if(isRow) {
			cp_arr = map[num].clone();
		}
		else {
			for(int i=0; i<N; i++) {
				cp_arr[i] = map[i][num];
			}
		}		
		
		int beforeLevel = cp_arr[0];
		int sameLevelCounter = 1;
		boolean decendingFlag = false;
		boolean flag = true;
		while(idx < N) {
			int currentLevel = cp_arr[idx];
			if(Math.abs(beforeLevel - currentLevel) > 1) {
				flag = false;
				break;
			}
			
			if(beforeLevel == currentLevel) {
				sameLevelCounter++;
				if(sameLevelCounter == L && decendingFlag == true) {
					decendingFlag = false;
					sameLevelCounter = 0;
				}
				else if(sameLevelCounter != L && decendingFlag == true) {
					visited[idx] = true;
				}
			}
			else if(beforeLevel < currentLevel) {
				if(sameLevelCounter < L) {
					flag = false;
					break;
				}				
				boolean checkVisited = false;
				for(int i = idx-L; i<idx; i++) {
					if(visited[i] == true) {
						checkVisited = true;
						break;
					}
				}
				if(checkVisited == true) {
					flag = false;
					break;
				}
				sameLevelCounter = 1;
			}
			else {
				if(decendingFlag == true && sameLevelCounter < L) {
					flag = false;
					break;
				}
				
				if(L == 1) {
					decendingFlag = false;
				}
				else {
					decendingFlag = true;
				}
				visited[idx] = true;
				sameLevelCounter = 1;
			}
			
			if(idx == N-1 && decendingFlag) flag = false;
			beforeLevel = currentLevel;
			idx++;
		}
		
		if(flag) {
			return 1;
		} else {
			return 0;
		}
	}

}
