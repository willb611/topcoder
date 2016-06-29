import java.util.*;
public class FewestFactors {
  public int number(int[] digits) {
    if (digits.length == 1) {
      return digits[0];
    } else {
      List<String> combinations = new ArrayList<>();
      ds = digits;
      String s = "";
      for (int i = 0; i < digits.length; i++) s += "?";
      find(new boolean[digits.length], combinations, s.toCharArray());
      TreeSet<Integer> poss = convert(combinations);
      ps(poss.first());
      TreeMap<Integer, List<Integer>> results = new TreeMap<>();
      for (int made : poss) {
        int factCount = facts.get(made).size();
        //System.err.println("found a possible: " + made + " with num factors: " + factCount); // DEBUG
        List<Integer> lis = results.get(factCount);
        if (lis == null) lis = new ArrayList<>();
        lis.add(made);
        results.put(factCount, lis);
      }
      
      int val = results.firstKey();
      Collections.sort(results.get(val));
      return results.get(val).get(0);
    }
  }
  TreeSet<Integer> convert(List<String> l) {
    TreeSet<Integer> set = new TreeSet<Integer>(Collections.reverseOrder());
    for (String s : l) {
      set.add(Integer.parseInt(s));
      //System.out.println("Got str: " + s); // DEBUG
    }
    return set;
  }
  static int[] ds;
  public void find(boolean[] used, List<String> l, char[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == '?') {
        for (int j = 0; j < used.length; j++) {
          if (!used[j]) {
            used[j] = true;
            arr[i] = String.valueOf(ds[j]).toCharArray()[0];
            
            find(used, l, arr);
            
            used[j] = false;
            arr[i] = '?';
          }
        }       
        return;
      }
    }
    l.add(String.valueOf(arr));
  }
  Map<Integer, Set<Integer>> facts;
  void ps(int max) {
    facts = new HashMap<>();
    for (int i = 0; i <= max; i++) facts.put(i, new HashSet<>());
    int limit = (int)Math.ceil(Math.sqrt(max));
    for (int i = 2; i <= max; i++) {
      for (int j = i*2; j <= max; j += i) {
        facts.get(j).add(i);
      }
    }
    for (int i = 1; i <= max; i++) facts.get(i).add(i);
  }
  
  
}