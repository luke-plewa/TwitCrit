package edu.calpoly.twitcrit;

import twitter4j.*;
import java.util.*;

public class Tester {
   private static final int BASE_SCORE = 5;
   private static final int MAX_SCORE = 10;
   private static final int PAGES_TO_SEARCH = 90;
   private static final int TWEETS_PER_PAGE = 100;
   private static final String[][] KEYWORDS = {
         {"worst", "terrible", "horrible"},
         {"garbage", "miserable", "embarrassing", "painful"},
         {"suck", "crap", "poop", "awful", "rotten"},
         {"bad", "poor", "not good"},
         {"boring", "unfunny"},
         {"okay", "decent", "not bad"},
         {"good", "alright", "enjoy"},
         {"great", "better", "well done", "excite"},
         {"love", "marvelous", "fabulous", "legit", "fresh"},
         {"awesome", "excellent", "amazing", "must see"},
         {"best", "incredible", "Oscar"}	//removed "top", because it was including tweets with "stop"
    };

    /*private static HashMap<String, Double> movie_scores;
    private static HashMap<String, Integer> num_reviews;
    private static HashMap<String, Integer> keywords_seen;

    private Tester(){
  	  movie_scores = new HashMap<String, Double>();
      num_reviews = new HashMap<String, Integer>();
      keywords_seen = new HashMap<String, Integer>();
    }

    public static void displayScore(String hashtag){
    	new Tester().printScore(hashtag);
    }*/

    //use this if we go with predefining our statics, otherwise use the commented out
   	//part that's above this for static factory instancing
    private static HashMap<String, Double> movie_scores = new HashMap<String, Double>();
    private static HashMap<String, Integer> num_reviews = new HashMap<String, Integer>();
    private static HashMap<String, Integer> keywords_seen = new HashMap<String, Integer>();

   public static Query makeQuery(String keyword) {
      Query query = new Query(keyword);
      query.setLang("en");
      query.count(TWEETS_PER_PAGE);
      return query;
   }

   public static String[][] getKeywords() {
      return KEYWORDS;
   }

   /* given the text of a tweet, return the score */
   public static double scoreTweet(Status status) {
      double score = 0;
      String text = status.getText();
      double count = 0;
      int index = 0;

      for (String set[] : KEYWORDS) {
         for (String word : set) {
            if (text.contains(word)) {
               score += index;
               count++;

               //keep track of which keywords have been seen the most
               Integer seen = keywords_seen.get(word);
               if (seen != null) {
                  keywords_seen.put(word, seen + 1);
               }
               else {
                  keywords_seen.put(word, 1);
               }
            }
         }
         index++;
      }
      if (count == 0) {
         return 0;
      }

      return score / count;
   }

   public double getScore(String hashtag) {
      return movie_scores.get(hashtag);
   }

   public double getNumReviews(String hashtag) {
      return num_reviews.get(hashtag);
   }

   public static double searchForScore(String hashtag) {
      try {
         double score = 0;
         double new_score = 0;
         double index = 0;
         // The factory instance is re-useable and thread safe.
         Twitter twitter = TwitterFactory.getSingleton();
         Query query = makeQuery(hashtag);
         QueryResult result = twitter.search(query);

         for (int i = 0; i < PAGES_TO_SEARCH; i++) {
            for (Status status : result.getTweets()) {
               new_score = scoreTweet(status);
               score += scoreTweet(status);
               // System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
               if (new_score != 0) {
                  index++;
               }
            }
            query = result.nextQuery();
            if (query != null) {
               result = twitter.search(query);
            } else {
              break;
            }
         }

         num_reviews.put(hashtag, new Integer((int) index));
         movie_scores.put(hashtag, new Double(score / index));
         int checkValidReturn = num_reviews.get(hashtag);
         if(checkValidReturn > 0){
          return movie_scores.get(hashtag);
         }
         else {
           MessageDisplayPane.displayMessage("There were no search results for this movie.");
           return -1;
         }

      }
      catch (TwitterException e) {
         RateLimitStatus r = e.getRateLimitStatus();
         System.out.println("" + e.getMessage());
         System.out.println("Please wait " + r.getSecondsUntilReset() + " seconds before searching again.");
         System.exit(0);
      }
      return -1;
   }

   public static void printScore(String hashtag) {
      try {
         System.out.println("Generating movie score for movie with hashtag: " + hashtag);
         double score = 0;
         double new_score = 0;
         double index = 0;
         // The factory instance is re-useable and thread safe.
         Twitter twitter = TwitterFactory.getSingleton();
         Query query = makeQuery(hashtag);
         QueryResult result = twitter.search(query);

         for (int i = 0; i < PAGES_TO_SEARCH; i++) {
            for (Status status : result.getTweets()) {
               new_score = scoreTweet(status);
               score += scoreTweet(status);
               // System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
               if (new_score != 0) {
                  index++;
               }
            }
            query = result.nextQuery();
            if (query != null) {
               result = twitter.search(query);
            }
            else {
               break;
            }
         }

         num_reviews.put(hashtag, new Integer((int) index));
         movie_scores.put(hashtag, new Double(score / index));
         System.out.println("Movie hashtag: " + hashtag);
         int checkValidReturn = num_reviews.get(hashtag);
         if(checkValidReturn > 0) {
	         System.out.println("Score: " + String.format("%.2f", movie_scores.get(hashtag)) + " out of "
	            + MAX_SCORE + ", based on " + checkValidReturn + " reviews.");

	         String movieTag = "Movie hashtag: " + hashtag + "\n"
	                           + "Score: " + String.format("%.2f", movie_scores.get(hashtag)) + " out of "
	                           + MAX_SCORE + ", based on " + num_reviews.get(hashtag) + " reviews.";

	         //update the panels with the new results
	         MainWindow.updateSearchHistory(movieTag);

	         //Prints out the keywords used.
	         String mostSeen = null;
	         Integer maxValue = 0;
	         for (Map.Entry<String, Integer> entry : keywords_seen.entrySet()) {
	            String keyword = entry.getKey();
	            Integer value = entry.getValue();

	            System.out.println("Keyword: " + keyword + "	Count: " + value);
	            if (value > maxValue) {
	               maxValue = value;
	               mostSeen = keyword;
	            }
	         }
	         System.out.println("Most used keyword: " + mostSeen + ", used " + maxValue + " times.\n");
	         
	         //Attempting to print a graph of the used words
	         new ChartWindow(keywords_seen);
	         
	         keywords_seen.clear(); //clear the map so we don't just keep expanding it
         }
         else {
        	 MessageDisplayPane.displayMessage("There were no search results for this movie.");
         }

      }
      catch (TwitterException e) {
         RateLimitStatus r = e.getRateLimitStatus();
         System.out.println("" + e.getMessage());
         System.out.println("Please wait " + r.getSecondsUntilReset() + " seconds before searching again.");
         System.exit(0);
      }
   }
}
