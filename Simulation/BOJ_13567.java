package Simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_13567 {

	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int direction = 0;
        int cur_x = 0; int cur_y = 0;
        int[][] map = new int[M][M];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	String cmd = st.nextToken();
        	int num = Integer.parseInt(st.nextToken());
        	
        	if(cmd.equals("MOVE")) {
        		cur_x += dx[direction]*num;
        		cur_y += dy[direction]*num;
        	}
        	else {
        		if(num == 0) {
        			direction++;
        			if(direction > 3) direction = 0;
        		}
        		else {
        			direction--;
        			if(direction < 0) direction = 3;
        		}
        	}
        	
        	if(cur_x < 0 || cur_x >= M || cur_y < 0 || cur_y >= M) {
        		System.out.println("-1");
        		return;
        	}
        }
        System.out.println(cur_x + " " + cur_y);
	}

}
