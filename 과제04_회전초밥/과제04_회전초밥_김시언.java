import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		int k = Integer.parseInt(s[2]);
		int c = Integer.parseInt(s[3]);
		int sushi[] = new int[N+k-1];
		int eat[] = new int[d+1];
		int max = 1;
		
		for(int i=0; i<N; i++) sushi[i] = Integer.parseInt(br.readLine());
		for(int i=0; i<k-1; i++) sushi[i+N] = sushi[i];
		
		eat[c]++;
		
		for(int i=0; i<k; i++) {
			if(eat[sushi[i]] == 0) max++;
			eat[sushi[i]]++;
		}
		
		int first = 0;
		int tempMax = max;
		
		for(int i=k; i<sushi.length; i++) {
			eat[sushi[first]]--;
			if(eat[sushi[first]] == 0) tempMax--;
			
			if(eat[sushi[i]] == 0) tempMax++;
			eat[sushi[i]]++;
			max = Math.max(max, tempMax);
			first++;
		}
		
		System.out.println(max);
		
		br.close();
	}
}
