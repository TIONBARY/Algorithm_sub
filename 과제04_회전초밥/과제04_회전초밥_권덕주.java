import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 과제04_회전초밥 {

	static int N, d, k, c;
	static int arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		d = Integer.parseInt(line[1]);
		k = Integer.parseInt(line[2]);
		c = Integer.parseInt(line[3]);
		
		arr = new int[N + k - 1];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < k - 1; i++) {
			arr[N + i] = arr[i];
		}
		
		Map<Integer, Integer> m = new HashMap<>();
		m.put(c, 1);
		int total = 1;
		for (int i = 0; i < k; i++) {
			if (m.containsKey(arr[i])) {
				m.put(arr[i], m.get(arr[i]) + 1);
			} else {
				m.put(arr[i], 1);
				total++;
			}
			
		}
		
		int max = total;
		
		for (int i = 0; i < N - 1; i++) {
			m.put(arr[i], m.get(arr[i]) - 1);
			if (m.get(arr[i]) == 0) {
				total--;
			}
			
			if (m.containsKey(arr[i + k])) {
				if (m.get(arr[i + k]) == 0) {
					total++;
				}
				m.put(arr[i + k], m.get(arr[i + k]) + 1);
			} else {
				m.put(arr[i + k], 1);
				total++;
			}
			if (total > max) {
				max = total;
			}
		}
		
		
		System.out.println(max);
	}

}
