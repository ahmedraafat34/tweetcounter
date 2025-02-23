TweetCounter is a simple Android app that allows you to post tweets directly to your Twitter account. With a clean and user-friendly interface, you can easily compose and share your thoughts, ideas, or updates in just a few taps. Whether you're sharing a quick update or a longer message, TweetPoster ensures your tweets are posted seamlessly.

Features
Post Tweets: Compose and post tweets directly from the app.

Character Count: Track the number of characters to stay within Twitter's 280-character limit.

Simple UI: Clean and intuitive design for a hassle-free experience.

Secure Authentication: Safely authenticate with Twitter using OAuth.

Technologies Used
Kotlin: The primary programming language for Android development.

Twitter API: Used to interact with Twitter for posting tweets and authentication.

OAuth 1.0a/2.0: For secure user authentication and authorization.

Retrofit: A type-safe HTTP client for making API requests.

ViewModel & LiveData: For managing UI-related data in a lifecycle-aware manner.

Hilt: For dependency injection to simplify code and improve testability.

Gradle: For building and managing dependencies.


Setup Instructions
Clone the repository:

bash
Copy
git clone https://github.com/your-username/tweetposter.git
Open the project in Android Studio.

Add your Twitter API keys and secrets to gradle.properties or set them as environment variables:

TWITTER_API_KEY=your_api_key
TWITTER_API_SECRET=your_api_secret
ACCESS_TOKEN=your_access_token
ACCESS_SECRET=your_access_secret


Build and run the app on an emulator or physical device.

How to Use
Open the app and authenticate with your Twitter account.

Compose your tweet in the text box.

The character counter will show how many characters you have left.

Tap the "Post" button to share your tweet.
