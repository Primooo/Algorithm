package TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_2003 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] nums;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		int left = 0, right = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		int count = 0;
		while(true) {
			if(sum >= M) {
				sum -= nums[left++];
			}
			else if(right == N) break;
			else if(sum < M){
				sum += nums[right++];
			}
			
			if(sum == M) {
				count++;
			}
		}
		System.out.println(count);
	}

}
