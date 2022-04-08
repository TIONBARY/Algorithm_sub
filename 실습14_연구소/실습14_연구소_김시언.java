import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M, result, map[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static class Virus{
		int x, y;

		public Virus(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		buildWall(0);
		
		System.out.println(result);
		
		br.close();
	}

	static void buildWall(int wall) {
		if(wall == 3) {
			spreadVirus();
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					buildWall(wall+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	static void spreadVirus() {
		Queue<Virus> queue = new LinkedList<>();
		int nx, ny;
		int virusMap[][] = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				virusMap[i][j] = map[i][j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(virusMap[i][j] == 2) queue.add(new Virus(i, j));
			}
		}
		
		while(!queue.isEmpty()) {
			Virus virus = queue.poll();

			for(int i=0; i<4; i++) {
				nx = virus.x + dx[i];
				ny = virus.y + dy[i];
				
				if(nx>=0 && ny>=0 && nx<N && ny<M) {
					if(virusMap[nx][ny] == 0) {
						virusMap[nx][ny] = 2;
						queue.add(new Virus(nx, ny));
					}
				}
			}
		}
		
		countSafezone(virusMap);
	}
	
	static void countSafezone(int virusMap[][]) {
		int temp = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(virusMap[i][j] == 0) temp++; 
			}
		}
		
		result = Math.max(result, temp);
	}
}
