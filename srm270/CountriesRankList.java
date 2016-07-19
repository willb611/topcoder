import java.util.*;
public class CountriesRanklist {
  static int lowest = Integer.MAX_VALUE;
  static final int CONTESTANTS_PER_COUTRY = 4;
  static Map<Integer, List<String>> announcedScoresMap = new HashMap<>();
  static Map<Integer, List<String>> bestMap = new HashMap<>();
  static List<Integer> announcedScoresListSorted;
  static List<Integer> bestScoresListSorted;
  public String[] findPlaces(String[] knownPoints) {
    // Solve
    TreeMap<String, List<Integer>> known = getScores(knownPoints);
    String[] countryScores = new String[known.size()];
    int countryScoresIndex = 0;
    makeMaps(known);
    makeAndSortLists();
    for (Map.Entry<String, List<Integer>> entry : known.entrySet()) {
      String country = entry.getKey();
      debug("[findPlaces] found coujntry: " + country);
      int knownTotal = entry.getValue().stream().mapToInt(Integer::intValue).sum();
      int bestPosition = findVirtualPositionIfNeedleInsertedKeepingListAsSet(announcedScoresListSorted, getBestScore(knownTotal, entry.getValue().size()));
      int worstPosition = findVirtualPositionIfNeedleInsertedKeepingListAsSet(bestScoresListSorted, knownTotal);
      if (entry.getValue().size() < CONTESTANTS_PER_COUTRY && lowest > 0) {
        worstPosition--; 
        /* This virtual position thing doesn't work because this country is in the list
        // It would work for the best list. As this country will draw with or beat its own score
        // Fails on the worst list. Because the best score this country has will be inserted there. */
      }
      String output = country + " " + bestPosition + " " + worstPosition;
      countryScores[countryScoresIndex++] = output;
      debug("[findPlaces] got output: " + output);
    }
    return countryScores;
  }
  public static TreeMap<String, List<Integer>> getScores(String[] knownPoints) {
    debug("[getScores] knownPoints size: " + knownPoints);
    TreeMap<String, List<Integer>> scores = new TreeMap<>();
    for (int i = 0; i < knownPoints.length; i++) {
      String[] split = knownPoints[i].trim().split("\\s+");
      String country = split[0];
      List<Integer> scoresForCountryOfThisContestant = scores.get(country);
      if (scoresForCountryOfThisContestant == null) {
        scoresForCountryOfThisContestant = new ArrayList<>();
      }
      scores.put(country, scoresForCountryOfThisContestant);
      int scoreForContestant = Integer.parseInt(split[2]);
      if (scoreForContestant < lowest) {
        lowest = scoreForContestant;
      }
      debug("for country " + country + " and contestant: " + split[1],  ", scoreForContestant: " + scoreForContestant);
      scoresForCountryOfThisContestant.add(scoreForContestant);
    }
    debug("[getScores] got " + scores.size());
    return scores;
  }
  public static void makeMaps(Map<String, List<Integer>> map) {
    for (String country : map.keySet()) {
      List<Integer> scores = map.get(country);
      int total  = scores.stream().mapToInt(Integer::intValue).sum();
      int worst = total;
      // save worst
      List<String> countries = announcedScoresMap.get(worst);
      if (countries == null) {
        countries = new ArrayList<>();
      }
      countries.add(country);
      announcedScoresMap.put(worst, countries);
      // save best
      int best = getBestScore(total, scores.size());
      countries = bestMap.get(best);
      if (countries == null) {
        countries = new ArrayList<>();
      }
      countries.add(country);
      bestMap.put(best, countries);
    }
  }
  public static int findVirtualPositionIfNeedleInsertedKeepingListAsSet(List<Integer> haystack, int needle) {
    final int INDEXING_OFFSET = 1;
    debug("got haystack: ");
    for (int i : haystack) { debug(i); }
    debug("finished haystack");
    int high = haystack.size() - 1;
    int low = 0;
    int score = -1, mid = 0;
    while (high >= low) {
      mid = (low + high)/2;
      score = haystack.get(mid);
      if (needle == score) {
        return mid + INDEXING_OFFSET;
      } else if (needle > score) {
        high = mid - 1;
      } else if (needle < score) {
        low = mid + 1;
      } else {
        throw new RuntimeException("unexpected");
      }
    }
    debug("[findVirtualPositionIfNeedleInsertedKeepingListAsSet] Not found. score: " + score + " needle: " + needle + " mid: " + mid);
    // not found
    if (score < needle) {
      return mid + INDEXING_OFFSET;
    } else if (score > needle) {
      return mid + 1+  INDEXING_OFFSET;
    } else {
      throw new RuntimeException("unexpected");
    }
    // if if total = 7, and sequence = 1,4,9 then position = 3 (before 9)
  }
  public static void makeAndSortLists() {
    announcedScoresListSorted = new ArrayList<>(announcedScoresMap.keySet());
    Collections.sort(announcedScoresListSorted, Collections.reverseOrder());
    debug("[makeAndSortLists] Found announcedScoresMap size " + announcedScoresMap.keySet().size());
    bestScoresListSorted = new ArrayList<>(bestMap.keySet());
    Collections.sort(bestScoresListSorted, Collections.reverseOrder());
  }
  public static int getBestScore(int total, int contestantNumber) {
      int times = 4 - contestantNumber;
      int best = total + (times * Math.max(0, lowest - 1));
      return best;
  }

  static final boolean DEBUG_EN = false;
  private static void debug(Object... arr) {
    for (Object o : arr) {
      System.out.println("DEBUG: " + o);
    }
  }
}