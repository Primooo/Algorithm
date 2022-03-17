package SlidingWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10025 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int MAX = 1000001;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] map = new int[MAX];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[x] = g;
		}
		
		int sum = 0;
		int max = 0;
		int window = 1 + (2*K);
		
		for(int i=0; i<MAX; i++) {
			if(i >= window) {
				sum -= map[i-window];
			}
			sum += map[i];
			if(sum > max) {
				max = sum;
			}
		}
		
		System.out.println(max);
	}
}
