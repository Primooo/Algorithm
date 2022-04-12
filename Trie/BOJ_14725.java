package Trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class AntNode implements Comparable<AntNode>{
	int depth;
	String val;
	ArrayList<AntNode> childList;
	boolean isTerminal;
	
	AntNode(int depth, String val, boolean isTerminal) {
		this.depth = depth;
		this.val = val;
		this.isTerminal = isTerminal;
		this.childList = new ArrayList<AntNode>();
	}
	
	@Override
	public int compareTo(AntNode a) {
		return this.val.compareTo(a.val);
	}
}

public class BOJ_14725 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static AntNode root = new AntNode(0, "", false);
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			AntNode current = root;
			int depth = 1;
			for(int j=0; j<K; j++) {
				String str = st.nextToken();
				boolean flag = false;
				int idx = -1;
				for(int k=0; k<current.childList.size(); k++) {
					if(current.childList.get(k).val.equals(str) ) {
						flag = true;
						idx = k;
						break;
					}
				}
				
				if(flag) {
					current = current.childList.get(idx);
					depth++;
				}
				else {
					current.childList.add(new AntNode(depth++, str, false));
					int childIdx = current.childList.size() - 1;
					current = current.childList.get(childIdx);
				}
			}
			current.isTerminal = true;
			current = root;
		}
		
		print(root);
		System.out.println(sb.toString());
		
	}
	private static void print(AntNode current) {
		if(current.depth > 0) {
			StringBuilder tmp = new StringBuilder();
			for(int i=1; i<current.depth; i++) {
				tmp.append("--");
			}
			tmp.append(current.val + "\n");
			sb.append(tmp.toString());
		}
		Collections.sort(current.childList);
		for(int i=0; i<current.childList.size(); i++) {
			print(current.childList.get(i));
		}
	}
}
