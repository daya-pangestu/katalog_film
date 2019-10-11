package com.daya.moviekataloe

import org.junit.Assert.assertNotEquals
import org.junit.Test

class AppUtilsKtTest {

    @Test
    fun testDate() {
        assertNotEquals("2019-09-07", getCurrentDate())

    }
}