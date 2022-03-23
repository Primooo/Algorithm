package Programmers;

import java.util.*;

//2022 KAKAO BLIND RECRUITMENT
//제목 : 양궁대회
//레벨 : 2
class Lion {
    int scoreDiff;
    ArrayList<Integer> scoreList;
    
    Lion(int scoreDiff, ArrayList<Integer> scoreList) {
        this.scoreDiff = scoreDiff;
        this.scoreList = scoreList;
    }
    
    public void setScoreDiff(int diff) {
        this.scoreDiff = diff;
    }
    
    public void setScoreList(ArrayList<Integer> scoreList) {
    	if(this.scoreList.size() == 0) {
    		for(int i=0; i<scoreList.size(); i++) {
    			this.scoreList.add(scoreList.get(i));
    		}
    	}
    	else {
	        for(int i=0; i<scoreList.size(); i++) {
	        	this.scoreList.set(i, scoreList.get(i));
	        }
    	}
    }
}

class Solution {
    private ArrayList<Integer> scores = new ArrayList<>();
    private Lion lion = new Lion(-1, new ArrayList<>(11));
    public int[] solution(int n, int[] info) {
        dfs(info, n, 0, 0);
        if(lion.scoreDiff != -1) {
            System.out.println(lion.scoreDiff);
            for(int i=0; i<lion.scoreList.size(); i++) {
                System.out.print(lion.scoreList.get(i) + " ");
            }
        }
        
        int[] answer = new int[11];
        if(lion.scoreDiff == -1) {
            int[] err = {-1};
            return err;
        }
        for(int i=0; i<lion.scoreList.size(); i++) {
            answer[i] = lion.scoreList.get(i);
        }
        return answer;
    }
    
    private void dfs(int[] info, int n, int depth, int count) {
        if(depth == 11) {
            int apacheScore = 0;
            int lionScore = 0;
            for(int i=0; i<scores.size(); i++) {
                if(info[i] == 0 && scores.get(i) == 0) {
                    continue;
                }
                
                if(info[i] >= scores.get(i)) {
                    apacheScore += (10-i);
                }
                else {
                    lionScore += (10-i);
                }
            }
            
            if(apacheScore < lionScore) {
                int diff = lionScore - apacheScore;
                if(lion.scoreDiff < diff) {
                    // lion = new Lion(diff, scores);
                    lion.setScoreDiff(diff);
                    lion.setScoreList(scores);
                }
                else if(lion.scoreDiff == diff) {
                    for(int i=10; i>=0; i--) {
                        if(scores.get(i) > lion.scoreList.get(i)) {
                            // lion = new Lion(diff, scores);
                            lion.setScoreDiff(diff);
                            lion.setScoreList(scores);
                            break;
                        }
                        else if(scores.get(i) < lion.scoreList.get(i)) {
                            break;
                        }
                    }
                    
                    
                }
            }
            return;
        }
        
        for(int i=n-count; i>=0; i--) {
            scores.add(i);
            dfs(info, n, depth+1, count+i);
            scores.remove(scores.size()-1);
        }
    }
}

public class KAKAO_BOW {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
		int[] answer = s.solution(5, info);
	}

}
