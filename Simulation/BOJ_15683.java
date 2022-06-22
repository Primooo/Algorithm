//package Simulation;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.*;
//
//class Coordinate {
//	int x;
//	int y;
//
//	Coordinate(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}
//
//public class BOJ_15683 {
//	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	private static int[][] map;
//	private static int answer = 987654321;
//	private static ArrayList<Integer> dirList = new ArrayList<>();
//	private static ArrayList<Coordinate> corList = new ArrayList<Coordinate>();
//	private static boolean[] visited;
//	private static int[] dx = {0, 1, 0, -1};
//	private static int[] dy = {1, 0, -1, 0};
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		map = new int[N][M];
//		for(int i=0; i<N; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			for(int j=0; j<M; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//				if(map[i][j] != 0 && map[i][j] != 6) {
//					corList.add(new Coordinate(i, j));
//				}
//			}
//		}
//
//		visited = new boolean[corList.size()];
//		dfs(0, corList.size());
//		System.out.println(answer);
//	}
//
//	private static void dfs(int depth, int n) {
//		if(depth == n) {
//			check();
//			return;
//		}
//
//		int cam_x = corList.get(depth).x;
//		int cam_y = corList.get(depth).y;
//		int cam_num = map[cam_x][cam_y];
//
//		for(int i=0; i<4; i++) {
//			if(cam_num == 2 && i > 2) continue;
//			else if(cam_num == 5 && i > 1) continue;
//			visited[depth] = true;
//			dirList.add(i);
//			dfs(depth+1, n);
//			dirList.remove(dirList.size()-1);
//			visited[depth] = false;
//		}
//	}
//
//	private static void check() {
//		int[][] cp_map = new int[map.length][map[0].length];
//		for(int i=0; i<map.length; i++) {
//			cp_map[i] = map[i].clone();
//		}
//
//		for(int i=0; i<corList.size(); i++) {
//			int cur_x = corList.get(i).x;
//			int cur_y = corList.get(i).y;
//			int cur_dir = dirList.get(i);
//			int cameraNum = cp_map[cur_x][cur_y];
//			switch(cameraNum) {
//			case 1:
//				watch(cp_map, cur_x, cur_y, cur_dir);
//				break;
//			case 2:
//				watch(cp_map, cur_x, cur_y, cur_dir);
//				watch(cp_map, cur_x, cur_y, cur_dir+2);
//				break;
//			case 3:
//				watch(cp_map, cur_x, cur_y, cur_dir);
//				watch(cp_map, cur_x, cur_y, cur_dir+3);
//				break;
//			case 4:
//				watch(cp_map, cur_x, cur_y, cur_dir);
//				watch(cp_map, cur_x, cur_y, cur_dir+2);
//				watch(cp_map, cur_x, cur_y, cur_dir+3);
//				break;
//			case 5:
//				watch(cp_map, cur_x, cur_y, cur_dir);
//				watch(cp_map, cur_x, cur_y, cur_dir+1);
//				watch(cp_map, cur_x, cur_y, cur_dir+2);
//				watch(cp_map, cur_x, cur_y, cur_dir+3);
//				break;
//			default:
//				break;
//			}
//		}
//
//		int count = 0;
//		for(int i=0; i<map.length; i++) {
//			for(int j=0; j<map[i].length; j++) {
//				if(cp_map[i][j] == 0) {
//					count++;
//				}
//			}
//		}
//		if(count < answer) answer = count;
//	}
//
//	private static void watch(int[][] cp_map, int x, int y, int dir) {
//		dir %= 4;
//		int next_x = x + dx[dir];
//		int next_y = y + dy[dir];
//		if(next_x >= 0 && next_x < cp_map.length && next_y >= 0 && next_y < cp_map[0].length) {
//			while(cp_map[next_x][next_y] != 6) {
//				if(cp_map[next_x][next_y] <= 0) {
//					cp_map[next_x][next_y] = -1;
//				}
//				next_x += dx[dir];
//				next_y += dy[dir];
//				if(next_x < 0 || next_x >= cp_map.length || next_y < 0 || next_y >= cp_map[0].length) {
//					break;
//				}
//			}
//		}
//	}
//
//}
