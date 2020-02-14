package com.telstra.amol

import android.view.View
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.telstra.amolassignmenttestra.R
import com.telstra.amolassignmenttestra.view.MainActivity
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActvityTest {

    @get:Rule
    var actvityTest: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    var mActivity: MainActivity? = null

    @Before
    fun setup() {
        mActivity = actvityTest.activity
    }

    @Test
    fun testlaunch() {
        var view: View = mActivity!!.findViewById(R.id.frgment)
        Assert.assertNotNull(view)
    }

    @After
    fun testDone() {
        mActivity = null
    }

}