package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BallStep {
	int red_x;
	int red_y;
	int blue_x;
	int blue_y;
	int step;
	
	BallStep(int red_x, int red_y, int blue_x, int blue_y, int step) {
		this.red_x = red_x;
		this.red_y = red_y;
		this.blue_x = blue_x;
		this.blue_y = blue_y;
		this.step = step;
	}
}

public class BOJ_13460 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[][] map;
	private static boolean[][][][] visited;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		int red_x = 0, red_y = 0;
		int blue_x = 0, blue_y = 0;
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				if(str.charAt(j) == 'R') {
					red_x = i;
					red_y = j;
					map[i][j] = '.';
				}
				else if(str.charAt(j) == 'B') {
					blue_x = i;
					blue_y = j;
					map[i][j] = '.';
				} 
				else {
					map[i][j] = str.charAt(j);					
				}
			}
		}
		
		visited = new boolean[N][M][N][M];
		Queue<BallStep> q = new LinkedList<BallStep>();
		q.add(new BallStep(red_x, red_y, blue_x, blue_y, 0));
		visited[red_x][red_y][blue_x][blue_y] = true;
		
		while(!q.isEmpty()) {
			BallStep currentStep = q.poll();
			if(currentStep.step > 10) {
				System.out.println(-1);
				return;
			}
			
			if(currentStep.red_x == 0 && currentStep.blue_x != 0) {
				System.out.println(currentStep.step);
				return;
			}						
			
			for(int i=0; i<4; i++) {
				char[][] cp_map = new char[N][M];
				for(int j=0; j<N; j++) {
					cp_map[j] = map[j].clone();
				}
				
				char firstBall = checkFirstMove(i, currentStep);
				
				int nextRed_x = currentStep.red_x + dx[i];
				int nextRed_y = currentStep.red_y + dy[i];
				int nextBlue_x = currentStep.blue_x + dx[i];
				int nextBlue_y = currentStep.blue_y + dy[i];
				
				if(nextRed_x < 0 || nextRed_x >= N || nextBlue_x < 0 || nextBlue_x >= N ||
						nextRed_y < 0 || nextRed_y >= M || nextBlue_y < 0 || nextBlue_y >= M) continue;
								
				if(firstBall == 'R') {
					while(cp_map[nextRed_x][nextRed_y] == '.') {
						nextRed_x += dx[i];
						nextRed_y += dy[i];
					}
					
					if(cp_map[nextRed_x][nextRed_y] == '#') {
						nextRed_x -= dx[i];
						nextRed_y -= dy[i];
						cp_map[nextRed_x][nextRed_y] = 'R';
					}
					else if(cp_map[nextRed_x][nextRed_y] == 'O') {
						nextRed_x = 0;
						nextRed_y = 0;
					}
					
					while(cp_map[nextBlue_x][nextBlue_y] == '.') {
						nextBlue_x += dx[i];
						nextBlue_y += dy[i];
					}
					
					if(cp_map[nextBlue_x][nextBlue_y] == '#' || cp_map[nextBlue_x][nextBlue_y] == 'R') {
						nextBlue_x -= dx[i];
						nextBlue_y -= dy[i];
						cp_map[nextBlue_x][nextBlue_y] = 'B';
					}
					else if(cp_map[nextBlue_x][nextBlue_y] == 'O') {
						nextBlue_x = 0;
						nextBlue_y = 0;
					}
					
				}
				
				else {
					while(cp_map[nextBlue_x][nextBlue_y] == '.') {
						nextBlue_x += dx[i];
						nextBlue_y += dy[i];
					}
					
					if(cp_map[nextBlue_x][nextBlue_y] == '#') {
						nextBlue_x -= dx[i];
						nextBlue_y -= dy[i];
						cp_map[nextBlue_x][nextBlue_y] = 'B';
					}
					else if(cp_map[nextBlue_x][nextBlue_y] == 'O') {
						nextBlue_x = 0;
						nextBlue_y = 0;
					}
					
					while(cp_map[nextRed_x][nextRed_y] == '.') {
						nextRed_x += dx[i];
						nextRed_y += dy[i];
					}
					
					if(cp_map[nextRed_x][nextRed_y] == '#' || cp_map[nextRed_x][nextRed_y] == 'B') {
						nextRed_x -= dx[i];
						nextRed_y -= dy[i];
						cp_map[nextRed_x][nextRed_y] = 'R';
					}
					else if(cp_map[nextRed_x][nextRed_y] == 'O') {
						nextRed_x = 0;
						nextRed_y = 0;
					}
					
				}
				
				
				if(visited[nextRed_x][nextRed_y][nextBlue_x][nextBlue_y] == false) {
					q.add(new BallStep(nextRed_x, nextRed_y, nextBlue_x, nextBlue_y, currentStep.step+1));
					visited[nextRed_x][nextRed_y][nextBlue_x][nextBlue_y] = true;
				}
			}
		}
		System.out.println(-1);
	}

	private static char checkFirstMove(int dir, BallStep bs) {
		if(dir == 0) {
			if(bs.blue_x >= bs.red_x) {
				return 'B';
			}
			else {
				return 'R';
			}
		} 
		else if(dir == 1) {
			if(bs.blue_x < bs.red_x) {
				return 'B';
			}
			else {
				return 'R';
			}
			
		}
		else if(dir == 2) {
			if(bs.blue_y >= bs.red_y) {
				return 'B';
			}
			else {
				return 'R';
			}
		}
		else {
			if(bs.blue_y < bs.red_y) {
				return 'B';
			}
			else {
				return 'R';
			}
		}
	}
}
