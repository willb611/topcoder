import java.util.*;
public class BiconnectedDiv2 {
	public class Edge{
		public Edge(int s, int d, int w) { this.source = s; dest = d; weight = w; }
		public int source, dest, weight;
	}
	public class EComp implements Comparator<Edge> {
  		@Override
 		 public int compare(Edge e1, Edge e2) {
   			 return new Integer(e1.weight).compareTo(e2.weight);
  		}
  	}
	public int minimize(int[] w1, int[] w2) {
		List<Edge> edges = new ArrayList<>();
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>(); // key = node, val = map (key = destination, val = weight)
		int n = w1.length + 1;
		for (int i = 0; i < n; i++) map.put(i, new HashMap<>());
		for (int i = 0; i < w1.length; i++) {
			Edge e = new Edge(i, i+1, w1[i]);
			edges.add(e);
			map.get(i).put(i+1, w1[i]);
			map.get(i+1).put(i, w1[i]);
		}
		for (int i = 0; i < w2.length; i++) {
			Edge e = new Edge(i, i+2, w2[i]);
			edges.add(e);
			map.get(i).put(i+2, w2[i]);
			map.get(i+2).put(i, w2[i]);
		}
		Collections.sort(edges, Collections.reverseOrder(new EComp()));
		for (Edge e : edges) {
			if (e.weight > 0 &&
				map.get(e.source).size() > 2 && map.get(e.dest).size() > 2) {
				// remove this one
				map.get(e.source).remove(e.dest);
				map.get(e.dest).remove(e.source);
			}
		}
		// sum remaining
		int total = 0;
		for (Integer source : map.keySet()) {
			for (Integer dest : map.get(source).keySet()) {
				// remove duplicate
				map.get(dest).remove(source);
				// add weight
				total += map.get(source).get(dest);
			}
			// remove all destinations now processed htem
			map.get(source).clear();
		}
		return total;
	}
}