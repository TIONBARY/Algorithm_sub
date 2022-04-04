// LIS by DP

import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			
			int N = sc.nextInt();
			int[] arr = new int[N];
			int[] dp = new int[N];
			
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			dp[0] = 1;
			int ret = 1;
			
			for(int i = 1; i < N; i++) {
				dp[i] = 1;
				for(int j = 0; j < i; j++) {
					if(arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				ret = Math.max(ret, dp[i]);
			}
			
			System.out.println("#" + test_case + " " + ret);
		}
	}
}
