import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static int N, M, sum, result, student[];
	static ArrayList<Integer> list[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			student = new int[N+1];
			list = new ArrayList[N+1];
			
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				s = br.readLine().split(" ");
				int small = Integer.parseInt(s[0]);
				int tall = Integer.parseInt(s[1]);
				list[small].add(tall);
			}
			
			for(int i=1; i<=N; i++) {
				sum = 0;
				visited = new boolean[N+1];
				visited[i] = true;
				DFS(i);
				student[i] += sum;
			}
				
			result = 0;
			for(int i=1; i<=N; i++) {
				if(student[i] == N) {
					result++;
				}
			}
			
			System.out.println("#"+tc+" "+result);
		}
		
		br.close();
	}
	
	static void DFS(int index) {
		sum++;
		for(int i=0; i<list[index].size(); i++) {
			if(visited[list[index].get(i)]) {
				continue;
			}
			
			visited[list[index].get(i)] = true;
			student[list[index].get(i)]++;
			DFS(list[index].get(i));
		}
	}
}
