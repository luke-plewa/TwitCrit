import twitter4j.*;

public class Tester {
   private static final int BASE_SCORE = 5;
   private static final int MAX_SCORE = 10;
   private static final int PAGES_TO_SEARCH = 100;
   private static final int TWEETS_PER_PAGE = 100;
   private static final String SEARCH_KEYWORD = "#CaptainAmericaTheWinterSoldier";
   private static final String[][] KEYWORDS = {
         {"worst", "terrible", "horrible"},
         {"garbage", "miserable", "embarrassing"},
         {"suck", "crap", "poop", "awful", "rotten"},
         {"bad", "poor", "not good"},
         {"boring", "unfunny"},
         {"okay", "decent"},
         {"good", "alright", "enjoy"},
         {"great", "better", "well done"},
         {"love", "marvelous", "fabulous", "legit", "fresh"},
         {"awesome", "excellent", "amazing", "must see"},
         {"best", "top", "incredible", "Oscar"}
    };

   public static Query makeQuery(String keyword) {
      Query query = new Query(keyword);
      query.setLang("en");
      query.count(TWEETS_PER_PAGE);
      return query;
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
            }
         }
         index++;
      }
      if (count == 0) {
         return 0;
      }

      return score / count;
   }

   public static void main(String[] args) {
      try {
         double score = 0;
         double new_score = 0;
         double index = 0;
         // The factory instance is re-useable and thread safe.
         Twitter twitter = TwitterFactory.getSingleton();
         Query query = makeQuery(SEARCH_KEYWORD);
         QueryResult result = twitter.search(query);

         for(int i = 0; i < PAGES_TO_SEARCH; i++) {
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
         }
         System.out.println("Movie hashtag: " + SEARCH_KEYWORD);
         System.out.println("Score: " + (score / index) + " out of " + MAX_SCORE + ", based on " + index + " reviews.");
      }
      catch (TwitterException e) {
         RateLimitStatus r = e.getRateLimitStatus();
         System.out.println("" + e.getMessage());
         System.out.println("Please wait " + r.getSecondsUntilReset() + " seconds before searching again.");
         System.exit(0);
      }
   }
}
