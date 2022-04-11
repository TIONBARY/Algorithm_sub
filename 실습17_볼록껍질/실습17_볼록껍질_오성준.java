import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		// ccw 구하기 -> 직선 두개의 외적을 통해서 구한다 양수:반시계// 음수:시계
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int X = Integer.parseInt(input[0]);
			int Y = Integer.parseInt(input[1]);
			points[i] = new Point(X, Y);
		}
		
		// 기준점 잡기 -> y값이 가장 작은점 이후론 x값이 가장 작은 점
		Arrays.sort(points, (Point a, Point b) ->{
			if(a.y > b.y) return 1;
			else if(a.y < b.y ) return -1;
			if(a.y == b.y) {
				if(a.x > b.x) return 1;
				else if(a.x < b.x) return -1;
			}
			return 0;
		});
		
		// 기준점 r
		Point r = points[0];
		
		// 반시계방향으로 정ㄹ려시키고, 일직선상에 있으면 먼거부터 나오도록 정렬
		Arrays.sort(points, (Point a, Point b) ->{
			int ccwR = ccw(r, a, b);
			if(ccwR > 0) return -1;
			else if(ccwR < 0) return 1;
			else {
				long distA = dist(r, a);
				long distB = dist(r, b);
				if(distA > distB) return 1;
				return -1;
			}
		});
		
		// 그라함 스캔 알고리즘 구현
		Stack<Point> st = new Stack<>();
		st.add(r);
		
		for(int i = 1; i < N; i++) {
			while(st.size() > 1) {
				Point b = st.pop();
				Point a = st.peek();
				
				if(ccw(a,b,points[i]) > 0) {
					st.push(b);
					break;
				}
			}
			st.add(points[i]);
		}
		
		System.out.println(st.size());
	}

	private static long dist(Point a, Point b) {
		return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
	}

	public static int ccw(Point a, Point b, Point c) {
		long ccwR = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (ccwR > 0) return 1;
        if (ccwR < 0) return -1;
        return 0;
	}
	
	static class Point{
		long x,y;
		Point(long x, long y){
			this.x = x;
			this.y = y;
		}
	}
}

