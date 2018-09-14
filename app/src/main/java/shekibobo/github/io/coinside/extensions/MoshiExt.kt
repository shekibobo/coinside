package shekibobo.github.io.coinside.extensions

import com.squareup.moshi.Moshi

inline fun <reified T> Moshi.fromJson(json: String): T? = adapter(T::class.java).fromJson(json)
