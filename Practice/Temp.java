package Practice;

import java.io.*;
import java.util.*;

public class Temp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long[] arr = new long[N];
		long median = arr[0] = 1983;
		for(int i=1; i<N; i++) {
			arr[i] = arr[i-1] * a+b % 20090711;
		}
		
		Comparator<Long> maxHeapComparator = new Comparator<Long>() {
			@Override
			public int compare(Long l1, Long l2) {
				if(l1 > l2) return -1;
				else return 1;
			}
		};
		
		Comparator<Long> minHeapComparator = new Comparator<Long>() {
			@Override
			public int compare(Long l1, Long l2) {
				if(l1 > l2) return 1;
				else return -1;
			}
		};
		
		PriorityQueue<Long> maxHeap = new PriorityQueue<>(maxHeapComparator);
		PriorityQueue<Long> minHeap = new PriorityQueue<>(minHeapComparator);
		
		Long result = 0L;
		for(int i=0; i<N; i++) {
			long value = arr[i];
			if(maxHeap.size() == minHeap.size()) {
				maxHeap.offer(value);
			}
			else {
				minHeap.offer(value);
			}
			
			if(!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				long max = maxHeap.poll();
				long min = minHeap.poll();
				minHeap.offer(max);
				maxHeap.offer(min);
			}
			result = (result + maxHeap.peek()) % 20090711;
		}
		System.out.println(result);
	}

}
