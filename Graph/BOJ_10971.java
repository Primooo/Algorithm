package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_10971 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static boolean[] visited;
	private static int result = 987654321;
	private static ArrayList<Integer> idxList = new ArrayList<>();
	
	public static void backTracking(int n, int cnt) {
		if(n == cnt) {
			int distSum = 0;
			for(int i=1; i<idxList.size(); i++) {
				int start = idxList.get(i-1);
				int end = idxList.get(i);
				if(map[start][end] == 0) return;
				distSum += map[start][end];
//				System.out.print(start + " - " + end);
			}
//			System.out.println(distSum);
			int e = idxList.get(idxList.size()-1);
			int s = idxList.get(0);
			if(map[e][s] == 0) return;
			distSum += map[e][s];
			if(distSum < result) result = distSum;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				idxList.add(i);
				backTracking(n, cnt+1);
				idxList.remove(idxList.size()-1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			String[] parsedStr = br.readLine().split(" ");
			for(int j=0; j<parsedStr.length; j++) {
				map[i][j] = Integer.parseInt(parsedStr[j]);
			}
		}
		backTracking(n, 0);
		System.out.println(result);
	}

}
