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
			tweeter.follow("a", "b");
			// tweeter.follow("c", "b");
			// tweeter.follow("d", "b");
			// tweeter.follow("p", "l");
			// tweeter.follow("q", "l");
			// tweeter.follow("r", "l");
			// tweeter.follow("s", "l");
			// tweeter.tweet("b", "thistatadada");
			// tweeter.tweet("l", "message");
			// tweeter.block("a", "b");
			// tweeter.block("c", "b");
			// tweeter.block("d", "a");
			// tweeter.block("e", "a");
			// tweeter.follow("bob", "alice");
			// PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
			// pq.offer(2);
			// pq.offer(3);
			// System.out.println(pq.peek());
		} catch (Exception e) {
			e.printStackTrace();
		}

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
