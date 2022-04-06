import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			
			int ret = 0;
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			ArrayList<Integer>[] listD = new ArrayList[N]; // 나보다 큰 수로 가는 간선
			ArrayList<Integer>[] listU = new ArrayList[N]; // 나보다 작은 수로 가는 간선
			
			for(int i = 0; i < N ; i++) {
				listD[i] = new ArrayList<>();
				listU[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				int A = sc.nextInt() - 1;
				int B = sc.nextInt() - 1;
				listD[A].add(B);
				listU[B].add(A);
			}
			
			// 각 점을 돌면서 listD와 listU를 활용해서 BFS를 실행한다 
			// -> 지나간 점의 수를 모두 합쳐서 그게 모든 점의 수와 같으면 해당 점은 위치가 확실하다고 생각한다.
			for(int i = 0; i < N; i++) {
				int cnt = 0;
				boolean[] visit = new boolean[N];
				cnt += bfs(visit, listD, i); // 나보다 큰 수 개수 세기
				cnt += bfs(visit, listU, i); // 나보다 작은 수 개수 세기
				
				if(cnt == N - 1) ret++;
			}
			
			System.out.println("#" + tc + " " + ret);
		}
	}

	private static int bfs(boolean[] visit, ArrayList<Integer>[] list, int start) {
		
		int cnt = 0;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next: list[now]) {
				if(visit[next]) continue;
				q.offer(next);
				visit[next] = true;
				cnt++;
			}
		}
		
		return cnt;
	}

}

