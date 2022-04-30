package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] belt;
	private static boolean[] check;
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		belt = new int[N*2];
		check = new boolean[N*2];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		int step = 1;
		
		while(true) {
			rotate(N);
			goForward(N);
			
			if(belt[0] > 0 && check[0] == false) {
				check[0] = true;
				belt[0]--;
			}
			
			if(check(K)) {
				break;
			}
			step++;
		}
		System.out.println(step);
	}
	
	private static void rotate(int N) {
		int lastDurability = belt[belt.length-1];
		for(int i=belt.length-1; i>=1; i--) {
			belt[i] = belt[i-1];
			check[i] = check[i-1];
		}
		belt[0] = lastDurability;
		check[0] = false;
		
		if(check[N-1] == true) {
			check[N-1] = false;
		}
	}
	
	private static void goForward(int N) {
		for(int i=N-2; i>=0; i--) {
			if(check[i] == true && check[i+1] == false && belt[i+1] > 0) {
				check[i+1] = check[i];
				check[i] = false;
				belt[i+1]--;
			}
			
			if(i+1 == N-1 && check[i+1] == true) {
				check[i+1] = false;
			}
		}
	}
	
	private static boolean check(int K) {
		int counter = 0;
		for(int i=0; i<belt.length; i++) {
			if(belt[i] == 0) {
				counter++;
			}
		}
		
		if(counter >= K) {
			return true;
		}
		return false;
	}

}
