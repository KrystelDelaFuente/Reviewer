package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.DifficultyLevel
import com.example.data.model.FlipbookContent
import com.example.data.model.QuizCategory
import com.example.data.repository.QuizQuestionBank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context matches app name`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("DAR Reviewer", appName)
    }

    @Test
    fun `verify questions exist across all difficulty levels`() {
        val beginnerQuestions = QuizQuestionBank.getQuestionsByDifficulty(DifficultyLevel.BEGINNER)
        val intermediateQuestions = QuizQuestionBank.getQuestionsByDifficulty(DifficultyLevel.INTERMEDIATE)
        val advancedQuestions = QuizQuestionBank.getQuestionsByDifficulty(DifficultyLevel.ADVANCED)

        assertTrue("Should have beginner questions", beginnerQuestions.isNotEmpty())
        assertTrue("Should have intermediate questions", intermediateQuestions.isNotEmpty())
        assertTrue("Should have advanced questions", advancedQuestions.isNotEmpty())

        assertEquals(20, beginnerQuestions.size)
        assertEquals(20, intermediateQuestions.size)
        assertEquals(20, advancedQuestions.size)
        assertEquals(60, QuizQuestionBank.allQuestions.size)
    }

    @Test
    fun `verify each question has 4 options and valid correct index`() {
        for (q in QuizQuestionBank.allQuestions) {
            assertEquals("Question ${q.id} must have 4 options", 4, q.options.size)
            assertTrue("Question ${q.id} correctIndex must be within 0..3", q.correctIndex in 0..3)
            assertTrue("Question ${q.id} must have legal basis", q.legalBasis.isNotBlank())
            assertTrue("Question ${q.id} must have explanation", q.explanation.isNotBlank())
            assertTrue("Question ${q.id} must have page reference", q.pageReference > 0)
        }
    }

    @Test
    fun `verify random sampling returns requested count`() {
        val sample10 = QuizQuestionBank.getRandomSample(count = 10)
        assertEquals(10, sample10.size)

        val sampleBeginner = QuizQuestionBank.getRandomSample(count = 5, difficulty = DifficultyLevel.BEGINNER)
        assertEquals(5, sampleBeginner.size)
        assertTrue(sampleBeginner.all { it.difficulty == DifficultyLevel.BEGINNER })
    }

    @Test
    fun `verify flipbook self test contains all 26 questions`() {
        val selfTestDeck = FlipbookContent.selfTestList
        assertEquals(26, selfTestDeck.size)
        for (item in selfTestDeck) {
            assertTrue(item.prompt.isNotBlank())
            assertTrue(item.answer.isNotBlank())
            assertTrue(item.reference.isNotBlank())
        }
    }
}
