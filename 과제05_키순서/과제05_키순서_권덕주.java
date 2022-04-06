import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 과제05_키순서 {

	static int T, N, M;
	static ArrayList<Integer> adj1[];
	static ArrayList<Integer> adj2[];
	static boolean visited[];
	
    // 큰쪽 방향과 작은쪽 방향으로 모두 방향 그래프를 만든다.
    // 각 방향에 대해서 dfs 탐색을 하면 어떤 사람보다 큰 사람 수, 작은 사람 수를 구할 수 있다.

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adj1 = (ArrayList<Integer>[])new ArrayList[N + 1];
			adj2 = (ArrayList<Integer>[])new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adj1[i] = new ArrayList<>();
				adj2[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				String line[] = br.readLine().split(" ");
				int a = Integer.parseInt(line[0]);
				int b = Integer.parseInt(line[1]);
				adj1[a].add(b);
				adj2[b].add(a);
			}
			
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				visited = new boolean[N + 1];
				Arrays.fill(visited, false);
				int taller = dfs(adj1, visited, i);
				visited = new boolean[N + 1];
				Arrays.fill(visited, false);
				int smaller = dfs(adj2, visited, i);
                // taller와 smaller 개수에 본인이 포함되서 나오기 때문에 합이 N - 1이 아닌 N + 1이다.
				if (taller + smaller == N + 1) {
					answer++;
				}
			}
			System.out.printf("#%d %d\n", testCase, answer);
		}
	}
	
	static int dfs(ArrayList<Integer> arr[], boolean visited[], int node) {
		if (visited[node]) {
			return 0;
		}
		visited[node] = true;
		int res = 1;
		for (int next : arr[node]) {
			res += dfs(arr, visited, next);
		}
		return res;
	}

}
