import java.util.*;
public class FallingCoconuts {
  int offset = 12;
  final int MAX_DROP_INDEX = 20;
  public String[] harvest(int[] drops) {
    LinkedList<LinkedList<Boolean>> cocos = new LinkedList<>();
    cocos.add(new LinkedList<>());
    for (int i = 0; i < offset*2 + MAX_DROP_INDEX; i++) {
      cocos.get(0).add(false);
    }
    for (int i = 0; i < drops.length; i++) {
      int drop = drops[i];
      addDrop(drop, cocos);
    }
    trim(cocos);
    String[] res =  build(cocos);
    return res;
  }
  private String[] build(LinkedList<LinkedList<Boolean>> cocos) {
    String[] result = new String[cocos.size()];
    for (int i = 0; i < cocos.size(); i++) {
      StringBuilder sb = new StringBuilder();
      LinkedList<Boolean> row = cocos.get(i);
      for (int j = 0; j < row.size(); j++) {
        if (row.get(j)) {
          sb.append("O");
        } else {
          sb.append("-");
        }
      }
      result[i] = sb.toString();
    }
    return result;
  }
  private void trim(LinkedList<LinkedList<Boolean>> cocos) {
    int minFirst = Integer.MAX_VALUE;
    int maxLast = Integer.MIN_VALUE;
    for (int j = 0; j < cocos.get(0).size(); j++) {
      if (cocos.get(0).get(j)) {
        minFirst = Math.min(minFirst, j);
        maxLast = Math.max(maxLast, j);
      }
    }
    // trim edges
    int numToRemoveFromEnd = cocos.get(0).size() - maxLast - 1;
    for (int i = 0; i < cocos.size(); i++) {
      for (int j = 0; j < minFirst; j++) {
        cocos.get(i).removeFirst();
      }
      for (int k = 0; k < numToRemoveFromEnd; k++) {
        cocos.get(i).removeLast();
      }
    }
    // trim last row ? 
    boolean hasC = false;
    int lastIndex = cocos.size() - 1;
    for (int i = 0; i < cocos.get(lastIndex).size(); i++) {
      if (cocos.get(lastIndex).get(i)) {
        hasC = true;
        break;
      }
    }
    if (!hasC) {
      cocos.removeLast();
    }
  }
  private void addDrop(int drop, LinkedList<LinkedList<Boolean>> cocos) {
    int index = drop + offset;
    int yval = 0;
    while (yval < cocos.size()) {
      if (!cocos.get(yval).get(index)) {
        slide(index, yval, cocos);
        return;
      }
      yval++;
    }
    LinkedList<Boolean> list = new LinkedList<>();
    for (int i = 0; i < cocos.get(0).size(); i++) list.add(false);
    cocos.addLast(list);
    slide(index, yval, cocos);
  }
  private void slide(int x, int newCoco, LinkedList<LinkedList<Boolean>> cocos) {
    int Y = newCoco - 1;
    if (newCoco == 0 && !cocos.get(0).get(x)) { // ground
      cocos.get(0).set(x, true);
    } else if (cocos.get(Y).get(x-1) && cocos.get(Y).get(x+1)) {
      cocos.get(newCoco).set(x, true);
    } else if (!cocos.get(Y).get(x+1)) {
      slide(x+1, Y, cocos);
    } else if (cocos.get(Y).get(x+1) && !cocos.get(Y).get(x-1)) {
      slide(x-1, Y, cocos);
    } else {
      throw new RuntimeException("unexpected case");
    }
  }
}