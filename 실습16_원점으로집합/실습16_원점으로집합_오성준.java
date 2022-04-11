import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] d = new int[2];
			int max = -1;
			for(int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				int x = Math.abs(Integer.parseInt(input[0]));
				int y = Math.abs(Integer.parseInt(input[1]));
				
				d[(x + y) % 2]++; 
				max = Math.max(max, x + y);
			}
			
			if(d[0] != 0 && d[1] != 0) {
				sb.append("#").append(tc).append(" ").append(-1).append("\n");
				continue;
			}

			int ret = -1;
			int n = 0, sum = 0;
			while(true) { 
				if(sum >= max && (sum - max)%2 == 0) break;
				sum += ++n;
			}
			ret = Math.max(ret, n);
			
			sb.append("#").append(tc).append(" ").append(ret).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}

