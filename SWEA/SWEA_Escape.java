package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
	int x;
	int y;
	int type;
	int timer;
	
	Pos(int x, int y, int type, int timer) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.timer = timer;
	}
	
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_Escape {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static boolean[][] visited;
	private static Map<Integer, int[]> dirMap;
	private static Queue<Pos> q;
	private static int N, M, R, C, L, answer;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			q.add(new Pos(R, C, map[R][C], 1));
			BFS();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]) {
						answer++;
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
		
	}
	
	private static void init() {
		map = new int[N][M];
		visited = new boolean[N][M];
		answer = 0;
		q = new LinkedList<Pos>();
		dirMap = new HashMap<Integer, int[]>();
		dirMap.put(1, new int[]{0, 1, 2, 3});
		dirMap.put(2, new int[]{0, 1});
		dirMap.put(3, new int[]{2, 3});
		dirMap.put(4, new int[]{0, 3});
		dirMap.put(5, new int[]{1, 3});
		dirMap.put(6, new int[]{1, 2});
		dirMap.put(7, new int[]{0, 2});
	}
	
	private static void BFS() {
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			if(pos.timer > L) {
				return;
			}
			
			visited[pos.x][pos.y] = true;
			ArrayList<Pos> nextList = getNextStep(pos);
			for(int i=0; i<nextList.size(); i++) {
				Pos nextPos = nextList.get(i);
				if(!visited[nextPos.x][nextPos.y]) {
					q.add(new Pos(nextPos.x, nextPos.y, map[nextPos.x][nextPos.y], pos.timer+1));
				}
			}
			
		}
	}

	
	private static ArrayList<Pos> getNextStep(Pos curPos) {
		ArrayList<Pos> posList = new ArrayList<Pos>();
		for(int i=0; i<dirMap.get(curPos.type).length; i++) {
			int next_x = curPos.x + dx[dirMap.get(curPos.type)[i]];
			int next_y = curPos.y + dy[dirMap.get(curPos.type)[i]];
			if(check(next_x, next_y, dirMap.get(curPos.type)[i])) {
				posList.add(new Pos(next_x, next_y));
			}
		}
		return posList;
	}
	
	private static boolean check(int next_x, int next_y, int dir) {
		if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M) {
			return false;
		}
		
		if(dir == 0) { // up
			if(map[next_x][next_y] == 1 || map[next_x][next_y] == 2 || map[next_x][next_y] == 5 || map[next_x][next_y] == 6) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(dir == 1) { // down
			if(map[next_x][next_y] == 1 || map[next_x][next_y] == 2 || map[next_x][next_y] == 4 || map[next_x][next_y] == 7) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(dir == 2) { // left
			if(map[next_x][next_y] == 1 || map[next_x][next_y] == 3 || map[next_x][next_y] == 4 || map[next_x][next_y] == 5) {
				return true;
			}
			else {
				return false;
			}
		}
		else { // right
			if(map[next_x][next_y] == 1 || map[next_x][next_y] == 3 || map[next_x][next_y] == 6 || map[next_x][next_y] == 7) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}
