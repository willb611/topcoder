public class ModModMod {
  public long findSum(int[] m, int R) {
    long sum = 0;
    for (int f = 1; f <= R; f++) {
      long tmp = f;
      for (int i = 0; i < m.length; i++) {
        tmp = tmp % m[i];
      }
      sum += tmp;
    }
    return sum;
  }
}