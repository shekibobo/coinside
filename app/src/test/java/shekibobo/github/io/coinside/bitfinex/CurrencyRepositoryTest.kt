package shekibobo.github.io.coinside.bitfinex

import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import me.jorgecastillo.hiroaki.Method
import me.jorgecastillo.hiroaki.internal.MockServerSuite
import me.jorgecastillo.hiroaki.models.inlineBody
import me.jorgecastillo.hiroaki.models.success
import me.jorgecastillo.hiroaki.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import shekibobo.github.io.coinside.bitfinex.models.TickerItem

class CurrencyRepositoryTest : MockServerSuite() {
  lateinit var api: BitfinexApi
  lateinit var repo: CurrencyRepository

  private val symbolsJson = """
      [
        "btcusd",
        "ltcusd"
      ]
    """

  private val btcJson = """
    {
      "mid": "6549.25",
      "bid": "6549.2",
      "ask": "6549.3",
      "last_price": "6549.2",
      "low": "6381.9",
      "high": "6580.0",
      "volume": "17818.286315069996",
      "timestamp": "1536906956.1089513"
    }
  """
  private val btcTickerItem = TickerItem(
    symbol = "btcusd",
    mid = "6549.25",
    bid = "6549.2",
    ask = "6549.3",
    lastPrice = "6549.2",
    low = "6381.9",
    high = "6580.0",
    volume = "17818.286315069996",
    timestamp = "1536906956.1089513"
  )

  private val ltcJson = """
    {
      "mid": "54.94",
      "bid": "54.933",
      "ask": "54.947",
      "last_price": "54.949",
      "low": "52.118",
      "high": "55.819",
      "volume": "150251.69407342997",
      "timestamp": "1536907240.3705375"
    }
  """
  private val ltcTickerItem = TickerItem(
    symbol = "ltcusd",
    mid = "54.94",
    bid = "54.933",
    ask = "54.947",
    lastPrice = "54.949",
    low = "52.118",
    high = "55.819",
    volume = "150251.69407342997",
    timestamp = "1536907240.3705375"
  )

  @Before override fun setup() {
    super.setup()
    api = Retrofit.Builder()
      .baseUrl(server.url("/"))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.trampoline()))
      .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().forBitfinex()))
      .build()
      .create(BitfinexApi::class.java)
    repo = CurrencyRepository(api)
  }

  @Test fun `allCurrencies() fetches all available ticker symbols and gets their prices`() {
    server.whenever(Method.GET, "v1/symbols")
      .thenRespond(success(jsonBody = inlineBody(symbolsJson)))

    server.whenever(Method.GET, "v1/pubticker/ltcusd")
      .thenRespond(success(jsonBody = inlineBody(ltcJson)))

    server.whenever(Method.GET, "v1/pubticker/btcusd")
      .thenRespond(success(jsonBody = inlineBody(btcJson)))

    repo.allTickers().test().apply {
      assertNoErrors()
      assertValue(listOf(btcTickerItem, ltcTickerItem))
    }
  }
}
