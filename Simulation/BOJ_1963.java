package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.stream.events.Characters;

class Num {
	String num;
	int step;
	
	Num(String num, int step) {
		this.num = num;
		this.step = step;
	}
}

public class BOJ_1963 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static boolean isPrime(int num) {
		for(int i=2; i*i <= num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			boolean[] visited = new boolean[10000];
			String[] input = br.readLine().split(" ");
			Queue<Num> q = new LinkedList<>();
			q.add(new Num(input[0], 0));
			boolean isFind = false;
			while(!q.isEmpty()) {
				Num curNum = q.poll();
				int n = Integer.parseInt(curNum.num);
				visited[n] = true;
				int step = curNum.step;
				
				if(curNum.num.equals(input[1])) {
					System.out.println(step);
					isFind = true;
					break;
				}
				
				for(int j=0; j<4; j++) {
					int start = curNum.num.charAt(j) - '0';
					StringBuilder sb = new StringBuilder(curNum.num);
					if(j == 0) {
						for(int k=0; k<8; k++) {
							start++;
							if(start >= 10) start = 1;
							sb.setCharAt(j, Integer.toString(start).charAt(0));
							int checkNum = Integer.parseInt(sb.toString());
							if(isPrime(checkNum) && visited[checkNum] == false) {
								q.add(new Num(sb.toString(), step+1));
							}
						}
					}
					else {
						for(int k=0; k<9; k++) {
							start++;
							if(start >= 10) start = 0;
							sb.setCharAt(j, Integer.toString(start).charAt(0));
							int checkNum = Integer.parseInt(sb.toString());
							if(isPrime(checkNum) && visited[checkNum] == false) {
								q.add(new Num(sb.toString(), step+1));
							}
						}
					}
				}
				
			}
			if(!isFind) System.out.println("Impossible");
		}
	}

}
