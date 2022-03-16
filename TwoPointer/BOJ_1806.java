package TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1806 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int min = 987654321;
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int sum = 0;
		while(true) {
			if(sum >= S) {
				if(right-left < min) {
					min = right-left;
				}
				sum -= nums[left++];
			}
			else if(right == N) break;
			else if(sum < S) {
				sum += nums[right++];
			}
		}

		if(min == 987654321) {
			System.out.println(0);
		}
		else {
			System.out.println(min);
		}
	}

}
