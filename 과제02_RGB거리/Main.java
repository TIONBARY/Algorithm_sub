package Baekjoon1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, RGB[][], DP[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		N = Integer.parseInt(br.readLine());
		RGB = new int[N][3];
		DP = new int[N][3];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				RGB[i][j] = Integer.parseInt(s[j]);
				if(i==0) {
					DP[i][j] = RGB[i][j];
				}
			}
		}
		
		for(int i=1; i<N; i++) {
			DP[i][0] = Math.min(DP[i-1][1], DP[i-1][2]) + RGB[i][0];
			DP[i][1] = Math.min(DP[i-1][0], DP[i-1][2]) + RGB[i][1];
			DP[i][2] = Math.min(DP[i-1][0], DP[i-1][1]) + RGB[i][2];
		}
		
		System.out.println(Math.min(Math.min(DP[N-1][0], DP[N-1][1]), DP[N-1][2]));
		
		br.close();
	}
}
