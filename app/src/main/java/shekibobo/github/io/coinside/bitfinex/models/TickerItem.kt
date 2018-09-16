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
) {
  val name: String?
    get() = when (symbol) {
      CRYPTO_SYMBOL_BITCOIN -> "Bitcoin"
      CRYPTO_SYMBOL_BITCOIN_CASH -> "Bitcoin Cash"
      CRYPTO_SYMBOL_ETHEREUM -> "Ethereum"
      CRYPTO_SYMBOL_LITECOIN -> "Litecoin"
      CRYPTO_SYMBOL_NEO -> "Neo"
      else -> symbol?.removeSuffix("usd")?.capitalize()

    }
}
