import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 과제02_RGB거리_김시언 {
	static int N, RGB[][], result[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		N = Integer.parseInt(br.readLine());
		RGB = new int[N][3];
		result = new int[N][3];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				RGB[i][j] = Integer.parseInt(s[j]);
				if(i==0) {
					result[i][j] = RGB[i][j];
				}
			}
		}
		
		for(int i=1; i<N; i++) {
			result[i][0] = Math.min(result[i-1][1], result[i-1][2]) + RGB[i][0];
			result[i][1] = Math.min(result[i-1][0], result[i-1][2]) + RGB[i][1];
			result[i][2] = Math.min(result[i-1][0], result[i-1][1]) + RGB[i][2];
		}
		
		System.out.println(Math.min(Math.min(result[N-1][0], result[N-1][1]), result[N-1][2]));
		
		br.close();
	}
}
