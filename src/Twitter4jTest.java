import twitter4j.*;

public class Twitter4jTest {
   public static void main(String[] args) {
      try {
         // The factory instance is re-useable and thread safe.
         Twitter twitter = TwitterFactory.getSingleton();
         Query query = new Query("#yolo");
         QueryResult result = twitter.search(query);
         for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
         }
      }
      catch (TwitterException e) {
         e.printStackTrace();
         System.exit(0);
      }
   }
}