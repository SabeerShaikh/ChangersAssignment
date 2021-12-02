package com.enfecassignment.domain.repository.remote

import com.google.gson.annotations.SerializedName


open class APIErrorResponse {
    companion object {
        var defaultAPIErrorResponse = APIErrorResponse()

        init {
            defaultAPIErrorResponse.error = "Unknown error"
            defaultAPIErrorResponse.message = "Internal application error"
            defaultAPIErrorResponse.statusCode = 7001
        }
    }

    @SerializedName("statusCode")
    var statusCode = 0
        private set

    @SerializedName("error")
    private var error: String? = null

    @SerializedName("message")
    var message: String? = null
        private set

    fun getError(): String {
        return if (error == null) (if (message != null) message!! else "Oops , seems like something went wrong") else error!!
    }

    override fun toString(): String {
        return "APIErrorResponse{" +
                "statusCode=" + statusCode +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}'
    }
}
