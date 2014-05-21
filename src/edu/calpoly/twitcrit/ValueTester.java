package edu.calpoly.twitcrit;

import twitter4j.*;
import java.util.*;

public class ValueTester {

  public static String[] movieHashtags = {
    "#EndlessLove", "#CaptainAmericaTheWinterSoldier", "#DraftDay",
    "#Noah", "#Rio2", "#Godzilla2014", "Neighbors"
  };
  private static HashMap<String, Double> rotten_scores = new HashMap<String, Double>();
  private static HashMap<String, Double> metacritic_scores = new HashMap<String, Double>();
  private static HashMap<String, Double> movie_scores = new HashMap<String, Double>();
  private static final double TOLERANCE = 1.5;

  public static void setExistingScores() {
    rotten_scores.put(movieHashtags[0], 7.2);
    rotten_scores.put(movieHashtags[1], 8.8);
    rotten_scores.put(movieHashtags[2], 7.4);
    rotten_scores.put(movieHashtags[3], 5.8);
    rotten_scores.put(movieHashtags[4], 7.6);
    rotten_scores.put(movieHashtags[5], 7.6);
    rotten_scores.put(movieHashtags[6], 7.6);

    metacritic_scores.put(movieHashtags[0], 2.7);
    metacritic_scores.put(movieHashtags[1], 8.4);
    metacritic_scores.put(movieHashtags[2], 6.8);
    metacritic_scores.put(movieHashtags[3], 5.4);
    metacritic_scores.put(movieHashtags[4], 6.6);
    metacritic_scores.put(movieHashtags[5], 7.4);
    metacritic_scores.put(movieHashtags[6], 6.6);
  }

  public static void checkMetaCriticScores() {
    System.out. println("\nComparing against metacritic scores...\n");
    for (int i = 0; i < movieHashtags.length; i++) {
      String movie = movieHashtags[i];
      double score = Tester.searchForScore(movie);
      if (TOLERANCE > Math.abs(score - metacritic_scores.get(movie))) {
        System.out.println(movie + " PASSED tests with score " + score);
      } else {
        System.out.println(movie + " FAILED tests with score " + score);
        System.out.println("Compared to score: " + metacritic_scores.get(movie));
      }
    }
  }

  public static void checkRottenScores() {
    System.out. println("\nComparing against rotten tomatoes scores...\n");
    for (int i = 0; i < movieHashtags.length; i++) {
      String movie = movieHashtags[i];
      double score = Tester.searchForScore(movie);
      if (TOLERANCE > Math.abs(score - rotten_scores.get(movie))) {
        System.out.println(movie + " PASSED tests with score " + score);
      } else {
        System.out.println(movie + " FAILED tests with score " + score);
        System.out.println("Compared to score: " + rotten_scores.get(movie));
      }
    }
  }

  public static void main(String[] args) {
    setExistingScores();
    checkMetaCriticScores();
    checkRottenScores();
  }
}
