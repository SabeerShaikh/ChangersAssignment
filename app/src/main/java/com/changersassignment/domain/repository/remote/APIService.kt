package com.enfecassignment.domain.repository.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import java.io.IOException

open interface APIService {
    fun errorResponseHandler(responseBody: ResponseBody): APIErrorResponse? {
        var apiErrorResponse: APIErrorResponse? = APIErrorResponse.defaultAPIErrorResponse
        try {
            apiErrorResponse = Gson().fromJson(responseBody.string(), APIErrorResponse::class.java)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return apiErrorResponse
    }
}