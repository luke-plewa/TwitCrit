import twitter4j.*;

public class Tester {
   private static final String[] GOOD_KEYWORDS = {"good", "epic", "brilliant", "better", "fabulous", "love", "enjoy", "incredible"};
   private static final String[] BAD_KEYWORDS = {"bad", "boring", "horrible", "suck", "awful", "terrible", "shit", "garbage"};
   private static final int BASE_SCORE = 5;
   private static final int MAX_SCORE = 10;
   private static final int PAGES_TO_SEARCH = 100;
   private static final int TWEETS_PER_PAGE = 100;
   private static final String SEARCH_KEYWORD = "#CaptainAmericaTheWinterSoldier";

   public static Query makeQuery(String keyword) {
      Query query = new Query(keyword);
      query.setLang("en");
      query.count(TWEETS_PER_PAGE);
      return query;
   }

   /* given the text of a tweet, return the score */
   public static int scoreTweet(Status status) {
      int score = 5;
      String text = status.getText();

      for (String str : GOOD_KEYWORDS) {
         if (text.contains(str)) {
            score++;
         }
      }

      for (String str : BAD_KEYWORDS) {
         if (text.contains(str)) {
            score--;
         }
      }

      if (score > 5) {
         score = 5;
      } else if (score < -5) {
         score = -5;
      }

      return score;
   }

   public static void main(String[] args) {
      try {
         int score = 0;
         int index = 0;
         // The factory instance is re-useable and thread safe.
         Twitter twitter = TwitterFactory.getSingleton();
         Query query = makeQuery(SEARCH_KEYWORD);
         QueryResult result = twitter.search(query);

         for(int i = 0; i < PAGES_TO_SEARCH; i++) {
            for (Status status : result.getTweets()) {
               score += scoreTweet(status);
               // System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
               if (score != 0) {
                  index++;
               }
            }
            query = result.nextQuery();
            if (query != null) {
               result = twitter.search(query);
            }
         }
         System.out.println("Score: " + (BASE_SCORE + (double) score / index) + " out of " + MAX_SCORE + ", based on " + index + " reviews.");
      }
      catch (TwitterException e) {
         e.printStackTrace();
         System.exit(0);
      }
   }
}
