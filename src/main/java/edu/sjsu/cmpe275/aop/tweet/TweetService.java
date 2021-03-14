package edu.sjsu.cmpe275.aop.tweet;

import java.io.IOException;
import java.security.AccessControlException;

public interface TweetService {
	// Please do NOT change this file.

	/**
	 * @throws IllegalArgumentException if the message is more than 140 characters
	 *                                  as measured by string length, or any
	 *                                  parameter is null or empty.
	 * @throws IOException              if there is a network failure.
	 * @returns a unique message ID
	 */
	Long tweet(String user, String message) throws IllegalArgumentException, IOException;

	/**
	 * If Alice follows Bob, and Bob has not blocked Alice before, any future
	 * message that Bob tweets after this are shared with Alice. If at any point Bob
	 * blocks Alice, the sharing after blocking will be stopped.
	 * 
	 * @throws IllegalArgumentException if either parameter is null or empty, or
	 *                                  when a user attempts to follow himself.
	 * @throws IOException              if there is a network failure.
	 */
	void follow(String follower, String followee)
			throws IllegalArgumentException, UnsupportedOperationException, IOException;

	/**
	 * This method allows a user to block a follower or a potential follower so that
	 * subsequently tweets will not be shared with the latter. The same block
	 * operation can be repeated.
	 * 
	 * @throws IllegalArgumentException if the user or follower is null or empty, or
	 *                                  when a user attempts to block himself.
	 * 
	 * @throws IOException              if there is a network failure
	 */
	void block(String user, String followee) throws IOException, UnsupportedOperationException, IOException;

	/**
	 * Show fond of the message with the given message ID. The given user must be
	 * currently successfully following the current sender of the message in order
	 * to like it. One can only like a message with a given message ID once. As a
	 * special case, one is not allowed to like his own message.
	 * 
	 * 
	 * @throws IllegalArgumentException if any parameter is null or empty
	 * @throws IOException              if there is a network failure.
	 * @throws AccessControlException   if the given user is not following the
	 *                                  sender of the given message, or the sender
	 *                                  has blocked the given user, the given
	 *                                  message does not exist, someone tries to
	 *                                  like his own messages or when the message
	 *                                  with the given ID is already successfully
	 *                                  liked by the same user.
	 */
	void like(String user, Long messageId) throws AccessControlException, IllegalArgumentException, IOException;

}
