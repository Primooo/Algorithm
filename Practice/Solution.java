package Practice;

import java.util.*;

class IntersectedArea {
    int x1;
    int y1;
    int x2;
    int y2;
    int area1;
    int area2;

    IntersectedArea(int x1, int y1, int x2, int y2, int area1, int area2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.area1 = area1;
        this.area2 = area2;
    }
}

class Solution {
    private final int div = 10000019;
    public int solution(int width, int height, int[][] diagonals) {
        int answer = 0;        
        for(int i=0; i<diagonals.length; i++) {
            int x1 = diagonals[i][0]-1;
            int y1 = diagonals[i][1];
            int x2 = diagonals[i][0];
            int y2 = diagonals[i][1]-1;

            answer += getDistance(0, 0, x1, y1) % div;
            answer += getDistance(0, 0, height-x2, width-y2) % div;
            answer += getDistance(0, 0, x2, y2) % div;
            answer += getDistance(0, 0, height-x1, width-y1) % div;
        }

        return answer;
    }

    private int getDistance(int start_x, int start_y, int end_x, int end_y) {
        int[][] map = new int[end_x+1][end_y+1];

        for(int i=0; i<=end_x; i++) {
            map[i][0] = 1;
        }

        for(int i=0; i<=end_y; i++) {
            map[0][i] = 1;
        }

        for(int i=start_x+1; i<=end_x; i++) {
            for(int j=start_y+1; j<=end_y; j++) {
                map[i][j] = (map[i-1][j] + map[i][j-1]) % div;
            }
        }
        return map[end_x][end_y];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		int[][] aa = {{1, 1}, {2, 2}};
		solution.solution(2, 2, aa);
	}

}
