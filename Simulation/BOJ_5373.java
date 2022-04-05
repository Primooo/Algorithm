package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5373 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[][][] origin = {
			{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}}, 	// 윗면
			{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}},	// 앞면
			{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}},	// 왼쪽면
			{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}},	// 아래면
			{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}},	// 뒷면
			{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}}		// 오른쪽면
	};
	private static final int[][] clockwise = {
			{0, 5, 1, 3, 2, 4},
			{2, 1, 3, 5, 4, 0},
			{4, 0, 2, 1, 3, 5},
			{0, 2, 4, 3, 5, 1},
			{5, 1, 0, 2, 4, 3},
			{1, 3, 2, 4, 0, 5}
	};
	
	private static final int[][] counter_clockwise = {
			{0, 2, 4, 3, 5, 1},
			{5, 1, 0, 2, 4, 3},
			{1, 3, 2, 4, 0, 5},
			{0, 5, 1, 3, 2, 4},
			{2, 1, 3, 5, 4, 0},
			{4, 0, 2, 1, 3, 5}
	};
	
	private static final int[][] rotatedIndexes = {
			{0, 0, 0, 0, 0, 0},
			{2, 2, 2, 0, 2, 0},
			{0, 0, 0, 0, 2, 0},
			{2, 2, 2, 2, 2, 2},
			{0, 0, 0, 2, 0, 2},
			{2, 2, 2, 2, 0, 2}
	};
	
	private static char[][][] cube = new char[6][3][3];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int rotations = Integer.parseInt(br.readLine());
			String[] cmds = br.readLine().split(" ");
			
			cube = new char[6][3][3];
			for(int j=0; j<6; j++) {
				for(int k=0; k<3; k++) {
					for(int l=0; l<3; l++) {
						cube[j][k][l] = origin[j][k][l];
					}
				}
			}
			
			for(int j=0; j<rotations; j++) {
				char selected = cmds[j].charAt(0);
				char direction = cmds[j].charAt(1);
				rotate(selected, direction);
			}
			printResult();			
		}
	}
	
	private static void copy_row_clockwise(int side, boolean isRow) {
		char[][] cp_rows = new char[6][3];
		
		for(int i=0; i<6; i++) {
			int idx = clockwise[side][i];
			int rotatedIdx = rotatedIndexes[side][i];
			if(isRow) {
				for(int j=0; j<3; j++) {
					cp_rows[i][j] = cube[idx][rotatedIdx][j];
				}
			}
			else {
				for(int j=0; j<3; j++) {
					cp_rows[i][j] = cube[idx][j][rotatedIdx];
				}
			}
		}
		for(int i=0; i<6; i++) {
			int rotatedIdx = rotatedIndexes[side][i];
			if(isRow) {
				for(int j=0; j<3; j++) {
					cube[i][rotatedIdx][j] = cp_rows[i][j];
				}
			}
			else {
				for(int j=0; j<3; j++) {
					cube[i][j][rotatedIdx] = cp_rows[i][j];
				}
			}
		}
	}
	
	private static void copy_row_counter_clockwise(int side, boolean isRow) {
		char[][] cp_rows = new char[6][3];

		for(int i=0; i<6; i++) {
			int idx = counter_clockwise[side][i];
			int rotatedIdx = rotatedIndexes[side][i];
			if(isRow) {
				for(int j=0; j<3; j++) {
					cp_rows[i][j] = cube[idx][rotatedIdx][j];
				}
			}
			else {
				for(int j=0; j<3; j++) {
					cp_rows[i][j] = cube[idx][j][rotatedIdx];
				}
			}
		}
		
		for(int i=0; i<6; i++) {
			int rotatedIdx = rotatedIndexes[side][i];
			if(isRow) {
				for(int j=0; j<3; j++) {
					cube[i][rotatedIdx][j] = cp_rows[i][j];
				}
			}
			else {
				for(int j=0; j<3; j++) {
					cube[i][j][rotatedIdx] = cp_rows[i][j];
				}
			}
		}
	}
	
	private static void rotate(char selected, char direction) {
		switch(selected) {
		case 'U':
			if(direction == '+') {
				copy_row_clockwise(0, true);
			}
			else {
				copy_row_counter_clockwise(0, true);
			}
			break;
		case 'F':
			if(direction == '+') {
				copy_row_clockwise(1, true);
			}
			else {
				copy_row_counter_clockwise(1, true);
			}
			break;
		case 'L':
			if(direction == '+') {
				copy_row_clockwise(2, false);
			}
			else {
				copy_row_counter_clockwise(2, false);
			}
			break;
		case 'D':
			if(direction == '+') {
				copy_row_clockwise(3, true);
			}
			else {
				copy_row_counter_clockwise(3, true);
			}
			break;
		case 'B':
			if(direction == '+') {
				copy_row_clockwise(4, true);
			}
			else {
				copy_row_counter_clockwise(4, true);
			}
			break;
		case 'R':
			if(direction == '+') {
				copy_row_clockwise(5, false);
			}
			else {
				copy_row_counter_clockwise(5, false);
			}
			break;
			
		default:
			break;
		}
	}
	
	private static void printResult() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(cube[0][i][j]);
			}
			System.out.println();
		}
	}
}
