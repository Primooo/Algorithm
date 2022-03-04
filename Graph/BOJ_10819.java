package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10819 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static ArrayList<Integer> idxList = new ArrayList<>();
	private static int[] nums;
	private static boolean[] visited;
	private static int answer = -1;
	public static void dfs(int n, int cnt) {
		if(n == cnt) {
			int sum = 0;
			for(int i=0; i<idxList.size()-1; i++) {
				sum += Math.abs(nums[idxList.get(i)] - nums[idxList.get(i+1)]);
			}
			answer = answer > sum ? answer : sum;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				idxList.add(i);
				dfs(n, cnt+1);
				idxList.remove(idxList.size()-1);
				visited[i] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(br.readLine());
		String[] inputNums = br.readLine().split(" ");
		visited = new boolean[n];
		nums = new int[n];
		for(int i=0; i<inputNums.length; i++) {
			nums[i] = Integer.parseInt(inputNums[i]);
		}
		dfs(n, 0);
		System.out.println(answer);
	}

}
