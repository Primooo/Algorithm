package Trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	Node[] child;
	boolean isTerminal;
	int childNum;
	char val;
	
	Node(boolean flag, int childNum, char val) {
		this.child = new Node[26];
		this.isTerminal = flag;
		this.childNum = childNum;
		this.val = val;
	}
}

public class BOJ_14425 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static Node root = new Node(false, 0, ' ');
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		for(int i=0; i<N; i++) {
			String word = br.readLine();
			insert(word);
		}
		
		for(int i=0; i<M; i++) {
			String word = br.readLine();
			if(find(word)) {
				answer++;
			}
		}
		System.out.println(answer);
	}
	
	private static void insert(String str) {
		Node current = root;
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			int charIdx = c - 'a';
			
			if(current.child[charIdx] == null) {
				current.child[charIdx] = new Node(false, 0, c);
				current.childNum++;
			}
			current = current.child[charIdx];
		}
		current.isTerminal = true;
	}
	
	private static boolean find(String str) {
		Node current = root;
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			int charIdx = c - 'a';
			if(current.child[charIdx] == null) {
				return false;
			}
			current = current.child[charIdx];
		}
		return current != null && current.isTerminal;
	}

}
