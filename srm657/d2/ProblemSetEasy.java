public class ProblemSetsEasy
{
  public int maxSets(int E, int EM, int M, int MH, int H)
  {
    while (EM > 0 || MH > 0)
    {
      if (M <= E && M <= H) {
        if (EM == 0) {
          MH--;
          M++;
        } else if (MH == 0) {
          EM--;
          M++;
        } else {
          if (E + EM < MH + H) {
            MH--;
            M++;
          } else {
            EM--;
            M++;
          }
        }
      } else if (E <= H) {
        if (EM > 0) {
          EM--;
          E++;
        } else break;
      } else {
        if (MH > 0) {
          MH--;
          H++;
        } else break;
      }
    } // while
    int min = Math.min(E,M);
    min = Math.min(min, H);
    return min;
  } // meth
}