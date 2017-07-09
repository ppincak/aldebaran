package com.aldebaran.integration.test

import com.aldebaran.integration.ErrorResponse
import org.testng.Assert


fun assertErrorResponse(errorResponse: ErrorResponse, code: Int, subCode: Int, key: String) : Unit {
    Assert.assertNotNull(errorResponse)
    Assert.assertEquals(errorResponse.code, code)
    Assert.assertEquals(errorResponse.subCode, subCode)
    Assert.assertEquals(errorResponse.key, key)
}