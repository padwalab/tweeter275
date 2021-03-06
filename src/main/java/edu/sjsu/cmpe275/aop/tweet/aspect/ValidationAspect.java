package edu.sjsu.cmpe275.aop.tweet.aspect;

import java.security.AccessControlException;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.tweet.TweetStatsServiceImpl;

@Aspect
@Order(2)
public class ValidationAspect {
    /***
     * Following is a dummy implementation of this aspect. You are expected to
     * provide an actual implementation based on the requirements, including
     * adding/removing advices as needed.
     */

    @Before("execution(Long edu.sjsu.cmpe275.aop.tweet.TweetService.tweet(..))")
    public void beforeTweetAdvice(JoinPoint joinPoint) throws Throwable {
        System.out.println("Before validation: validating tweet attrs...");
        if (joinPoint.getArgs()[0] == null || joinPoint.getArgs()[1] == null) {
            throw new IllegalArgumentException();
        }
        Object[] args = joinPoint.getArgs();
        if (args[0].toString().isEmpty() || args[1].toString().isEmpty() || args[1].toString().length() > 140) {
            throw new IllegalArgumentException();
        }

    }

    @Before("execution(void edu.sjsu.cmpe275.aop.tweet.TweetService.follow(..))")
    public void FollowAdvice(JoinPoint joinPoint) throws Throwable {
        System.out.println("follow befoer...");
        Object[] args = joinPoint.getArgs();
        if (args[0] == null || args[1] == null) {
            throw new IllegalArgumentException();
        }
        if (args[0].toString().isEmpty() || args[1].toString().isEmpty() || args[0].toString() == args[1].toString()) {
            throw new IllegalArgumentException();
        }
    }

    @Before("execution(void edu.sjsu.cmpe275.aop.tweet.TweetService.block(..))")
    public void BlockAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args[0] == null || args[1] == null) {
            throw new IllegalArgumentException();
        }
        if (args[0].toString().isEmpty() || args[1].toString().isEmpty() || args[0].toString() == args[1].toString()) {
            throw new IllegalArgumentException();
        }
    }

    @Before("execution(void edu.sjsu.cmpe275.aop.tweet.TweetService.like(..))")
    public void LikeAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args[0] == null || args[1] == null) {
            throw new IllegalArgumentException();
        }
        if (args[0].toString().isEmpty()) {
            throw new IllegalArgumentException();
        }
        String tweetLiker = args[0].toString();
        String tweetOwner = TweetStatsServiceImpl.tweetIdOwner.get((Long) args[1]);
        Set<String> tweetOwnerFollowers = null;
        Set<String> tweetOwnerBlocked = null;
        if (TweetStatsServiceImpl.followCountMap.containsKey(tweetOwner)) {
            tweetOwnerFollowers = TweetStatsServiceImpl.followCountMap.get(tweetOwner);
            if (TweetStatsServiceImpl.blockCountMap.containsKey(tweetOwner)) {
                tweetOwnerFollowers.removeAll(TweetStatsServiceImpl.blockCountMap.get(tweetOwner));
            }
        }
        if (tweetOwnerFollowers != null) {
            if (!tweetOwnerFollowers.contains(tweetLiker)) {
                throw new AccessControlException("can't like the tweet");
            }
        }
    }
}
