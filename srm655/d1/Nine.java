import java.util.ArrayList;
import java.util.HashSet;
public class Nine
{
  public static void main(String[] args) {
    int[] arr1 = new int[] {1,2,3};
    // { 1,3,5,8,24,22,25,21,30,2,4,0,6,7,9,11,14,13,12,15,18,17,16,19,26,29,31,28,27,10,20,23};
    System.out.println(new Nine().count(2,arr1));
  }
  public int count(int N, int[] d) {
    // N = number of digits (ie 2 = 00 to 99,4 = 0000 to 9999 inclusive)
    int total = 0;
    boolean[] dp = new boolean[(int)Math.pow(10, N)];
    System.out.println("for n: " + N + " dp length: " + dp.length);
    ArrayList<String> div9 = new ArrayList<String>();
    for (int i=0;i<dp.length; i+=9)
    {
      dp[i] = true;
      StringBuilder s = new StringBuilder(String.valueOf(i));
      while (s.length() < N) s.insert(0, "0");
      div9.add(s.toString());
    }
    ArrayList<String> overall = new ArrayList<String>();
    for (int i= 0; i< d.length; ++i)
    {
      ArrayList<String> poss;
      poss = new ArrayList<String>(div9);
      // poss.removeAll(overall);
      if (d[i] == 0) continue;
      for (int j=0;j<poss.size();)
      {
        String s = poss.get(j);
        StringBuilder sb = new StringBuilder(s.length());
        // read all digits in d[i]
        for (int digit =1, indx=1; digit <= d[i]; digit <<=1, indx++)
        {
          // System.out.println("d[i]: " + d[i]);
          if ((d[i]&digit) != 0) {
            sb.append(s.charAt(s.length() - indx));
            // System.out.println("apending " + s.charAt(s.length() - indx));
          } else {
            // System.out.println("not appending " + s.charAt(s.length() - indx));
          }
        }
        // System.out.println("Sb: " + sb.length());

        if (!dp[(int)Integer.valueOf(sb.toString())]) {
          // System.out.println("removing " + poss.get(j) + " because of d[i]: " + d[i]);
          poss.remove(j);
        } else {
          j++;
        }
      }
      int tmp = overall.size();
      overall.addAll(poss);
      System.out.println("Added " + (overall.size() - tmp) + " for d[i]: " + d[i]);
    }
      total  += overall.size();
      while (total > 1_000_000_007) total -= 1_000_000_007;
      System.out.println("Adding: " + overall.size());
      for (String s: overall) System.out.println(s);
    return total;   
  }
}