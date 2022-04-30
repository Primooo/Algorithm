package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Fireball {
	int massive;
	int speed;
	int dir;
	
	Fireball(int massive, int speed, int dir) {
		this.massive = massive;
		this.dir = dir;
		this.speed = speed;
	}
}

public class BOJ_20056 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static ArrayList<Fireball>[][] map;
	private static ArrayList<Fireball>[][] variations;
	private static int N, M, K;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r-1][c-1].add(new Fireball(m, s, d));
		}
		
		for(int i=0; i<K; i++) {
			init();
			move();
			calculate();
		}
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size() != 0) {
					for(int k=0; k<map[i][j].size(); k++) {
						result += map[i][j].get(k).massive;
					}
				}
			}
		}
		System.out.println(result);
	}

	private static void init() {
		variations = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				variations[i][j] = new ArrayList<Fireball>();
			}
		}
	}
	
	private static void move() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size() != 0) {
					for(int k=0; k<map[i][j].size(); k++) {
						int cur_x = i;
						int cur_y = j;
						int cur_massive = map[i][j].get(k).massive;
						int cur_spd = map[i][j].get(k).speed;
						int cur_dir = map[i][j].get(k).dir;
						for(int l=0; l<cur_spd; l++) {
							switch(cur_dir) {
							case 0:
								cur_x--;
								break;
							case 1:
								cur_x--;
								cur_y++;
								break;
							case 2:
								cur_y++;
								break;
							case 3:
								cur_x++;
								cur_y++;
								break;
							case 4:
								cur_x++;
								break;
							case 5:
								cur_x++;
								cur_y--;
								break;
							case 6:
								cur_y--;
								break;
							case 7:
								cur_x--;
								cur_y--;
								break;
							default:
								break;
							}
							
							if(cur_x < 0) cur_x = N-1;
							else if(cur_x >= N) cur_x = 0;
							
							if(cur_y < 0) cur_y = N-1;
							else if(cur_y >= N) cur_y = 0;
						}
						variations[cur_x][cur_y].add(new Fireball(cur_massive, cur_spd, cur_dir));
					}
				}
			}
		}
	}
	
	private static void calculate() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int size = variations[i][j].size();
				if(size > 1) {
					int totalMassive = 0;
					int totalSpeed = 0;
					int totalDir = 0;
					for(int k=0; k<size; k++) {
						Fireball fb = variations[i][j].get(k);
						totalMassive += fb.massive;
						totalSpeed += fb.speed;
						if(fb.dir % 2 == 1) {
							totalDir += 1;
						}
						else {
							totalDir += 2;
						}
					}
					totalMassive = totalMassive / 5;
					totalSpeed = totalSpeed / size;
					if(totalMassive == 0) {
						variations[i][j] = new ArrayList<Fireball>();
						continue;
					}
					
					variations[i][j] = new ArrayList<Fireball>();
					if(totalDir == size || totalDir == size * 2) {
						for(int k=0; k<=6; k+=2) {
							variations[i][j].add(new Fireball(totalMassive, totalSpeed, k));
						}
					}
					else {
						for(int k=1; k<=7; k+=2) {
							variations[i][j].add(new Fireball(totalMassive, totalSpeed, k));
						}						
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			map[i] = variations[i].clone();
		}
	}
}
