package SWEA;
/*
 * 삼성 모의 SW 역량테스트
 * 문제: 점심 식사시간
 */
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Stair {
	int x;
	int y;
	int length; 
	
	Stair(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}
}

class Person implements Comparable<Person> {
	int x;
	int y;
	int selection;
	int dist;
	
	Person(int x, int y, int selection, int dist) {
		this.x = x;
		this.y = y;
		this.selection = selection;
		this.dist = dist;
	}
	
	public void chooseStair(int selection, int dist) {
		this.selection = selection;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Person p) {
		return this.dist - p.dist;
	}
}

public class SWEA_LunchTime {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[][] map;
	private static ArrayList<Person> personList;
	private static ArrayList<Stair> stairList;
	private static int answer;
	
	public static void main(String args[]) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int personCount = 0;
			answer = 987654321;
			map = new int[N][N];
			personList = new ArrayList<Person>();
			stairList = new ArrayList<Stair>();
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 1) {
						stairList.add(new Stair(i, j, map[i][j]));
					}
					else if(map[i][j] == 1) {
						personList.add(new Person(i, j, -1, -1));
						personCount++;
					}
				}
			}
			dfs(0, personCount);
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static void dfs(int idx, int n) {
		if(idx == n) {
//			for(int i=0; i<personList.size(); i++) {
//				System.out.print(personList.get(i).selection + " ");
//			}
//			System.out.println();
			
			down(n);
			return;
		}
		
		for(int i=0; i<=1; i++) {
			Person p = personList.get(idx);
			Stair s = stairList.get(i);
			int dist = Math.abs(p.x-s.x) + Math.abs(p.y-s.y);
			p.chooseStair(i, dist);
			personList.set(idx, p);
			dfs(idx+1, n);
		}
	}
	
	private static void down(int n) {
		int[] personInStair = new int[2];
		boolean[] visited = new boolean[n];
		int[] waiting = new int[n];
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		int timer = 0;
		int count = 0;
		while(true) {
			while(!q1.isEmpty()) {
				if(q1.peek() == timer) {
					personInStair[0]--;
					count++;
					q1.poll();
				}
				else {
					break;
				}
			}
			
			while(!q2.isEmpty()) {
				if(q2.peek() == timer) {
					personInStair[1]--;
					count++;
					q2.poll();
				}
				else {
					break;
				}
			}
			
			for(int i=0; i<n; i++) {
				if(visited[i] == true) continue;
				int stairNum = personList.get(i).selection;
				int dist = personList.get(i).dist;
				if(dist <= timer && personInStair[stairNum] < 3) {
					personInStair[stairNum]++;
					if(stairNum == 0) {
						q1.add(dist + waiting[i] + stairList.get(stairNum).length + 1);
					}
					else {
						q2.add(dist + waiting[i] + stairList.get(stairNum).length + 1);
					}
					visited[i] = true;
				}
				else if(dist < timer && personInStair[stairNum] >= 3) {
					waiting[i]++;
				}
			}
			
			if(count == n) {
				break;
			}
			timer++;
		}
		
		if(answer > timer) {
			answer = timer;
		}
	}
}
