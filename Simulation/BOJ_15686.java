package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Spot {
	int x;
	int y;
	
	Spot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15686 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static ArrayList<Spot> houseSpot = new ArrayList<Spot>();
	private static ArrayList<Spot> chickenSpot = new ArrayList<Spot>();
	private static boolean[] excluded;
	private static int answer = 987654321;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					houseSpot.add(new Spot(i, j));
				}
				else if(map[i][j] == 2) {
					chickenSpot.add(new Spot(i, j));
				}
			}
		}
		excluded = new boolean[chickenSpot.size()];
		dfs(0, M, chickenSpot.size(), 0);
		System.out.println(answer);
	}
	
	private static void dfs(int depth, int M, int length, int cur) {
		if(depth == M) {
			calculate();
			return;
		}
		
		for(int i=cur; i<length; i++) {
			if(excluded[i] == false) {
				excluded[i] = true;
				dfs(depth+1, M, length, i);
				excluded[i] = false;
			}
		}
	}
	
	private static void calculate() {
		int result = 0;
		for(int i=0; i<houseSpot.size(); i++) {
			int house_x = houseSpot.get(i).x;
			int house_y = houseSpot.get(i).y;
			int temp = 987654321;
			for(int j=0; j<chickenSpot.size(); j++) {
				if(excluded[j] == false) continue;
				int chicken_x = chickenSpot.get(j).x;
				int chicken_y = chickenSpot.get(j).y;
				if(Math.abs(chicken_x-house_x) + Math.abs(chicken_y-house_y) < temp) {
					temp = Math.abs(chicken_x-house_x) + Math.abs(chicken_y-house_y);
				}
			}
			result += temp;
		}
		if(result < answer) {
			answer = result;
		}
	}

}
