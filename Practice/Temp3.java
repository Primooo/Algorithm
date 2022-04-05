package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Temp3 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
    public static void main(String[] args) throws IOException {
    	int N = Integer.parseInt(br.readLine());
    	map = new int[1+4*(N-1)][N];
    	int end = 1;
    	for(int i=0; i<N; i++) {
    		end += 6*i;
    	}
        System.out.println(end);
        
    }
}
