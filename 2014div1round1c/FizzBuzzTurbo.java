public class FizzBuzzTurbo
{
  public long[] counts(long A, long B)
  {
    long[] res = new long[3]; // fiz,bz,fzbz
    long a;
    long mul=3;
    a = A + mul-(A%mul);
    if (a>B)
      res[0]=0;
    else
    {
      res[0] = ((B-a) / mul)+1;
    }
    //bz
    mul=5;
    a = A + mul-(A%mul);
    if (a>B)
      res[1]=0;
    else
    {
      res[1] = ((B-a) / mul)+1;
    } 

    // fzbz

    mul=15;
    a = A + mul-(A%mul);
    if (a>B)
      res[2]=0;
    else
    {
      res[2] = ((B-a) / mul)+1;
    } 
    return res;
  }
}