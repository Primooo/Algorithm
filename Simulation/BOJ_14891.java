package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14891 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	private static ArrayList<ArrayDeque<Integer>> gearList = new ArrayList<ArrayDeque<Integer>>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int answer = 0;
		for(int i=0; i<4; i++) {
			gearList.add(new ArrayDeque<Integer>());
		}
		
		for(int i=0; i<4; i++) {
			String inputStr = br.readLine();
			for(int j=0; j<inputStr.length(); j++) {
				gearList.get(i).add(inputStr.charAt(j)-'0');
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotateGear(gearNum-1, dir);
		}
		
		for(int i=0; i<4; i++) {
			if(gearList.get(i).peekFirst() == 1) {
				answer += Math.pow(2, i);
			}
		}
		System.out.println(answer);
	}

	private static void rotateGear(int gearNum, int dir) {
		int[] rotateStatus = new int[4];
		rotateStatus[gearNum] = dir;
		
		for(int i=gearNum-1; i>=0; i--) {			
			int leftGearValue = getGearValue(i, 2);
			int rightGearValue = getGearValue(i+1, 6);
			
			if(rotateStatus[i+1] != 0 && leftGearValue != rightGearValue) {
				rotateStatus[i] = rotateStatus[i+1] * -1;
			}
		}
		
		for(int i=gearNum+1; i<4; i++) {
			int leftGearValue = getGearValue(i-1, 2);
			int rightGearValue = getGearValue(i, 6);
			
			if(rotateStatus[i-1] != 0 && leftGearValue != rightGearValue) {
				rotateStatus[i] = rotateStatus[i-1] * -1;
			}
		}
		
		for(int i=0; i<4; i++) {
			if(rotateStatus[i] == 1) {
				int last = gearList.get(i).pollLast();
				gearList.get(i).addFirst(last);
			}
			else if(rotateStatus[i] == -1) {
				int first = gearList.get(i).pollFirst();
				gearList.get(i).addLast(first);
			}
		}
	}
	
	private static int getGearValue(int gearNum, int target_idx) {
		int idx = 0;
		int target = -1;
		
		Iterator<Integer> dq_iter = gearList.get(gearNum).iterator();
		while(dq_iter.hasNext()) {
			target = dq_iter.next();
			if(idx == target_idx) break;
			idx++;
		}

		return target;
	}
}
