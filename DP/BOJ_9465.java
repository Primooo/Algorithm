package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9465 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] stickers;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            stickers = new int[2][N+1];
            dp = new int[2][N+1];

            for(int row = 0; row < 2; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j=1; j<=N; j++) {
                    stickers[row][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];
            for(int j=2; j<=N; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + stickers[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + stickers[1][j];
            }

            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }

}
