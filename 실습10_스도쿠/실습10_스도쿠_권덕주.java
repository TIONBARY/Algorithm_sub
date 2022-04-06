import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 실습10_스도쿠 {

	static int board[][] = new int[9][9];
	static ArrayList<Point> list;
	
    // 모든 경우를 다 해보되 중간에 안되는경우에는 가지를 친다.

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			char line[] = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				board[i][j] = line[j] - '0';
				if (board[i][j] == 0) {
					list.add(new Point(i, j));
				}
			}
		}
		tryAll(0);

	}
	
	static void tryAll(int num) {
		if (num == list.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		Point p = list.get(num);
		int y = p.y;
		int x = p.x;
		for (int i = 1; i <= 9; i++) {
			board[y][x] = i;
			if (vCheck(y) && hCheck(x) && sCheck(y, x)) {
				tryAll(num + 1);
			}
			board[y][x] = 0;
		}
	}
	
	static boolean vCheck(int y) {
		
		boolean check[] = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (board[y][i] == 0) {
				continue;
			}
			if (check[board[y][i]]) {
				return false;
			}else {
				check[board[y][i]] = true;
			}
		}
		
		return true;
	}
	
	static boolean hCheck(int x) {
		boolean check[] = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (board[i][x] == 0) {
				continue;
			}
			if (check[board[i][x]]) {
				return false;
			}else {
				check[board[i][x]] = true;
			}
		}
		
		return true;
	}
	
	static boolean sCheck(int y, int x) {
		boolean check[] = new boolean[10];
		check[0] = true;
		int startY = (y / 3) * 3;
		int startX = (x / 3) * 3;
		
		for (int i = startY; i < startY + 3; i++) {
			for (int j = startX; j < startX + 3; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				if (check[board[i][j]]) {
					return false;
				}else {
					check[board[i][j]] = true;
				}
			}
		}
		
		return true;
		
	}
	
	static class Point {
		int y, x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
