import java.util.*;
public class Unique
{
  public String removeDuplicates(String S)
  {
    List<Character> letts = new ArrayList<Character>();
    StringBuffer sb = new StringBuffer(26);
    for (int i=0;i<S.length() && letts.size()<26;i++)
    {
      char c = S.charAt(i);
      if (!letts.contains(new Character(c)))
      {
        sb.append(c);
        letts.add(c);
      }
    }
    return sb.toString();
  }
}