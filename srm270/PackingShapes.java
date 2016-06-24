public class PackingShapes {
  public String[] tryToFit(int width, int height, String[] shapes) {
    String[] results = new String[shapes.length];
    for (int i = 0; i < shapes.length; i++) {
      results[i] = fits(width, height, shapes[i]);
    }
    return results;
  }
  public String fits(int width, int height, String shape) {   
    String[] split = shape.split("\\s+");
    if (split[0].equalsIgnoreCase("CIRCLE")) {
      int radius = Integer.parseInt(split[1]);
      if (radius < Math.min(width, height)) {
        return "YES";
      }  else {
        return "NO";
      }
    } else {
      int w = Integer.parseInt(split[1]);
      int h = Integer.parseInt(split[2]);
      if (w < width && h < height
        || w < height && h < width) {
        return "YES";
      } else if (h+w < Math.hypot(width, height)) {
        return "YES";
      } {
        return "NO";
      }
    }
  }
}