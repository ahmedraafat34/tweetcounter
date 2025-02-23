package com.ahmedraafat.halanassignment.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ahmedraafat.halanassignment.data.ApiResponse
import com.ahmedraafat.halanassignment.databinding.ActivityTweetCounterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TweetActivity : AppCompatActivity() {

    private val tweetViewModel: TweetViewModel by viewModels()

    private lateinit var binding: ActivityTweetCounterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        setupTweetCounterView()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupTweetCounterView() {
        binding.tweetCounterView.setOnTextChangedListener { text, _ ->
            tweetViewModel.updateRemainingCharacters(text)
        }
    }

    private fun setupClickListeners() {

        binding.postButton.setOnClickListener {
            val tweetText = binding.tweetCounterView.getText()
            tweetViewModel.postTweet(tweetText)
        }
        binding.clear.setOnClickListener {
            binding.tweetCounterView.clearInput()
        }

        binding.copy.setOnClickListener {
            val textToCopy = binding.tweetCounterView.getText()
            val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", textToCopy)
            clipboard.setPrimaryClip(clip)
        }

    }

    private fun observeViewModel() {
        tweetViewModel.tweetResponse.observe(this) { response ->
            when (response.status) {
                ApiResponse.Status.LOADING -> {
                    binding.postButton.isEnabled = false
                }

                ApiResponse.Status.SUCCESS -> {
                    binding.postButton.isEnabled = true
                    Toast.makeText(
                        this,
                        "Tweet posted: ${response.data?.data?.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tweetCounterView.clearInput()
                    tweetViewModel.updateRemainingCharacters("")
                }
                ApiResponse.Status.ERROR -> {
                    binding.postButton.isEnabled = true
                    Toast.makeText(this, "Error: ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}
