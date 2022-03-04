package Practice;

import java.util.*;

public class Solution {
	public int solution(String s) {
		int answer = -1;
		if(s.length() == 1) return 0;
		
		StringBuilder sb = new StringBuilder(s);
		for(int i=1; i<=5; i++) {
			for(int j=0; j<s.length()-i; j++) {
				StringBuilder temp = new StringBuilder();
				int currentIdx = j;
				int jumpStep = i;
				
				while(currentIdx < s.length()) {
					int nextIdx = currentIdx + jumpStep;
					if(nextIdx >= s.length()) break;
					if(i > 1 && sb.charAt(currentIdx) == '0') {
						currentIdx += jumpStep;
						continue;
					}
					
					int currentNum = Integer.parseInt(sb.substring(currentIdx, nextIdx).toString());
					if(currentNum == Math.pow(10,  i)-1) {
						jumpStep++;
					}
					
					if(nextIdx+jumpStep > s.length()) break;
					int nextNum = Integer.parseInt(sb.substring(nextIdx, nextIdx+jumpStep).toString());
					if(nextNum >= 100000) {
						currentIdx += jumpStep;
						continue;
					}
					
					if(nextNum - currentNum == 1) {
						if(temp.length() == 0) {
							temp.append(Integer.toString(currentNum));
							temp.append(Integer.toString(nextNum));
						}
						else {
							temp.append(Integer.toString(nextNum));
						}
					} else {
						if(temp.length() > answer) {
							answer = temp.length();
						}
						temp.setLength(0);
					}
					currentIdx += jumpStep;
				}
				
				if(temp.length() > answer) {
					answer = temp.length();
				}
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		System.out.println(solution.solution("999910000"));
	}

}
