package edu.calpoly.twitcrit;

import com.twitter.hbc.example;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {

  private static String API_key = "jCtjk7mO08aMwcZ7bwnEYWnE6";
  private static String API_secret  = "rkgJGMttVIbd8RF3AHaLl0rjTNt1PEjyyIN3L9kxf7oJeCdBh8";
  private static String Access_token = "354589333-xJOBhoOyC64uMETx7s9L3mVu2KqSJEPgbThzfEVr";
  private static String Access_token_secret = "tpOrloa3BqZNS3NR722lCScfI0ZHkUmqs2xi2NhuROWym";

	public static int main() {
    Authentication auth = new OAuth1(API_key, API_secret, Access_token, Access_token_secret);
    //BasicAuth basic_auth = new BasicAuth("username", "password");

    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
    // add some track terms
    endpoint.trackTerms(Lists.newArrayList("twitterapi", "#yolo"));

    //Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
    // Authentication auth = new BasicAuth(username, password);

    // Create a new BasicClient. By default gzip is enabled.
    Client client = new ClientBuilder()
      .hosts(Constants.STREAM_HOST)
      .endpoint(endpoint)
      .authentication(auth)
      .processor(new StringDelimitedProcessor(queue))
      .build();

    // Establish a connection
    client.connect();

    // Do whatever needs to be done with messages
    for (int msgRead = 0; msgRead < 1000; msgRead++) {
      String msg = queue.take();
      System.out.println(msg);
    }

    client.stop();
		return 0;
	}
}
