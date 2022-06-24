package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9461 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[101];
        arr[1] = arr[2] = arr[3] = 1;
        arr[4] = arr[5] = 2;
        for(int i=6; i<=100; i++) {
            arr[i] = arr[i-5] + arr[i-1];
        }

        for(int i=0; i<N; i++) {
            int K = Integer.parseInt(br.readLine());
            System.out.println(arr[K]);
        }
    }
}
