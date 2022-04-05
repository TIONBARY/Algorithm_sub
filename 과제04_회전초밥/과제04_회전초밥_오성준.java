import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int D = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		int C = Integer.parseInt(input[3]);
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int ret = 0;
		int cnt = 0;
		int[] kind = new int[D + 1];
		
		// K개부터 세팅하기
		for(int i = 0; i < K; i++) {
			if(kind[arr[i]] == 0) {
				cnt++;
			}
			kind[arr[i]]++;
		}
		if(kind[C] == 0) ret = cnt + 1;
		else ret = cnt;

		int pointer = K;
		// K 부터 O(N)으로 돌면서 처리하기
		for(int i = 0; i < N; i++) {
			// 가장 앞에꺼 빼기
			kind[arr[i]]--;
			if(kind[arr[i]] == 0) cnt--;
			
			// 이번꺼 넣기
			if(kind[arr[K % N]] == 0) cnt++;
			kind[arr[K % N]]++;
			
			// 쿠폰 이미 먹었나 확인
			if(kind[C] == 0) {
				ret = Math.max(ret, cnt + 1);
			} else {
				ret = Math.max(ret, cnt);
			}
			K++;
		}
		
		System.out.println(ret);
	}

}
