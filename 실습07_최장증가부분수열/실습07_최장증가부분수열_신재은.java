import java.io.*;

public class 실습07_최장증가부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // tc 개수
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 수열 길이
			
			int[] arr = new int[N];
			String[] row = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(row[i]);
			}
			
			int[] LIS = new int[N];
			int lis = 0;
			for(int i=0; i<N; i++) {
				LIS[i] = 1;
				for(int j=0; j<i; j++) {
					// 증가수열 만족 && 기존 lis보다 길 때
					if(arr[j]<arr[i] && LIS[i]<LIS[j]+1) {
						LIS[i] = LIS[j] + 1;
					}
				}
				if(lis < LIS[i]) lis = LIS[i]; // 최장길이 더 길면 갱신
			}
			
			System.out.println("#"+tc+" "+lis);
		}
		
	}

}
