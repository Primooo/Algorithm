package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_Film {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static int D, W, K;
	private static int answer;
	public static void main(String args[]) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			answer = 987654321;
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			chooseFilm(0, 0);
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
		
	private static void chooseFilm(int idx, int count) {
		if(isSafe()) {
			answer = Math.min(answer, count);
			return;
		}
		
		if(count > answer) return;
		if(idx == D) return;
		int[] cp_row = new int[W];
		cp_row = map[idx].clone();
		
		chooseFilm(idx+1, count);
		
		// change row to A
		for(int i=0; i<W; i++) {
			map[idx][i] = 0;
		}
		chooseFilm(idx+1, count+1);
		
		// change row to B
		for(int i=0; i<W; i++) {
			map[idx][i] = 1;
		}
		chooseFilm(idx+1, count+1);
		
		map[idx] = cp_row.clone();		
	}
	
	private static boolean isSafe() {
		for(int i=0; i<W; i++) {
			int type = map[0][i];
			int count = 1;
			boolean flag = false;
			for(int j=1; j<D; j++) {
				if(map[j][i] == type) {
					count++;
				}
				else {
					type = map[j][i];
					count = 1;
				}
				
				if(count == K) {
					flag = true;
					break;
				}
			}
			if(flag == false) {
				return false;
			}
		}
		return true;
	}
}
