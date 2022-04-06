import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int ret = Integer.MAX_VALUE; 
	static int sum = 0;
	
	public static void main(String[] args) throws Exception{
		
		//////////////// 입력 시작 //////////////////
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] population = new int[N];
		boolean[] check = new boolean[N];
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(input[i]);
			sum += population[i];
		}
		
		ArrayList<Integer>[] list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			
			input = br.readLine().split(" ");
			int M = Integer.parseInt(input[0]);
			
			for(int j = 1; j <= M; j++) {
				list[i].add(Integer.parseInt(input[j]) - 1);
			}
		}
		//////////////// 입력 완료 ///////////////////
		
		dfs(N, population, check, list, 0);
		
		System.out.println(ret==Integer.MAX_VALUE? -1: ret);
	}

	private static void dfs(int N, int[] population, boolean[] check, ArrayList<Integer>[] list, int now) {
		if(now == N) {
			// check에서 true인것과 false인거 서로 연결가능한지 확인
			// 한점만 잡고 다른 점들로 이동 가능한지 확인하면 됨 -> 한점에서 다른 점들로 무조건 갈 수 있어야하기 때문
			boolean[] visit = new boolean[N];
			
			// true인 점 하나 false인 점 하나 뽑기
			int trueStart = -1, falseStart = -1;
			for(int i = 0; i < N; i++) {
				if(trueStart < 0 && check[i]) trueStart = i;
				if(falseStart < 0 && !check[i]) falseStart = i;
				if(trueStart >= 0 && falseStart >= 0) break;
			}
			
			if(trueStart < 0 || falseStart < 0) return;
			
			int cnt = 0;
			
			// BFS를 사용해서 개수 세기 -> dfs한번 사용해서 또 쓰기 싫었음
			cnt += bfs(N, check, list, visit, trueStart, true);
			cnt += bfs(N, check, list, visit, falseStart, false);
			
			// 모든 점이 연결될 경우 값계산 -> 연결된 점들이 모두 합쳐져서 그 개수가 N과 같아야 함
			if(cnt == N) {
				int trueSum = 0;
				for(int i = 0; i < N; i++) {
					if(check[i]) trueSum += population[i];
				}
				
				ret = Math.min(ret, Math.abs(sum - trueSum * 2));
			}
			
			return;
		}
		
		check[now] = true;
		dfs(N, population, check, list, now + 1);
		check[now] = false;
		dfs(N, population, check, list, now + 1);
	}

	private static int bfs(int n, boolean[] check, ArrayList<Integer>[] list, boolean[] visit, int start,
			boolean b) {
		int cnt = 0;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visit[start] = true;
		cnt++;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next: list[now]){
				if(check[next] == b && !visit[next]) { // b를 사용해서 true일때 false일 때 코드 한번에 처리
					q.offer(next);
					visit[next] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
