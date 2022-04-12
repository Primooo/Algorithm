package Trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class NumberNode {
	NumberNode[] child;
	char ch;
	int childCount;
	boolean isTerminal;
	
	NumberNode(char ch, int childCount, boolean isTerminal) {
		this.child = new NumberNode[10];
		this.ch = ch;
		this.childCount = childCount;
		this.isTerminal = isTerminal;
	}
}

public class BOJ_5052 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static NumberNode root;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			root = new NumberNode(' ', 0, false);
			ArrayList<String> arrList = new ArrayList<String>();
			for(int j=0; j<N; j++) {
				String str = br.readLine();
				arrList.add(str);
				insert(str);
			}
			
			int count = 0;
			for(int j=0; j<arrList.size(); j++) {
				if(find(arrList.get(j))) {
					count++;
				}
			}
			
			if(count == N) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
	
	public static void insert(String str) {
		NumberNode current = root;
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			int idx = ch - '0';
			
			if(current.child[idx] == null) {
				current.child[idx] = new NumberNode(ch, 0, false);
				current.childCount++;
			}
			current = current.child[idx];
		}
		current.isTerminal = true;
	}

	public static boolean find(String str) {
		NumberNode current = root;
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			int idx = ch - '0';
			
			if(i != str.length()-1 && current.child[idx].isTerminal) {
				return false;
			}
			current = current.child[idx];
		}
		return current != null && current.isTerminal;
	}
}
