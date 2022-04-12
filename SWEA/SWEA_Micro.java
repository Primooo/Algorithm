package SWEA;

/*
 * 삼성 모의 SW 역량테스트
 * 문제: 미생물
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cell {
	int count;
	int dir;
	int max;
	
	Cell(int count, int dir, int max) {
		this.count = count;
		this.dir = dir;
		this.max = max;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
}

public class SWEA_Micro {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static Cell[][] map;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new Cell[N][N];
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[n][m] = new Cell(cnt, dir, 0);
			}
			
			for(int i=0; i<M; i++) {
				move();
			}
			
			int answer = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != null) {
						answer += map[i][j].count;
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static void move() {
		Cell[][] cp_map = new Cell[map.length][map.length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				if(map[i][j] != null) {
					int cnt = map[i][j].count;
					int dir = map[i][j].dir;
					int next_x = i + dx[dir-1];
					int next_y = j + dy[dir-1];
					int next_dir = dir;
					
					if(checkMargin(next_x, next_y)) {
						if(dir == 1) next_dir = 2;
						else if(dir == 2) next_dir = 1;
						else if(dir == 3) next_dir = 4;
						else if(dir == 4) next_dir = 3;
						cnt /= 2;
					}
					
					if(cnt != 0) {
						if(cp_map[next_x][next_y] == null) {
							cp_map[next_x][next_y] = new Cell(cnt, next_dir, cnt);
						}
						else {
							if(cp_map[next_x][next_y].max < cnt) {
								cp_map[next_x][next_y] = new Cell(cp_map[next_x][next_y].count + cnt, next_dir, cnt);
							}
							else {
								cp_map[next_x][next_y].setCount(cp_map[next_x][next_y].count + cnt);
							}
						}						
					}
				}
			}
		}
		
		for(int i=0; i<map.length; i++) {
			map[i] = cp_map[i].clone();
		}
	}
	
	private static boolean checkMargin(int x, int y) {
		if(x == 0 || y == 0 || x == map.length-1 || y == map.length-1) {
			return true;
		}
		return false;
	}
}
