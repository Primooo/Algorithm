package Programmers;

import java.util.*;

//2022 KAKAO BLIND RECRUITMENT
//문제 : 신고 결과 받기
//레벨 : 1
public class KAKAO_Report {

	class Solution {
	    private Map<String, Integer> reportMap = new HashMap<String, Integer>();
	    private Map<String, HashSet<String>> reportSet = new HashMap<String, HashSet<String>>();
	    
	    public int[] solution(String[] id_list, String[] report, int k) {
	        int[] answer = new int[id_list.length];
	        for(int i=0; i<id_list.length; i++) {
	            reportMap.put(id_list[i], 0);
	            reportSet.put(id_list[i], new HashSet<String>());
	        }
	        
	        for(int i=0; i<report.length; i++) {
	            String[] reportLog = report[i].split(" ");
	            if(reportSet.get(reportLog[0]).contains(reportLog[1])) {
	                continue;
	            }
	            else {
	                reportMap.put(reportLog[1], reportMap.get(reportLog[1]) + 1);
	                HashSet<String> temp = reportSet.get(reportLog[0]);
	                temp.add(reportLog[1]);
	                reportSet.put(reportLog[0], temp);
	            }
	        }
	        
	        for(int i=0; i<id_list.length; i++) {
	            int count = 0;
	            Iterator iter = reportSet.get(id_list[i]).iterator();
	            while(iter.hasNext()) {
	                if(reportMap.get(iter.next()) >= k) {
	                    count++;
	                }
	            }
	            answer[i] = count;
	        }
	        return answer;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
