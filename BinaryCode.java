public class BinaryCode
{
  public static void main(String[] args)
  {
    new BinaryCode();
  }
  public BinaryCode() {
    // // 123210122
    // String[] res = decode("011100011");
    // System.out.printf("%s gives %s and %s\n","011100011", res[0], res[1]);
    String[] res = decode("123210122");
    System.out.printf("%s gives %s and %s\n","123210122", res[0], res[1]);
  }
  public String[] decode(String enc)
  {
    int[] encArr = new int[enc.length()];
    for (int i=0;i<encArr.length;i++)
      encArr[i]=Integer.parseInt(enc.substring(i,i+1));
    String[] res = new String[2];
    for (int count=0;count<2;count++) {
      StringBuilder sb = new StringBuilder(enc.length());
      int[] decoded = new int[encArr.length];
      decoded[0] = count;
      sb.append(decoded[0]);
      if (enc.length() < 2) {
        if (decoded[0] == encArr[0]) {
          res[count] = sb.toString();
          continue;
      } else {
        res[count] = "NONE";
        continue;
      }      
      }
      decoded[1] = encArr[0] - decoded[0];
      if (decoded[1] > 1 || decoded[1] < 0) {
        res[count] = "NONE";
        System.out.printf("failing at index: %d, beacuse of: %d, so far: %s\n", i,decoded[i], sb);
        continue;
      }
      sb.append(decoded[1]);
      for (int i=2;i<encArr.length;i++) {
        decoded[i] = encArr[i - 1] - decoded[i-1] - decoded[i-2];
        if (decoded[i] > 1 || decoded[i] < 0) {
          res[count] = "NONE";
          System.out.printf("failing at index: %d, beacuse of: %d, so far: %s\n", i,decoded[i], sb);
          break;
        }
        sb.append(decoded[i]);
      }
      if (res[count] == null) {
        res[count] = sb.toString();
      }
    }
    return res;
  }
}