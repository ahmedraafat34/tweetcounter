package com.ahmedraafat.tweetcounter.ui

object TweetCounter {

    const val MAX_TWITTER_CHAR_LIMIT = 280

    fun countTwitterCharacters(text: String): Int {
        //URLs are counted as 23 characters
        val urlPattern = Regex("(https?://\\S+)")
        val urlCount = urlPattern.findAll(text).count()
        val urlAdjustedLength = text.replace(urlPattern, "").length + (urlCount * 23)

        return urlAdjustedLength.coerceAtMost(MAX_TWITTER_CHAR_LIMIT)
    }

    fun getRemainingCharacters(currentCount: Int): Int {
        return (MAX_TWITTER_CHAR_LIMIT - currentCount).coerceAtLeast(0)
    }
}
