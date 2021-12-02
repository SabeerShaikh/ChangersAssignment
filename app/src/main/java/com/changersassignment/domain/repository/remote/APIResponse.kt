package com.enfecassignment.domain.repository.remote

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


open class APIResponse<T> {
    @SerializedName("code")
    val code = 0

    @SerializedName("status")
    private val status = false

    @SerializedName("errMessage")
    val msg: String? = null

    @SerializedName("data")
    val data: T? = null

    fun status(): Boolean {
        return status
    }

    override fun toString(): String {
        return "APIResponse{" +
                "code=" + code +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}'
    }

    companion object {
        fun map(json: String?): APIResponse<*> {
            val gson = Gson()
            return gson.fromJson(json, APIResponse::class.java)
        }
    }
}