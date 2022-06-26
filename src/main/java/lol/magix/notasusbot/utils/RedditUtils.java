package lol.magix.notasusbot.utils;

import lol.magix.notasusbot.Constants;
import lol.magix.notasusbot.NotASuspiciousBot;
import lol.magix.notasusbot.objects.PostData;

public final class RedditUtils {
    private RedditUtils() {
        // This class is not meant to be instantiated.
    }

    /**
     * Picks a random sub-reddit from the list of sub-reddits.
     * @return A sub-reddit.
     */
    public static String randomReddit() {
        var reddits = Constants.SUB_REDDITS;
        var random = (int) (Math.random() * reddits.length);
        return reddits[random];
    }

    /**
     * Picks a random post from a sub-reddit.
     * @param subReddit The sub-reddit to get a post from.
     * @return A post.
     */
    public static String toUri(String subReddit) {
        return "https://www.reddit.com/r/" + subReddit + "/random.json";
    }

    /**
     * Gets a random post from a sub-reddit.
     * @param subReddit The sub-reddit to get a post from.
     * @return A post.
     */
    public static PostData getRandomPost(String subReddit) {
        return NotASuspiciousBot.getGsonInstance()
                .fromJson(HttpUtils.get(RedditUtils.toUri(subReddit)), PostData[].class)[0];
    }
}