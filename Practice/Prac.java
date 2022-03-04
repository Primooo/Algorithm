package Practice;

import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int score;
    
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getScore() {
        return this.score;
    }
    
    @Override
    public int compareTo(Student s) {
//        if (this.score < s.getScore()) {
//            return -1;
//        } else if (this.score > s.getScore()) {
//            return 1;
//        }
//        return 0;
    	return this.score - s.score;
    }
}

public class Prac {

	public static ArrayList<Integer> list = new ArrayList<>();
	public static void dfs(int[] arr, boolean[] visited, int idx, int cnt, int target) {
		if(cnt == target) {
			for(Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(visited[i] == false && i >= idx) {
				list.add(arr[i]);
				visited[i] = true;
				dfs(arr, visited, i, cnt+1, target);
				visited[i] = false;
				list.remove(list.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		q.add(2);
		
		int[] arr = {3, 8, 9, 2, 5};
		boolean[] visited = {false, false, false, false, false};
//		Arrays.sort(arr);
		Arrays.sort(arr);
		int idx = Arrays.binarySearch(arr,  8);
		System.out.println(idx);
//		Integer[] arr = {3, 8, 9, 2, 5};
		List<Integer> arrr = new ArrayList<>();
		arrr.add(1);
		arrr.add(8);
		arrr.add(2);
		arrr.add(5);
		arrr.add(3);
		arrr.sort(null);
		Arrays.fill(arr, 2);
		for(int num: arr) {
			System.out.println(num);
		}
		System.out.println(arrr.indexOf(5));
		arrr.clear();
//		arrr = Arrays.asList(arr);
		
//		dfs(arr, visited, 0, 0, 4);
		
		StringBuilder sb = new StringBuilder(new String("HIHI"));
		sb.append(" zzz");
//		String subs = sb.substring(0, 6);
		sb.replace(0,  4,  "aaaaaadfas");
		String result = sb.toString();
		sb.setCharAt(0, 'b');
		sb.charAt(0);
		sb.deleteCharAt(sb.length()-1);
		sb.delete(0, 1);
		System.out.println(sb);
		
        List<Student> list = new ArrayList<Student>();
        
        list.add(new Student("a", 5));
        list.add(new Student("b", 10));
        list.add(new Student("c", 1));
        list.add(new Student("d", 52));
        list.add(new Student("e", 23));
        
        Collections.sort(list);
        for(Student s : list) {
        	System.out.println(s.name + " " + s.score);
        }
	}

}
