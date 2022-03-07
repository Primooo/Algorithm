package Simulation;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_13458 {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		int room[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long count = 0;
		for(int i=0; i<N; i++) {
			room[i] -= B;
			if(room[i] > 0) {
				if(room[i] % C == 0) {
					count += (int)(room[i]/C);
				}
				else {
					count += (int)(room[i]/C+1);
				}
			}
			count++;
		}
		System.out.println(count);
	}
}
