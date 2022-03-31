package 아파트색칠하기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result[] = new int[N+1];
		result[1] = 2;
		result[2] = 3;
		
		for(int i=3; i<=N; i++) {
			result[i] = result[i-1] + result[i-2];
		}
		
		System.out.println(result[N]);
		
		sc.close();
	}
}