package shekibobo.github.io.coinside.bitfinex.models

typealias CryptoSymbol = String

const val CRYPTO_SYMBOL_BITCOIN = "btcusd"
const val CRYPTO_SYMBOL_BITCOIN_CASH = "bchusd"
const val CRYPTO_SYMBOL_ETHEREUM = "ethusd"
const val CRYPTO_SYMBOL_LITECOIN = "ltcusd"
const val CRYPTO_SYMBOL_NEO = "neousd"

val filteredSymbols: List<String> = listOf(
  CRYPTO_SYMBOL_BITCOIN,
  CRYPTO_SYMBOL_BITCOIN_CASH,
  CRYPTO_SYMBOL_ETHEREUM,
  CRYPTO_SYMBOL_LITECOIN,
  CRYPTO_SYMBOL_NEO
)
