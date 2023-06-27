package com.example.testcomponenttree

import androidx.compose.ui.test.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.* // ktlint-disable no-wildcard-imports
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun myTest() {
        val listTag = "attachmentList"
        val removeButton = "removeAttachmentButton"

        composeTestRule.onRoot().printToLog("COMPONENT TREE")

        composeTestRule.onNodeWithTag(listTag).onChildren().assertCountEquals(4)

        Thread.sleep(3000)

        composeTestRule.onNodeWithTag(listTag).onChildren()[0].onChildren().filter(
            hasTestTag(removeButton),
        ).onFirst().performClick()

        composeTestRule.onRoot().printToLog("COMPONENT TREE 2")

        Thread.sleep(3000)

        composeTestRule.onNodeWithTag(listTag).onChildren().assertCountEquals(3)
    }
}
