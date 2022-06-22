package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2293 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n+1];
        int[] dp = new int[10010];
        for(int i=1; i<=n; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        dp[0] = 1;
        for(int i=1; i<=n; i++) {
            for(int j=coins[i]; j<=k; j++) {
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }
        System.out.println(dp[k]);
    }
}
