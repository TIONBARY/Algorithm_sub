import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1600 {

	static int[] moveX = { -1, 1, 0, 0, -2, -1, 1, 2 ,- 2, -1, 1, 2 };
	static int[] moveY = { 0, 0, 1, -1, -1, -2, -2, -1, 1, 2, 2, 1 };
	static int K,W,H;
	static int[][] arr;
	static boolean[][][] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		String input[] = br.readLine().split(" ");
		W = Integer.parseInt(input[0]);
		H = Integer.parseInt(input[1]);
		
		arr = new int[H][W];
		visit = new boolean[H][W][K+1];
		
		for(int i = 0; i < H; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int level = 0;
		Queue<int[]> q= new LinkedList<int[]>();
		visit[0][0][0] = true;
		q.offer(new int[] {0,0,0});
		boolean flag = false;
		if(H==1 && W == 1) flag = true;
		while(!q.isEmpty() && !flag) {
			level++;
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int[] now = q.poll();
				
				for(int j = 0; j < 4; j++) {
					
					int xx = now[0] + moveX[j];
					int yy = now[1] + moveY[j];
					
					if(xx < 0 || yy < 0 || xx >= H || yy >= W || visit[xx][yy][now[2]] || arr[xx][yy] == 1) {
						continue;
					}
					
					visit[xx][yy][now[2]] = true;
					q.offer(new int[] {xx, yy, now[2]});
					
					if(xx == H - 1 && yy == W - 1) {
						flag = true;
					}
				}
				
				if(now[2] == K) continue;
				
				for(int j = 4; j < 12; j++) {
					
					int xx = now[0] + moveX[j];
					int yy = now[1] + moveY[j];
					
					if(xx < 0 || yy < 0 || xx >= H || yy >= W || visit[xx][yy][now[2] + 1] || arr[xx][yy] == 1) {
						continue;
					}
					
					visit[xx][yy][now[2] + 1] = true;
					q.offer(new int[] {xx, yy, now[2] + 1});
					if(xx == H - 1 && yy == W - 1) {
						flag = true;
					}
				}
			}
			
		}
		
		if(flag)System.out.println(level);
		else System.out.println(-1);
	}

}
