package shekibobo.github.io.coinside.bitfinex

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun Moshi.Builder.forBitfinex(): Moshi {
  return add(KotlinJsonAdapterFactory()).build()
}
