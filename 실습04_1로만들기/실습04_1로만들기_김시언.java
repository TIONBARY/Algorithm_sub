import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N, result[];
		N = sc.nextInt();
		result = new int[N+1];
		
		for(int i=2; i<=N; i++) {
			result[i] = result[i-1] + 1;
			if(i%2 == 0) result[i] = Math.min(result[i], result[i/2] + 1);
			if(i%3 == 0) result[i] = Math.min(result[i], result[i/3] + 1);
		}
		
		System.out.println(result[N]);
		
		sc.close();
	}
}
