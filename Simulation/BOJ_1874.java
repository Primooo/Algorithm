package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_1874 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		int[] numArr = new int[N];
		ArrayList<Character> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			numArr[i] = num;
		}
		
		Stack<Integer> st = new Stack<>();
		int cur = 1;
		int idx = 0;
		boolean canDo = true;
		while(idx <= N-1) {
			if(cur <= N) {
				if(cur < numArr[idx]) {
					st.add(cur);
					cur++;
					list.add('+');
				}
				else if(cur == numArr[idx]) {
					st.add(cur);
					list.add('+');
					st.pop();
					list.add('-');
					idx++;
					cur++;
				}
				
				else {
					if(st.peek() == numArr[idx]) {
						idx++;
					}
					st.pop();
					list.add('-');
				}
			}
			
			else {
				if(numArr[idx] == st.peek()) {
					st.pop();
					list.add('-');
					idx++;
				}
				else {
					canDo = false;
					break;
				}
			}
		}
		
		if(canDo) {
			for(int i=0; i<list.size(); i++) System.out.println(list.get(i));
		}
		else {
			System.out.println("NO");
		}
	}

}
