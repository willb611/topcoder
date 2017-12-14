public class BearNSWE {
  public double totalDistance(int[] a, String dir) {
    char[] dirs = dir.toCharArray();
    int x = 0, y = 0;
    int distance = 0;
    for (int i = 0; i < a.length; i++) {
      if (dirs[i] == 'N') {
        y += a[i];
      } else if (dirs[i] == 'S') {
        y -= a[i];
      } else if (dirs[i] == 'E') {
        x += a[i];
      } else {
        x -= a[i];
      }
      distance += a[i];
    }
    double res = Math.hypot(Math.abs(x), Math.abs(y));
    return res + distance;
  }
}