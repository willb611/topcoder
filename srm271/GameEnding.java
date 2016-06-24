import java.util.*;
public class GameEnding {
  int N, M;
  int[][] pieces = new int[10][2];
  boolean attacks(int i, int j) {
    int dx = Math.abs(pieces[i][0] - pieces[j][0]);
    int dy = Math.abs(pieces[i][1] - pieces[j][1]);
    if (dx == 0 || dy == 0) return true;
    if (dx == 2 && dy == 1) return true;
    if (dx == 1 && dy == 1) return true;
    return false;
  }
  public boolean playerOnMoveWins() {
     M++;
     // try all possible moves
      for (int i=0; i<N; i++) 
        for (int j=0; j<N; j++) {
         // add a new piece
         pieces[M-1][0] = i;
         pieces[M-1][1] = j;
      
         // check whether this is a valid move
         boolean ok=true;
         for (int k=0; k<M-1; k++) if (attacks(k,M-1)) { ok=false; break; }
         if (!ok) continue;

          // Move is valid, evaluate the new position recursively
          if (!playerOnMoveWins()) { 
            // we found a valid move
            M--;
            return true; 
          }
      }
    
      // no move will guarantee winning the game, we lose
      M--;
      return false;
  }
  
  public int getV(char c) {
      return c - 'a';
    }
  public void parse(int n, String[] moves) {
    N = n;
    for (int i = 0; i < moves.length; i++) {
      String move = moves[i];
          pieces[M][0] = getV(move.charAt(0));
          pieces[M][1] = Integer.parseInt(String.valueOf(move.charAt(1))) - 1;
      M++;
    }
  }
  public String result(int n, String[] moves) {
    parse(n, moves);
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < i; j++) {
        if (attacks(i, j)) {
          return "Invalid input";
        }
      }
    }
    boolean winning = playerOnMoveWins();
    if (moves.length % 2 == 1) {
      winning = !winning;
    }
    if (winning) {
      return "First player wins";
    } else {
      return "Second player wins";
    }
  }
}