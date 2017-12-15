public class PolynomialRemainder
{
  public int findRootMod(int a, int b, int c, int mod)
  {
    for (int i = 0; i < mod; ++i)
    {
      long x = i;
      long p = (a*(x*x % mod)) % mod;
      long q = (b*x) % mod;
      if ((p + q+ c) % mod == 0)
        return i;
    }
    return -1;
  }
  public int findRoot(int a, int b, int c)
  {
    final int mod2 = 2*2*2 * 2*2*2 * 2*2*2;
    final int mod5 = 5*5*5 * 5*5*5 * 5*5*5;
    
    int x2 = findRootMod(a,b,c,mod2);
    int x5 = findRootMod(a,b,c,mod5);
    if (x2 == -1 || x5 == -1) return -1;
    
    int x = x5;
    while (x % mod2 != x2) {
      x += mod5;
    }
    return x;
  }
}