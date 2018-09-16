package shekibobo.github.io.coinside.bitfinex

import io.reactivex.Single
import shekibobo.github.io.coinside.bitfinex.models.CryptoSymbol
import shekibobo.github.io.coinside.bitfinex.models.TickerItem
import shekibobo.github.io.coinside.bitfinex.models.filteredSymbols
import shekibobo.github.io.coinside.extensions.bodyOrError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(private val api: BitfinexApi) {
  fun allTickers(symbols: List<CryptoSymbol>? = null): Single<List<TickerItem>> {
    return api.symbols()
      .toObservable()
      .flatMapIterable { it.body() }
      .filter { symbols?.contains(it) ?: true }
      .take(10)
      .flatMapSingle { symbol ->
        api.ticker(symbol).map { it.bodyOrError().copy(symbol = symbol) }
      }
      .toList()
  }
}
