import twitter4j.*;

public class Tester {
   private static final String[] GOOD_KEYWORDS = {"good", "epic"};
   private static final String[] BAD_KEYWORDS = {"bad", "boring"};

   public static Query makeQuery() {
      Query query = new Query("#CaptainAmericaTheWinterSoldier");
      query.setLang("en");
      return query;
   }

   public static int parseTweet(Status status) {
      int score = 0;
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

      return score;
   }

   public static void main(String[] args) {
      try {
         int score = 0;
         int index = 0;
         // The factory instance is re-useable and thread safe.
         Twitter twitter = TwitterFactory.getSingleton();
         Query query = makeQuery();
         QueryResult result = twitter.search(query);

         for (Status status : result.getTweets()) {
            score += parseTweet(status);
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());

            if (score != 0) {
               index++;
            }
         }
         System.out.println("Score: " + ((double) score / index) + " out of " + index + " reviews");
      }
      catch (TwitterException e) {
         e.printStackTrace();
         System.exit(0);
      }
   }
}
