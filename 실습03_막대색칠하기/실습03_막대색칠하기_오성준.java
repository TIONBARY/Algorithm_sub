/*
- 연습문제 2 -
1cm 짜리 파란 막대와 1cm 짜리 노란 막대 그리고 2cm 짜리 빨간 막대가 있다.
이 막대들을 연결하여 길이가 ncm인 막대를 만드는 방법의 수를 f(n)이라 하자.
예를 들면 2cm 막대를 만드는 방법은
(파란 막대, 파란 막대),
(파란 막대, 노란 막대),
(노란 막대, 파란 막대),
(노란 막대, 노란 막대),
(빨간 막대)
5가지이므로 f(2) = 5이다.
f(6)의 값은? 
 */

public class problem2 {

	static final int N = 6;
	
	public static void main(String[] args) {
		int dp[] = new int[N + 1];
		
		dp[1] = 2;
		dp[2] = 5;
		
		for(int i = 3; i <= N; i++) {
			dp[i] = 2*dp[i-1] + dp[i-2];
		}
		// 1cm짜리를 넣으면 나머지 N-1cm를 넣으면되는데 1cm짜리가 종류가 두개
		// 2cm짜리를 넣으면 나머지 N-2cm를 넣으면되고 종루가 하나
		// 그러므로 점화식 An = An-1 * 2 + An-2
		
		System.out.println(dp[N]); // f(6) = 169
	}

}
