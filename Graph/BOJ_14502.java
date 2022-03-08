package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Coordinate {
	int x;
	int y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_14502 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static ArrayList<Integer> idxList = new ArrayList<>();
	private static ArrayList<Coordinate> coorList = new ArrayList<Coordinate>();
	private static boolean[] visited;
	private static boolean[][] mapVisited;
	private static int answer = 0;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					coorList.add(new Coordinate(i, j));
				}
			}
		}
		visited = new boolean[coorList.size()];
		DFS(0, coorList.size(), N, M);
		System.out.println(answer);
	}

	private static void DFS(int num, int candidatesLength, int N, int M) {
		if(idxList.size() == 3) {
			int[][] cp_map = new int[N][M];
			for(int i=0; i<N; i++) {
				cp_map[i] = map[i].clone();
			}
			
			for(int i=0; i<idxList.size(); i++) {
				int idx = idxList.get(i);
				int wall_x = coorList.get(idx).x;
				int wall_y = coorList.get(idx).y;
				cp_map[wall_x][wall_y] = 1;
			}
			
			int safeCounter = BFS(cp_map, N, M);
			if(safeCounter > answer) {
				answer = safeCounter;
			}

			return;
		}
		
		for(int i=0; i<candidatesLength; i++) {
			if(visited[i] == false && num <= i) {
				idxList.add(i);
				visited[i] = true;
				DFS(i, candidatesLength, N, M);
				visited[i] = false;
				idxList.remove(idxList.size()-1);
			}
		}
	}
	
	private static int BFS(int[][] cp_map, int N, int M) {
		Queue<Coordinate> q = new LinkedList<Coordinate>();
		mapVisited = new boolean[N][M];
		int safeCounter = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cp_map[i][j] == 2) {
					q.add(new Coordinate(i, j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Coordinate cor = q.poll();
			mapVisited[cor.x][cor.y] = true;
			
			for(int i=0; i<4; i++) {
				int next_x = cor.x + dx[i];
				int next_y = cor.y + dy[i];
				if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M) continue;
				if(mapVisited[next_x][next_y] == false && cp_map[next_x][next_y] == 0) {
					cp_map[next_x][next_y] = 2;
					q.add(new Coordinate(next_x, next_y));
				}
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cp_map[i][j] == 0) {
					safeCounter++;
				}
			}
		}
		
		return safeCounter;
	}
}
