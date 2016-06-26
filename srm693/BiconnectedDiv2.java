import java.util.*;
public class BiconnectedDiv2 {
	public int minimize(int[] w1, int[] w2) {
		int total = 0;

		for (int i = 0; i < w1.length; i++) {
			if (i == 0 || i == w1.length - 1
					|| w1[i] <= 0) {
				total += w1[i];
			}
		}
		for (int i = 0; i < w2.length; i++) {
			total += w2[i];
		}
		return total;
	}
}