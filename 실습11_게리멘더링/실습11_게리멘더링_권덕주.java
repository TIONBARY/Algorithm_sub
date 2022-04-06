import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 실습11_게리맨더링 {

	static ArrayList<Integer> adj[];
	static int N, R;
	static boolean selected[];
	static int group1[];
	static int group2[];
	static boolean visited1[];
	static boolean visited2[];
	static int population[];
	static int minDiff = Integer.MAX_VALUE;

	// 1. 먼저 그룹을 2개로 나눈다.
	// 2. 각각에 대해서 dfs를 돌려서 각각이 연결되어있는지 확인한다. 이 때 반대 그룹으로 가는 경로는 포함하지 않아야 한다.
	// 3. 각각이 연결되어 있으면 인구 차이의 최소값을 갱신한다.
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = (ArrayList<Integer>[])new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		population = new int[N + 1];
		String line[] = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			population[i + 1] = Integer.parseInt(line[i]);
		}
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			
			for (int j = 1; j <= n; j++) {
				adj[i + 1].add(Integer.parseInt(line[j]));
			}
			
		}
		int half = N / 2;
		for (int i = 1; i <= half; i++) {
			R = i;
			selected = new boolean[N + 1];
			combination(0, 1);
		}
		
		if (minDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minDiff);
		}
		
	}

	static void combination(int cnt, int start) {
		if (cnt == R) {
			group1 = new int[R];
			group2 = new int[N - R];
			visited1 = new boolean[N + 1];
			visited2 = new boolean[N + 1];
			int pos1 = 0;
			int pos2 = 0;
			for (int i = 1; i <= N; i++) {
				if (selected[i]) {
					group1[pos1++] = i;
				}else {
					group2[pos2++] = i;
				}
			}
			

			dfs1(group1[0]);
			dfs2(group2[0]);
			
			// 각각이 연결되어 있는지 체크
			if (groupCheck(group1, visited1) && groupCheck(group2, visited2)) {
				int pop1 = getPop(group1);
				int pop2 = getPop(group2);
				int popDiff = Math.abs(pop1 - pop2);
				if (popDiff < minDiff) {
					minDiff = popDiff;
				}
			}
			return;
		}
		
		for (int i = start; i <= N; i++) {
			selected[i] = true;
			combination(cnt + 1, i + 1);
			selected[i] = false;
		}
		
	}
	
	static boolean isIn(int num, int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	static void dfs1(int n) {
		if (visited1[n]) {
			return;
		}
		visited1[n] = true;
		for (int i : adj[n]) {
			if (isIn(i, group2)) {
				continue;
			}
			dfs1(i);
		}
	}
	
	static void dfs2(int n) {
		if (visited2[n]) {
			return;
		}
		visited2[n] = true;
		for (int i : adj[n]) {
			if (isIn(i, group1)) {
				continue;
			}
			dfs2(i);
		}
	}
	
	static boolean groupCheck(int group[], boolean visited[]) {
		for (int i = 0; i < group.length; i++) {
			if (!visited[group[i]]) {
				return false;
			}
		}
		return true;
	}
	
	static int getPop(int group[]) {
		int pop = 0;
		for (int i = 0; i < group.length; i++) {
			pop += population[group[i]];
		}
		return pop;
	}
}
