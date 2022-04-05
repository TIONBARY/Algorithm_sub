import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 과제04_회전초밥 {

	static int N, d, k, c;
	static int arr[];
	
	// 원형으로 된 초밥 테이블을 모사하기 위해서 배열을 N에서 N + k - 1개로 크기를 늘린다음 최초 k - 1개를 복사한다.
	// 그 후 k개 만큼의 접시를 집은 상태에서 가지수를 세준다. 이를 시작 접시가 n - 1일때까지 반복한다.
	// Map은 문제를 다시 읽어보니까 초밥 번호 숫자의 제한이 있어서 필요가 없다. index로 접근 가능하기때문에 배열로 충분하다.


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
