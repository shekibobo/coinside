package shekibobo.github.io.coinside.bitfinex

typealias CryptoSymbol = String

object CryptoSymbols {
  const val BITCOIN = "BTCUSD"
  const val BITCOIN_CLASSIC = "BCCUSD"
  const val ETHERIUM = "ETHUSD"
  const val LIGHTCOIN = "LTCUSD"
  const val NEO = "NEOUSD"
}

interface BitfinexApi {
  fun ticker(symbol: CryptoSymbol)
}
