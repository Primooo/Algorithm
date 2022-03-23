package Programmers;

import java.util.*;
/*
 * 2022 ㅆㅋ Backend 1번문제 다시 풀기
 * Category: DP
 */

public class CAR_Bicycle_RENT {
	private static int[][] map;
	public static int[] solution(int n, int k, int[][] roads) {
		int[][] map = new int[n+1][n+1];
		boolean[][] dp = new boolean[n+1][k+1];
		ArrayList<Integer> answerList = new ArrayList<>();
		
		for(int i=0; i<roads.length; i++) {
			int x = roads[i][0];
			int y = roads[i][1];
			int w = roads[i][2];
			map[x][y] = map[y][x] = w;
		}
		
		dp[0][0] = true;
		for(int dist=1; dist<=k; dist++) {	// 거리(bottom-up 방식으로, 원하는 거리보다 작은 거리부터 채워나감(DP))
			for(int i=0; i<n; i++) {		// 가고자 하는 지점
				for(int j=0; j<n; j++) {	// 가고자 하는 지점과 연결되어 있는 지점
					if(map[i][j] != 0 && dist - map[i][j] >= 0) {	
						dp[i][dist] = dp[j][dist-map[i][j]] || dp[i][dist];
					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			if(dp[i][k] == true) {
				answerList.add(i);
			}
		}
		
		int[] answer = new int[answerList.size()];
		for(int i=0; i<answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] roads = {{5, 4, 6}, {5, 2, 5}, {0, 4, 2}, {2, 3, 3}, {1, 2, 7}, {0, 1, 3}};
		int[] answer = solution(6, 17, roads);
		if(answer.length == 0) {
			System.out.println(-1);
			return;
		}
		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}

}
