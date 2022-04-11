import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습16_원점으로집합 {

	static int T, N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			boolean isDifferent = false;
			long isOdd = 0;
			long max = -1;
			for (int i = 0; i < N; i++) {
				String line[] = br.readLine().split(" ");
				long a = Long.parseLong(line[0]);
				long b = Long.parseLong(line[1]);
				long d = Math.abs(a) + Math.abs(b);
				if (i == 0) {
					isOdd = d % 2;
				}
				if (i > 0) {
					if (d % 2 != isOdd) {
						isDifferent = true;
					}
				}
				if (d > max) {
					max = d;
				}
			}
			
			if (isDifferent) {
				System.out.printf("#%d -1\n", testCase);
				continue;
			}
			
			for (long i = 0; i < 100000; i++) {
				long end = i * (i + 1) / 2;
				if (end % 2 == isOdd) {
					if (end >= max) {
						System.out.printf("#%d %d\n", testCase, i);
						break;
					}
				}
			}
			
			
		}
		
	}

}
