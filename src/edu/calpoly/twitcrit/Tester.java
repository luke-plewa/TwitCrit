package edu.calpoly.twitcrit;

public class Tester {

  private static String API_key = "jCtjk7mO08aMwcZ7bwnEYWnE6";
  private static String API_secret  = "rkgJGMttVIbd8RF3AHaLl0rjTNt1PEjyyIN3L9kxf7oJeCdBh8";
  private static String Access_token = "354589333-xJOBhoOyC64uMETx7s9L3mVu2KqSJEPgbThzfEVr";
  private static String Access_token_secret = "tpOrloa3BqZNS3NR722lCScfI0ZHkUmqs2xi2NhuROWym";

	public static int main() {
    OAuth1 oauth = new OAuth1(API_key, API_secret, Access_token, Access_token_secret);
    BasicAuth basic_auth = new BasicAuth("username", "password");
		return 0;
	}
}
