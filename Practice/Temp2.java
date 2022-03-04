package Practice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Temp2 {
 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int tCase, N, a, b;
 
    public static void main(String[] args) throws Exception {
 
//        tCase = Integer.parseInt(br.readLine());
 
        for (int t = 0; t < 1; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(runningMedian(N, a, b));
        }
    }
 
    public static Long runningMedian(int N, int a, int b) {
 
        Comparator<Long> maxComp = new Comparator<Long>() {
 
            @Override
            public int compare(Long o1, Long o2) {
                if (o1 > o2)
                    return -1;
                else
                    return 1;
            }
        };
        Comparator<Long> minComp = new Comparator<Long>() {
 
            @Override
            public int compare(Long o1, Long o2) {
                if (o1 > o2)
                    return 1;
                else
                    return -1;
            }
        };
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(maxComp);
        PriorityQueue<Long> minHeap = new PriorityQueue<>(minComp);
 
        long seed = 1983;
        Long result = 0L;
        for (int i = 0; i < N; i++) {
            if (maxHeap.size() == minHeap.size())
                maxHeap.offer(seed);
            else
                minHeap.offer(seed);
 
            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.element() < maxHeap.element()) {
                Long maxElement = maxHeap.poll();
                Long minElement = minHeap.poll();
 
                maxHeap.offer(minElement);
                minHeap.offer(maxElement);
            }
            seed = (seed * (long) a + b) % 20090711;
            System.out.println(seed);
            result = (result + maxHeap.element()) % 20090711;
        }
        return result;
    }
}