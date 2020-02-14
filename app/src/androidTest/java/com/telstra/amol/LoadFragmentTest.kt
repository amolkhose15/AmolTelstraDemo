package com.telstra.amol

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.view.MainActivity
import com.telstra.amolassignmenttestra.view.MainFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoadFragmentTest {

    private val TAG: String = LoadFragmentTest::class.java.name

    private var activty: MainActivity? = null

    @get:Rule
    var testruls: ActivityTestRule<MainActivity>? =
        ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {
        testruls?.let { activty = it.activity }
    }

    @Test
    fun fra() {
        activty?.run {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frgment, fragment)
                .commit()
            InstrumentationRegistry.getInstrumentation().waitForIdleSync()
            val swaprefrsh: SwipeRefreshLayout? =
                fragment.view?.findViewById(R.id.refreshmenu)
            if (swaprefrsh != null) {
                print("TAG ----- $TAG ---> Test Case Done...")
            } else {
                print("TAG ----- $TAG ---> Test Case Getting Error...")
            }

        }
    }

    @After
    fun testDraw() {
        activty = null
        testruls = null
    }
}