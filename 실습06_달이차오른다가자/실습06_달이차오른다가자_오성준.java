import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// dist 배열 + BFS

public class Main {

	public static void main(String[] args) throws Exception {
		int[] moveX = { -1, 1, 0, 0 };
		int[] moveY = { 0, 0, -1, 1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		char[][] arr = new char[N][M];

		Point start = null;

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '0') {
					start = new Point(i, j);
					arr[i][j] = '.';
				}
			}
		}

		// 메모리 용량상 비트마스킹을 하라는 신의 계시다 -> 배열로 열쇠 체크하면 터질듯?
		// 최단거리는 BFS 또는 다익스트라가 근본이다 -> 근데 사실 q를 쓰는거랑 pq를 쓰는거 정도의 차이 => q로 결정
		// 근데 이건 싸이킁을 어떻게 없앨까? visit을 쓰면 아예 재방문을 할 수 가 없다.. -> 근데 이건 재방문을 해야 함

		Queue<Node> q = new LinkedList<>();
		int[][][] dist = new int[N][M][64]; // 이거 상태에 따라서 다르게 확인해야 함 -> 그래서 64칸짜리
		// 즉 내가 키를 가지러 다시 그 길을 갈 필요가 없다는 이야기
		// 그 흔적을 남기는 코드

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		}
		
		dist[start.x][start.y][0] = 0;
		q.offer(new Node(start, 0));

		int level = 0;
		boolean flag = false;
		while(!q.isEmpty() && !flag) {
			level++;
			int size = q.size();
			
			for(int s = 0; s < size; s++) {
				Point p = q.peek().p;
				int k = q.peek().key;
				q.remove();
				
				for(int i = 0; i < 4; i++) {
					int xx = moveX[i] + p.x;
					int yy = moveY[i] + p.y;
					
					if(xx < 0 || yy < 0 || xx >= N || yy >= M || arr[xx][yy] == '#') continue;
				
					if(arr[xx][yy] == '.') {
						if(dist[xx][yy][k] > dist[p.x][p.y][k] + 1) {
							dist[xx][yy][k] = dist[p.x][p.y][k] + 1;
							q.offer(new Node(new Point(xx,yy), k));
						}
					} else if(arr[xx][yy] == '1') {
						flag = true;
						break;
					} else if(arr[xx][yy] >= 'a' && arr[xx][yy] <= 'f') {
						int tmp = arr[xx][yy] - 'a';
						if(dist[xx][yy][k|1<<tmp] > dist[p.x][p.y][k] + 1) {
							dist[xx][yy][k|1<<tmp] = dist[p.x][p.y][k] + 1;
							q.offer(new Node(new Point(xx,yy), k|1<<tmp));
						}
					} else if(arr[xx][yy] >= 'A' && arr[xx][yy] <= 'F') {
						int tmp = arr[xx][yy] - 'A';
						if((k & 1 << tmp) > 0 && dist[xx][yy][k] > dist[p.x][p.y][k] + 1) {
							dist[xx][yy][k] = dist[p.x][p.y][k] + 1;
							q.offer(new Node(new Point(xx,yy), k));
						}
					}
				}
			}
		}
		
		if(!flag) System.out.println(-1);
		else System.out.println(level);
	}

	static class Node{
		Point p;
		int key;
		
		Node(Point p, int key) {
			this.p = p;
			this.key = key;
		}

	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
