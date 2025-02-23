package com.ahmedraafat.halanassignment.data

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class OAuthInterceptor(
    private val apiKey: String,
    private val apiSecret: String,
    private val accessToken: String,
    private val accessSecret: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val nonce = UUID.randomUUID().toString().replace("-", "")
        val timestamp = (System.currentTimeMillis() / 1000).toString()

        // Collect OAuth parameters
        val oauthParams = mutableMapOf(
            "oauth_consumer_key" to apiKey,
            "oauth_nonce" to nonce,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to timestamp,
            "oauth_token" to accessToken,
            "oauth_version" to "1.0"
        )

        // Collect query parameters (if any)
        val queryParams = request.url.queryParameterNames.associateWith { key ->
            request.url.queryParameterValues(key).joinToString(",")
        }

        // Combine OAuth and query parameters
        val allParams = (oauthParams + queryParams).toSortedMap()

        // Build the parameter string
        val baseParams = allParams.map { "${it.key.encode()}=${it.value.encode()}" }
            .joinToString("&")

        // Build the base string
        val baseUrl = request.url.newBuilder().query(null).build().toString()
        val baseString = "${request.method}&${baseUrl.encode()}&${baseParams.encode()}"

        // Generate the signing key
        val signingKey = "${apiSecret.encode()}&${accessSecret.encode()}"

        // Generate the OAuth signature
        val signature = baseString.hmacSha1(signingKey)

        // Build the Authorization header
        val authHeader = "OAuth " + oauthParams.map { (k, v) -> "${k.encode()}=\"${v.encode()}\"" }
            .plus("oauth_signature=\"${signature.encode()}\"")
            .joinToString(", ")

        // Create the new request
        val newRequest = request.newBuilder()
            .addHeader("Authorization", authHeader)
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(newRequest)
    }

    private fun String.encode(): String =
        URLEncoder.encode(this, "UTF-8").replace("+", "%20")

    private fun String.hmacSha1(key: String): String {
        val mac = Mac.getInstance("HmacSHA1")
        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA1")
        mac.init(secretKey)
        return Base64.encodeToString(mac.doFinal(this.toByteArray()), Base64.NO_WRAP)
    }
}