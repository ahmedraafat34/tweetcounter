import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedraafat.halanassignment.data.repository.TweetRepository
import com.ahmedraafat.halanassignment.ui.TweetViewModel
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TweetViewModelTest {

    // Rule to make LiveData updates happen immediately
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test dispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    // Mock dependencies
    private val tweetRepository: TweetRepository = mockk()

    private lateinit var viewModel: TweetViewModel

    // LiveData Observers
    private lateinit var remainingCharsObserver: Observer<Int>

    @Before
    fun setUp() {
        // Set the main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Initialize the ViewModel with the repository
        viewModel = TweetViewModel(tweetRepository)

        // Initialize observers
        remainingCharsObserver = mockk(relaxed = true)

        // Observe LiveData
        viewModel.remainingChars.observeForever(remainingCharsObserver)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher
        Dispatchers.resetMain()

        // Remove observers
        viewModel.remainingChars.removeObserver(remainingCharsObserver)
    }


    @Test
    fun `updateRemainingCharacters should update remainingChars correctly`() {
        val input = "Hello, world!"

        viewModel.updateRemainingCharacters(input)

        val expectedRemainingChars = 280 - input.length
        verify { remainingCharsObserver.onChanged(expectedRemainingChars) }
    }
}