import java.util.HashSet;
public class ImageDithering {
	public int count(String dithered, String[] screen) {
		HashSet<Character> ds = new HashSet<>();
		for (char c : dithered.toCharArray()) {
			ds.add(c);
		}
		int count = 0;
		for (String line : screen) {
			for (char c : line.toCharArray()) {
				if (ds.contains(c)) {
					count++;
				}
			}
		}
		return count;
	}
}