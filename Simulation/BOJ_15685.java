package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15685 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int MAX = 101;
	private static boolean[][] map = new boolean[MAX][MAX];
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {1, 0, -1, 0};
	private static int answer = 0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			ArrayList<Integer> dirList = new ArrayList<>();
			ArrayList<Integer> dirHistory = new ArrayList<>();
			dirList.add(d);
			map[y][x] = true;
			for(int j=0; j<=g; j++) {
				if(j != 0) {
					dirList = new ArrayList<>();
					for(int k=dirHistory.size()-1; k>=0; k--) {
						int dir = (dirHistory.get(k)+1) % 4;
						dirList.add(dir);
					}
				}
				for(int k=0; k<dirList.size(); k++) {
					int next_x = x + dy[dirList.get(k)];
					int next_y = y + dx[dirList.get(k)];
					if(next_x >= 0 && next_x < MAX && next_y >= 0 && next_y < MAX) {
						map[next_y][next_x] = true;
					}
					x = next_x;
					y = next_y;
					dirHistory.add(dirList.get(k));
				}
			}
		}
		
		check();
		System.out.println(answer);
	}
	
	private static void check() {
		for(int i=0; i<MAX-1; i++) {
			for(int j=0; j<MAX-1; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
					answer++;
				}
			}
		}
	}
}
