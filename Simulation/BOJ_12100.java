package Simulation;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12100 {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int map[][];
	private static int temp[][];
	private static ArrayList<Integer> arrList;
	private static int answer = -1;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		arrList = new ArrayList<>();
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, N);
		System.out.println(answer);
	}
	
	private static void DFS(int seq, int N) {
		if(seq == 5) {
			temp = new int[N][N];
			for(int i=0; i<N; i++) {
				temp[i] = map[i].clone();
			}
			
			for(int i=0; i<arrList.size(); i++) {
				moveBlocks(arrList.get(i), N);
			}
			
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(temp[j][k] > answer) answer = temp[j][k];
				}
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			arrList.add(i);
			DFS(seq+1, N);
			arrList.remove(arrList.size()-1);
		}
	}
	
	private static void moveBlocks(int dir, int N) {
		boolean visited[][] = new boolean[N][N];
		
		if(dir == 0) {			// 위로 옮길때
			for(int i=0; i<N; i++) { // 열 선택
				for(int j=1; j<N; j++) {
					for(int k=j-1; k>=0; k--) {
						if(temp[k][i] != 0 && temp[k][i] == temp[k+1][i]) {
							if(visited[k][i] == false && visited[k+1][i] == false) {
								temp[k][i] += temp[k+1][i];
								temp[k+1][i] = 0;
								visited[k][i] = true;
							}
						}
						else if(temp[k][i] == 0) {
							temp[k][i] = temp[k+1][i];
							temp[k+1][i] = 0;
						}
					}
				}
			}
		}
		else if(dir == 1) {		// 오른쪽으로 옮길 때
			for(int i=0; i<N; i++) { // 행 선택
				for(int j=N-2; j>=0; j--) {
					for(int k=j+1; k<N; k++) {
						if(temp[i][k] != 0 && temp[i][k] == temp[i][k-1]) {
							if(visited[i][k] == false && visited[i][k-1] == false) {
								temp[i][k] += temp[i][k-1];
								temp[i][k-1] = 0;
								visited[i][k] = true;
							}
						}
						else if(temp[i][k] == 0) {
							temp[i][k] = temp[i][k-1];
							temp[i][k-1] = 0;
						}
					}
				}
			}
			
		}
		else if(dir == 2) {		// 아래로 옮길 때
			for(int i=0; i<N; i++) { // 열 선택
				for(int j=N-2; j>=0; j--) {
					for(int k=j+1; k<N; k++) {
						if(temp[k][i] != 0 && temp[k][i] == temp[k-1][i]) {
							if(visited[k][i] == false && visited[k-1][i] == false) {
								temp[k][i] += temp[k-1][i];
								temp[k-1][i] = 0;
								visited[k][i] = true;
							}
						}
						else if(temp[k][i] == 0) {
							temp[k][i] = temp[k-1][i];
							temp[k-1][i] = 0;
						}
					}
				}
			}
			
		}
		else {		// 왼쪽으로 옮길 때
			for(int i=0; i<N; i++) { // 행 선택
				for(int j=1; j<N; j++) {
					for(int k=j-1; k>=0; k--) {
						if(temp[i][k] != 0 && temp[i][k] == temp[i][k+1]) {
							if(visited[i][k] == false && visited[i][k+1] == false) {
								temp[i][k] += temp[i][k+1];
								temp[i][k+1] = 0;
								visited[i][k] = true;
							}
						}
						else if(temp[i][k] == 0) {
							temp[i][k] = temp[i][k+1];
							temp[i][k+1] = 0;
						}
					}
				}
			}
		}
	}
}
