import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 실습05_녹색옷을입은애가젤다지 {
	static int N;
	static int board[][];
	static int distance[][];
	

    // 모든 경로 값이 양수이기 때문에 다익스트라 알고리즘을 통해서 최단거리를 구한다.


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int pNum = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			board = new int[N][N];
			distance = new int[N][N];
			
			
			String line[];
			for (int i = 0; i < N; i++) {
				line = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(line[j]);
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			
			distance[0][0] = board[0][0];
			pq.add(new Node(0, 0, board[0][0]));
			
			
			while (!pq.isEmpty()) {
				Node n = pq.poll();
				if (distance[n.y][n.x] < n.dist) {
					continue;
				}
				
				if (n.y > 0 && distance[n.y - 1][n.x] > n.dist + board[n.y - 1][n.x]) {
					distance[n.y - 1][n.x] = n.dist + board[n.y - 1][n.x];
					pq.add(new Node(n.y - 1, n.x, n.dist + board[n.y -1][n.x]));
				}
				if (n.y < N - 1 && distance[n.y + 1][n.x] > n.dist + board[n.y + 1][n.x]) {
					distance[n.y + 1][n.x] = n.dist + board[n.y + 1][n.x];
					pq.add(new Node(n.y + 1, n.x, n.dist + board[n.y + 1][n.x]));
				}
				if (n.x > 0 && distance[n.y][n.x - 1] > n.dist + board[n.y][n.x - 1]) {
					distance[n.y][n.x - 1] = n.dist + board[n.y][n.x - 1];
					pq.add(new Node(n.y, n.x - 1, n.dist + board[n.y][n.x - 1]));
				}
				if (n.x < N - 1 && distance[n.y][n.x + 1] > n.dist + board[n.y][n.x + 1]) {
					distance[n.y][n.x + 1] = n.dist + board[n.y][n.x + 1];
					pq.add(new Node(n.y, n.x + 1, n.dist + board[n.y][n.x + 1]));
				}
				
				
			}
			
			System.out.println("Problem " + pNum + ": " + distance[N - 1][N - 1]);
			pNum++;
		}
		
		
	}
	
	static class Node implements Comparable<Node> {

		public int y;
		public int x;
		public int dist;
		
		Node (int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
		
	}

}
