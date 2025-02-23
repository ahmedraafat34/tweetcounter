package com.ahmedraafat.tweetcounter

import com.ahmedraafat.tweetcounter.ui.TweetCounter
import org.junit.Assert.assertEquals
import org.junit.Test

class TweetCounterTest {

    @Test
    fun `countTwitterCharacters should return correct count for text without URLs`() {
        val text = "Hello, this is a test tweet."
        val expectedCount = text.length
        val actualCount = TweetCounter.countTwitterCharacters(text)
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun `countTwitterCharacters should return correct count for text with URLs`() {
        val text = "Check out this link: https://example.com"
        val expectedCount = "Check out this link: ".length + 23
        val actualCount = TweetCounter.countTwitterCharacters(text)
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun `countTwitterCharacters should return correct count for text with multiple URLs`() {
        val text = "Check out these links: https://example.com and https://another.com"
        val expectedCount = "Check out these links:  and ".length + (23 * 2)
        val actualCount = TweetCounter.countTwitterCharacters(text)
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun `countTwitterCharacters should not exceed MAX_TWITTER_CHAR_LIMIT`() {
        val longText = "a".repeat(300) // 300 characters
        val expectedCount = TweetCounter.MAX_TWITTER_CHAR_LIMIT
        val actualCount = TweetCounter.countTwitterCharacters(longText)
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun `getRemainingCharacters should return correct remaining characters`() {
        val currentCount = 100
        val expectedRemaining = TweetCounter.MAX_TWITTER_CHAR_LIMIT - currentCount
        val actualRemaining = TweetCounter.getRemainingCharacters(currentCount)
        assertEquals(expectedRemaining, actualRemaining)
    }

    @Test
    fun `getRemainingCharacters should return 0 if currentCount exceeds MAX_TWITTER_CHAR_LIMIT`() {
        val currentCount = 300
        val expectedRemaining = 0
        val actualRemaining = TweetCounter.getRemainingCharacters(currentCount)
        assertEquals(expectedRemaining, actualRemaining)
    }

    @Test
    fun `getRemainingCharacters should return MAX_TWITTER_CHAR_LIMIT if currentCount is 0`() {
        val currentCount = 0
        val expectedRemaining = TweetCounter.MAX_TWITTER_CHAR_LIMIT
        val actualRemaining = TweetCounter.getRemainingCharacters(currentCount)
        assertEquals(expectedRemaining, actualRemaining)
    }
}