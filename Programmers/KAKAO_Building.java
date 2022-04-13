package Programmers;

class BuildingSolution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int s = skill.length;
        int answer = 0;
        int[][] variation = new int[n+1][m+1];
        for(int i=0; i<s; i++) {
            int type = skill[i][0];
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            int degree = type == 1 ? -skill[i][5] : skill[i][5];
            
            variation[x1][y1] += degree;
            variation[x2+1][y1] += degree * -1;
            variation[x1][y2+1] += degree * -1;
            variation[x2+1][y2+1] += degree;
        }

        
        for(int j=1; j<n; j++) {
            for(int k=0; k<m; k++) {
                variation[j][k] += variation[j-1][k];
            }
        }
        
        for(int j=1; j<m; j++) {
            for(int k=0; k<n; k++) {
                variation[k][j] += variation[k][j-1];
            }
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] + variation[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}

public class KAKAO_Building {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
		int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
		BuildingSolution solution = new BuildingSolution();
		solution.solution(board, skill);
	}

}
