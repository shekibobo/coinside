package shekibobo.github.io.coinside.bitfinex.models

import com.squareup.moshi.Moshi
import org.junit.Test
import shekibobo.github.io.coinside.bitfinex.forBitfinex
import shekibobo.github.io.coinside.extensions.fromJson
import kotlin.test.expect

class TickerItemTest {
  private val tickerItemJson = """
    {
      "mid":"244.755",
      "bid":"244.75",
      "ask":"244.76",
      "last_price":"244.82",
      "low":"244.2",
      "high":"248.19",
      "volume":"7842.11542563",
      "timestamp":"1444253422.348340958"
    }
  """
  private val moshi = Moshi.Builder().forBitfinex()
  private val tickerItem: TickerItem = moshi.fromJson<TickerItem>(tickerItemJson)!!

  @Test fun `correctly excludes symbol`() = expect(null) { tickerItem.symbol }
  @Test fun `correctly parses mid`() = expect("244.755") { tickerItem.mid }
  @Test fun `correctly parses bid`() = expect("244.75") { tickerItem.bid }
  @Test fun `correctly parses ask`() = expect("244.76") { tickerItem.ask }
  @Test fun `correctly parses lastPrice`() = expect("244.82") { tickerItem.lastPrice }
  @Test fun `correctly parses low`() = expect("244.2") { tickerItem.low }
  @Test fun `correctly parses high`() = expect("248.19") { tickerItem.high }
  @Test fun `correctly parses volume`() = expect("7842.11542563") { tickerItem.volume }
  @Test fun `correctly parses timestamp`() = expect("1444253422.348340958") { tickerItem.timestamp }
}
