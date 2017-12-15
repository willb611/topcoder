import java.io.*;
import java.util.*;
public class WolvesAndSheep
{
  boolean[][] matched;
  public List<Integer> fAd(int )
  {
    int m=n;
    int tm = m;
    for (int r=col;r<field[0].length();r++)
    {
      if (field[r].charAt(col)=='S')
        m=r-1;
      else
        matched[r][col]=true;
    }
    if (tm!=m & tm!=n)
      for (int r=row)
  }
  public int getmin(String[] field)
  {
    int f=0;
    int n = -1;
    int s=n;
    int w=n;
    matched = new boolean[field.length][field[0].length()];
    int t;
    for (int col =0; col< field[0].length();col++)
    {
      int w1 =w;
      int s1 = s;
      for (int row=0;row<field.length;row++)
      {
        if (field[row].charAt(col)=='W')
          w=col;
        if (field[row].charAt(col)=='S')
          s=col;
      }
      if ((w1!=w | s1!=s) && (w1!=n ^ s1!=n)) // one found b4
      {
        f++;
        if (w>s)
          w=n;
        else
          s=n;
      } else if ((w1!=w | s1!=s) && (w1!=n & s1!=n)) // both found before
      {
        f++;
        if (w1!= w & s1 !=s);
        else if (w1 != w) s=n;
        else w=n;
      } else
      {
        System.out.println("Going acrs rows, s" + s + " s1 " + s1 + " w " + w + " w1");
      }
    }
    s=n;
    w=n;

    for (int row =0; row< field.length;row++)
    {
      int w1 =w;
      int s1 = s;
      for (int col=0;col<field[0].length();col++)
      {
        if (field[row].charAt(col)=='w')
          w=col;
        if (field[row].charAt(col)=='s')
          s=col;
      }
      if ((w1!=w | s1!=s) && (w1!=n ^ s1!=n)) // one found b4
      {
        f++;
        if (w>s)
          w=n;
        else
          s=n;
      } else if ((w1!=w | s1!=s) & (w1!=n & s1!=n)) // both found before
      {
        f++;
        if (w1!= w & s1 !=s);
        else if (w1 != w) s=n;
        else w=n;
      } else
      {
        System.out.println("Going acrs cols, s" + s + " s1 " + s1 + " w " + w + " w1");
      }
    }
    return f;
  }
}