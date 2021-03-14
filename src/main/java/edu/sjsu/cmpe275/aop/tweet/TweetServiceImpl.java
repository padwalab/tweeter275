package edu.sjsu.cmpe275.aop.tweet;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TweetServiceImpl implements TweetService {

	/***
	 * Following is a dummy implementation, which the correctness of your submission
	 * cannot depend on. You can tweak the implementation to suit your need, but
	 * this file is NOT part of the submission.
	 */
	public static HashMap<String, HashMap<String, Set<String>>> tweetHashMap = new HashMap<String, HashMap<String, Set<String>>>();

	private static long msgCount = 0;
	public static int h = 3;

	@Override
	public Long tweet(String user, String message) throws IllegalArgumentException, IOException {
		System.out.printf("User %s tweeted message: %s\n", user, message);
		// if (!tweetHashMap.containsKey(user)) {
		// HashMap<String, Set<String>> userDetails = new HashMap<String,
		// Set<String>>();
		// Set<String> userTweets = new HashSet<String>();
		// userTweets.add(message);
		// userDetails.put("tweets", userTweets);
		// tweetHashMap.put(user, userDetails);
		// } else {
		// HashMap<String, Set<String>> userDetails = tweetHashMap.get(user);
		// if (!userDetails.containsKey("tweets")) {
		// Set<String> tweets = new HashSet<String>();
		// tweets.add(message);
		// userDetails.put("tweets", tweets);
		// } else {
		// Set<String> tweets = userDetails.get("tweets");
		// tweets.add(message);
		// }
		// }
		// System.out.println(tweetHashMap.toString());
		return msgCount++;
	}

	@Override
	public void follow(String follower, String followee) throws IOException {
		System.out.printf("User %s followed user %s \n", follower, followee);
		if (h < 5) {
			h += 1;
			throw new IOException();
		}
		// if (!tweetHashMap.containsKey(followee)) {
		// HashMap<String, Set<String>> userDetails = new HashMap<String,
		// Set<String>>();
		// Set<String> usersFollowers = new HashSet<String>();
		// usersFollowers.add(follower);
		// userDetails.put("followers", usersFollowers);
		// tweetHashMap.put(followee, userDetails);
		// } else {
		// HashMap<String, Set<String>> userDetails = tweetHashMap.get(followee);
		// if (!userDetails.containsKey("followers")) {
		// Set<String> followers = new HashSet<String>();
		// followers.add(follower);
		// userDetails.put("followers", followers);
		// } else {
		// Set<String> followers = userDetails.get("followers");
		// followers.add(follower);
		// }
		// }
		// System.out.println(tweetHashMap.toString());
	}

	@Override
	public void block(String user, String follower) throws IOException {
		System.out.printf("User %s blocked user %s \n", user, follower);
		// if (!tweetHashMap.containsKey(user)) {
		// HashMap<String, Set<String>> userDetails = new HashMap<String,
		// Set<String>>();
		// Set<String> userBlockList = new HashSet<String>();
		// userBlockList.add(follower);
		// userDetails.put("blocklist", userBlockList);
		// tweetHashMap.put(user, userDetails);
		// } else {
		// HashMap<String, Set<String>> userDetails = tweetHashMap.get(user);
		// if (!userDetails.containsKey("blocklist")) {
		// Set<String> blocklist = new HashSet<String>();
		// blocklist.add(follower);
		// userDetails.put("blocklist", blocklist);
		// } else {
		// Set<String> blocklist = userDetails.get("blocklist");
		// blocklist.add(follower);
		// }
		// }
		// System.out.println(tweetHashMap.toString());
	}

	@Override
	public void like(String user, Long messageId) throws AccessControlException, IllegalArgumentException, IOException {
		System.out.printf("User %s liked message with ID %s \n", user, messageId);
	}

}
