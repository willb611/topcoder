import java.util.*;
public class TriangleEasy {
	public int find(int n, int[] x, int[] y) {
		if (x.length == 0) {
			return 3;
		}
		boolean[] seen = new boolean[n];
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) map.put(i, new HashSet<>());
		for (int i = 0; i < x.length; i++) {
			Set<Integer> c = map.get(x[i]);
			c.add(y[i]);
			map.put(x[i], c);

			c = map.get(y[i]);
			c.add(x[i]);
			map.put(y[i], c);
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(map.get(i).size(), max);
		}
		if (max == 1) return 2;
		for (int i = 0; i < n; i++) {
			List<Integer> c = new ArrayList<>(map.get(i));
			if (c.size() > 1) {
				for (int k = 0; k < c.size(); k++) {
					for (int j = 0; j < c.size(); j++) {
						if (j == k) continue;
						int n1 = c.get(j);
						int n2 = c.get(k);
						if (map.get(n1).contains(n2)) return 0;
					}
				}
			}
		}
		return 1;
	}
}