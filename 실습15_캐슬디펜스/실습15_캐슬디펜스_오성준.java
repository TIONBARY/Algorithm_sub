import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int[] archer = new int[3];
	static int[][] arr;
	static int N, M, D, ret = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		D = Integer.parseInt(input[2]);

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		// 구현 문제
		// 15C3이므로 궁수들의 위치는 조합을 통해 구한뒤에 처리하면 될듯
		combination(0, 0);
		System.out.println(ret);
	}

	private static void combination(int now, int place) { //

		if (place == 3) {
			// archer 배열로 처리하기
			int cnt = 0;
			boolean[][] check = new boolean[N][M];
			
			Queue<int[]> q = new LinkedList<int[]>(); // 중복을 처리하기 위해서 이걸로 처리해보자
			// 처리 후에 적들을 한칸씩 내릴 수 있지만 내가 한칸씩 올라갈 것
			for (int n = N; n > 0; n--) {
				for (int m : archer) {
					// 궁수들의 현재 위치 (n,m)
					// 왼쪽 보기 (+ 가운데)
					for (int d = 1; d <= D; d++) {
						boolean kill = false;
						for (int l = d - 1; l >= 0; l--) {
							int yy = m - l;
							int xx = n - d + l;
							if (xx < 0 || yy < 0 || yy >= M || arr[xx][yy] == 0 || check[xx][yy])
								continue;
							q.offer(new int[] {xx,yy});
							kill = true;
							break;
						}
						if(kill) break;
						
						// 오른쪽 보기
						for (int l = d - 1; l >= 1; l--) {
							int yy = m + d - l;
							int xx = n - l;
							if (xx < 0 || yy < 0 || yy >= M || arr[xx][yy] == 0 || check[xx][yy])
								continue;
							q.offer(new int[] {xx,yy});
							kill = true;
							break;
						}
						if(kill) break;
					}
				}
				
				while(!q.isEmpty()) { // visit했으면 처리하지 않는 것과 visit처리를 한줄이 끝나고 한번에 해주는게 핵심
					int[] nn = q.poll();
					int xx = nn[0];
					int yy = nn[1];
					
					if(check[xx][yy]) continue;
					check[xx][yy] = true;
					cnt++;
				}
				
			}
			ret = Math.max(ret, cnt);
			return;
		}
		if (now == M) {
			return;
		}

		archer[place] = now;
		combination(now + 1, place + 1);
		combination(now + 1, place);
	}

}

