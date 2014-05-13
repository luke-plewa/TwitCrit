package edu.calpoly.twitcrit;

import twitter4j.*;
import java.util.*;

public class ValueTester {

  public static String[] movieHashtags = {
    "#EndlessLove", "#CaptainAmericaTheWinterSoldier", "#DraftDay",
    "#Noah", "#Rio2"
  };
  private static HashMap<String, Double> rotten_scores = new HashMap<String, Double>();
  private static HashMap<String, Double> metacritic_scores = new HashMap<String, Double>();
  private static HashMap<String, Double> movie_scores = new HashMap<String, Double>();
  private static final double TOLERANCE = 2.5;

  public static void setExistingScores() {
    rotten_scores.put(movieHashtags[0], 7.2);
    rotten_scores.put(movieHashtags[1], 8.8);
    rotten_scores.put(movieHashtags[2], 7.4);
    rotten_scores.put(movieHashtags[3], 5.8);
    rotten_scores.put(movieHashtags[4], 7.6);

    metacritic_scores.put(movieHashtags[0], 2.7);
    metacritic_scores.put(movieHashtags[1], 8.4);
    metacritic_scores.put(movieHashtags[2], 6.8);
    metacritic_scores.put(movieHashtags[3], 5.4);
    metacritic_scores.put(movieHashtags[4], 6.6);
  }

  public static void checkMetaCriticScores() {
    System.out. println("Comparing against metacritic scores...");
    for (int i = 0; i < movieHashtags.length; i++) {
      String movie = movieHashtags[i];
      double score = Tester.searchForScore(movie);
      if (TOLERANCE < Math.abs(score - metacritic_scores.get(movie))) {
        System.out.println(movie + " PASSED tests with score " + score);
      } else {
        System.out.println(movie + " FAILED tests with score " + score);
      }
    }
  }

  public static void checkRottenScores() {
    System.out. println("Comparing against rotten tomatoes scores...");
    for (int i = 0; i < movieHashtags.length; i++) {
      String movie = movieHashtags[i];
      double score = Tester.searchForScore(movie);
      if (TOLERANCE < Math.abs(score - rotten_scores.get(movie))) {
        System.out.println(movie + " PASSED tests with score " + score);
      } else {
        System.out.println(movie + " FAILED tests with score " + score);
      }
    }
  }

  public static void main(String[] args) {
    setExistingScores();
    checkMetaCriticScores();
    checkRottenScores();
  }
}
