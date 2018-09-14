package shekibobo.github.io.coinside.bitfinex.models

import com.squareup.moshi.Json

data class TickerItem(
  val symbol: CryptoSymbol? = null,
  val mid: Price = DEFAULT_PRICE,
  val bid: Price = DEFAULT_PRICE,
  val ask: Price = DEFAULT_PRICE,
  @Json(name = "last_price") val lastPrice: Price = DEFAULT_PRICE,
  val low: Price = DEFAULT_PRICE,
  val high: Price = DEFAULT_PRICE,
  val volume: Price = DEFAULT_PRICE,
  val timestamp: TimeStamp = "0.0"
)
