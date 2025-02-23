package com.ahmedraafat.tweetcounter.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import com.ahmedraafat.tweetcounter.R
import com.ahmedraafat.tweetcounter.databinding.TweetCounterViewBinding

class TweetCounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = TweetCounterViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var onTextChangedListener: ((String, Int) -> Unit)? = null

    init {
        setupTextWatcher()
        updateCharacterCount(0)
    }

    private fun setupTextWatcher() {
        binding.tweetInput.doOnTextChanged { text, _, _, _ ->
            val tweetText = text.toString()
            val charCount = TweetCounter.countTwitterCharacters(tweetText)
            updateCharacterCount(charCount)
            onTextChangedListener?.invoke(tweetText, TweetCounter.getRemainingCharacters(charCount))
        }
    }

    private fun updateCharacterCount(charCount: Int) {
        val remainingChars = TweetCounter.getRemainingCharacters(charCount)
        binding.charCount.title.text = context.getString(R.string.characters_typed)
        binding.charCount.count.text = "$charCount/280"

        binding.remainingCount.title.text = context.getString(R.string.characters_remaining)
        binding.remainingCount.count.text = "$remainingChars"

    }

    fun setOnTextChangedListener(listener: (String, Int) -> Unit) {
        onTextChangedListener = listener
    }

    fun getText(): String = binding.tweetInput.text.toString()

    fun clearInput() {
        binding.tweetInput.text?.clear()
    }

}
