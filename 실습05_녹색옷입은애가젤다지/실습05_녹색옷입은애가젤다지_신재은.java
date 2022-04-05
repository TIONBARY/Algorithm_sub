import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class B4485_녹색옷입은애가젤다지 {
	
	static int[][] map, minR; // 동굴배치, 최소루피
	static int N; // 맵 크기
	static int[] dr = {-1, 0, 1, 0}; // 행이동 - 시계방향(상우하좌)
	static int[] dc = {0, 1, 0, -1}; // 열이동 - 시계방향(상우하좌)
	
	static class Node implements Comparable<Node> {

		int r, c, cost; // 좌표(r, c), 지불루피
		
		public Node(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost; // 오름차순
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 초기값
		int idx = 1;
		// 입력값 0 들어와야 종료
		while(N != 0) {
			map = new int[N][N];
			minR = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String[] row = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(row[j]);
					minR[i][j] = Integer.MAX_VALUE; // 최소값 찾을 수 있게 초기값 max로 설정
				}
			}
						
			System.out.println("Problem "+(idx++)+": "+moveZ());
			N = Integer.parseInt(br.readLine());
		}

	}

	// 다익스트라 최단경로
	// 현재 노드와 연결된 노드의 최소 비용을 저장, 방문 안한 노드 중 최소 비용 노드를 선택해 이동
	public static int moveZ() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minR[0][0] = map[0][0]; // 초기값
		pq.offer(new Node(0, 0, map[0][0])); // 시작 좌표
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			// 사방탐색
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				// 범위 초과
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				// 기존 잃은 값(가중치)보다 현재 이동값이 작으면 업데이트하고 이동
				if(minR[nr][nc] > minR[now.r][now.c]+ map[nr][nc]) {
					minR[nr][nc] = minR[now.r][now.c] + map[nr][nc];
					pq.offer(new Node(nr, nc, minR[nr][nc]));
				}
			}
			
		}
		
		return minR[N-1][N-1];
		
	}

}
