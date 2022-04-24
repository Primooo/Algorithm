package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_SwimmingPool {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int dayPrice, monthPrice_1, monthPrice_3, yearPrice, answer, leftPersons;
	private static int[] planner;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			answer = Integer.MAX_VALUE;
			leftPersons = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			dayPrice = Integer.parseInt(st.nextToken());
			monthPrice_1 = Integer.parseInt(st.nextToken());
			monthPrice_3 = Integer.parseInt(st.nextToken());
			yearPrice = Integer.parseInt(st.nextToken());
			planner = new int[13];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=12; i++) {
				planner[i] = Integer.parseInt(st.nextToken());
				leftPersons += planner[i];
			}
			
			dfs(1, 0, leftPersons);
			
			if(answer > yearPrice) {
				answer = yearPrice;
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static void dfs(int curMonth, int price, int leftPersons) {
		if(curMonth > 12) {
			if(leftPersons == 0 && answer > price) {
				answer = price;
			}
			return;
		}
		
		if(planner[curMonth] != 0) {
			price += planner[curMonth] * dayPrice;
			leftPersons -= planner[curMonth];
			dfs(curMonth+1, price, leftPersons);
			price -= planner[curMonth] * dayPrice;
			leftPersons += planner[curMonth];
			
			price += monthPrice_1;
			leftPersons -= planner[curMonth];
			dfs(curMonth+1, price, leftPersons);
			price -= monthPrice_1;
			leftPersons += planner[curMonth];
		}
		
		int check3Person = check3Month(curMonth);
		if(check3Person > 0) {
			price += monthPrice_3;
			leftPersons -= check3Person;
			dfs(curMonth+3, price, leftPersons);
			price -= monthPrice_3;
			leftPersons += check3Person;
		}
		
//		if(!flag) {
		dfs(curMonth+1, price, leftPersons);
//		}
	}

	private static int check3Month(int month) {
		int persons = 0;
		int counter = 0;
		for(int i=month; i<13; i++) {
			if(counter == 3) break;
			
			if(planner[i] != 0) {
				persons += planner[i];
			}
			counter++;
		}
		return persons;
	}
}
