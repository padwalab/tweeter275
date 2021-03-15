package edu.sjsu.cmpe275.aop.tweet;

import java.util.PriorityQueue;

import org.aspectj.org.eclipse.jdt.internal.core.SourceType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		/***
		 * Following is a dummy implementation of App to demonstrate bean creation with
		 * Application context. You may make changes to suit your need, but this file is
		 * NOT part of the submission.
		 */

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		TweetService tweeter = (TweetService) ctx.getBean("tweetService");
		TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");

		try {
			tweeter.follow("bob", "alice");
			Long msg = tweeter.tweet("alice", "first tweet");
			// tweeter.like("nano", msg);
			// System.out.println(msg);
			tweeter.like("bob", msg);
			tweeter.block("alice", "bob");
			Long ms = tweeter.tweet("alice", "second tweet");
			// tweeter.like("bob", ms);
			// Long ms = tweeter.tweet("bob", "dasfasdfdsafadsfasdfsad");

		} catch (Exception e) {
			e.printStackTrace();
		}
		stats.resetStatsAndSystem();
		System.out.println("Most productive user: " + stats.getMostProductiveUser());
		System.out.println("Most popular user: " + stats.getMostFollowedUser());
		System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
		System.out.println("Most popular message: " + stats.getMostPopularMessage());
		System.out.println("Most liked message: " + stats.getMostLikedMessage());

		System.out.println("Most most message: " + stats.getMostPopularMessage());
		System.out.println("Most unpopular follower: " + stats.getMostUnpopularFollower());
		ctx.close();
	}
}
