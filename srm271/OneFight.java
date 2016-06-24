import java.util.*;
public class OneFight {
  public int monsterKilling(int[] life, int[] damage, int yourDamage) {
    final int MIN_LIFE_NEEDED_AT_END = 1;
    // get map sorted by damage / health ratio - kill those with highest ratio first
    TreeMap<Double, List<Integer>> map = new TreeMap<>(Collections.reverseOrder());
    for (int i = 0; i < life.length; i++) {
      double d = damage[i]/(double)life[i];
      List<Integer> monsters = map.get(Double.valueOf(d));
      if (monsters == null) { monsters = new ArrayList<>(); }
      monsters.add(i);
      map.put(d, monsters);
    }
    int time = 0;
    int damageToMe = 0;
    while (!map.isEmpty()) {
      int monster = removeNext(map);
      int timeToKill = (int) Math.ceil(life[monster] / (double) yourDamage);
      int damageFromMonster = damage[monster] * timeToKill;
      damageToMe += damageFromMonster;
      time += timeToKill;
    }
    return damageToMe + MIN_LIFE_NEEDED_AT_END;
  }
  public int removeNext(TreeMap<Double, List<Integer>> map) {
    Double key = map.firstKey();
    List<Integer> list = map.get(key);
    if (list.size() == 1) {
      map.remove(key);
      return list.get(0);
    } else {
      return list.remove(0);
    }
  }
}