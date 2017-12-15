class RandomPancakeStackDiv2
{
  private double pancakeSolveMy(int[] d, boolean[] use)
  {
    double total = 0;
    int n = use.length;
    int min = Integer.MAX_VALUE;
    for (int i=0;i<use.length;i++){
      if (!use[i]) {
        n--;
        if (i+1 < min) min = i+1;
      }
    }
    for (int i=0;i<d.length;i++)
    {
      if (!use[i]) continue;
      if (i+1 > min) break;
      use[i] = false;
      double res = ((pancakeSolveMy(d, use)+d[i]) * (1/(double)n));
      total += res;
      use[i] = true;
    }
    return total;
  }
  public double expectedDeliciousness(int[] d)
  {
    boolean[] use = new boolean[d.length];
    for (int i=0;i<use.length;i++) use[i] = true;
    return pancakeSolveMy(d, use);
  }
}