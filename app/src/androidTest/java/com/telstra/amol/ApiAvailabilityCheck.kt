package com.telstra.amol

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.Charset


@RunWith(AndroidJUnit4ClassRunner::class)
class ApiAvailabilityCheck {

    @Test
    public fun checkApiAvailable() {
        var urlConnection: URLConnection =
            URL("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json").openConnection()
        var inputSteam: InputStream = urlConnection.getInputStream()
        var bufferd: StringBuffer = StringBuffer();

        try {
            BufferedReader(
                InputStreamReader(
                    inputSteam,
                    Charset.defaultCharset()
                )
            ).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    bufferd.append(line)
                }
            }
        } catch (exception: Exception) {
            exception.message
        }

        assert(bufferd.length > 0)


    }


}