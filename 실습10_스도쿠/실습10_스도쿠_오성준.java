import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static boolean[][] colNumCheck = new boolean[10][10]; // Column 내 숫자 확인
	static boolean[][] rowNumCheck = new boolean[10][10]; // Row 내 숫자 확인
	static boolean[][][] boxNumCheck = new boolean[3][3][10]; // 3X3 박스에서 숫자 확인
	static int[][] arr = new int[9][9];
	static int cnt = 0;
	static boolean done = false;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = input[j] - '0';
				if(arr[i][j] == 0) cnt++;
				
				// 0일때도 체크해도 자연스럽게 넘어감 -> 총 10칸이기 때문에
				colNumCheck[i][arr[i][j]] = true;
				rowNumCheck[j][arr[i][j]] = true;
				boxNumCheck[i/3][j/3][arr[i][j]] = true;
			}
		}
		
		backTracking(0);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void backTracking(int count) {
		if(done) return;
		if(count == cnt) {
			done = true;
			return;
		}
		boolean find = false;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(arr[i][j] != 0) continue;
				find = true;
				for(int t = 1; t <= 9; t++) {
					if(!colNumCheck[i][t] && !rowNumCheck[j][t] && !boxNumCheck[i/3][j/3][t]) {
						// 우선 true로 체크해주고
						colNumCheck[i][t] = true;
						rowNumCheck[j][t] = true;
						boxNumCheck[i/3][j/3][t] = true;
						arr[i][j] = t;
						// 실행후 
						backTracking(count + 1);
						// 하나 찾았나 확인하고
						if(done) break;
						// 완성안됬으면 다시 돌려준다
						colNumCheck[i][t] = false;
						rowNumCheck[j][t] = false;
						boxNumCheck[i/3][j/3][t] = false;
						arr[i][j] = 0;
					}
				}
				if(find) return;
			}
			if(find) return;
		}
	}
}
