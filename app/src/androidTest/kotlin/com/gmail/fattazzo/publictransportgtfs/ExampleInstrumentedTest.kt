package com.gmail.fattazzo.publictransportgtfs

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.gmail.fattazzo.publictransportgtfs.transitfeed.Config
import com.gmail.fattazzo.publictransportgtfs.transitfeed.domain.LocationResponse
import com.gmail.fattazzo.publictransportgtfs.transitfeed.rest.ApiRestCLient

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.gmail.fattazzo.publictransportgtfs", appContext.packageName)
    }
}
