package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14888 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] numArr;
	private static int[] ops;
	private static int minValue = 987654321;
	private static int maxValue = -987654321;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		numArr = new int[N];
		ops = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int opSum = 0;
		for(int i=0; i<4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
			opSum += ops[i];
		}
		DFS(0, opSum, numArr[0]);
		System.out.println(maxValue);
		System.out.println(minValue);
	}
	
	public static void DFS(int count, int opSum, int value) {
		if(count == opSum) {
			if(value < minValue) minValue = value;
			if(value > maxValue) maxValue = value;
			return;
		}
		
		int origin = value;
		for(int i=0; i<4; i++) {
			if(ops[i] > 0) {
				ops[i]--;
				value = calculate(i, count+1, value);
				DFS(count+1, opSum, value);
				value = origin;
				ops[i]++;
			}
		}
	}
	
	private static int calculate(int opIdx, int count, int value) {
		if(opIdx == 0) {
			return value + numArr[count];
		}
		else if(opIdx == 1) {
			return value - numArr[count];
		}
		else if(opIdx == 2) {
			return value * numArr[count];
		}
		else {
			if(value < 0) {
				value *= -1;
				int div = value / numArr[count];
				return div*-1;
			}
			else {
				return value / numArr[count];
			}
		}
	}
}
