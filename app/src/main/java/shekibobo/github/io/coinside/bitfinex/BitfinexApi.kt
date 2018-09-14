package shekibobo.github.io.coinside.bitfinex

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import shekibobo.github.io.coinside.bitfinex.models.CryptoSymbol
import shekibobo.github.io.coinside.bitfinex.models.TickerItem

interface BitfinexApi {
  @GET("v1/pubticker/{symbol}")
  fun ticker(@Path("symbol") symbol: CryptoSymbol): Single<Response<TickerItem>>

  @GET("v1/symbols")
  fun symbols(): Single<Response<List<CryptoSymbol>>>
}
