package me.tergel.toolbox

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test_result() {
        val expectedVal = "123"
        val expectedError = RuntimeError(-1, "illegal args")
        var result: Result<String> = Result.Success("abc")

        result = result.onSuccess {
            expectedVal
        }

        assertTrue(result.isSucceed())
        assertEquals(result.unwrap(), expectedVal)

        result = result.onSuccessResult {
            Result.Failure(expectedError)
        }

        assertFalse(result.isSucceed())
        assert(result.error() == expectedError)

        result = result.onSuccessResult {
            Result.Success(expectedVal)
        }

        assertTrue(result.isFailed())
    }
}