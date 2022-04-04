import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 실습07_최장증가부분수열 {

	static int T;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			int arr[] = new int[N];
			String line[] = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(line[i]);
			}
			
			long iArr[] = new long[N];
			for (int i = 0; i < N; i++) {
				iArr[i] = Long.MAX_VALUE;
			}
			
			
			for (int i = 0; i < N; i++) {
				int cur = Arrays.binarySearch(iArr, arr[i]);
				if (cur < 0) {
					iArr[-cur - 1] = arr[i]; 
				} else {
					iArr[cur] = arr[i];
				}
			}
			int cnt = 0;	
			for (int i = 0; i < N; i++) {
				if (iArr[i] == Long.MAX_VALUE) {
					break;
				}
				cnt++;
			}
			
			System.out.printf("#%d %d\n", testCase, cnt);
		}
		
	}

}
