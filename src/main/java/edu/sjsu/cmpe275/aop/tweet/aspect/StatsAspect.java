package edu.sjsu.cmpe275.aop.tweet.aspect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;

@Aspect
@Order(0)
public class StatsAspect {
	/***
	 * Following is a dummy implementation of this aspect. You are expected to
	 * provide an actual implementation based on the requirements, including
	 * adding/removing advices as needed.
	 */

	@Autowired
	TweetStatsServiceImpl stats;

	@After("execution(public Long edu.sjsu.cmpe275.aop.tweet.TweetService.tweet(..))")
	public void maxLengthTweet(JoinPoint joinPoint) {
		System.out.println("checking max length tweet, most popular tweeter");
		String user = joinPoint.getArgs()[0].toString();
		String tweet = joinPoint.getArgs()[1].toString();
		System.out.println("tweet: " + tweet + " user: " + user);
		int tweetShareCount = 0;
		if (tweet.length() > stats.lengthOfLongestTweet) {
			stats.lengthOfLongestTweet = tweet.length();
		}
		if (!stats.tweetOwner.containsKey(tweet)) {
			stats.tweetOwner.put(tweet, user);
			if (stats.followCountMap.containsKey(user)) {
				stats.tweetRecievers.put(tweet, stats.followCountMap.get(user));
			} else {
				stats.tweetRecievers.put(tweet, null);
			}
		} else {
			if (stats.followCountMap.containsKey(user)) {
				if (stats.tweetRecievers.get(tweet) == null) {
					stats.tweetRecievers.put(tweet, stats.followCountMap.get(user));
					stats.tweetRecievers.get(tweet).add(user);
				} else {
					stats.tweetRecievers.get(tweet).addAll(stats.followCountMap.get(user));
				}
			}
		}
		if (stats.tweetRecievers.get(tweet).size() >= stats.mostPopularMessage) {
			if (stats.tweetRecievers.get(tweet).size() == stats.mostPopularMessage
					&& stats.mostPopularMessageValue.compareTo(tweet) > 0) {
				stats.mostPopularMessageValue = tweet;
			} else {
				stats.mostPopularMessageValue = tweet;
				stats.mostPopularMessage = stats.tweetRecievers.get(tweet).size();
			}
		}
		if (!stats.tweetDatabase.containsKey(user)) {
			Set<String> tweets = new HashSet<String>();
			tweets.add(tweet);
			stats.tweetDatabase.put(user, tweets);
		} else {
			stats.tweetDatabase.get(user).add(tweet);
		}
		int totalLengthOfTweets = 0;
		for (String twts : stats.tweetDatabase.get(user)) {
			totalLengthOfTweets += twts.length();
		}
		if (totalLengthOfTweets >= stats.mostProductiveUserTweetLength) {
			if (totalLengthOfTweets == stats.mostProductiveUserTweetLength) {
				if (stats.mostProductiveUser == null) {
					stats.mostProductiveUser = user;
				} else if (stats.mostProductiveUser.compareTo(user) > 0) {
					stats.mostProductiveUser = user;
				}
			} else if (totalLengthOfTweets > stats.mostProductiveUserTweetLength) {
				stats.mostProductiveUser = user;
				stats.mostProductiveUserTweetLength = totalLengthOfTweets;
			}
		}
		// System.out.println("current user tweets all: " + user + " " +
		// stats.tweetDatabase.get(user).toString());
	}

	@AfterReturning("execution(void edu.sjsu.cmpe275.aop.tweet.TweetService.block(..))")
	public void AfterBlockStatsUpdate(JoinPoint joinPoint) {
		System.out.println("block count logics..");
		String user = joinPoint.getArgs()[0].toString();
		String follower = joinPoint.getArgs()[1].toString();
		if (!stats.blockCountMap.containsKey(user)) {
			Set<String> blocked = new HashSet<String>();
			blocked.add(follower);
			stats.blockCountMap.put(user, blocked);
		} else {
			stats.blockCountMap.get(user).add(follower);
		}
		if (!stats.blockedByCountMap.containsKey(follower)) {
			Set<String> blockedBy = new HashSet<String>();
			blockedBy.add(user);
			stats.blockedByCountMap.put(follower, blockedBy);
		} else {
			stats.blockedByCountMap.get(follower).add(user);
		}
		if (stats.mostBlockedByCount <= stats.blockedByCountMap.get(follower).size()) {
			if (stats.mostBlockedByCount == stats.blockedByCountMap.get(follower).size()
					&& stats.mostBlockedByUserValue.compareTo(follower) > 0) {
				stats.mostBlockedByUserValue = follower;
				return;
			}
			stats.mostBlockedByUserValue = follower;
			stats.mostBlockedByCount = stats.blockedByCountMap.get(follower).size();
		}
		// System.out.println("block count map: " + stats.blockCountMap + "\n " +
		// stats.blockedByCountMap);
	}

	@AfterReturning("execution(void edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
	public void AfterFollowStatUpdate(JoinPoint joinPoint) {
		System.out.println("follow count logics " + stats.maxFollowCount);
		String user = joinPoint.getArgs()[1].toString();
		String follower = joinPoint.getArgs()[0].toString();
		int count = 0;
		if (!stats.followCountMap.containsKey(user)) {
			count += 1;
			Set<String> followers = new HashSet<String>();
			followers.add(follower);
			count = followers.size();
			stats.followCountMap.put(user, followers);
		} else {
			if (!stats.followCountMap.get(user).contains(follower)) {
				Set<String> followers = stats.followCountMap.get(user);
				followers.add(follower);
				count = followers.size();
			}
		}
		if ((stats.maxFollowCount == count && stats.maxFollowedUser.compareTo(user) > 0)
				|| stats.maxFollowCount < count) {
			stats.maxFollowCount = count;
			stats.maxFollowedUser = user;
		}

	}
}
