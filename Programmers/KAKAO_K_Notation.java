package Programmers;

import java.util.*;

// 2022 KAKAO BLIND RECRUITMENT
// 문제 : k진수에서 소수 개수 구하기
// 레벨 : 2
public class KAKAO_K_Notation {
	public int solution(int n, int k) {
        int answer = -1;
        String notation = makeNotation(n, k);
        answer = getResult(notation);
        return answer;
    }
    
    private String makeNotation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        while(Math.pow(k, tmp) <= n) {
            tmp++;
        }
        for(int i = tmp-1; i >= 0; i--) {
            double num = Math.pow(k, i);
            if(n >= num) {
                int div = (int)(n / num);
                n -= num * div;
                sb.append(Integer.toString(div));
            }
            else {
                sb.append("0");
            }
        } 
        
        return sb.toString();
    }
    
    private boolean isPrime(Long num) {
        if(num<=1) return false;
        else if(num==2) return true;
        for(int i=2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private int getResult(String notation) {
        StringBuilder sb = new StringBuilder();
        Stack<Long> st = new Stack<>();
        int result = 0;
        for(int i=0; i<notation.length(); i++) {
            if(notation.charAt(i) == '0') {
                if(sb.length() != 0) {
                    st.add(Long.parseLong(sb.toString()));
                    sb = new StringBuilder();
                }
                
                if(st.isEmpty()) {
                    st.add((long)0);
                }
                else {
                    if(st.peek() == 0) {
                        continue;
                    }
                    else { // P0 pattern
                        long num = st.pop();
                        st.add((long)0);
                        if(isPrime(num)) result++;
                    }
                }
            }
            else {
                sb.append(notation.charAt(i));
            }
            
            if(i == notation.length()-1 && sb.length() != 0) {
                if(isPrime(Long.parseLong(sb.toString()))) {
                    result++;
                }
            }
        }
        return result;
    }
}