import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= N; tc++) {
			String[] input = br.readLine().split(" ");
			long start = Long.parseLong(input[0]);
			long end = Long.parseLong(input[1]);
			
			long[] number = new long[10];
			long mul = 1; // 자릿수 의미
			if(start == 0) start = 1;
			while(start <= end) {
				while(start % 10 != 0 && start <= end) {
					calc(number, start, mul);
					start++;
				}
				if(start > end) break;
				while(end % 10 != 9 && start <= end) {
					calc(number, end, mul);
					end--;
				}
				
				long d = end/10 - start/10 + 1; // 한번에 계산을 하기위함
				for(int i = 0; i < 10; i++) {
					number[i] += d * mul;
				}
				mul *= 10;
				start /= 10;
				end /= 10;
			}
			long ret = 0;
			for(int i = 1; i < 10; i++) {
				ret += i * number[i];
			}
			System.out.println("#" + tc + " " + ret);
		}
	}

	private static void calc(long[] number, long x, long mul) {
		for(long i = x; i > 0; i /= 10) {
			String s = Long.toString(i);
			number[s.charAt(s.length() - 1) - '0'] += mul;
		}
	}

}
