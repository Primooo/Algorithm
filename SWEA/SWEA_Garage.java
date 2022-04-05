package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 삼성 모의 SW 역량테스트
 * 문제: 차량 정비소
 */
import java.util.*;

class Customer {
	int timer;
	int customerNum;
	int reception;
	
	Customer(int timer, int customerNum, int reception) {
		this.timer = timer;
		this.customerNum = customerNum;
		this.reception = reception;
	}
	
	public void setTimer() {
		this.timer = this.timer+1;
	}
}

public class SWEA_Garage {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
			int M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
			int K = Integer.parseInt(st.nextToken()); // 고객 수
			int A = Integer.parseInt(st.nextToken()); // 지갑 두고간 고객이 이용한 접수 창구번호 A
			int B = Integer.parseInt(st.nextToken()); // 지갑 두고간 고객이 이용한 정비 창구번호 B
			Queue<Customer> receptionQueue = new LinkedList<Customer>();
			Queue<Customer> repairQueue = new LinkedList<Customer>();
			
			st = new StringTokenizer(br.readLine(), " ");
			int[] receptions = new int[N];
			int[] repairs = new int[M];
			Customer[] cur_receptions = new Customer[N];
			Customer[] cur_repairs = new Customer[M];
			
			for(int j=0; j<N; j++) {
				receptions[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				repairs[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<K; j++) {
				receptionQueue.add(new Customer(Integer.parseInt(st.nextToken()), j+1, 0));
			}
			
			int timer = 0;
			int answer = 0;
			while(true) {
				// reception 증가
				for(int j=0; j<N; j++) {
					if(cur_receptions[j] != null) {
						cur_receptions[j].setTimer();
						if(cur_receptions[j].timer == receptions[j]) {
							int customerNum = cur_receptions[j].customerNum;
							int customerReception = cur_receptions[j].reception;
							repairQueue.add(new Customer(0, customerNum, customerReception));
							cur_receptions[j] = null;
						}
					}
				}
				// repair 증가
				for(int j=0; j<M; j++) {
					if(cur_repairs[j] != null) {
						cur_repairs[j].setTimer();
						if(cur_repairs[j].timer == repairs[j]) {
							cur_repairs[j] = null;
						}
					}
				}
				
				for(int j=0; j<N; j++) {
					if(cur_receptions[j] == null && !receptionQueue.isEmpty()) {
						if(timer >= receptionQueue.peek().timer) { 
							int customerNum = receptionQueue.poll().customerNum;
							cur_receptions[j] = new Customer(0, customerNum, j+1);
						}
					}
				}
				
				for(int j=0; j<M; j++) {
					if(cur_repairs[j] == null && !repairQueue.isEmpty()) {
						Customer c = repairQueue.poll();
						cur_repairs[j] = new Customer(0, c.customerNum, c.reception);
						if(cur_repairs[j].reception == A && j+1 == B) {
							answer += cur_repairs[j].customerNum;
						}
					}
				}
				
				boolean flag = false;
				for(int j=0; j<N; j++) {
					if(cur_receptions[j] != null) {
						flag = true;
						break;
					}
				}
				
				for(int j=0; j<M; j++) {
					if(cur_repairs[j] != null) {
						flag = true;
						break;
					}
				}
				
				if(receptionQueue.isEmpty() && repairQueue.isEmpty() && flag == false) {
					break;
				}
				timer++;
			}
			
			if(answer == 0) {
				System.out.println("#" + i + " " + -1);
			}
			else {
				System.out.println("#" + i + " " + answer);
			}
		}
	}

}
