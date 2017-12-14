import java.util.*;
public class SalesmansDilemma {
  private boolean reachable, endlessProfit;
  private List<List<Edge>> adjList;
  public String bestRoute(int towns, int origin, int destination, String[] travelCosts, int[] profits) {
    adjList = makeAdjacencyList(towns, travelCosts, profits);
    doBfs(towns, origin, destination, travelCosts, profits);
    String result;
    if (!reachable) {
      result = "IMPOSSIBLE";
    } else if (endlessProfit) {
      result = "ENDLESS PROFIT";
    } else {
      int bestProfitFound = doDijkstras(towns, origin, destination, travelCosts, profits);
      result = "BEST PROFIT: " + bestProfitFound;
    }
    return result;
  }
  public List<List<Edge>> makeAdjacencyList(int n, String[] travelCosts, int[] profits) {
    List<List<Edge>> adjList = new ArrayList<>();
    for (int i = 0; i < n; i++) {  adjList.add(new ArrayList<Edge>()); }
    for (int i = 0; i < travelCosts.length; i++) {
      String[] split = travelCosts[i].split("\\s+");
      int source = Integer.parseInt(split[0]);
      int dest = Integer.parseInt(split[1]);
      int cost = Integer.parseInt(split[2]);
      Edge d = new Edge(dest, profits[dest] - cost);
      adjList.get(source).add(d);
    }
    return adjList;
  }
  public class Edge {
    public int target, profit;
    public Edge(int target, int profit){
      this.target = target;
      this.profit = profit; // the money made at target - cost of travel there ## The profit of this destionation movement
    }
  }
  public class Step {
    public int profitAtStartOfStep;
    public Edge dest;
    public Step(int p, Edge d) {
      this.profitAtStartOfStep = p;
      dest = d;
    }
    public int profitAtEndOfStep() { return profitAtStartOfStep + dest.profit; }
  }
  public int doDijkstras(int towns, int origin, int destination, String[] travelCosts, int[] profits) {
    int[] travelProfit = new int[towns];
    Arrays.fill(travelProfit, Integer.MIN_VALUE);
    travelProfit[origin] = profits[origin];
    moveAll(origin, profits[origin], travelProfit);
    return travelProfit[destination];
  }
  public void moveAll(int source, int currentCash, int[] travelProfit) {
    for (Edge dest : adjList.get(source)) {
      Step step = new Step(currentCash, dest);
      dijMove(step, travelProfit);
    }
  }
  public void dijMove(Step step, int[] travelProfit) {
    int dest = step.dest.target;
    if (step.profitAtEndOfStep() > travelProfit[dest]) {
      travelProfit[dest] = step.profitAtEndOfStep();
      moveAll(dest, step.profitAtEndOfStep(), travelProfit);
    }
  }
  // BFS
  public void doBfs(int towns, int origin, int destination, String[] travelCosts, int[] profits) {
    Deque<Step> toVisit = new ArrayDeque<Step>();
    boolean[] seen = new boolean[towns];
    int[] profitAtTown = new int[towns];
    
    doStep(toVisit, new Step(0, new Edge(0, profits[origin])), seen, profitAtTown);
    while (!toVisit.isEmpty()) {
      doStep(toVisit, toVisit.removeFirst(), seen, profitAtTown);
    }
    if (seen[destination]) {
      reachable = true;
    }
  }
  public void doStep(Deque<Step> toVisit, Step step, boolean[] seen, int[] profitAtTown) {
    int dest = step.dest.target;
    if (seen[dest]) { // cycle
      if (profitAtTown[dest] < step.profitAtEndOfStep()) { // cycle is profitable
        endlessProfit = true;
      } else {
        // do nothing with the cycle.
      }
    } else {
      int moneyNow = step.profitAtEndOfStep();
      seen[dest] = true;
      profitAtTown[dest] = moneyNow;
      for (Edge connectedTown : adjList.get(dest)) {
        Step s = new Step(moneyNow, connectedTown);
        toVisit.addLast(s);
      }
    }
  }
  
}