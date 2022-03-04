package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Direction {
	int time;
	String dir;
	
	Direction(int time, String dir) {
		this.time = time;
		this.dir = dir;
	}
}

class Coordinate {
	int x;
	int y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_3190 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int map[][];
		
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int apple_x = Integer.parseInt(st.nextToken());
			int apple_y = Integer.parseInt(st.nextToken());
			map[apple_x-1][apple_y-1] = 2;
		}
		map[0][0] = 1;
		int L = Integer.parseInt(br.readLine());
		ArrayList<Direction> dirList = new ArrayList<Direction>();
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			dirList.add(new Direction(time, dir));
		}
		
		Deque<Coordinate> dq = new ArrayDeque<Coordinate>(); 			// >> �ڹٿ��� �� �ڷ��� �� �ð� ���⵵�� �̷��� �ѹ� �˾ƺ��� �ҵ�
		int head_x = 0, head_y = 0;
		dq.add(new Coordinate(head_x, head_y));
		int cur_dir = 0;
		int dirIndex = 0;
		int totalTimer = 0;
		int nextDirTimer = dirList.get(dirIndex).time;
		String nextDir = dirList.get(dirIndex).dir;
		while(true) {
			totalTimer++;
			if(cur_dir == 0) {			// ������ ���� ����
				head_y++;
			} else if(cur_dir == 1) {	// �Ʒ� ���� ����
				head_x++;
			} else if(cur_dir == 2) {	// ���� ���� ����
				head_y--;
			} else {					// �� ���� ����
				head_x--;
			}
			
			if(head_x < 0 || head_x >= N || head_y < 0 || head_y >= N) {	// ���� �ε����� ��
				break;
			} 
//			else {
			if(map[head_x][head_y] == 1) {			// ������ ������ �ڽ��� ������ ��
				break;
			}
			else if(map[head_x][head_y] == 2) {		// ����� �������� ��
				dq.addFirst(new Coordinate(head_x, head_y));			// >>> �Ʒ� ���ǹ��̶� ���� ���� �κ��� �����ϱ� ���� �ɵ�
				map[head_x][head_y] = 1;
			}
			else {									// ��ĭ�� ���
				dq.addFirst(new Coordinate(head_x, head_y));			
				map[head_x][head_y] = 1;
				Coordinate cor = dq.pollLast();
				map[cor.x][cor.y] = 0; 
			}
//			}
			
			if(totalTimer == nextDirTimer) {
				if(nextDir.equals("D")) {
					cur_dir = (cur_dir + 1) % 4;
				}
				else {
					cur_dir = (cur_dir + 3) % 4;
				}
				
				if(dirIndex < dirList.size()-1) {
					dirIndex++;
					nextDirTimer = dirList.get(dirIndex).time;
					nextDir = dirList.get(dirIndex).dir;
				}
			}
		}
		
		System.out.println(totalTimer);
	}
}
