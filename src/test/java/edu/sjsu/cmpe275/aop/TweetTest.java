package edu.sjsu.cmpe275.aop;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.aop.tweet.TweetService;
import edu.sjsu.cmpe275.aop.tweet.TweetStatsService;

public class TweetTest {

    /***
     * These are dummy test cases. You may add test cases based on your own need.
     * 
     * @throws IOException
     * @throws IllegalArgumentException
     */

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUsernameTweet() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.tweet(null, "dfadf");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyTweet() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.tweet("sdfas", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTweetLength() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.tweet("sdfas",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadassddddddddd");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyfollowUsername() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.follow("follower", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyfollowerUsername() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.follow("", "fadsfadsfads");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFollowSelf() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.follow("alice", "alice");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyBlockUsername() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.block("follower", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyBlockerUsername() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.block("", "fadsfadsfads");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlockSelf() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        tweeter.block("alice", "alice");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLikeEmptyUsername() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");
        long msg = tweeter.tweet("alice", "alice");
        tweeter.like("", msg);
    }

    @Test
    public void mostPopularUser() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");

        tweeter.follow("alice", "bob");
        tweeter.follow("kate", "bob");
        tweeter.follow("kate", "alice");
        tweeter.follow("bob", "alice");
        tweeter.follow("nate", "bob");

        assertEquals(stats.getMostFollowedUser(), "bob");
    }

    @Test
    public void mostPopularMessage() throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStatsService stats = (TweetStatsService) ctx.getBean("tweetStatsService");

        tweeter.tweet("alice", "this message");
        assertEquals(stats.getMostPopularMessage(), null);

        tweeter.follow("bob", "alice");
        tweeter.tweet("bob", "this message");
        assertEquals(stats.getMostPopularMessage(), "this message");
    }

}