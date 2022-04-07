import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int X = Integer.parseInt(input[1]);
			
			int[][] arr = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			int ret = 0;
			
			// 물론 세로 방향 배열을 따로 만들어서 할 수 있겠지만, 그렇게 괜히 메모리 쓸바엔 그냥 코드로 하겠다는 생각
			// 가로 처리
			for(int i = 0; i < N; i++) {
				int before = arr[i][0];
				int cnt = 0;
				int j = 0;
				boolean flag = true;
				
				while(j < N) {
					int now = arr[i][j];
					
					if(now == before) {
						cnt++;
						j++;
					} else if(Math.abs(now - before) > 1) { // 높이는 1로 고정이므로 1보다 크면 안됨
						flag = false;
						break;
					} else if(now - before == 1) { // cnt해온던 걸로 처리
						if(cnt < X) {
							flag = false;
							break;
						}
						before = now;
						cnt = 1;
						j++;
					} else if(before - now == 1) { // 진행방향으로 가서 확인해야함
						int tmp = 0;
						int t = 0;
						for(t = j; t < N && t < j + X; t++) {
							if(arr[i][t] == now) tmp++;
							else break;
						}
						if(tmp == X) {
							before = now;
							cnt = 0; // 이미 경사로를 설치했기 때문에 cnt는 0이여야함
							j = t;
						} else {
							flag = false;
							break;
						}
					}
				}
				
				if(flag) {
					ret++;
				}
			}
			
			// 세로 처리
			for(int i = 0; i < N; i++) {
				int before = arr[0][i];
				int cnt = 0;
				int j = 0;
				boolean flag = true;
				
				while(j < N) {
					int now = arr[j][i];
					
					if(now == before) {
						cnt++;
						j++;
					} else if(Math.abs(now - before) > 1) { // 높이는 1로 고정이므로 1보다 크면 안됨
						flag = false;
						break;
					} else if(now - before == 1) { // cnt해온던 걸로 처리
						if(cnt < X) {
							flag = false;
							break;
						}
						before = now;
						cnt = 1;
						j++;
					} else if(before - now == 1) { // 진행방향으로 가서 확인해야맣
						int tmp = 0;
						int t = 0;
						for(t = j; t < N && t < j + X; t++) {
							if(arr[t][i] == now) tmp++;
							else break;
						}
						if(tmp == X) {
							before = now;
							cnt = 0; // 이미 경사로를 설치했기 때문에 cnt는 0이여야함 -> 이거 안해주면 40개만 맞음
							j = t;
						} else {
							flag = false;
							break;
						}
					}
				}
				
				if(flag) {
					ret++;
				}
			}
			
			System.out.println("#" + tc + " " + ret);
		}
	}

}
