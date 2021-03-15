package edu.sjsu.cmpe275.aop.tweet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TweetStatsServiceImpl implements TweetStatsService {
	/***
	 * Following is a dummy implementation. You are expected to provide an actual
	 * implementation based on the requirements.
	 */

	public static int lengthOfLongestTweet = 0;
	public static int maxFollowCount;
	public static String maxFollowedUser = null;
	public static int mostPopularMessage = 0;
	public static String mostPopularMessageValue = "";
	public static int mostBlockedByCount = 0;
	public static String mostBlockedByUserValue = null;
	public static int mostProductiveUserTweetLength = 0;
	public static String mostProductiveUser = null;
	public static long mostLikedTweet = 0;
	public static int mostLikedTweetCount = 0;
	public static HashMap<String, Set<String>> followCountMap = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> blockCountMap = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> blockedByCountMap = new HashMap<String, Set<String>>();
	public static HashMap<String, String> tweetOwner = new HashMap<String, String>();
	public static HashMap<String, Set<String>> tweetRecievers = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> tweetDatabase = new HashMap<String, Set<String>>();
	public static HashMap<Long, String> tweetIdOwner = new HashMap<Long, String>();
	public static HashMap<Long, Set<String>> tweetLikers = new HashMap<Long, Set<String>>();

	@Override
	public void resetStatsAndSystem() {
		lengthOfLongestTweet = 0;
		maxFollowCount = 0;
		maxFollowedUser = null;
		mostPopularMessage = 0;
		mostPopularMessageValue = "";
		mostBlockedByCount = 0;
		mostBlockedByUserValue = null;
		mostProductiveUserTweetLength = 0;
		mostProductiveUser = null;
		mostLikedTweet = 0;
		mostLikedTweetCount = 0;
		followCountMap = new HashMap<String, Set<String>>();
		blockCountMap = new HashMap<String, Set<String>>();
		blockedByCountMap = new HashMap<String, Set<String>>();
		tweetOwner = new HashMap<String, String>();
		tweetRecievers = new HashMap<String, Set<String>>();
		tweetDatabase = new HashMap<String, Set<String>>();
		tweetIdOwner = new HashMap<Long, String>();
		tweetLikers = new HashMap<Long, Set<String>>();
	}

	@Override
	public int getLengthOfLongestTweet() {
		return lengthOfLongestTweet;
	}

	@Override
	public String getMostFollowedUser() {
		return maxFollowedUser;
	}

	@Override
	public String getMostPopularMessage() {

		if (mostPopularMessageValue == "") {
			return null;
		}
		return mostPopularMessageValue;
	}

	@Override
	public String getMostProductiveUser() {
		return mostProductiveUser;
	}

	@Override
	public Long getMostLikedMessage() {
		// TODO Auto-generated method stub
		if (mostLikedTweet == 0) {
			return null;
		}
		return mostLikedTweet;
	}

	@Override
	public String getMostUnpopularFollower() {
		// TODO Auto-generated method stub
		return mostBlockedByUserValue;
	}

}
